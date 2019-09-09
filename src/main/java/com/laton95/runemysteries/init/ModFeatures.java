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
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModFeatures {
	
	public static final Structure<NoFeatureConfig> AIR_OBELISK = null;
	
	public static final Structure<NoFeatureConfig> EARTH_OBELISK = null;
	
	public static final Structure<NoFeatureConfig> FIRE_OBELISK = null;
	
	public static final Structure<NoFeatureConfig> WATER_OBELISK = null;
	
	public static final Structure<NoFeatureConfig> AIR_RUIN = null;
	
	public static final Structure<NoFeatureConfig> ASTRAL_RUIN = null;
	
	public static final Structure<NoFeatureConfig> BLOOD_RUIN = null;
	
	public static final Structure<NoFeatureConfig> BODY_RUIN = null;
	
	public static final Structure<NoFeatureConfig> CHAOS_RUIN = null;
	
	public static final Structure<NoFeatureConfig> COSMIC_RUIN = null;
	
	public static final Structure<NoFeatureConfig> DEATH_RUIN = null;
	
	public static final Structure<NoFeatureConfig> EARTH_RUIN = null;
	
	public static final Structure<NoFeatureConfig> FIRE_RUIN = null;
	
	public static final Structure<NoFeatureConfig> LAW_RUIN = null;
	
	public static final Structure<NoFeatureConfig> MIND_RUIN = null;
	
	public static final Structure<NoFeatureConfig> NATURE_RUIN = null;
	
	public static final Structure<NoFeatureConfig> SOUL_RUIN = null;
	
	public static final Structure<NoFeatureConfig> WATER_RUIN = null;
	
	public static final Structure<NoFeatureConfig> OURANIA_RUIN = null;
	
	public static final Structure<NoFeatureConfig> BLOOD_TEMPLE = null;
	
	public static final Structure<NoFeatureConfig> COSMIC_TEMPLE = null;
	
	public static final Structure<NoFeatureConfig> MIND_TEMPLE = null;
	
	public static final Structure<NoFeatureConfig> NATURE_TEMPLE = null;
	
	public static final Structure<NoFeatureConfig> WATER_TEMPLE = null;
	
	public static final Feature<NoFeatureConfig> MONOLITH = null;
	
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
		
		registerFeature(event, new MonolithFeature(NoFeatureConfig::deserialize), "monolith");
		
		registerFeature(event, new AirObeliskStructure(NoFeatureConfig::deserialize), "air_obelisk");
		registerFeature(event, new EarthObeliskStructure(NoFeatureConfig::deserialize), "earth_obelisk");
		registerFeature(event, new FireObeliskStructure(NoFeatureConfig::deserialize), "fire_obelisk");
		registerFeature(event, new WaterObeliskStructure(NoFeatureConfig::deserialize), "water_obelisk");
		
		registerFeature(event, new AirRuinStructure(NoFeatureConfig::deserialize), "air_ruin");
		registerFeature(event, new AstralRuinStructure(NoFeatureConfig::deserialize), "astral_ruin");
		registerFeature(event, new BloodRuinStructure(NoFeatureConfig::deserialize), "blood_ruin");
		registerFeature(event, new BodyRuinStructure(NoFeatureConfig::deserialize), "body_ruin");
		registerFeature(event, new ChaosRuinStructure(NoFeatureConfig::deserialize), "chaos_ruin");
		registerFeature(event, new CosmicRuinStructure(NoFeatureConfig::deserialize), "cosmic_ruin");
		registerFeature(event, new DeathRuinStructure(NoFeatureConfig::deserialize), "death_ruin");
		registerFeature(event, new EarthRuinStructure(NoFeatureConfig::deserialize), "earth_ruin");
		registerFeature(event, new FireRuinStructure(NoFeatureConfig::deserialize), "fire_ruin");
		registerFeature(event, new LawRuinStructure(NoFeatureConfig::deserialize), "law_ruin");
		registerFeature(event, new MindRuinStructure(NoFeatureConfig::deserialize), "mind_ruin");
		registerFeature(event, new NatureRuinStructure(NoFeatureConfig::deserialize), "nature_ruin");
		registerFeature(event, new SoulRuinStructure(NoFeatureConfig::deserialize), "soul_ruin");
		registerFeature(event, new WaterRuinStructure(NoFeatureConfig::deserialize), "water_ruin");
		registerFeature(event, new OuraniaRuinStructure(NoFeatureConfig::deserialize), "ourania_ruin");
		
		registerFeature(event, new BloodTempleStructure(NoFeatureConfig::deserialize), "blood_temple");
		registerFeature(event, new CosmicTempleStructure(NoFeatureConfig::deserialize), "cosmic_temple");
		registerFeature(event, new MindTempleStructure(NoFeatureConfig::deserialize), "mind_temple");
		registerFeature(event, new NatureTempleStructure(NoFeatureConfig::deserialize), "nature_temple");
		registerFeature(event, new WaterTempleStructure(NoFeatureConfig::deserialize), "water_temple");
	}
	
	private static void registerFeature(RegistryEvent.Register<Feature<?>> event, Feature<?> feature, String name) {
		feature.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(feature);
	}
	
	@SubscribeEvent
	public static void applyFeatures(FMLCommonSetupEvent event) {
		ModLog.info("Applying features to biomes");
		
		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
			switch(biome.getCategory()) {
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
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, SOUL_RUIN);
				addStructure(biome, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_RUIN);
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, OURANIA_RUIN);
				
				biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.RUNE_ESSENCE_BLOCK.getDefaultState(), 5), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
			}
			
			if(biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE) {
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FIRE_OBELISK);
			}
			
			if(biome.getCategory() != Biome.Category.OCEAN && biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE) {
				addStructure(biome, GenerationStage.Decoration.UNDERGROUND_STRUCTURES, EARTH_OBELISK);
			}
		}
		
		addTempleStructure((RuneTempleBiome) ModBiomes.BLOOD_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, BLOOD_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.COSMIC_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, COSMIC_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.MIND_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, MIND_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.NATURE_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, NATURE_TEMPLE);
		addTempleStructure((RuneTempleBiome) ModBiomes.WATER_TEMPLE, GenerationStage.Decoration.SURFACE_STRUCTURES, WATER_TEMPLE);
	}
	
	private static void addStructure(Biome biome, GenerationStage.Decoration stage, Structure structure) {
		biome.addFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.addStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
	}
	
	private static void addTempleStructure(RuneTempleBiome biome, GenerationStage.Decoration stage, Structure structure) {
		biome.actuallyAddFeature(stage, Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		biome.actuallyAddStructure(structure, IFeatureConfig.NO_FEATURE_CONFIG);
	}
}
