package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.world.biome.RuneTempleBiome;
import com.laton95.runemysteries.world.gen.feature.MonolithFeature;
import com.laton95.runemysteries.world.gen.feature.structure.altar.*;
import com.laton95.runemysteries.world.gen.feature.structure.obelisk.*;
import com.laton95.runemysteries.world.gen.feature.structure.temple.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {
	
	public static Structure<NoFeatureConfig> AIR_OBELISK;
	
	public static Structure<NoFeatureConfig> EARTH_OBELISK;
	
	public static Structure<NoFeatureConfig> FIRE_OBELISK;
	
	public static Structure<NoFeatureConfig> WATER_OBELISK;
	
	public static Structure<NoFeatureConfig> AIR_RUIN;
	
	public static Structure<NoFeatureConfig> ASTRAL_RUIN;
	
	public static Structure<NoFeatureConfig> BLOOD_RUIN;
	
	public static Structure<NoFeatureConfig> BODY_RUIN;
	
	public static Structure<NoFeatureConfig> CHAOS_RUIN;
	
	public static Structure<NoFeatureConfig> COSMIC_RUIN;
	
	public static Structure<NoFeatureConfig> DEATH_RUIN;
	
	public static Structure<NoFeatureConfig> EARTH_RUIN;
	
	public static Structure<NoFeatureConfig> FIRE_RUIN;
	
	public static Structure<NoFeatureConfig> LAW_RUIN;
	
	public static Structure<NoFeatureConfig> MIND_RUIN;
	
	public static Structure<NoFeatureConfig> NATURE_RUIN;
	
	public static Structure<NoFeatureConfig> SOUL_RUIN;
	
	public static Structure<NoFeatureConfig> WATER_RUIN;
	
	public static Structure<NoFeatureConfig> OURANIA_RUIN;
	
	public static Structure<NoFeatureConfig> BLOOD_TEMPLE;
	
	public static Structure<NoFeatureConfig> COSMIC_TEMPLE;
	
	public static Structure<NoFeatureConfig> MIND_TEMPLE;
	
	public static Structure<NoFeatureConfig> NATURE_TEMPLE;
	
	public static Structure<NoFeatureConfig> WATER_TEMPLE;
	
	public static Feature<NoFeatureConfig> MONOLITH;
	
	public static IStructurePieceType OBELISK;
	
	public static IStructurePieceType SURFACE_RUIN;
	
	public static IStructurePieceType UNDERGROUND_RUIN;
	
	public static IStructurePieceType ISLAND_RUIN;
	
	public static IStructurePieceType WELL_RUIN;
	
	public static IStructurePieceType RUNE_TEMPLE;
	
	@SubscribeEvent
	public static void onFeaturesRegistry(RegistryEvent.Register<Feature<?>> event) {
		ModLog.info("Registering features");
		
		OBELISK = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:obelisk", ObeliskPieces.Piece::new);
		SURFACE_RUIN = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:surface_ruin", SurfaceRuinPieces.Piece::new);
		UNDERGROUND_RUIN = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:underground_ruin", UndergroundRuinPieces.Piece::new);
		ISLAND_RUIN = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:island_ruin", IslandRuinPieces.Piece::new);
		WELL_RUIN = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:well_ruin", SoulRuinPieces.Piece::new);
		RUNE_TEMPLE = Registry.register(Registry.STRUCTURE_PIECE, "runemysteries:rune_temple", RuneTemplePieces.Piece::new);
		
		MONOLITH = new MonolithFeature(NoFeatureConfig::deserialize);
		registerFeature(event, MONOLITH, "monolith");
		
		AIR_OBELISK = new AirObeliskStructure(NoFeatureConfig::deserialize);
		registerFeature(event, AIR_OBELISK, "air_obelisk");
		
		EARTH_OBELISK = new EarthObeliskStructure(NoFeatureConfig::deserialize);
		registerFeature(event, EARTH_OBELISK, "earth_obelisk");
		
		FIRE_OBELISK = new FireObeliskStructure(NoFeatureConfig::deserialize);
		registerFeature(event, FIRE_OBELISK, "fire_obelisk");
		
		WATER_OBELISK = new WaterObeliskStructure(NoFeatureConfig::deserialize);
		registerFeature(event, WATER_OBELISK, "water_obelisk");
		
		AIR_RUIN = new AirRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, AIR_RUIN, "air_ruin");
		
		ASTRAL_RUIN = new AstralRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, ASTRAL_RUIN, "astral_ruin");
		
		BLOOD_RUIN = new BloodRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, BLOOD_RUIN, "blood_ruin");
		
		BODY_RUIN = new BodyRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, BODY_RUIN, "body_ruin");
		
		CHAOS_RUIN = new ChaosRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, CHAOS_RUIN, "chaos_ruin");
		
		COSMIC_RUIN = new CosmicRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, COSMIC_RUIN, "cosmic_ruin");
		
		DEATH_RUIN = new DeathRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, DEATH_RUIN, "death_ruin");
		
		EARTH_RUIN = new EarthRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, EARTH_RUIN, "earth_ruin");
		
		FIRE_RUIN = new FireRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, FIRE_RUIN, "fire_ruin");
		
		LAW_RUIN = new LawRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, LAW_RUIN, "law_ruin");
		
		MIND_RUIN = new MindRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, MIND_RUIN, "mind_ruin");
		
		NATURE_RUIN = new NatureRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, NATURE_RUIN, "nature_ruin");
		
		SOUL_RUIN = new SoulRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, SOUL_RUIN, "soul_ruin");
		
		WATER_RUIN = new WaterRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, WATER_RUIN, "water_ruin");
		
		OURANIA_RUIN = new OuraniaRuinStructure(NoFeatureConfig::deserialize);
		registerFeature(event, OURANIA_RUIN, "ourania_ruin");
		
		BLOOD_TEMPLE = new BloodTempleStructure(NoFeatureConfig::deserialize);
		registerFeature(event, BLOOD_TEMPLE, "blood_temple");
		
		COSMIC_TEMPLE = new CosmicTempleStructure(NoFeatureConfig::deserialize);
		registerFeature(event, COSMIC_TEMPLE, "cosmic_temple");
		
		MIND_TEMPLE = new MindTempleStructure(NoFeatureConfig::deserialize);
		registerFeature(event, MIND_TEMPLE, "mind_temple");
		
		NATURE_TEMPLE = new NatureTempleStructure(NoFeatureConfig::deserialize);
		registerFeature(event, NATURE_TEMPLE, "nature_temple");
		
		WATER_TEMPLE = new WaterTempleStructure(NoFeatureConfig::deserialize);
		registerFeature(event, WATER_TEMPLE, "water_temple");
		
		applyFeatures();
	}
	
	private static void registerFeature(RegistryEvent.Register<Feature<?>> event, Feature<?> feature, String name) {
		feature.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(feature);
		if(feature instanceof Structure) {
			Feature.STRUCTURES.put("runemysteries:" + name, (Structure<?>) feature);
			Registry.register(Registry.STRUCTURE_FEATURE, "runemysteries:" + name, (Structure<?>) feature);
		}
	}
	
	private static void applyFeatures() {
		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
			switch(biome.getCategory()){
				case NONE:
					break;
				case TAIGA:
					break;
				case EXTREME_HILLS:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, AIR_OBELISK);
					break;
				case JUNGLE:
					biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.MONOLITH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(100)));
					break;
				case MESA:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, AIR_OBELISK);
					break;
				case PLAINS:
					break;
				case SAVANNA:
					break;
				case ICY:
					break;
				case THEEND:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, COSMIC_RUIN);
					break;
				case BEACH:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_OBELISK);
					break;
				case FOREST:
					break;
				case OCEAN:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_OBELISK);
					break;
				case DESERT:
					break;
				case RIVER:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_OBELISK);
					break;
				case SWAMP:
					addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_OBELISK);
					break;
				case MUSHROOM:
					break;
				case NETHER:
					addStructure(biome, GenerationStage.Decoration.UNDERGROUND_DECORATION, CHAOS_RUIN);
					break;
			}
			
			if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE) {
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, AIR_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, ASTRAL_RUIN);
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, BLOOD_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, BODY_RUIN);
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, DEATH_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, EARTH_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, FIRE_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, LAW_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, MIND_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, NATURE_RUIN);
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, SOUL_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_RUIN);
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, OURANIA_RUIN);
				
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.RUNE_ESSENCE_BLOCK.getDefaultState(), 5), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
			}
			
			if(biome.getCategory() != Biome.Category.THEEND) {
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FIRE_OBELISK);
			}
			
			if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, EARTH_OBELISK);
			}
		}
		
		addTempleStructure((RuneTempleBiome) ModBiomes.BLOOD_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, BLOOD_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.COSMIC_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, COSMIC_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.MIND_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, MIND_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.NATURE_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, NATURE_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.WATER_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_TEMPLE);
	}
	
	private static void addStructure(Biome biome, GenerationStage.Decoration stage, Structure structure){
		biome.addFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
	}
	
	private static void addTempleStructure(RuneTempleBiome biome, GenerationStage.Decoration stage, Structure structure){
		biome.actuallyAddFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
	}
}
