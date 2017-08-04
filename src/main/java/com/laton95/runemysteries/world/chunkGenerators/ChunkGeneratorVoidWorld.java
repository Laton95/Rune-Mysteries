package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorVoidWorld implements IChunkGenerator {
	private final Random rand;
	private final World world;

	private MapGenRuneTemple temple;

	public ChunkGeneratorVoidWorld(World worldIn, long seed) {
		world = worldIn;
		rand = new Random(seed);
		temple = new MapGenRuneTemple(worldIn);
	}

	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z) {
		rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();

		temple.generate(world, x, z, chunkprimer);

		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
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
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return new LinkedList<>();
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return true;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}