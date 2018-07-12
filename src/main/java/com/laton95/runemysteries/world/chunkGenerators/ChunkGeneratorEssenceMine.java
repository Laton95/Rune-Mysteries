package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenEssenceMine;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorEssenceMine extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorEssenceMine(World worldIn, long seed)
	{
		super(worldIn, seed, 64, Blocks.STONE.getDefaultState());
		centerpiece = new MapGenEssenceMine();
	}
	
	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		if(Math.max(Math.abs(x), Math.abs(z)) < 16)
		{
			rand.setSeed(x * 341873128712L + z * 132897987541L);
			for(int y = 0; y < surfaceLevel + 9; y++)
			{
				for(int xPos = 0; xPos < 16; xPos++)
				{
					for(int zPos = 0; zPos < 16; zPos++)
					{
						if(y == 0)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.BEDROCK.getDefaultState());
						}
						else if(y == surfaceLevel + 8)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.SNOW_LAYER.getDefaultState());
						}
						else if(y < surfaceLevel)
						{
							chunkprimer.setBlockState(xPos, y, zPos, WORLDBLOCK);
						}
						else
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.SNOW.getDefaultState());
						}
					}
				}
			}
		}
		
		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		
		centerpiece.generate(world, x, z, chunkprimer);
		
		chunk.generateSkylightMap();
		return chunk;
	}
}
