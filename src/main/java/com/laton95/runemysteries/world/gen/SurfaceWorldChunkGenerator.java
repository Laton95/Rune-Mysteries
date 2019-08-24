package com.laton95.runemysteries.world.gen;

import com.laton95.runemysteries.world.gen.feature.structure.temple.RuneTempleStructure;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawJunction;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.spawner.WorldEntitySpawner;

public class SurfaceWorldChunkGenerator extends ChunkGenerator<GenerationSettings> {
	private static final float[] jigsawSmootherMask = Util.make(new float[13824], (p_222557_0_) -> {
		for(int i = 0; i < 24; ++i) {
			for(int j = 0; j < 24; ++j) {
				for(int k = 0; k < 24; ++k) {
					p_222557_0_[i * 24 * 24 + j * 24 + k] = (float) makeJigsawSmoother(j - 12, k - 12, i - 12);
				}
			}
		}
		
	});
	
	private static final float[] biomeSmootherMask = Util.make(new float[25], (p_222575_0_) -> {
		for(int i = -2; i <= 2; ++i) {
			for(int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
				p_222575_0_[i + 2 + (j + 2) * 5] = f;
			}
		}
		
	});
	
	private static final BlockState AIR = Blocks.AIR.getDefaultState();
	private final int verticalNoiseGranularity;
	private final int horizontalNoiseGranularity;
	private final int noiseSizeX;
	private final int noiseSizeY;
	private final int noiseSizeZ;
	protected final SharedSeedRandom randomSeed;
	private final OctavesNoiseGenerator field_222568_o;
	private final OctavesNoiseGenerator field_222569_p;
	private final OctavesNoiseGenerator field_222570_q;
	private final INoiseGenerator surfaceDepthNoise;
	private final OctavesNoiseGenerator depthNoise;
	protected final BlockState defaultBlock;
	protected final BlockState defaultFluid;
	
	public SurfaceWorldChunkGenerator(IWorld world, BiomeProvider biomeProvider, GenerationSettings settings) {
		super(world, biomeProvider, settings);
		this.verticalNoiseGranularity = 8;
		this.horizontalNoiseGranularity = 4;
		this.defaultBlock = settings.getDefaultBlock();
		this.defaultFluid = settings.getDefaultFluid();
		this.noiseSizeX = 16 / this.horizontalNoiseGranularity;
		this.noiseSizeY = 256 / this.verticalNoiseGranularity;
		this.noiseSizeZ = 16 / this.horizontalNoiseGranularity;
		this.randomSeed = new SharedSeedRandom(this.seed);
		this.field_222568_o = new OctavesNoiseGenerator(this.randomSeed, 16);
		this.field_222569_p = new OctavesNoiseGenerator(this.randomSeed, 16);
		this.field_222570_q = new OctavesNoiseGenerator(this.randomSeed, 8);
		boolean usePerlin = true;
		this.surfaceDepthNoise = usePerlin ? new PerlinNoiseGenerator(this.randomSeed, 4) : new OctavesNoiseGenerator(this.randomSeed, 4);
		this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
	}
	
	@Override
	public boolean hasStructure(Biome biomeIn, Structure<? extends IFeatureConfig> structureIn) {
		return structureIn instanceof RuneTempleStructure;
	}
	
	@Override
	public void spawnMobs(WorldGenRegion region) {
		int i = region.getMainChunkX();
		int j = region.getMainChunkZ();
		Biome biome = region.getChunk(i, j).getBiomes()[0];
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
	}
	
