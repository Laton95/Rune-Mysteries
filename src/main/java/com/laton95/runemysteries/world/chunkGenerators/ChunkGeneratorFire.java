package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorFire extends ChunkGeneratorOceanWorld {

	public ChunkGeneratorFire(World worldIn, long seed) {
		super(worldIn, seed, Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.LAVA.getDefaultState(), 85, 84, 83, NamesReference.worldGenStrings.WATER);
	}
}