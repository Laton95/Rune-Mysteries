package com.laton95.runemysteries.world.biome;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class WaterTempleBiome extends RuneTempleBiome {
	
	public WaterTempleBiome() {
		super((new Builder()).surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG).precipitation(RainType.NONE).category(Category.NONE).depth(-3F).scale(0.1F).temperature(0.5F).downfall(0).waterColor(4159204).waterFogColor(329011).parent(null));
		this.addStructure(Feature.SHIPWRECK, new ShipwreckConfig(false));
		this.addStructure(Feature.OCEAN_RUIN, new OceanRuinConfig(net.minecraft.world.gen.feature.structure.OceanRuinStructure.Type.COLD, 0.3F, 0.9F));
		actuallyAddFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.SHIPWRECK, new ShipwreckConfig(false), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		actuallyAddFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(Feature.OCEAN_RUIN, new OceanRuinConfig(net.minecraft.world.gen.feature.structure.OceanRuinStructure.Type.COLD, 0.3F, 0.9F), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(Feature.SEAGRASS, new SeaGrassConfig(48, 0.8D), Placement.TOP_SOLID_HEIGHTMAP, IPlacementConfig.NO_PLACEMENT_CONFIG));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.SIMPLE_BLOCK, new BlockWithContextConfig(Blocks.SEAGRASS.getDefaultState(), new BlockState[]{Blocks.STONE.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}, new BlockState[]{Blocks.WATER.getDefaultState()}), Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.1F)));
		actuallyAddFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.KELP, IFeatureConfig.NO_FEATURE_CONFIG, Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED, new TopSolidWithNoiseConfig(120, 80.0D, 0.0D, net.minecraft.world.gen.Heightmap.Type.OCEAN_FLOOR_WG)));
		
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.SQUID, 1, 1, 4));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.COD, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.SALMON, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.TROPICAL_FISH, 10, 3, 6));
		this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(EntityType.DOLPHIN, 1, 1, 2));
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.DROWNED, 5, 1, 1));
	}
}
