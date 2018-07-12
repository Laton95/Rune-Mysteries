package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenDeathTemple;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkGeneratorDeath extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorDeath(World worldIn, long seed)
	{
		super(worldIn, seed, 256, Blocks.STONE.getDefaultState());
		centerpiece = new MapGenDeathTemple();
	}
	
}
