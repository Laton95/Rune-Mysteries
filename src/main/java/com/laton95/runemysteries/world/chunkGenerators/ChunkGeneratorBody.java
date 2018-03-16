package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.init.BlockRegistry;
import net.minecraft.world.World;

public class ChunkGeneratorBody extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorBody(World worldIn, long seed)
	{
		super(worldIn, seed, 256, BlockRegistry.FLESH_BLOCK.getDefaultState());
	}
	
}
