package com.laton95.runemysteries.world.chunkGenerators;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorMind extends ChunkGeneratorSolidWorld
{

	public ChunkGeneratorMind(World worldIn, long seed)
	{
		super(worldIn, seed, 97, Blocks.STONE.getDefaultState());
	}

}
