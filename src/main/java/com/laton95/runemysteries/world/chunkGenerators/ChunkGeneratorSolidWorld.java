package com.laton95.runemysteries.world.chunkGenerators;

import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorSolidWorld implements IChunkGenerator
{
	
	protected final IBlockState WORLDBLOCK;
	protected final Random rand;
	protected final World world;
	protected final int surfaceLevel;
	protected MapGenRuneTemple temple;
	
	public ChunkGeneratorSolidWorld(World worldIn, long seed, int surfaceLevel, IBlockState worldblock)
	{
		world = worldIn;
		rand = new Random(seed);
		temple = new MapGenRuneTemple(worldIn);
		this.surfaceLevel = surfaceLevel;
		WORLDBLOCK = worldblock;
	}
	
	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		if (Math.max(Math.abs(x), Math.abs(z)) < 16)
		{
			rand.setSeed(x * 341873128712L + z * 132897987541L);
			for (int y = 0; y < surfaceLevel; y++)
			{
				for (int xPos = 0; xPos < 16; xPos++)
				{
					for (int zPos = 0; zPos < 16; zPos++)
					{
						if (y == 0)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.BEDROCK.getDefaultState());
						} else
						{
							chunkprimer.setBlockState(xPos, y, zPos, WORLDBLOCK);
						}
					}
				}
			}
		}
		
		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		
		temple.generate(world, x, z, chunkprimer);
		
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
		
		temple.generateStructure(world, rand, chunkpos);
		
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, world, rand, x, z, false);
		
		BlockFalling.fallInstantly = false;
	}
	
	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return new LinkedList<>();
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return true;
	}
	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
	{
		return null;
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
	{
	
	}
	
	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
	{
		return false;
	}
}
