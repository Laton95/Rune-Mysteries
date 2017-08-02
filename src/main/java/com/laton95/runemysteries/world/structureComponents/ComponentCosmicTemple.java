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

public class ComponentCosmicTemple extends WorldHelper.ModFeature {

	public ComponentCosmicTemple() {
	}

	public ComponentCosmicTemple(Random rand) {
		super(rand, -26, 85, -26, 52, 4, 52);
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = getBoundingBox();
		BlockPos blockposNW = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);
		BlockPos blockposNE = new BlockPos(structureboundingbox.minX + 26, structureboundingbox.minY,
				structureboundingbox.minZ);
		BlockPos blockposSW = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ + 26);
		BlockPos blockposSE = new BlockPos(structureboundingbox.minX + 26, structureboundingbox.minY,
				structureboundingbox.minZ + 26);

		Template templeNW = WorldHelper.getTemplate(worldIn, "cosmic_temple_nw");
		Template templeNE = WorldHelper.getTemplate(worldIn, "cosmic_temple_ne");
		Template templeSW = WorldHelper.getTemplate(worldIn, "cosmic_temple_sw");
		Template templeSE = WorldHelper.getTemplate(worldIn, "cosmic_temple_se");
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockposNW, worldIn, templeNW, settings);
		WorldHelper.loadStructure(blockposNE, worldIn, templeNE, settings);
		WorldHelper.loadStructure(blockposSW, worldIn, templeSW, settings);
		WorldHelper.loadStructure(blockposSE, worldIn, templeSE, settings);
		worldIn.setBlockState(new BlockPos(-1, 87, 22), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		worldIn.setBlockState(new BlockPos(-23, 87, -1), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		worldIn.setBlockState(new BlockPos(0, 87, -23), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		worldIn.setBlockState(new BlockPos(22, 87, 0), BlockRegistry.ALTAR_PORTAL.getDefaultState());
		return true;
	}
}