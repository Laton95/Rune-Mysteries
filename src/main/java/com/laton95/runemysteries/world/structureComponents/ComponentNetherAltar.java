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
import net.minecraft.world.gen.structure.template.Template;

public class ComponentNetherAltar extends WorldHelper.ModFeature {
	private String name;

	public ComponentNetherAltar() {
	}

	public ComponentNetherAltar(Random rand, int x, int z, String name) {
		super(rand, x, 45, z, 10, 3, 10);
		this.name = name;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		BlockPos pos = new BlockPos(structureBoundingBoxIn.minX, structureBoundingBoxIn.minY, structureBoundingBoxIn.minZ);
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY() - 6, pos.getZ() - 1);
		PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setReplacedBlock(Blocks.STRUCTURE_VOID).setChunk(new ChunkPos(pos));
		StructureHelper structureHelper = new StructureHelper(worldIn, "nether_island", pos);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, "stone_circle", pos2);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, name, pos2);
		structureHelper.generate();
		return true;
	}
}