package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenTempleEarth;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorEarth extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorEarth(World worldIn, long seed)
	{
		super(worldIn, seed, 256, Blocks.DIRT.getDefaultState());
		centerpiece = new MapGenTempleEarth();
	}
	
}
