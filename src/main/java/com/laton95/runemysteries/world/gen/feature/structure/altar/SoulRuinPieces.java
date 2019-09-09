package com.laton95.runemysteries.world.gen.feature.structure.altar;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModFeatures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class SoulRuinPieces {
	
	public static final ResourceLocation SOUL_WELL = new ResourceLocation(RuneMysteries.MOD_ID, "soul_well");
	
	public static final ResourceLocation SOUL_LADDER = new ResourceLocation(RuneMysteries.MOD_ID, "soul_ladder");
	
	public static final ResourceLocation SOUL_ROOM = new ResourceLocation(RuneMysteries.MOD_ID, "soul_room");
	
	public static final ResourceLocation SOUL_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/soul_ruin");
	
	public static final ResourceLocation CIRCLE_LITE = new ResourceLocation(RuneMysteries.MOD_ID, "circle_lite");
	
	public static void addPieces(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, Random rand) {
		int ladderCount = rand.nextInt(10) + 10;
		components.add(new Piece(templateManager, SOUL_ROOM, pos, new BlockPos(0, -8, -4), ladderCount));
		
		components.add(new Piece(templateManager, CIRCLE_LITE, pos, new BlockPos(5, -7, -2), ladderCount));
		
		components.add(new Piece(templateManager, SOUL_RUIN, pos, new BlockPos(5, -7, -2), ladderCount));
		
		components.add(new Piece(templateManager, UndergroundRuinPieces.LANTERNS, pos, new BlockPos(5, -7, -2), ladderCount));
		
		for(int j = 0; j < ladderCount - 1; ++j) {
			components.add(new Piece(templateManager, SOUL_LADDER, pos, new BlockPos(1, -1, 1), j));
		}
		
		components.add(new Piece(templateManager, SOUL_WELL, pos, new BlockPos(0, -1, 0), 0));
	}
	
	public static class Piece extends TemplateStructurePiece {
		
		private final ResourceLocation structure;
		
		private final BlockPos offset;
		
		public Piece(TemplateManager templateManager, ResourceLocation structure, BlockPos pos, BlockPos offset, int yOffset) {
			super(ModFeatures.WELL_RUIN, 0);
			this.structure = structure;
			this.offset = offset;
			this.templatePosition = pos.add(offset.getX(), offset.getY() - yOffset, offset.getZ());
			this.func_207614_a(templateManager);
		}
		
		public Piece(TemplateManager templateManager, CompoundNBT nbt) {
			super(ModFeatures.WELL_RUIN, nbt);
			this.structure = new ResourceLocation(nbt.getString("Template"));
			int[] coords = nbt.getIntArray("Offset");
			this.offset = new BlockPos(coords[0], coords[1], coords[2]);
			this.func_207614_a(templateManager);
		}
		
		private void func_207614_a(TemplateManager templateManager) {
			Template template = templateManager.getTemplateDefaulted(this.structure);
			PlacementSettings placementsettings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}
		
		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.structure.toString());
			tagCompound.putIntArray("Offset", new int[] {offset.getX(), offset.getY(), offset.getZ()});
		}
		
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}
		
		/**
		 * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
		 * the end, it adds Fences...
		 */
		public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_) {
			PlacementSettings placementsettings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			BlockPos pos = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - offset.getX(), 0, 0 - offset.getZ())));
			int height = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
			this.templatePosition = this.templatePosition.add(0, height - 90 - 1, 0);
			
			if(structure.equals(CIRCLE_LITE)) {
				RuneMysteries.ruinManager.setRuinPosition(EnumRuneType.SOUL, templatePosition.add(4, 0, 4));
			}
			return super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, p_74875_4_);
		}
	}
}
