package com.laton95.runemysteries.world.biome;

import com.laton95.runemysteries.world.gen.surfacebuilders.FireTempleSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class FireTempleBiome extends RuneTempleBiome {
	
	public FireTempleBiome() {
		super((new Builder()).surfaceBuilder(new FireTempleSurfaceBuilder(SurfaceBuilderConfig::deserialize), SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG).precipitation(RainType.NONE).category(Category.NONE).depth(-0.3F).scale(0.1F).temperature(2.0F).downfall(0).waterColor(4159204).waterFogColor(329011).parent(null));
	}
}
