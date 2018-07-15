package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.world.mapGenerators.MapGenTempleChaos;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkGeneratorChaos extends ChunkGeneratorSolidWorld
{
	
	public ChunkGeneratorChaos(World worldIn, long seed)
	{
		super(worldIn, seed, 84, Blocks.STONE.getDefaultState());
		centerpiece = new MapGenTempleChaos();
	}
	
	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		if(Math.max(Math.abs(x), Math.abs(z)) < 16)
		{
			rand.setSeed(x * 341873128712L + z * 132897987541L);
			
			for(int y = 0; y < world.getActualHeight(); y++)
			{
				for(int xPos = 0; xPos < 16; xPos++)
				{
					for(int zPos = 0; zPos < 16; zPos++)
					{
						if(y == 0)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.BEDROCK.getDefaultState());
						}
						else if(y == 85)
						{
							int colour = rand.nextInt(EnumDyeColor.values().length);
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.CONCRETE.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.values()[colour]));
						}
						else if(y == 92)
						{
							int colour = rand.nextInt(EnumDyeColor.values().length);
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.CONCRETE.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.values()[colour]));
						}
						else if(y > 92)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.STONE.getDefaultState());
						}
						else if(y > surfaceLevel)
						{
							if(rand.nextFloat() <= 0.015)
							{
								int colour = rand.nextInt(EnumDyeColor.values().length);
								chunkprimer.setBlockState(xPos, y, zPos, Blocks.CONCRETE.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.values()[colour]));
							}
							else if(rand.nextFloat() <= 0.005)
							{
								chunkprimer.setBlockState(xPos, y, zPos, ModBlocks.ORANGE_LIGHT.getDefaultState());
							}
						}
						else
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.STONE.getDefaultState());
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
