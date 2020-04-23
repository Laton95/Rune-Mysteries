package com.laton95.runemysteries.world.biome;

import com.laton95.runemysteries.init.ModFeatures;
import com.laton95.runemysteries.world.gen.feature.structure.temple.RuneTempleStructure;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public abstract class RuneTempleBiome extends Biome {
	
	public RuneTempleBiome(Builder biomeBuilder) {
		super(biomeBuilder);
	}
	
	@Override
	public <C extends ICarverConfig> void addCarver(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
	}
	
	@Override
	protected void addSpawn(EntityClassification type, SpawnListEntry spawnListEntry) {
		if(type.getPeacefulCreature()) {
			super.addSpawn(type, spawnListEntry);
		}
	}
	
	@Override
	public void addFeature(GenerationStage.Decoration stage, ConfiguredFeature<?, ?> feature) {
		if(ModFeatures.isVanillaFeature(feature.feature) || feature.feature instanceof RuneTempleStructure) {
			super.addFeature(stage, feature);
		}
	}
	
	@Override
	public <C extends IFeatureConfig> void addStructure(ConfiguredFeature<C, ? extends Structure<C>> structure) {
		if(structure.feature instanceof RuneTempleStructure) {
			super.addStructure(structure);
		}
	}
}
