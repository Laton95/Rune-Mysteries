package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentUndergroundAltar extends WorldHelper.ModFeature {
	private String name;
	private String room;
	private int yOffset;

	public ComponentUndergroundAltar() {
	}

	public ComponentUndergroundAltar(Random rand, int x, int z, String name, String room, int yOffset) {
		super(rand, x, 64, z, 16, 7, 16);
		this.name = name;
		this.room = room;
		this.yOffset = yOffset;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);
		BlockPos blockpos2 = new BlockPos(blockpos.getX() + 3, blockpos.getY() + yOffset, blockpos.getZ() + 3);

		Template structure = WorldHelper.getTemplate(worldIn, room);
		Template circle = WorldHelper.getTemplate(worldIn, "stone_circle");
		Template altar = WorldHelper.getTemplate(worldIn, name);
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, structure, settings);
		WorldHelper.loadStructure(blockpos2, worldIn, circle, settings);
		WorldHelper.loadStructure(blockpos2, worldIn, altar, settings);
		return true;
	}
}