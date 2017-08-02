package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.util.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentFireTemple extends WorldHelper.ModFeature {

	public ComponentFireTemple() {
	}

	public ComponentFireTemple(Random rand, int x, int z) {
		super(rand, x, 82, z, 32, 14, 32);
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);

		Template temple = WorldHelper.getTemplate(worldIn, "fire_temple");
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, temple, settings);
		worldIn.setBlockState(new BlockPos(0, 85, -9), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		return true;
	}
}