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

public class ComponentSurfaceAltar extends WorldHelper.ModFeature {
	private String name;

	public ComponentSurfaceAltar() {
	}

	public ComponentSurfaceAltar(Random rand, int x, int z, String name) {
		super(rand, x, 64, z, 10, 4, 10);
		this.name = name;
	}

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		BlockPos pos = new BlockPos(structureBoundingBoxIn.minX, structureBoundingBoxIn.minY, structureBoundingBoxIn.minZ);
		PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setReplacedBlock(Blocks.STRUCTURE_VOID).setChunk(new ChunkPos(pos));
		StructureHelper structureHelper = new StructureHelper(worldIn, "stone_circle", pos);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, name, pos);
		structureHelper.generate();
		return true;
	}
}