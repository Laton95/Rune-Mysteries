package com.laton95.runemysteries.world.gen.feature.structure.temple;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModFeatures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class RuneTemplePieces {
	
	public static void addPieces(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, EnumRuneType rune) {
		components.add(new Piece(templateManager, pos.add(0, 0, 0), new ResourceLocation(RuneMysteries.MOD_ID, String.format("temple/%1$s/%1$s_temple_se", rune.name().toLowerCase())), rune));
		components.add(new Piece(templateManager, pos.add(0, 0, -32), new ResourceLocation(RuneMysteries.MOD_ID, String.format("temple/%1$s/%1$s_temple_ne", rune.name().toLowerCase())), rune));
		components.add(new Piece(templateManager, pos.add(-32, 0, 0), new ResourceLocation(RuneMysteries.MOD_ID, String.format("temple/%1$s/%1$s_temple_sw", rune.name().toLowerCase())), rune));
		components.add(new Piece(templateManager, pos.add(-32, 0, -32), new ResourceLocation(RuneMysteries.MOD_ID, String.format("temple/%1$s/%1$s_temple_nw", rune.name().toLowerCase())), rune));
	}
	
	public static class Piece extends TemplateStructurePiece {
		
		private final ResourceLocation structure;

		private final EnumRuneType rune;
		
		public Piece(TemplateManager templateManager, BlockPos pos, ResourceLocation structure, EnumRuneType rune) {
			super(ModFeatures.SURFACE_RUIN, 0);
			this.structure = structure;
			this.rune = rune;
			this.templatePosition = pos;
			this.func_207614_a(templateManager);
		}
		
		public Piece(TemplateManager templateManager, CompoundNBT NBT) {
			super(ModFeatures.SURFACE_RUIN, NBT);
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
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}
		
		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
			if(world.getDimension().getType() == rune.getTempleDimension()) {
				return super.addComponentParts(world, random, boundingBox, chunkPos);
			}
			
			return false;
		}
	}
}
