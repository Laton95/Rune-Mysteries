package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
	
	@SubscribeEvent
	public static void onBiomesRegistry(final RegistryEvent.Register<Biome> event) {
		ModLog.info("Registering biomes");
		
		for(Biome biome : getVanillaBiomes()) {
			switch(biome.getCategory()) {
				case NONE:
					break;
				case TAIGA:
					break;
				case EXTREME_HILLS:
					break;
				case JUNGLE:
					//TODO fix this
					//biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createCompositeFeature(ModFeature.BLACK_MONOLITH, new NoFeatureConfig(), TOP_SURFACE_WITH_CHANCE, new ChanceConfig(16)));
					break;
				case MESA:
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
					break;
				case FOREST:
					break;
				case OCEAN:
					break;
				case DESERT:
					break;
				case RIVER:
					break;
				case SWAMP:
					break;
				case MUSHROOM:
					break;
				case NETHER:
					break;
			}
		}
	}
	
	public static Biome[] getVanillaBiomes() {
		return new Biome[] {
				Biomes.OCEAN,
				Biomes.PLAINS,
				Biomes.DESERT,
				Biomes.MOUNTAINS,
				Biomes.FOREST,
				Biomes.TAIGA,
				Biomes.SWAMP,
				Biomes.RIVER,
				Biomes.NETHER,
				Biomes.THE_END,
				Biomes.FROZEN_OCEAN,
				Biomes.FROZEN_RIVER,
				Biomes.SNOWY_TUNDRA,
				Biomes.SNOWY_MOUNTAINS,
				Biomes.MUSHROOM_FIELDS,
				Biomes.MUSHROOM_FIELD_SHORE,
				Biomes.BEACH,
				Biomes.DESERT_HILLS,
				Biomes.WOODED_HILLS,
				Biomes.TAIGA_HILLS,
				Biomes.MOUNTAIN_EDGE,
				Biomes.JUNGLE,
				Biomes.JUNGLE_HILLS,
				Biomes.JUNGLE_EDGE,
				Biomes.DEEP_OCEAN,
				Biomes.STONE_SHORE,
				Biomes.SNOWY_BEACH,
				Biomes.BIRCH_FOREST,
				Biomes.BIRCH_FOREST_HILLS,
				Biomes.DARK_FOREST,
				Biomes.SNOWY_TAIGA,
				Biomes.SNOWY_TAIGA_HILLS,
				Biomes.GIANT_TREE_TAIGA,
				Biomes.GIANT_TREE_TAIGA_HILLS,
				Biomes.WOODED_MOUNTAINS,
				Biomes.SAVANNA,
				Biomes.SAVANNA_PLATEAU,
				Biomes.BADLANDS,
				Biomes.WOODED_BADLANDS_PLATEAU,
				Biomes.BADLANDS_PLATEAU,
				Biomes.SMALL_END_ISLANDS,
				Biomes.END_MIDLANDS,
				Biomes.END_HIGHLANDS,
				Biomes.END_BARRENS,
				Biomes.WARM_OCEAN,
				Biomes.LUKEWARM_OCEAN,
				Biomes.COLD_OCEAN,
				Biomes.DEEP_WARM_OCEAN,
				Biomes.DEEP_LUKEWARM_OCEAN,
				Biomes.DEEP_COLD_OCEAN,
				Biomes.DEEP_FROZEN_OCEAN,
				Biomes.THE_VOID,
				Biomes.SUNFLOWER_PLAINS,
				Biomes.DESERT_LAKES,
				Biomes.GRAVELLY_MOUNTAINS,
				Biomes.FLOWER_FOREST,
				Biomes.TAIGA_MOUNTAINS,
				Biomes.SWAMP_HILLS,
				Biomes.ICE_SPIKES,
				Biomes.MODIFIED_JUNGLE,
				Biomes.MODIFIED_JUNGLE_EDGE,
				Biomes.TALL_BIRCH_FOREST,
				Biomes.TALL_BIRCH_HILLS,
				Biomes.DARK_FOREST_HILLS,
				Biomes.SNOWY_TAIGA_MOUNTAINS,
				Biomes.GIANT_SPRUCE_TAIGA,
				Biomes.GIANT_SPRUCE_TAIGA_HILLS,
				Biomes.MODIFIED_GRAVELLY_MOUNTAINS,
				Biomes.SHATTERED_SAVANNA,
				Biomes.SHATTERED_SAVANNA_PLATEAU,
				Biomes.ERODED_BADLANDS,
				Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU,
				Biomes.MODIFIED_BADLANDS_PLATEAU,
			};
	}
	
	public static ArrayList<Biome> getBiomesWithCategories(Biome.Category... categories) {
		ArrayList<Biome> result = new ArrayList<>();
		
		for(Biome biome : getVanillaBiomes()) {
			for(Biome.Category category : categories) {
				if(biome.getCategory().equals(category)) {
					result.add(biome);
					break;
				}
			}
		}
		
		return result;
	}
	
	public static ArrayList<Biome> getBiomesWithoutCategories(Biome.Category... categories) {
		ArrayList<Biome> result = new ArrayList<>();
		
		for(Biome biome : getVanillaBiomes()) {
			for(Biome.Category category : categories) {
				if(!biome.getCategory().equals(category)) {
					result.add(biome);
					break;
				}
			}
		}
		
		return result;
	}
}
