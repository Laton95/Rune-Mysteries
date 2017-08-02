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

public class ComponentAirTemple extends WorldHelper.ModFeature {

	public ComponentAirTemple() {
	}

	public ComponentAirTemple(Random rand, int x, int z) {
		super(rand, x, 62, z, 32, 27, 32);
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);

		Template temple = WorldHelper.getTemplate(worldIn, "air_temple");
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, temple, settings);
		worldIn.setBlockState(new BlockPos(-5, 87, -6), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		return true;
	}
}