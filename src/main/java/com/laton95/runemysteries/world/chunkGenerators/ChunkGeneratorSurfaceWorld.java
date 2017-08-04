package com.laton95.runemysteries.world.chunkGenerators;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import scala.annotation.implicitNotFound;

public class ChunkGeneratorSurfaceWorld implements IChunkGenerator {
	private final IBlockState worldBlock;
	private final Random rand;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	private ChunkGeneratorSettings settings;
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
	private final MapGenRuneTemple temple;
	private final int treesPerChunk;
	private final int flowersPerChunk;
	private final List<Biome.SpawnListEntry> worldMobSpawns;

	public ChunkGeneratorSurfaceWorld(World worldIn, long seed, IBlockState worldBlock, String generatorSettings, int treesPerChunk, int flowersPerChunk, List<Biome.SpawnListEntry> worldMobSpawns) {
		world = worldIn;

		WorldSettings settings = new WorldSettings(world.getWorldInfo());
		settings.setGeneratorOptions(generatorSettings);
		world.getWorldInfo().populateFromWorldSettings(settings);
		this.settings = ChunkGeneratorSettings.Factory.jsonToFactory(generatorSettings).build();
		worldIn.setSeaLevel(this.settings.seaLevel);

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
		
		this.worldBlock = worldBlock;
		this.treesPerChunk = treesPerChunk;
		this.flowersPerChunk = flowersPerChunk;
		
		this.worldMobSpawns = worldMobSpawns;

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
		ChunkPrimer chunkprimer = new ChunkPrimer();
		if (Math.max(Math.abs(x), Math.abs(z)) < 16) {
			rand.setSeed(x * 341873128712L + z * 132897987541L);
			setBlocksInChunk(x, z, chunkprimer);
			biomesForGeneration = world.getBiomeProvider().getBiomes(biomesForGeneration, x * 16, z * 16, 16, 16);
			replaceBiomeBlocks(x, z, chunkprimer, biomesForGeneration);
		}

		Chunk chunk = new Chunk(world, chunkprimer, x, z);

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
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, worldBlock);
								} else if (i2 * 8 + j2 < settings.seaLevel) {
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
		depthRegion = depthNoise.generateNoiseOctaves(depthRegion, p_185978_1_, p_185978_3_, 5, 5,
				settings.depthNoiseScaleX, settings.depthNoiseScaleZ, settings.depthNoiseScaleExponent);
		float f = settings.coordinateScale;
		float f1 = settings.heightScale;
		mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, p_185978_1_, p_185978_2_, p_185978_3_,
				5, 33, 5, f / settings.mainNoiseScaleX, f1 / settings.mainNoiseScaleY, f / settings.mainNoiseScaleZ);
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
						float f5 = settings.biomeDepthOffSet + biome1.getBaseHeight() * settings.biomeDepthWeight;
						float f6 = settings.biomeScaleOffset + biome1.getHeightVariation() * settings.biomeScaleWeight;

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
				d8 = d8 * settings.baseSize / 8.0D;
				double d0 = settings.baseSize + d8 * 4.0D;

				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = (l1 - d0) * settings.stretchY * 128.0D / 256.0D / d9;

					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}

					double d2 = minLimitRegion[i] / settings.lowerLimitScale;
					double d3 = maxLimitRegion[i] / settings.upperLimitScale;
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
		if (Math.max(Math.abs(x), Math.abs(z)) < 16) {
			BlockFalling.fallInstantly = true;
			int xPos = x * 16;
			int zPos = z * 16;
			BlockPos chunkStart = new BlockPos(xPos, 0, zPos);
			Biome biome = world.getBiome(chunkStart.add(16, 0, 16));
			rand.setSeed(world.getSeed());
			long k = rand.nextLong() / 2L * 2L + 1L;
			long l = rand.nextLong() / 2L * 2L + 1L;
			rand.setSeed(x * k + z * l ^ world.getSeed());
			boolean flag = false;
			ChunkPos chunkpos = new ChunkPos(x, z);

			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, world, rand, x, z, flag);
			
			biome.decorator.generateFalls = false;
			biome.decorator.treesPerChunk = 5;
			biome.decorate(world, rand, new BlockPos(xPos, 0, zPos));
			performWorldGenSpawning(this.world, biome, xPos + 8, zPos + 8, 16, 16, this.rand);

			temple.generateStructure(world, rand, chunkpos);

			net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, world, rand, x, z, flag);

			BlockFalling.fallInstantly = false;
		}
	}
	
	public void performWorldGenSpawning(World worldIn, Biome biomeIn, int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random randomIn)
    {
		if (!worldMobSpawns.isEmpty())
        {
	        while (randomIn.nextFloat() < 0.2)
	        {
	            Biome.SpawnListEntry entitySpawnEntry = (Biome.SpawnListEntry)WeightedRandom.getRandomItem(worldIn.rand, worldMobSpawns);
	            int i = entitySpawnEntry.minGroupCount + randomIn.nextInt(1 + entitySpawnEntry.maxGroupCount - entitySpawnEntry.minGroupCount);
	            IEntityLivingData ientitylivingdata = null;
	            int j = p_77191_2_ + randomIn.nextInt(p_77191_4_);
	            int k = p_77191_3_ + randomIn.nextInt(p_77191_5_);
	            int l = j;
	            int i1 = k;
	
	            for (int j1 = 0; j1 < i; ++j1)
	            {
	                boolean flag = false;
	
	                for (int k1 = 0; !flag && k1 < 4; ++k1)
	                {
	                    BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(j, 0, k));
	
	                    if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, worldIn, blockpos))
	                    {
	                        EntityLiving entityliving;
	
	                        try
	                        {
	                            entityliving = entitySpawnEntry.newInstance(worldIn);
	                        }
	                        catch (Exception exception)
	                        {
	                            exception.printStackTrace();
	                            continue;
	                        }
	
	                        entityliving.setLocationAndAngles((double)((float)j + 0.5F), (double)blockpos.getY(), (double)((float)k + 0.5F), randomIn.nextFloat() * 360.0F, 0.0F);
	                        worldIn.spawnEntity(entityliving);
	                        ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
	                        flag = true;
	                    }
	
	                    j += randomIn.nextInt(5) - randomIn.nextInt(5);
	
	                    for (k += randomIn.nextInt(5) - randomIn.nextInt(5); j < p_77191_2_ || j >= p_77191_2_ + p_77191_4_ || k < p_77191_3_ || k >= p_77191_3_ + p_77191_4_; k = i1 + randomIn.nextInt(5) - randomIn.nextInt(5))
	                    {
	                        j = l + randomIn.nextInt(5) - randomIn.nextInt(5);
	                    }
	                }
	            }
	        }
        }
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