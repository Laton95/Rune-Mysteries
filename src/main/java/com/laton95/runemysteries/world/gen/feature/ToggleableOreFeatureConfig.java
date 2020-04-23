package com.laton95.runemysteries.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.function.Supplier;

public class ToggleableOreFeatureConfig extends OreFeatureConfig {
	
	public final Supplier<Boolean> generate;
	
	public ToggleableOreFeatureConfig(FillerBlockType target, BlockState state, int size, Supplier<Boolean> generate) {
		super(target, state, size);
		this.generate = generate;
	}
}
