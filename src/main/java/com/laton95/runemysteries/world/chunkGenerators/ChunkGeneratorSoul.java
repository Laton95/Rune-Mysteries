package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenSoulTemple;
import net.minecraft.world.World;

public class ChunkGeneratorSoul extends ChunkGeneratorVoidWorld
{
	
	public ChunkGeneratorSoul(World worldIn, long seed)
	{
		super(worldIn, seed);
		centerpiece = new MapGenSoulTemple();
	}
}
