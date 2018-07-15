package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenTempleMind;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorMind extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorMind(World worldIn, long seed)
	{
		super(worldIn, seed, 97, Blocks.STONE.getDefaultState());
		centerpiece = new MapGenTempleMind();
	}
	
}
