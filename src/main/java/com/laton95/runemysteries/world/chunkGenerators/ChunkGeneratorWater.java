package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorWater extends ChunkGeneratorOceanWorld
{

	public ChunkGeneratorWater(World worldIn, long seed)
	{
		super(worldIn, seed, Blocks.STONE.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.WATER.getDefaultState(), 85, 82, 80, NamesReference.worldGenStrings.WATER);
	}

}