	private double func_222552_a(int quarterChunkX, int p_222552_2_, int quarterChunkZ, double p_222552_4_, double p_222552_6_, double p_222552_8_, double p_222552_10_) {
		double d0 = 0.0D;
		double d1 = 0.0D;
		double d2 = 0.0D;
		double d3 = 1.0D;
		
		for(int i = 0; i < 16; ++i) {
			double d4 = OctavesNoiseGenerator.maintainPrecision((double)quarterChunkX * p_222552_4_ * d3);
			double d5 = OctavesNoiseGenerator.maintainPrecision((double)p_222552_2_ * p_222552_6_ * d3);
			double d6 = OctavesNoiseGenerator.maintainPrecision((double)quarterChunkZ * p_222552_4_ * d3);
			double d7 = p_222552_6_ * d3;
			d0 += this.field_222568_o.getOctave(i).func_215456_a(d4, d5, d6, d7, (double)p_222552_2_ * d7) / d3;
			d1 += this.field_222569_p.getOctave(i).func_215456_a(d4, d5, d6, d7, (double)p_222552_2_ * d7) / d3;
			if (i < 8) {
				d2 += this.field_222570_q.getOctave(i).func_215456_a(OctavesNoiseGenerator.maintainPrecision((double)quarterChunkX * p_222552_8_ * d3), OctavesNoiseGenerator.maintainPrecision((double)p_222552_2_ * p_222552_10_ * d3), OctavesNoiseGenerator.maintainPrecision((double)quarterChunkZ * p_222552_8_ * d3), p_222552_10_ * d3, (double)p_222552_2_ * p_222552_10_ * d3) / d3;
			}
			
			d3 /= 2.0D;
		}
		
		return MathHelper.clampedLerp(d0 / 512.0D, d1 / 512.0D, (d2 / 10.0D + 1.0D) / 2.0D);
	}
	
	protected double[] getNoiseAtDefault(int quarterChunkX, int quarterChunkY) {
		double[] noises = new double[this.noiseSizeY + 1];
		this.getNoiseAtDefault(noises, quarterChunkX, quarterChunkY);
		return noises;
	}
	
	protected void getNoiseAt(double[] noises, int quarterChunkX, int quarterChunkZ, double p_222546_4_, double p_222546_6_, double p_222546_8_, double p_222546_10_, int p_222546_12_, int p_222546_13_) {
		double[] biomeNoise = this.getBiomeNoiseAt(quarterChunkX, quarterChunkZ);
		double biomeDepth = biomeNoise[0];
		double biomeScale = biomeNoise[1];
		double d2 = this.getNoiseSizeMinusThree();
		double d3 = this.func_222553_h();
		
		for(int i = 0; i < this.getNoiseSizePlusOne(); ++i) {
			double octaveNoise = this.func_222552_a(quarterChunkX, i, quarterChunkZ, p_222546_4_, p_222546_6_, p_222546_8_, p_222546_10_);
			octaveNoise = octaveNoise - this.getBiomeContribution(biomeDepth, biomeScale, i);
			if ((double)i > d2) {
				octaveNoise = MathHelper.clampedLerp(octaveNoise, (double)p_222546_13_, ((double)i - d2) / (double)p_222546_12_);
			} else if ((double)i < d3) {
				octaveNoise = MathHelper.clampedLerp(octaveNoise, -30.0D, (d3 - (double)i) / (d3 - 1.0D));
			}
			
			noises[i] = octaveNoise;
		}
		
	}
	
	private double[] getBiomeNoiseAt(int quarterChunkX, int quarterChunkZ) {
		double[] noises = new double[2];
		float scale = 0.0F;
		float depth = 0.0F;
		float totalBiomeMask = 0.0F;
		float biomeDepth = this.biomeProvider.func_222366_b(quarterChunkX, quarterChunkZ).getDepth();
		
		for(int xOffset = -2; xOffset <= 2; ++xOffset) {
			for(int zOffset = -2; zOffset <= 2; ++zOffset) {
				Biome adjacentBiome = this.biomeProvider.func_222366_b(quarterChunkX + xOffset, quarterChunkZ + zOffset);
				float adjacentBiomeDepth = adjacentBiome.getDepth();
				float adjacentBiomeScale = adjacentBiome.getScale();
				
				float maskValue = biomeSmootherMask[xOffset + 2 + (zOffset + 2) * 5] / (adjacentBiomeDepth + 2.0F);
				if (adjacentBiome.getDepth() > biomeDepth) {
					maskValue /= 2.0F;
				}
				
				scale += adjacentBiomeScale * maskValue;
				depth += adjacentBiomeDepth * maskValue;
				totalBiomeMask += maskValue;
			}
		}
		
		scale = scale / totalBiomeMask;
		depth = depth / totalBiomeMask;
		scale = scale * 0.9F + 0.1F;
		depth = (depth * 4.0F - 1.0F) / 8.0F;
		noises[0] = (double)depth + this.getDepthNoiseAt(quarterChunkX, quarterChunkZ);
		noises[1] = (double)scale;
		return noises;
	}
	
