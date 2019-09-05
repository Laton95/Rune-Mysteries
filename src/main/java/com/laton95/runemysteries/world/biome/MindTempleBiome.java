package com.laton95.runemysteries.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MindTempleBiome extends RuneTempleBiome {
	
	public MindTempleBiome() {
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.AIR_CONFIG).precipitation(RainType.NONE).category(Category.NONE).depth(0).scale(0).temperature(0.8F).downfall(0.4F).waterColor(4159204).waterFogColor(329011).parent(null));
	}
}
