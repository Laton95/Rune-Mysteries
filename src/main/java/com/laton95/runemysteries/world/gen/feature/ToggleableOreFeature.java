package com.laton95.runemysteries.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ToggleableOreFeature extends OreFeature {
	
	public ToggleableOreFeature(Function<Dynamic<?>, ? extends OreFeatureConfig> config) {
		super(config);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, OreFeatureConfig config) {
		if(((ToggleableOreFeatureConfig) config).generate.get()) {
			return super.place(worldIn, generator, rand, pos, config);
		}
		return false;
	}
}
