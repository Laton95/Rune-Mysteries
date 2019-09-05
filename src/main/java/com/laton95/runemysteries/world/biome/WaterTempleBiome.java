package com.laton95.runemysteries.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class WaterTempleBiome extends RuneTempleBiome {
	
	public WaterTempleBiome() {
		super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_CONFIG).precipitation(RainType.NONE).category(Category.NONE).depth(-1.8F).scale(0.0F).temperature(0.5F).downfall(0.5F).waterColor(4445678).waterFogColor(270131).parent(null));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(48, 0.8D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(), new BlockState[] {Blocks.STONE.getDefaultState()}, new BlockState[] {Blocks.WATER.getDefaultState()}, new BlockState[] {Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F)));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.KELP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(120, 80.0D, 0.0D, net.minecraft.world.gen.Heightmap.Type.OCEAN_FLOOR_WG)));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SingleRandomFeature(new Feature[] {Feature.CORAL_TREE, Feature.CORAL_CLAW, Feature.CORAL_MUSHROOM}, new IFeatureConfig[] {
				IFeatureConfig.NO_FEATURE_CONFIG,
				IFeatureConfig.NO_FEATURE_CONFIG,
				IFeatureConfig.NO_FEATURE_CONFIG
		}), Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(20, 400.0D, 0.0D, net.minecraft.world.gen.Heightmap.Type.OCEAN_FLOOR_WG)));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SEA_PICKLE, new CountConfig(20), Placement.CHANCE_TOP_SOLID_HEIGHTMAP, new ChanceConfig(16)));
		
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.SQUID, 1, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.COD, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.SALMON, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.TROPICAL_FISH, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.DOLPHIN, 1, 1, 2));
	}
}
