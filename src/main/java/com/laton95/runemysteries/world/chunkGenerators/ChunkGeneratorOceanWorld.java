package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorOceanWorld implements IChunkGenerator
{

	private final IBlockState WORLDBLOCK;
	private final IBlockState SURFACEBLOCK;
	private final IBlockState OCEANBLOCK;
	private final Random rand;
	private final World world;
	private final int oceanSurface;
	private final int crustSurface;
	private final int worldSurface;
	private final ChunkGeneratorSettings settings;

	private MapGenRuneTemple temple;

	public ChunkGeneratorOceanWorld(World worldIn, long seed, IBlockState worldBlock, IBlockState surfaceBlock,
			IBlockState oceanBlock, int oceanSurface, int crustSurface, int worldSurface, String generatorSettings)
	{
		world = worldIn;
		rand = new Random(seed);
		temple = new MapGenRuneTemple(worldIn);
		WORLDBLOCK = worldBlock;
		SURFACEBLOCK = surfaceBlock;
		OCEANBLOCK = oceanBlock;
		this.oceanSurface = oceanSurface;
		this.crustSurface = crustSurface;
		this.worldSurface = worldSurface;

		WorldSettings settings = new WorldSettings(world.getWorldInfo());
		settings.setGeneratorOptions(generatorSettings);
		world.getWorldInfo().populateFromWorldSettings(settings);
		this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(generatorSettings).build();
		worldIn.setSeaLevel(this.settings.seaLevel);
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

			for (int y = 0; y < world.getActualHeight(); y++)
			{
				for (int xPos = 0; xPos < 16; xPos++)
				{
					for (int zPos = 0; zPos < 16; zPos++)
					{
						if (y == 0)
						{
							chunkprimer.setBlockState(xPos, y, zPos,
									Blocks.BEDROCK.getDefaultState());
						}
						else if (y > oceanSurface)
						{
							chunkprimer.setBlockState(xPos, y, zPos, Blocks.AIR.getDefaultState());
						}
						else if (y > crustSurface)
						{
							chunkprimer.setBlockState(xPos, y, zPos, OCEANBLOCK);
						}
						else if (y > worldSurface)
						{
							chunkprimer.setBlockState(xPos, y, zPos, SURFACEBLOCK);
						}
						else
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
		Biome biome = world.getBiome(chunkStart.add(16, 0, 16));
		rand.setSeed(world.getSeed());
		long rand1 = rand.nextLong() / 2L * 2L + 1L;
		long rand2 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(x * rand1 + z * rand2 ^ world.getSeed());

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, false);

		biome.decorator.clayPerChunk = 0;
		biome.decorator.generateFalls = false;
		biome.decorate(world, rand, new BlockPos(xPos, 0, zPos));

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
