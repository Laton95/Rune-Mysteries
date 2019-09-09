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
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class IslandRuinPieces {
	
	public static ResourceLocation STONE_CIRCLE = new ResourceLocation(RuneMysteries.MOD_ID, "stone_circle");
	
	public static ResourceLocation NETHER_ISLAND = new ResourceLocation(RuneMysteries.MOD_ID, "nether_island");
	
	public static ResourceLocation END_ISLAND = new ResourceLocation(RuneMysteries.MOD_ID, "end_island");
	
	public static void addPieces(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, ResourceLocation structure, ResourceLocation island, EnumRuneType rune) {
		components.add(new IslandRuinPieces.Piece(templateManager, pos, island, rune));
		components.add(new IslandRuinPieces.Piece(templateManager, pos.add(1, 5, 1), STONE_CIRCLE, rune));
		components.add(new IslandRuinPieces.Piece(templateManager, pos.add(1, 5, 1), structure, rune));
	}
	
	public static class Piece extends TemplateStructurePiece {
		
		private final EnumRuneType rune;
		
		private final ResourceLocation structure;
		
		public Piece(TemplateManager templateManager, BlockPos pos, ResourceLocation structure, EnumRuneType rune) {
			super(ModFeatures.ISLAND_RUIN, 0);
			this.rune = rune;
			this.structure = structure;
			this.templatePosition = pos;
			this.func_207614_a(templateManager);
		}
		
		public Piece(TemplateManager templateManager, CompoundNBT NBT) {
			super(ModFeatures.ISLAND_RUIN, NBT);
			structure = new ResourceLocation(NBT.getString("Template"));
			rune = EnumRuneType.valueOf(NBT.getString("Rune"));
			this.func_207614_a(templateManager);
		}
		
		@Override
		protected void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", structure.toString());
			tagCompound.putString("Rune", rune.name());
		}
		
		private void func_207614_a(TemplateManager templateManager) {
			Template template = templateManager.getTemplateDefaulted(structure);
			PlacementSettings settings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, settings);
		}
		
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}
		
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
			if(world.getDimension().getType() == DimensionType.OVERWORLD || world.getDimension().getType() == DimensionType.THE_NETHER || world.getDimension().getType() == DimensionType.THE_END) {
				
				if(structure.equals(STONE_CIRCLE)) {
					RuneMysteries.ruinManager.setRuinPosition(rune, templatePosition.add(4, 0, 4));
				}
				
				return super.addComponentParts(world, random, boundingBox, chunkPos);
			}
			return false;
		}
	}
}