	private double getDepthNoiseAt(int quarterChunkX, int quarterChunkZ) {
		double noise = this.depthNoise.func_215462_a((double)(quarterChunkX * 200), 10.0D, (double)(quarterChunkZ * 200), 1.0D, 0.0D, true) / 8000.0D;
		if (noise < 0.0D) {
			noise = -noise * 0.3D;
		}
		
		noise = noise * 3.0D - 2.0D;
		if (noise < 0.0D) {
			noise = noise / 28.0D;
		} else {
			if (noise > 1.0D) {
				noise = 1.0D;
			}
			
			noise = noise / 40.0D;
		}
		
		return noise;
	}
	
	protected double getBiomeContribution(double biomeDepth, double biomeScale, int p_222545_5_) {
		double d0 = 8.5D;
		double d1 = ((double)p_222545_5_ - (d0 + biomeDepth * d0 / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / biomeScale;
		if (d1 < 0.0D) {
			d1 *= 4.0D;
		}
		
		return d1;
	}
	
	protected double getNoiseSizeMinusThree() {
		return (double)(this.getNoiseSizePlusOne() - 4);
	}
	
	protected double func_222553_h() {
		return 0.0D;
	}
	
	@Override
	public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmap) {
		int i = Math.floorDiv(p_222529_1_, this.horizontalNoiseGranularity);
		int j = Math.floorDiv(p_222529_2_, this.horizontalNoiseGranularity);
		int k = Math.floorMod(p_222529_1_, this.horizontalNoiseGranularity);
		int l = Math.floorMod(p_222529_2_, this.horizontalNoiseGranularity);
		double d0 = (double)k / (double)this.horizontalNoiseGranularity;
		double d1 = (double)l / (double)this.horizontalNoiseGranularity;
		double[][] adouble = new double[][]{this.getNoiseAtDefault(i, j), this.getNoiseAtDefault(i, j + 1), this.getNoiseAtDefault(i + 1, j), this.getNoiseAtDefault(i + 1, j + 1)};
		
		for(int j1 = this.noiseSizeY - 1; j1 >= 0; --j1) {
			double d2 = adouble[0][j1];
			double d3 = adouble[1][j1];
			double d4 = adouble[2][j1];
			double d5 = adouble[3][j1];
			double d6 = adouble[0][j1 + 1];
			double d7 = adouble[1][j1 + 1];
			double d8 = adouble[2][j1 + 1];
			double d9 = adouble[3][j1 + 1];
			
			for(int k1 = this.verticalNoiseGranularity - 1; k1 >= 0; --k1) {
				double d10 = (double)k1 / (double)this.verticalNoiseGranularity;
				double d11 = MathHelper.lerp3(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
				int l1 = j1 * this.verticalNoiseGranularity + k1;
				if (d11 > 0.0D || l1 < this.getSeaLevel()) {
					BlockState state;
					if (d11 > 0.0D) {
						state = this.defaultBlock;
					} else {
						state = this.defaultFluid;
					}
					
					if (heightmap.func_222684_d().test(state)) {
						return l1 + 1;
					}
				}
			}
		}
		
		return 0;
	}
	
	protected void getNoiseAtDefault(double[] noises, int quarterChunkX, int quarterChunkZ) {
		double d0 = (double)684.412F;
		double d1 = (double)684.412F;
		double d2 = 8.555149841308594D;
		double d3 = 4.277574920654297D;
		int i = -10;
		int j = 3;
		this.getNoiseAt(noises, quarterChunkX, quarterChunkZ, d0, d1, d2, d3, j, i);
	}
	
	public int getNoiseSizePlusOne() {
		return this.noiseSizeY + 1;
	}
	
	public void generateSurface(IChunk chunkIn) {
		ChunkPos chunkpos = chunkIn.getPos();
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setBaseChunkSeed(chunkpos.x, chunkpos.z);
		int chunkXStart = chunkpos.getXStart();
		int chunkZStart = chunkpos.getZStart();
		double noiseMultiplier = 0.0625D;
		Biome[] biomes = chunkIn.getBiomes();
		
		for(int chunkX = 0; chunkX < 16; ++chunkX) {
			for(int chunkZ = 0; chunkZ < 16; ++chunkZ) {
				int worldX = chunkXStart + chunkX;
				int worldZ = chunkZStart + chunkZ;
				int y = chunkIn.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, chunkX, chunkZ) + 1;
				double noise = this.surfaceDepthNoise.func_215460_a((double) worldX * noiseMultiplier, (double) worldZ * noiseMultiplier, noiseMultiplier, (double) chunkX * noiseMultiplier);
				biomes[chunkZ * 16 + chunkX].buildSurface(sharedseedrandom, chunkIn, worldX, worldZ, y, noise, this.getSettings().getDefaultBlock(), this.getSettings().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
			}
		}
		
		BlockPos.MutableBlockPos bedrockPos = new BlockPos.MutableBlockPos();
		for(BlockPos blockpos : BlockPos.getAllInBoxMutable(chunkXStart, 0, chunkZStart, chunkXStart + 15, 0, chunkZStart + 15)) {
			chunkIn.setBlockState(bedrockPos.setPos(blockpos.getX(), 0, blockpos.getZ()), Blocks.BEDROCK.getDefaultState(), false);
		}
	}
	
	@Override
	public int getGroundHeight() {
		return 64;
	}
	
	public void makeBase(IWorld world, IChunk chunk) {
		ObjectList<AbstractVillagePiece> villagePieces = new ObjectArrayList<>(10);
		ObjectList<JigsawJunction> jigsawJunctions = new ObjectArrayList<>(32);
		ChunkPos chunkPos = chunk.getPos();
		int chunkXPos = chunkPos.x;
		int chunkZPos = chunkPos.z;
		int chunkXStart = chunkXPos << 4;
		int chunkZStart = chunkZPos << 4;
		
		for(Structure<?> structure : Feature.field_214488_aQ) {
			String structureName = structure.getStructureName();
			LongIterator structureIterator = chunk.getStructureReferences(structureName).iterator();
			
			while(structureIterator.hasNext()) {
				long j1 = structureIterator.nextLong();
				ChunkPos structureChunkPos = new ChunkPos(j1);
				IChunk structureChunk = world.getChunk(structureChunkPos.x, structureChunkPos.z);
				StructureStart start = structureChunk.getStructureStart(structureName);
				if(start != null && start.isValid()) {
					for(StructurePiece piece : start.getComponents()) {
						if(piece.func_214810_a(chunkPos, 12) && piece instanceof AbstractVillagePiece) {
							AbstractVillagePiece villagePiece = (AbstractVillagePiece) piece;
							JigsawPattern.PlacementBehaviour placementBehaviour = villagePiece.func_214826_b().getPlacementBehaviour();
							if(placementBehaviour == JigsawPattern.PlacementBehaviour.RIGID) {
								villagePieces.add(villagePiece);
							}
							
							for(JigsawJunction jigsawJunction : villagePiece.getJunctions()) {
								int sourceX = jigsawJunction.getSourceX();
								int sourceZ = jigsawJunction.getSourceZ();
								if(sourceX > chunkXStart - 12 && sourceZ > chunkZStart - 12 && sourceX < chunkXStart + 15 + 12 && sourceZ < chunkZStart + 15 + 12) {
									jigsawJunctions.add(jigsawJunction);
								}
							}
						}
					}
				}
			}
		}
		
		double[][][] noises = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];
		
		for(int zNoiseOffset = 0; zNoiseOffset < this.noiseSizeZ + 1; ++zNoiseOffset) {
			noises[0][zNoiseOffset] = new double[this.noiseSizeY + 1];
			this.getNoiseAtDefault(noises[0][zNoiseOffset], chunkXPos * this.noiseSizeX, chunkZPos * this.noiseSizeZ + zNoiseOffset);
			noises[1][zNoiseOffset] = new double[this.noiseSizeY + 1];
		}
		
		ChunkPrimer primer = (ChunkPrimer) chunk;
		Heightmap oceanHightmap = primer.func_217303_b(Heightmap.Type.OCEAN_FLOOR_WG);
		Heightmap surfaceHeightmap = primer.func_217303_b(Heightmap.Type.WORLD_SURFACE_WG);
		BlockPos.MutableBlockPos lightPos = new BlockPos.MutableBlockPos();
		ObjectListIterator<AbstractVillagePiece> villagePiecesIterator = villagePieces.iterator();
		ObjectListIterator<JigsawJunction> jigsawJunctionIterator = jigsawJunctions.iterator();
		
		for(int xNoiseOffset = 0; xNoiseOffset < this.noiseSizeX; ++xNoiseOffset) {
			for(int zNoiseOffset = 0; zNoiseOffset < this.noiseSizeZ + 1; ++zNoiseOffset) {
				this.getNoiseAtDefault(noises[1][zNoiseOffset], chunkXPos * this.noiseSizeX + xNoiseOffset + 1, chunkZPos * this.noiseSizeZ + zNoiseOffset);
			}
			
			for(int zNoiseOffset = 0; zNoiseOffset < this.noiseSizeZ; ++zNoiseOffset) {
				ChunkSection section = primer.func_217332_a(15);
				section.lock();
				
				for(int yNoiseOffset = this.noiseSizeY - 1; yNoiseOffset >= 0; --yNoiseOffset) {
					double noise000 = noises[0][zNoiseOffset][yNoiseOffset];
					double noise010 = noises[0][zNoiseOffset + 1][yNoiseOffset];
					double noise100 = noises[1][zNoiseOffset][yNoiseOffset];
					double noise110 = noises[1][zNoiseOffset + 1][yNoiseOffset];
					double noise001 = noises[0][zNoiseOffset][yNoiseOffset + 1];
					double noise011 = noises[0][zNoiseOffset + 1][yNoiseOffset + 1];
					double noise101 = noises[1][zNoiseOffset][yNoiseOffset + 1];
					double noise111 = noises[1][zNoiseOffset + 1][yNoiseOffset + 1];
					
					for(int yGranularityOffset = this.verticalNoiseGranularity - 1; yGranularityOffset >= 0; --yGranularityOffset) {
						int worldY = yNoiseOffset * this.verticalNoiseGranularity + yGranularityOffset;
						int sectionY = worldY & 15;
						int sectionNumber = worldY >> 4;
						if(section.getYLocation() >> 4 != sectionNumber) {
							section.unlock();
							section = primer.func_217332_a(sectionNumber);
							section.lock();
						}
						
						double yNoiseScale = (double) yGranularityOffset / (double) this.verticalNoiseGranularity;
						double d6 = MathHelper.lerp(yNoiseScale, noise000, noise001);
						double d7 = MathHelper.lerp(yNoiseScale, noise100, noise101);
						double d8 = MathHelper.lerp(yNoiseScale, noise010, noise011);
						double d9 = MathHelper.lerp(yNoiseScale, noise110, noise111);
						
						for(int XGranularityOffset = 0; XGranularityOffset < this.horizontalNoiseGranularity; ++XGranularityOffset) {
							int worldX = chunkXStart + xNoiseOffset * this.horizontalNoiseGranularity + XGranularityOffset;
							int sectionX = worldX & 15;
							double xNoiseScale = (double) XGranularityOffset / (double) this.horizontalNoiseGranularity;
							double d11 = MathHelper.lerp(xNoiseScale, d6, d7);
							double d12 = MathHelper.lerp(xNoiseScale, d8, d9);
							
							for(int zGranularityOffset = 0; zGranularityOffset < this.horizontalNoiseGranularity; ++zGranularityOffset) {
								int worldZ = chunkZStart + zNoiseOffset * this.horizontalNoiseGranularity + zGranularityOffset;
								int sectionZ = worldZ & 15;
								double zNoiseScale = (double) zGranularityOffset / (double) this.horizontalNoiseGranularity;
								double finalNoise = MathHelper.lerp(zNoiseScale, d11, d12);
								double finalNoiseClamped = MathHelper.clamp(finalNoise / 200.0D, -1.0D, 1.0D);
								
								int sectionStructureXEnd;
								int sectionStructureY;
								int sectionStructureZEnd;
								for(finalNoiseClamped = finalNoiseClamped / 2.0D - finalNoiseClamped * finalNoiseClamped * finalNoiseClamped / 24.0D; villagePiecesIterator.hasNext(); finalNoiseClamped += getJigsawNoise(sectionStructureXEnd, sectionStructureY, sectionStructureZEnd) * 0.8D) {
									AbstractVillagePiece villagePiece = villagePiecesIterator.next();
									MutableBoundingBox boundingBox = villagePiece.getBoundingBox();
									sectionStructureXEnd = Math.max(0, Math.max(boundingBox.minX - worldX, worldX - boundingBox.maxX));
									sectionStructureY = worldY - (boundingBox.minY + villagePiece.getGroundLevelDelta());
									sectionStructureZEnd = Math.max(0, Math.max(boundingBox.minZ - worldZ, worldZ - boundingBox.maxZ));
								}
								
								villagePiecesIterator.back(villagePieces.size());
								
								while(jigsawJunctionIterator.hasNext()) {
									JigsawJunction jigsawJunction = jigsawJunctionIterator.next();
									int sourceX = worldX - jigsawJunction.getSourceX();
									int sourceY = worldY - jigsawJunction.getSourceGroundY();
									int sourceZ = worldZ - jigsawJunction.getSourceZ();
									finalNoiseClamped += getJigsawNoise(sourceX, sourceY, sourceZ) * 0.4D;
								}
								
								jigsawJunctionIterator.back(jigsawJunctions.size());
								
								BlockState state;
								if(finalNoiseClamped > 0.0D) {
									state = this.defaultBlock;
								}
								else if(worldY < getSeaLevel()) {
									state = this.defaultFluid;
								}
								else {
									state = AIR;
								}
								
								if(state != AIR) {
									if(state.getLightValue() != 0) {
										lightPos.setPos(worldX, worldY, worldZ);
										primer.addLightPosition(lightPos);
									}
									
									section.setBlockState(sectionX, sectionY, sectionZ, state, false);
									oceanHightmap.update(sectionX, worldY, sectionZ, state);
									surfaceHeightmap.update(sectionX, worldY, sectionZ, state);
								}
							}
						}
					}
				}
				
				section.unlock();
			}
			
			double[][] temp = noises[0];
			noises[0] = noises[1];
			noises[1] = temp;
		}
	}
	
	private static double getJigsawNoise(int sourceX, int sourceY, int sourceZ) {
		int i = sourceX + 12;
		int j = sourceY + 12;
		int k = sourceZ + 12;
		if (i >= 0 && i < 24) {
			if (j >= 0 && j < 24) {
				return k >= 0 && k < 24 ? (double) jigsawSmootherMask[k * 24 * 24 + i * 24 + j] : 0.0D;
			} else {
				return 0.0D;
			}
		} else {
			return 0.0D;
		}
	}
	
	private static double makeJigsawSmoother(int x, int y, int z) {
		double d0 = (double)(x * x + z * z);
		double d1 = (double)y + 0.5D;
		double d2 = d1 * d1;
		double d3 = Math.pow(Math.E, -(d2 / 16.0D + d0 / 16.0D));
		double d4 = -d1 * MathHelper.fastInvSqrt(d2 / 2.0D + d0 / 2.0D) / 2.0D;
		return d4 * d3;
	}
}
