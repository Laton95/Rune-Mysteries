package com.laton95.runemysteries.world.gen;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.spawner.WorldEntitySpawner;

public class SurfaceWorldChunkGenerator extends NoiseChunkGenerator<SurfaceGenerationSettings> {
	
	private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
		for(int i = -2; i <= 2; ++i) {
			for(int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				p_222575_0_[i + 2 + (j + 2) * 5] = f;
			}
		}
		
	});
	
	private final OctavesNoiseGenerator depthNoise;
	
	public SurfaceWorldChunkGenerator(IWorld world, BiomeProvider biomeProvider, SurfaceGenerationSettings settings) {
		super(world, biomeProvider, 4, 8, 256, settings, true);
		this.randomSeed.skip(2620);
		this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 15, 0);
	}
	
	@Override
	public void spawnMobs(WorldGenRegion region) {
		int x = region.getMainChunkX();
		int z = region.getMainChunkZ();
		Biome biome = region.getBiome((new ChunkPos(x, z)).asBlockPos());
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		sharedseedrandom.setDecorationSeed(region.getSeed(), x << 4, z << 4);
		WorldEntitySpawner.performWorldGenSpawning(region, biome, x, z, sharedseedrandom);
	}
	
	@Override
	protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
		double[] adouble = new double[2];
		float f = 0.0F;
		float f1 = 0.0F;
		float f2 = 0.0F;
		int i = 2;
		int j = this.getSeaLevel();
		float f3 = this.biomeProvider.getNoiseBiome(noiseX, j, noiseZ).getDepth();
		
		for(int k = -2; k <= 2; ++k) {
			for(int l = -2; l <= 2; ++l) {
				Biome biome = this.biomeProvider.getNoiseBiome(noiseX + k, j, noiseZ + l);
				float biomeDepth = biome.getDepth();
				float biomeScale = biome.getScale();
				
				float f6 = field_222576_h[k + 2 + (l + 2) * 5] / (biomeDepth + 2.0F);
				if(biome.getDepth() > f3) {
					f6 /= 2.0F;
				}
				
				f += biomeScale * f6;
				f1 += biomeDepth * f6;
				f2 += f6;
			}
		}
		
		f = f / f2;
		f1 = f1 / f2;
		f = f * 0.9F + 0.1F;
		f1 = (f1 * 4.0F - 1.0F) / 8.0F;
		adouble[0] = (double) f1 + this.getNoiseDepthAt(noiseX, noiseZ);
		adouble[1] = f;
		return adouble;
	}
	
	@Override
	protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
		double d0 = 8.5D;
		double d1 = ((double) p_222545_5_ - (d0 + p_222545_1_ * d0 / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
		if(d1 < 0.0D) {
			d1 *= 4.0D;
		}
		
		return d1;
	}
	
	@Override
	protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
		double d0 = 684.412F;
		double d1 = 684.412F;
		double d2 = 8.555149841308594D;
		double d3 = 4.277574920654297D;
		int i = -10;
		int j = 3;
		this.calcNoiseColumn(noiseColumn, noiseX, noiseZ, d0, d1, d2, d3, j, i);
	}
	
	@Override
	public int getGroundHeight() {
		return 64;
	}
	
	@Override
	public int getSeaLevel() {
		return settings.getSeaLevel();
	}
	
	private double getNoiseDepthAt(int noiseX, int noiseZ) {
		double d0 = this.depthNoise.getValue(noiseX * 200, 10.0D, noiseZ * 200, 1.0D, 0.0D, true) * 65535.0D / 8000.0D;
		if(d0 < 0.0D) {
			d0 = -d0 * 0.3D;
		}
		
		d0 = d0 * 3.0D - 2.0D;
		if(d0 < 0.0D) {
			d0 = d0 / 28.0D;
		}
		else {
			if(d0 > 1.0D) {
				d0 = 1.0D;
			}
			
			d0 = d0 / 40.0D;
		}
		
		return d0;
	}
}
