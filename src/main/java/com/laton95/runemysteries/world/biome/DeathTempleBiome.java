package com.laton95.runemysteries.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DeathTempleBiome extends RuneTempleBiome {
	
	public DeathTempleBiome() {
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(RainType.RAIN).category(Category.NONE).depth(0).scale(0).temperature(0.2F).downfall(0.3F).waterColor(4159204).waterFogColor(329011).parent(null));
		DefaultBiomeFeatures.addPlainsTallGrass(this);
	}
}
