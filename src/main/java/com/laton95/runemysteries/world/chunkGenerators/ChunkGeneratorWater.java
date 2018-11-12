package com.laton95.runemysteries.world.chunkGenerators;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.world.mapGenerators.MapGenCenterStructure;
import com.laton95.runemysteries.world.mapGenerators.MapGenTempleWater;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import javax.annotation.Nullable;
import java.util.List;

public class ChunkGeneratorWater implements IChunkGenerator
{
	private final MapGenCenterStructure temple = new MapGenTempleWater();
	
	private final World world;
	
	private final double[] heightMap = new double[825];
	
	private NoiseGeneratorOctaves depthNoise;
	
	private double[] mainNoiseRegion;
	
	private double[] minLimitRegion;
	
	private double[] maxLimitRegion;
	
	private double[] depthRegion;
	
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	
	private NoiseGeneratorOctaves mainPerlinNoise;
	
	public ChunkGeneratorWater(World world)
	{
		this.world = world;
		depthNoise = new NoiseGeneratorOctaves(world.rand, 16);
		minLimitPerlinNoise = new NoiseGeneratorOctaves(world.rand, 16);
		maxLimitPerlinNoise = new NoiseGeneratorOctaves(world.rand, 16);
		mainPerlinNoise = new NoiseGeneratorOctaves(world.rand, 8);
	}
	
	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		
		if(Math.max(Math.abs(x), Math.abs(z)) < 32)
		{
			setBlocksInChunk(x, z, chunkprimer);
			temple.generate(world, x, z, chunkprimer);
		}
		
		Chunk chunk = new Chunk(world, chunkprimer, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}
	
	@Override
	public void populate(int x, int z)
	{
		if(Math.max(Math.abs(x), Math.abs(z)) < 32)
		{
			BlockFalling.fallInstantly = true;
			
			temple.generateStructure(world, world.rand, new ChunkPos(x, z));
			
			BlockFalling.fallInstantly = false;
		}
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}
	
	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return ImmutableList.of(
				new Biome.SpawnListEntry(EntitySquid.class, 10, 2, 10)
							   );
	}
	
	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
	{
		return new BlockPos(0, 0, 0);
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
	
	private void setBlocksInChunk(int x, int z, ChunkPrimer primer)
	{
		generateHeightmap(x * 4, z * 4);
		
		for(int i = 0; i < 4; ++i)
		{
			int j = i * 5;
			int k = (i + 1) * 5;
			
			for(int l = 0; l < 4; ++l)
			{
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;
				
				for(int i2 = 0; i2 < 32; ++i2)
				{
					double d1 = heightMap[i1 + i2];
					double d2 = heightMap[j1 + i2];
					double d3 = heightMap[k1 + i2];
					double d4 = heightMap[l1 + i2];
					double d5 = (heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (heightMap[l1 + i2 + 1] - d4) * 0.125D;
					
					for(int j2 = 0; j2 < 8; ++j2)
					{
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;
						
						for(int k2 = 0; k2 < 4; ++k2)
						{
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;
							
							for(int l2 = 0; l2 < 4; ++l2)
							{
								if((lvt_45_1_ += d16) > 0.0D)
								{
									int y = i2 * 8 + j2;
									if(y == 0)
									{
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.BEDROCK.getDefaultState());
									}
									else if(y < 40)
									{
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.STONE.getDefaultState());
									}
									else
									{
										primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.SAND.getDefaultState());
									}
								}
								else if(i2 * 8 + j2 < 255)
								{
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.WATER.getDefaultState());
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
	
	private void generateHeightmap(int xOffset, int zOffset)
	{
		double depthNoiseScaleX = 200.0;
		double depthNoiseScaleZ = 200.0;
		double depthNoiseScaleExponent = 0.5;
		depthRegion = depthNoise.generateNoiseOctaves(depthRegion, xOffset, zOffset, 5, 5, depthNoiseScaleX, depthNoiseScaleZ, depthNoiseScaleExponent);
		float horizontalScale = 684.412f;
		float heightScale = 1;
		float mainNoiseScaleX = 80.0f;
		float mainNoiseScaleY = 160.0f;
		float mainNoiseScaleZ = 80.0f;
		mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, xOffset, 0, zOffset, 5, 33, 5, horizontalScale / mainNoiseScaleX, heightScale / mainNoiseScaleY, horizontalScale / mainNoiseScaleZ);
		minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, xOffset, 0, zOffset, 5, 33, 5, horizontalScale, heightScale, horizontalScale);
		maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, xOffset, 0, zOffset, 5, 33, 5, horizontalScale, heightScale, horizontalScale);
		int i = 0;
		int j = 0;
		
		for(int k = 0; k < 5; ++k)
		{
			for(int l = 0; l < 5; ++l)
			{
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				
				for(int j1 = -2; j1 <= 2; ++j1)
				{
					for(int k1 = -2; k1 <= 2; ++k1)
					{
						float f5 = -1.0f;
						float f6 = 0.3f;
						
						float f7 = 1 / (f5 + 2.0F);
						
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
				
				if(d7 < 0.0D)
				{
					d7 = -d7 * 0.3D;
				}
				
				d7 = d7 * 3.0D - 2.0D;
				
				if(d7 < 0.0D)
				{
					d7 = d7 / 2.0D;
					
					if(d7 < -1.0D)
					{
						d7 = -1.0D;
					}
					
					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				}
				else
				{
					if(d7 > 1.0D)
					{
						d7 = 1.0D;
					}
					
					d7 = d7 / 8.0D;
				}
				
				++j;
				double d8 = f3;
				double d9 = f2;
				d8 = d8 + d7 * 0.2D;
				float baseSize = 8.5f;
				d8 = d8 * baseSize / 8.0D;
				double d0 = baseSize + d8 * 4.0D;
				
				for(int l1 = 0; l1 < 33; ++l1)
				{
					float stretchY = 50.0f;
					double d1 = (l1 - d0) * stretchY * 128.0D / 256.0D / d9;
					
					if(d1 < 0.0D)
					{
						d1 *= 4.0D;
					}
					
					float lowerLimitScale = 512.0f;
					double d2 = minLimitRegion[i] / lowerLimitScale;
					float upperLimitScale = 512.0f;
					double d3 = maxLimitRegion[i] / upperLimitScale;
					double d4 = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;
					
					if(l1 > 29)
					{
						double d6 = (l1 - 29) / 3.0F;
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}
					
					heightMap[i] = d5;
					++i;
				}
			}
		}
	}
}
