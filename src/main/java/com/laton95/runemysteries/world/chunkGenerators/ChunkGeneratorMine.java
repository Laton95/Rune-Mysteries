package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenMine;
import net.minecraft.block.BlockFalling;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorMine extends ChunkGeneratorSolidWorld
{
	private MapGenMine mine;
	
	public ChunkGeneratorMine(World worldIn, long seed)
	{
		super(worldIn, seed, 64, Blocks.STONE.getDefaultState());
		mine = new MapGenMine(worldIn);
	}
	
	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		if(Math.max(Math.abs(x), Math.abs(z)) < 16)
		{
			rand.setSeed(x * 341873128712L + z * 132897987541L);
			for(int y = 0; y < surfaceLevel + 8; y++)
			{
				for(int xPos = 0; xPos < 16; xPos++)
				{
					for(int zPos = 0; zPos < 16; zPos++)
					{
						if(y == 0)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.BEDROCK.getDefaultState());
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
		
		mine.generate(world, x, z, chunkprimer);
		
		chunk.generateSkylightMap();
		return chunk;
	}
	
	@Override
	public void populate(int x, int z)
	{
		BlockFalling.fallInstantly = true;
		int xPos = x * 16;
		int zPos = z * 16;
		BlockPos chunkStart = new BlockPos(xPos, 0, zPos);
		ChunkPos chunkpos = new ChunkPos(x, z);
		world.getBiome(chunkStart);
		rand.setSeed(world.getSeed());
		long rand1 = rand.nextLong() / 2L * 2L + 1L;
		long rand2 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(x * rand1 + z * rand2 ^ world.getSeed());
		
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, false);
		
		mine.generateStructure(world, rand, chunkpos);
		
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, world, rand, x, z, false);
		
		BlockFalling.fallInstantly = false;
	}
}
