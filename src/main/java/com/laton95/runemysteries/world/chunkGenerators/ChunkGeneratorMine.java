package com.laton95.runemysteries.world.chunkGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorMine extends ChunkGeneratorSolidWorld {

	public ChunkGeneratorMine(World worldIn, long seed) {
		super(worldIn, seed, 64, Blocks.SNOW.getDefaultState());
	}
	
}