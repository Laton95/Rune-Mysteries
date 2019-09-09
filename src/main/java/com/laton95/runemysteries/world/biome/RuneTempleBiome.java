package com.laton95.runemysteries.world.biome;

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
	
	public void actuallyAddFeature(GenerationStage.Decoration state, ConfiguredFeature<?> feature) {
		super.addFeature(state, feature);
	}
	
	public <C extends IFeatureConfig> void actuallyAddStructure(Structure<C> structure, C config) {
		super.addStructure(structure, config);
	}
	
	@Override
	public <C extends ICarverConfig> void addCarver(GenerationStage.Carving stage, ConfiguredCarver<C> carver) {
		//Stops any carvers being added that I don't want to be
	}
	
	@Override
	public void addFeature(GenerationStage.Decoration state, ConfiguredFeature<?> feature) {
		//Stops any features being added that I don't want to be
	}
	
	@Override
	public <C extends IFeatureConfig> void addStructure(Structure<C> structure, C config) {
		//Stops any structures being added that I don't want to be
	}
}
