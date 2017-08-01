package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;

public class ChunkGeneratorNature implements IChunkGenerator {
	protected static final IBlockState STONE = Blocks.STONE.getDefaultState();
	private final Random rand;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	private final World world;
	private final WorldType terrainType;
	private final double[] heightMap;
	private final float[] biomeWeights;
	private IBlockState oceanBlock = Blocks.WATER.getDefaultState();
	private double[] depthBuffer = new double[256];
	private Biome[] biomesForGeneration;
	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;
	public WorldGenFlowers flowerGen = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);

	private MapGenRuneTemple temple;

	private int seaLevel = 63;
	private float mainNoiseScaleX = 80.0f;
	private float mainNoiseScaleY = 160.0f;
	private float mainNoiseScaleZ = 80.0f;
	private float depthNoiseScaleX = 200.0f;
	private float depthNoiseScaleZ = 200.0f;
	private float depthNoiseExponent = 0.5f;
	private float depthBaseSize = 11.0f;
	private float coordinateScale = 684.412f;
	private float heightScale = 1.0f;
	private float heightStretch = 50.0f;
	private float upperLimitScale = 512.0f;
	private float lowerLimitScale = 512.0f;
	private float biomeDepthWeight = 1.0f;
	private float biomeDepthOffset = 0.0f;
	private float biomeScaleWeight = 1.0f;
	private float biomeScaleOffset = 0.0f;

	public ChunkGeneratorNature(World worldIn, long seed) {
		world = worldIn;
		terrainType = worldIn.getWorldInfo().getTerrainType();
		rand = new Random(seed);
		minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
		mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
		surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
		scaleNoise = new NoiseGeneratorOctaves(rand, 10);
		depthNoise = new NoiseGeneratorOctaves(rand, 16);
		forestNoise = new NoiseGeneratorOctaves(rand, 8);
		heightMap = new double[825];
		biomeWeights = new float[25];

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt(i * i + j * j + 0.2F);
				biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}

		temple = new MapGenRuneTemple(world);

		net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx = new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(
				minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise,
				forestNoise);
		ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, rand, ctx);
		minLimitPerlinNoise = ctx.getLPerlin1();
		maxLimitPerlinNoise = ctx.getLPerlin2();
		mainPerlinNoise = ctx.getPerlin();
		surfaceNoise = ctx.getHeight();
		scaleNoise = ctx.getScale();
		depthNoise = ctx.getDepth();
		forestNoise = ctx.getForest();
	}

	/**
	 * Generates the chunk at the specified position, from scratch
	 */
	@Override
	public Chunk generateChunk(int x, int z) {
		rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		setBlocksInChunk(x, z, chunkprimer);
		biomesForGeneration = world.getBiomeProvider().getBiomes(biomesForGeneration, x * 16, z * 16, 16, 16);
		replaceBiomeBlocks(x, z, chunkprimer, biomesForGeneration);

		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(biomesForGeneration[i]);
		}

		temple.generate(world, x, z, chunkprimer);

		chunk.generateSkylightMap();
		return chunk;
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x * 4 - 2, z * 4 - 2,
				10, 10);
		generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d1 = heightMap[i1 + i2];
					double d2 = heightMap[j1 + i2];
					double d3 = heightMap[k1 + i2];
					double d4 = heightMap[l1 + i2];
					double d5 = (heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								if ((lvt_45_1_ += d16) > 0.0D) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
								} else if (i2 * 8 + j2 < seaLevel) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, oceanBlock);
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
		if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, world)) {
			return;
		}
		depthBuffer = surfaceNoise.getRegion(depthBuffer, x * 16, z * 16, 16, 16, 0.0625D, 0.0625D, 1.0D);

		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				Biome biome = biomesIn[j + i * 16];
				biome.genTerrainBlocks(world, rand, primer, x * 16 + i, z * 16 + j, depthBuffer[j + i * 16]);
			}
		}
	}

	private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_) {
		depthRegion = depthNoise.generateNoiseOctaves(depthRegion, p_185978_1_, p_185978_3_, 5, 5, depthNoiseScaleX,
				depthNoiseScaleZ, depthNoiseExponent);
		float f = coordinateScale;
		float f1 = heightScale;
		mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, p_185978_1_, p_185978_2_, p_185978_3_,
				5, 33, 5, f / mainNoiseScaleX, f1 / mainNoiseScaleY, f / mainNoiseScaleZ);
		minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_,
				5, 33, 5, f, f1, f);
		maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_,
				5, 33, 5, f, f1, f);
		int i = 0;
		int j = 0;

		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				Biome biome = biomesForGeneration[k + 2 + (l + 2) * 10];

				for (int j1 = -2; j1 <= 2; ++j1) {
					for (int k1 = -2; k1 <= 2; ++k1) {
						Biome biome1 = biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = biomeDepthOffset + biome1.getBaseHeight() * biomeDepthWeight;
						float f6 = biomeScaleOffset + biome1.getHeightVariation() * biomeScaleWeight;

						if (terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}

						float f7 = biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

						if (biome1.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0F;
						}

						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}

				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = depthRegion[j] / 8000.0D;

				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}

				d7 = d7 * 3.0D - 2.0D;

				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;

					if (d7 < -1.0D) {
						d7 = -1.0D;
					}

					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}

					d7 = d7 / 8.0D;
				}

				++j;
				double d8 = f3;
				double d9 = f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * depthBaseSize / 8.0D;
				double d0 = depthBaseSize + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = (l1 - d0) * heightStretch * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = minLimitRegion[i] / lowerLimitScale;
					double d3 = maxLimitRegion[i] / upperLimitScale;
					double d4 = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

					if (l1 > 29) {
						double d6 = (l1 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}

					heightMap[i] = d5;
					++i;
				}
			}
		}
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		int xPos = x * 16;
		int zPos = z * 16;
		BlockPos blockpos = new BlockPos(xPos, 0, zPos);
		Biome biome = world.getBiome(blockpos.add(16, 0, 16));
		rand.setSeed(world.getSeed());
		long k = rand.nextLong() / 2L * 2L + 1L;
		long l = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(x * k + z * l ^ world.getSeed());
		boolean flag = false;
		ChunkPos chunkpos = new ChunkPos(x, z);

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, flag);

		// Place trees
		int treesPerChunk = 10;

		for (int i = 0; i < treesPerChunk; ++i) {
			int rand1 = rand.nextInt(16) + 8;
			int rand2 = rand.nextInt(16) + 8;
			WorldGenAbstractTree worldgenabstracttree = biome.getRandomTreeFeature(rand);
			worldgenabstracttree.setDecorationDefaults();
			BlockPos chunkBlockPos = new BlockPos(chunkpos.getXStart(), 0, chunkpos.getZStart());
			BlockPos pos = world.getHeight(chunkBlockPos.add(rand1, 0, rand2));

			if (worldgenabstracttree.generate(world, rand, pos)) {
				worldgenabstracttree.generateSaplings(world, rand, pos);
			}
		}

		// Place flowers
		int flowersPerChunk = 7;

		for (int i = 0; i < flowersPerChunk; ++i) {
			int rand1 = rand.nextInt(16) + 8;
			int rand2 = rand.nextInt(16) + 8;
			BlockPos chunkBlockPos = new BlockPos(chunkpos.getXStart(), 0, chunkpos.getZStart());
			int y = world.getHeight(chunkBlockPos.add(rand1, 0, rand2)).getY() + 32;

			if (y > 0) {
				int rand3 = rand.nextInt(y);
				BlockPos blockpos1 = chunkBlockPos.add(rand1, rand3, rand2);
				BlockFlower.EnumFlowerType blockflower$enumflowertype = biome.pickRandomFlower(rand, blockpos1);
				BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();

				if (blockflower.getDefaultState().getMaterial() != Material.AIR) {
					flowerGen.setGeneratedBlock(blockflower, blockflower$enumflowertype);
					flowerGen.generate(world, rand, blockpos1);
				}
			}
		}

		// Place tallgrass
		int grassPerChunk = 14;

		for (int i = 0; i < grassPerChunk; ++i) {
			int rand1 = rand.nextInt(16) + 8;
			int rand2 = rand.nextInt(16) + 8;
			BlockPos chunkBlockPos = new BlockPos(chunkpos.getXStart(), 0, chunkpos.getZStart());
			int y = world.getHeight(chunkBlockPos.add(rand1, 0, rand2)).getY() * 2;

			if (y > 0) {
				int rand3 = rand.nextInt(y);
				biome.getRandomWorldGenForGrass(rand).generate(world, rand, chunkBlockPos.add(rand1, rand3, rand2));
			}
		}

		temple.generateStructure(world, rand, chunkpos);

		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, world, rand, x, z, flag);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return new LinkedList<>();
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
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