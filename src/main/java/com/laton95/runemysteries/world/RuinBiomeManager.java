package com.laton95.runemysteries.world;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedList;
import java.util.List;

import static com.laton95.runemysteries.RuneMysteries.MOD_ID;
import static net.minecraft.world.biome.Biome.Category.*;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RuinBiomeManager {

	private static boolean ranBiomeCheck = false;
	
	public static List<Biome> airRuinBiomes = new LinkedList<>();

	public static List<Biome> astralRuinBiomes = new LinkedList<>();

	public static List<Biome> bloodRuinBiomes = new LinkedList<>();

	public static List<Biome> bodyRuinBiomes = new LinkedList<>();

	public static List<Biome> chaosRuinBiomes = new LinkedList<>();

	public static List<Biome> cosmicRuinBiomes = new LinkedList<>();

	public static List<Biome> deathRuinBiomes = new LinkedList<>();

	public static List<Biome> earthRuinBiomes = new LinkedList<>();

	public static List<Biome> fireRuinBiomes = new LinkedList<>();

	public static List<Biome> lawRuinBiomes = new LinkedList<>();

	public static List<Biome> mindRuinBiomes = new LinkedList<>();

	public static List<Biome> natureRuinBiomes = new LinkedList<>();

	public static List<Biome> soulRuinBiomes = new LinkedList<>();

	public static List<Biome> waterRuinBiomes = new LinkedList<>();

	public static List<Biome> ouraniaRuinBiomes = new LinkedList<>();
	
	public static List<Biome> getBiomes(EnumRuneType rune) {
		switch(rune) {
			case AIR:
				return airRuinBiomes;
			case ASTRAL:
				return astralRuinBiomes;
			case BLOOD:
				return bloodRuinBiomes;
			case BODY:
				return bodyRuinBiomes;
			case CHAOS:
				return chaosRuinBiomes;
			case COSMIC:
				return cosmicRuinBiomes;
			case DEATH:
				return deathRuinBiomes;
			case EARTH:
				return earthRuinBiomes;
			case FIRE:
				return fireRuinBiomes;
			case LAW:
				return lawRuinBiomes;
			case MIND:
				return mindRuinBiomes;
			case NATURE:
				return natureRuinBiomes;
			case SOUL:
				return soulRuinBiomes;
			case WATER:
				return waterRuinBiomes;
			case OURANIA:
				return ouraniaRuinBiomes;
		}
		
		throw new IllegalArgumentException("Invalid rune type");
	}
	
	@SubscribeEvent
	public static void runBiomeCheck(final FMLCommonSetupEvent event) {
		ModLog.info("Starting biome check");
		long timer = System.currentTimeMillis();
		
		if(!ranBiomeCheck) {
			for(Biome biome : ForgeRegistries.BIOMES) {
				addAirBiome(biome);
				addAstralBiome(biome);
				addBloodBiome(biome);
				addBodyBiome(biome);
				addChaosBiome(biome);
				addCosmicBiome(biome);
				addDeathBiome(biome);
				addEarthBiome(biome);
				addFireBiome(biome);
				addLawBiome(biome);
				addMindBiome(biome);
				addNatureBiome(biome);
				addSoulBiome(biome);
				addWaterBiome(biome);
				addOuraniaBiome(biome);
			}
			
			ranBiomeCheck = true;
			
			//			ModLog.printList("Air ruin biomes: ", airRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Astral ruin biomes: ", astralRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Blood ruin biomes: ", bloodRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Body ruin biomes: ", bodyRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Chaos ruin biomes: ", chaosRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Cosmic ruin biomes: ", cosmicRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Death ruin biomes: ", deathRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Earth ruin biomes: ", earthRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Fire ruin biomes: ", fireRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Law ruin biomes: ", lawRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Mind ruin biomes: ", mindRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Nature ruin biomes: ", natureRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Soul ruin biomes: ", soulRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Water ruin biomes: ", waterRuinBiomes, b -> b.getDisplayName().getFormattedText());
			//			ModLog.printList("Ourania ruin biomes: ", ouraniaRuinBiomes, b -> b.getDisplayName().getFormattedText());
			
			ModLog.info(String.format("Biome check complete, took %d milliseconds", System.currentTimeMillis() - timer));
		}
	}
	
	private static boolean biomeInCategories(Biome biome, Category... categories) {
		for(Category category : categories) {
			if(biome.getCategory() == category) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isGenericGrassBiome(Biome biome) {
		return biomeInCategories(biome, PLAINS, FOREST, SAVANNA, TAIGA);
	}
	
	private static void addAirBiome(Biome biome) {
		if(isGenericGrassBiome(biome)) {
			airRuinBiomes.add(biome);
		}
	}
	
	private static void addAstralBiome(Biome biome) {
		if(biomeInCategories(biome, ICY)) {
			astralRuinBiomes.add(biome);
		}
	}
	
	private static void addBloodBiome(Biome biome) {
		if(biomeInCategories(biome, SWAMP)) {
			bloodRuinBiomes.add(biome);
		}
	}
	
	private static void addBodyBiome(Biome biome) {
		if(isGenericGrassBiome(biome)) {
			bodyRuinBiomes.add(biome);
		}
	}
	
	private static void addChaosBiome(Biome biome) {
		if(biomeInCategories(biome, NETHER)) {
			chaosRuinBiomes.add(biome);
		}
	}
	
	private static void addCosmicBiome(Biome biome) {
		if(biomeInCategories(biome, THEEND)) {
			cosmicRuinBiomes.add(biome);
		}
	}
	
	private static void addDeathBiome(Biome biome) {
		if(biomeInCategories(biome, EXTREME_HILLS)) {
			deathRuinBiomes.add(biome);
		}
	}
	
	private static void addEarthBiome(Biome biome) {
		if(isGenericGrassBiome(biome)) {
			earthRuinBiomes.add(biome);
		}
	}
	
	private static void addFireBiome(Biome biome) {
		if(biomeInCategories(biome, DESERT)) {
			fireRuinBiomes.add(biome);
		}
	}
	
	private static void addLawBiome(Biome biome) {
		if(isGenericGrassBiome(biome)) {
			lawRuinBiomes.add(biome);
		}
	}
	
	private static void addMindBiome(Biome biome) {
		if(isGenericGrassBiome(biome)) {
			mindRuinBiomes.add(biome);
		}
	}
	
	private static void addNatureBiome(Biome biome) {
		if(biomeInCategories(biome, JUNGLE)) {
			natureRuinBiomes.add(biome);
		}
	}
	
	private static void addSoulBiome(Biome biome) {
		if(biomeInCategories(biome, DESERT)) {
			soulRuinBiomes.add(biome);
		}
	}
	
	private static void addWaterBiome(Biome biome) {
		if(biomeInCategories(biome, SWAMP)) {
			waterRuinBiomes.add(biome);
		}
	}
	
	private static void addOuraniaBiome(Biome biome) {
		if(!biomeInCategories(biome, OCEAN, THEEND, NETHER)) {
			ouraniaRuinBiomes.add(biome);
		}
	}
}
