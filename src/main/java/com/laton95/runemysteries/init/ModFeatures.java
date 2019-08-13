package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.world.gen.feature.MonolithFeature;
import com.laton95.runemysteries.world.gen.feature.structure.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	
	public static Structure<NoFeatureConfig> AIR_OBELISK;
	
	public static Structure<NoFeatureConfig> EARTH_OBELISK;
	
	public static Structure<NoFeatureConfig> FIRE_OBELISK;
	
	public static Structure<NoFeatureConfig> WATER_OBELISK;
	
	public static Feature<NoFeatureConfig> MONOLITH;
	
	public static IStructurePieceType OBSK;
	
	@SubscribeEvent
	public static void onFeaturesRegistry(RegistryEvent.Register<Feature<?>> event) {
		ModLog.info("Registering features");
		
		MONOLITH = new MonolithFeature(NoFeatureConfig::deserialize);
		MONOLITH.setRegistryName(RuneMysteries.MOD_ID, "monolith");
		event.getRegistry().register(MONOLITH);
		
		OBSK = Registry.register(Registry.STRUCTURE_PIECE, "obelisk", BaseObeliskPieces.Piece::new);
		
		AIR_OBELISK = new AirObeliskStructure(NoFeatureConfig::deserialize);
		AIR_OBELISK.setRegistryName(RuneMysteries.MOD_ID, "air_obelisk");
		event.getRegistry().register(AIR_OBELISK);
		Feature.STRUCTURES.put("air_obelisk", AIR_OBELISK);
		Registry.register(Registry.STRUCTURE_FEATURE, "air_obelisk", AIR_OBELISK);
		
		EARTH_OBELISK = new EarthObeliskStructure(NoFeatureConfig::deserialize);
		EARTH_OBELISK.setRegistryName(RuneMysteries.MOD_ID, "earth_obelisk");
		event.getRegistry().register(EARTH_OBELISK);
		Feature.STRUCTURES.put("earth_obelisk", EARTH_OBELISK);
		Registry.register(Registry.STRUCTURE_FEATURE, "earth_obelisk", EARTH_OBELISK);
		
		FIRE_OBELISK = new FireObeliskStructure(NoFeatureConfig::deserialize);
		FIRE_OBELISK.setRegistryName(RuneMysteries.MOD_ID, "fire_obelisk");
		event.getRegistry().register(FIRE_OBELISK);
		Feature.STRUCTURES.put("fire_obelisk", FIRE_OBELISK);
		Registry.register(Registry.STRUCTURE_FEATURE, "fire_obelisk", FIRE_OBELISK);
		
		WATER_OBELISK = new WaterObeliskStructure(NoFeatureConfig::deserialize);
		WATER_OBELISK.setRegistryName(RuneMysteries.MOD_ID, "water_obelisk");
		event.getRegistry().register(WATER_OBELISK);
		Feature.STRUCTURES.put("water_obelisk", WATER_OBELISK);
		Registry.register(Registry.STRUCTURE_FEATURE, "water_obelisk", WATER_OBELISK);
		
		applyFeatures();
	}
	
	private static List<Biome> airBiomes = new LinkedList<>();
	private static List<Biome> earthBiomes = new LinkedList<>();
	private static List<Biome> fireBiomes = new LinkedList<>();
	private static List<Biome> waterBiomes = new LinkedList<>();
	
	
	private static void applyFeatures() {
		
		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
			switch(biome.getCategory()){
				case NONE:
					break;
				case TAIGA:
					break;
				case EXTREME_HILLS:
					addAirObelisk(biome);
					break;
				case JUNGLE:
					biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.MONOLITH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(100)));
					break;
				case MESA:
					addAirObelisk(biome);
					break;
				case PLAINS:
					break;
				case SAVANNA:
					break;
				case ICY:
					break;
				case THEEND:
					break;
				case BEACH:
					addWaterObelisk(biome);
					break;
				case FOREST:
					break;
				case OCEAN:
					addWaterObelisk(biome);
					break;
				case DESERT:
					break;
				case RIVER:
					addWaterObelisk(biome);
					break;
				case SWAMP:
					addWaterObelisk(biome);
					break;
				case MUSHROOM:
					break;
				case NETHER:
					break;
			}
			
			addFireObelisk(biome);
			
			if(biome.getCategory() != Biome.Category.OCEAN) {
				addEarthObelisk(biome);
			}
		}
		
		StringBuilder airBiomeOutput = new StringBuilder("Air obelisk biomes: \n");
		for(Biome biome : airBiomes) {
			airBiomeOutput.append(biome.getDisplayName().getFormattedText()).append("\n");
		}
		ModLog.info(airBiomeOutput.toString());
		
		StringBuilder earthBiomeOutput = new StringBuilder("Earth obelisk biomes: \n");
		for(Biome biome : earthBiomes) {
			earthBiomeOutput.append(biome.getDisplayName().getFormattedText()).append("\n");
		}
		ModLog.info(earthBiomeOutput.toString());
		
		StringBuilder fireBiomeOutput = new StringBuilder("Fire obelisk biomes: \n");
		for(Biome biome : fireBiomes) {
			fireBiomeOutput.append(biome.getDisplayName().getFormattedText()).append("\n");
		}
		ModLog.info(fireBiomeOutput.toString());
		
		StringBuilder waterBiomeOutput = new StringBuilder("Water obelisk biomes: \n");
		for(Biome biome : waterBiomes) {
			waterBiomeOutput.append(biome.getDisplayName().getFormattedText()).append("\n");
		}
		ModLog.info(waterBiomeOutput.toString());
		
	}
	
	private static void addAirObelisk(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(AIR_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(AIR_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG);
		airBiomes.add(biome);
	}
	
	private static void addEarthObelisk(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(EARTH_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(EARTH_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG);
		earthBiomes.add(biome);
	}
	
	private static void addFireObelisk(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(FIRE_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(FIRE_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG);
		fireBiomes.add(biome);
	}
	
	private static void addWaterObelisk(Biome biome) {
		biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(WATER_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(WATER_OBELISK, IFeatureConfig.NO_FEATURE_CONFIG);
		waterBiomes.add(biome);
	}
}
