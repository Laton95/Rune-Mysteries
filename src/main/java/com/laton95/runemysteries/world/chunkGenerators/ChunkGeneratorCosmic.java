package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenCosmicTemple;

import net.minecraft.world.World;

public class ChunkGeneratorCosmic extends ChunkGeneratorVoidWorld {
	

	public ChunkGeneratorCosmic(World worldIn, long seed) {
		super(worldIn, seed);
		temple = new MapGenCosmicTemple(worldIn);
	}
}