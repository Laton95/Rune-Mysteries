package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentSurfaceAltar extends WorldHelper.ModFeature {
	private String name;

	public ComponentSurfaceAltar() {
	}

	public ComponentSurfaceAltar(Random rand, int x, int z, String name) {
		super(rand, x, 64, z, 10, 3, 10);
		this.name = name;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);

		Template circle = WorldHelper.getTemplate(worldIn, "stone_circle");
		Template altar = WorldHelper.getTemplate(worldIn, name);
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, circle, settings);
		WorldHelper.loadStructure(blockpos, worldIn, altar, settings);
		return true;
	}
}