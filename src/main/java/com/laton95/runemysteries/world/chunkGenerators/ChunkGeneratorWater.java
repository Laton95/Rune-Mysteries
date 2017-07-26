package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.world.MapGenRuneTemple;

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
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChunkGeneratorWater implements IChunkGenerator {
	protected static final IBlockState WORLDBLOCK = Blocks.STONE.getDefaultState();
	protected static final IBlockState SURFACEBLOCK = Blocks.DIRT.getDefaultState();
	protected static final IBlockState OCEANBLOCK = Blocks.WATER.getDefaultState();
	private final Random rand;
	private final World world;
	private WorldGenerator waterlilyGen = new WorldGenWaterlily();

	private MapGenRuneTemple temple;

	public ChunkGeneratorWater(World worldIn, long seed) {
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

		int oceanSurface = 85;
		int crustSurface = 82;
		int worldSurface = 80;
		
		for (int y = 0; y < world.getActualHeight(); y++) {
			for (int xPos = 0; xPos < 16; xPos++) {
				for (int zPos = 0; zPos < 16; zPos++) {
					if (y > oceanSurface) {
						chunkprimer.setBlockState(xPos, y, zPos, Blocks.AIR.getDefaultState());
					} else if (y > crustSurface) {
						chunkprimer.setBlockState(xPos, y, zPos, OCEANBLOCK);
					} else if (y > worldSurface) {
						chunkprimer.setBlockState(xPos, y, zPos, SURFACEBLOCK);
					} else {
						chunkprimer.setBlockState(xPos, y, zPos, WORLDBLOCK);
					}
				}
			}
		}

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
		Biome biome = world.getBiome(chunkStart);
		rand.setSeed(world.getSeed());
		long rand1 = rand.nextLong() / 2L * 2L + 1L;
		long rand2 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(x * rand1 + z * rand2 ^ world.getSeed());
		

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, false);

		temple.generateStructure(world, rand, chunkpos);
		
		int waterlilyPerChunk = 5;
		for (int k3 = 0; k3 < waterlilyPerChunk; ++k3)
        {
            int l7 = rand.nextInt(16) + 8;
            int k11 = rand.nextInt(16) + 8;
            BlockPos chunkBlockPos = new BlockPos(chunkpos.getXStart(), 0, chunkpos.getZStart());
            int i15 = world.getHeight(chunkBlockPos.add(l7, 0, k11)).getY() * 2;

            if (i15 > 0)
            {
                int j18 = rand.nextInt(i15);
                BlockPos blockpos4;
                BlockPos blockpos7;

                for (blockpos4 = chunkBlockPos.add(l7, j18, k11); blockpos4.getY() > 0; blockpos4 = blockpos7)
                {
                    blockpos7 = blockpos4.down();

                    if (!world.isAirBlock(blockpos7))
                    {
                        break;
                    }
                }

                this.waterlilyGen.generate(world, rand, blockpos4);
            }
        }

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