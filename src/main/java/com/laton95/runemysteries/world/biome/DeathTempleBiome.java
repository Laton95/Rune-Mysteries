package com.laton95.runemysteries.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DeathTempleBiome extends RuneTempleBiome {
	
	public DeathTempleBiome() {
		super((new Biome.Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.AIR_CONFIG).precipitation(RainType.NONE).category(Category.NONE).depth(0).scale(0).temperature(0).downfall(0).waterColor(4159204).waterFogColor(329011).parent(null));
	}
}
