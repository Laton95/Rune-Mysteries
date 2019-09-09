package com.laton95.runemysteries.world.gen.feature.structure.ruin;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModFeatures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class SurfaceRuinPieces {
	
	public static final ResourceLocation STONE_CIRCLE = new ResourceLocation(RuneMysteries.MOD_ID, "stone_circle");
	
	public static final ResourceLocation DIRT_ISLAND = new ResourceLocation(RuneMysteries.MOD_ID, "dirt_island");
	
	public static final ResourceLocation SAND_ISLAND = new ResourceLocation(RuneMysteries.MOD_ID, "sand_island");
	
	
	public static void addPieces(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, ResourceLocation structure, ResourceLocation base, EnumRuneType rune) {
		components.add(new SurfaceRuinPieces.Piece(templateManager, pos, structure, base, rune));
	}
	
	public static class Piece extends TemplateStructurePiece {
		
		private final EnumRuneType rune;
		
		private final ResourceLocation altarStructure;
		
		private final ResourceLocation baseStructure;
		
		private Template circleTemplate;
		
		private Template baseTemplate;
		
		public Piece(TemplateManager templateManager, BlockPos pos, ResourceLocation structure, ResourceLocation base, EnumRuneType rune) {
			super(ModFeatures.SURFACE_RUIN, 0);
			this.rune = rune;
			altarStructure = structure;
			baseStructure = base;
			this.templatePosition = pos;
			this.func_207614_a(templateManager, baseStructure);
		}
		
		public Piece(TemplateManager templateManager, CompoundNBT NBT) {
			super(ModFeatures.SURFACE_RUIN, NBT);
			altarStructure = new ResourceLocation(NBT.getString("Template"));
			baseStructure = new ResourceLocation(NBT.getString("BaseTemplate"));
			rune = EnumRuneType.valueOf(NBT.getString("Rune"));
			this.func_207614_a(templateManager, baseStructure);
		}
		
		@Override
		protected void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", altarStructure.toString());
			tagCompound.putString("BaseTemplate", baseStructure.toString());
			tagCompound.putString("Rune", rune.name());
		}
		
		private void func_207614_a(TemplateManager templateManager, ResourceLocation base) {
			Template template = templateManager.getTemplateDefaulted(altarStructure);
			circleTemplate = templateManager.getTemplateDefaulted(STONE_CIRCLE);
			baseTemplate = templateManager.getTemplateDefaulted(base);
			PlacementSettings settings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, settings);
		}
		
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}
		
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
			if(world.getDimension().getType() == DimensionType.OVERWORLD || world.getDimension().getType() == DimensionType.THE_NETHER || world.getDimension().getType() == DimensionType.THE_END) {
				float height = 0;
				BlockPos blockpos = this.templatePosition.add(this.template.getSize().getX() - 1, 0, this.template.getSize().getZ() - 1);
				
				for(BlockPos blockpos1 : BlockPos.getAllInBoxMutable(this.templatePosition, blockpos)) {
					int k = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
					height += k;
					//height = Math.max(height, k);
				}
				
				height = height / (this.template.getSize().getX() * this.template.getSize().getZ());
				
				this.templatePosition = new BlockPos(this.templatePosition.getX(), height, this.templatePosition.getZ());
				//this.templatePosition = new BlockPos(this.templatePosition.getX(), Math.round(height), this.templatePosition.getZ());
				
				RuneMysteries.ruinManager.setRuinPosition(rune, templatePosition.add(4, 0, 4));
				
				circleTemplate.addBlocksToWorld(world, this.templatePosition, this.placeSettings, 2);
				
				baseTemplate.addBlocksToWorld(world, this.templatePosition.add(-1, -5, -1), this.placeSettings, 2);
				
				return super.addComponentParts(world, random, boundingBox, chunkPos);
			}
			
			return false;
		}
	}
}
