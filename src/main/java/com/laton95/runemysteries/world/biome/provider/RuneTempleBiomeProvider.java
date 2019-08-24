package com.laton95.runemysteries.world.biome.provider;

import com.laton95.runemysteries.world.gen.feature.structure.temple.RuneTempleStructure;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.feature.structure.Structure;

public class RuneTempleBiomeProvider extends SingleBiomeProvider {
	
	public RuneTempleBiomeProvider(SingleBiomeProviderSettings settings) {
		super(settings);
	}
	
	@Override
	public boolean hasStructure(Structure<?> structure) {
		return structure instanceof RuneTempleStructure;
	}
}
