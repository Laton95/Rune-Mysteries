package com.laton95.runemysteries.world.chunkGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorLaw extends ChunkGeneratorSolidWorld {

	public ChunkGeneratorLaw(World worldIn, long seed) {
		super(worldIn, seed, 256, Blocks.STONE.getDefaultState());
	}
	
}