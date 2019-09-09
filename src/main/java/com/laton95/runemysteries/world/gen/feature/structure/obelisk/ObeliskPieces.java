package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

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

public class ObeliskPieces {
	
	public static void addPieces(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components, ResourceLocation structure, int yLevel) {
		components.add(new ObeliskPieces.Piece(templateManager, pos, structure, yLevel));
	}
	
	public static class Piece extends TemplateStructurePiece {
		
		private final ResourceLocation obeliskStructure;
		
		private final int yLevel;
		
		public Piece(TemplateManager templateManager, BlockPos pos, ResourceLocation structure, int yLevel) {
			super(ModFeatures.OBELISK, 0);
			obeliskStructure = structure;
			this.templatePosition = pos;
			this.yLevel = yLevel;
			this.func_207614_a(templateManager);
		}
		
		public Piece(TemplateManager templateManager, CompoundNBT NBT) {
			super(ModFeatures.OBELISK, NBT);
			obeliskStructure = new ResourceLocation(NBT.getString("Template"));
			yLevel = NBT.getInt("yLevel");
			this.func_207614_a(templateManager);
		}
		
		@Override
		protected void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", obeliskStructure.toString());
			tagCompound.putInt("yLevel", yLevel);
		}
		
		private void func_207614_a(TemplateManager templateManager) {
			Template template = templateManager.getTemplateDefaulted(obeliskStructure);
			PlacementSettings settings = (new PlacementSettings()).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, settings);
		}
		
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}
		
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos) {
			BlockPos pos = this.templatePosition;
			int y = yLevel > 0 ? yLevel : world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ());
			this.templatePosition = this.templatePosition.add(0, y - 90, 0);
			return super.addComponentParts(world, random, boundingBox, chunkPos);
		}
	}
}
