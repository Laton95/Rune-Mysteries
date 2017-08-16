package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.util.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;

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
		BlockPos pos = new BlockPos(structureBoundingBoxIn.minX, structureBoundingBoxIn.minY, structureBoundingBoxIn.minZ);
		BlockPos pos2 = new BlockPos(pos.getX() + 3, pos.getY() + yOffset, pos.getZ() + 3);
		PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setReplacedBlock(Blocks.STRUCTURE_VOID).setChunk(new ChunkPos(pos));
		StructureHelper structureHelper = new StructureHelper(worldIn, room, pos);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, "stone_circle", pos2);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, name, pos2);
		structureHelper.generate();
		return true;
	}
}