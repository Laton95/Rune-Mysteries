package com.laton95.runemysteries.world.gen.surfacebuilders;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class FireTempleSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public FireTempleSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51305_1_) {
		super(p_i51305_1_);
	}
	
	@Override
	public void buildSurface(Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		double d0 = Biome.INFO_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);
		if (d0 > 0.0D) {
			int i = x & 15;
			int j = z & 15;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			
			for(int k = startHeight; k >= 0; --k) {
				blockpos$mutableblockpos.setPos(i, k, j);
				if (!chunk.getBlockState(blockpos$mutableblockpos).isAir()) {
					if (k == 62 && chunk.getBlockState(blockpos$mutableblockpos).getBlock() != defaultFluid.getBlock()) {
						chunk.setBlockState(blockpos$mutableblockpos, defaultFluid, false);
					}
					break;
				}
			}
		}
		
		if (noise > 2.0D || noise < 1.0D) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG);
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRAVEL_CONFIG);
		}
	}
}
