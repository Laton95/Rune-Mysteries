package com.laton95.runemysteries.world.chunkGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorBlood extends ChunkGeneratorSolidWorld {

	public ChunkGeneratorBlood(World worldIn, long seed) {
		super(worldIn, seed, 256, Blocks.STONE.getDefaultState());
	}
	
}