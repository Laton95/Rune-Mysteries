package com.laton95.runemysteries.config;

import com.laton95.runemysteries.util.ModLog;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
	
	public static final ServerConfig SERVER;
	
	public static final ForgeConfigSpec SERVER_SPEC;
	
	//TODO configs to add: ore rarity
	//TODO future configs: drop talismans, spawn teleport scrolls, spawn villager building, disable villager entirely?
	
	static {
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		SERVER_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}
	
	public static int battlestaffDurability = 500;
	
	public static boolean generateRuins = true;
	
	public static boolean generateEssence = true;
	
	public static boolean generateObelisks = true;
	
	public static boolean generateMonoliths = true;
	
	public static int airObeliskDistance = 32;
	
	public static int airObeliskSeparation = 1;
	
	public static int earthObeliskDistance = 32;
	
	public static int earthObeliskSeparation = 1;
	
	public static int fireObeliskDistance = 32;
	
	public static int fireObeliskSeparation = 1;
	
	public static int waterObeliskDistance = 32;
	
	public static int waterObeliskSeparation = 1;
	
	public static int ruinTries = 100;
	
	public static int ruinMinRange = 500;
	
	public static int ruinMaxRange = 5000;
	
	public static int stonetoucherDuration = 120;
	
	public static void load() {
		ModLog.info("Loading config");
		battlestaffDurability = SERVER.battlestaffDurability.get();
		generateRuins = SERVER.generateRuins.get();
		generateEssence = SERVER.generateEssence.get();
		generateObelisks = SERVER.generateObelisks.get();
		generateMonoliths = SERVER.generateMonoliths.get();
		airObeliskDistance = SERVER.airObeliskDistance.get();
		airObeliskSeparation = SERVER.airObeliskSeparation.get();
		earthObeliskDistance = SERVER.earthObeliskDistance.get();
		earthObeliskSeparation = SERVER.earthObeliskSeparation.get();
		fireObeliskDistance = SERVER.fireObeliskDistance.get();
		fireObeliskSeparation = SERVER.fireObeliskSeparation.get();
		waterObeliskDistance = SERVER.waterObeliskDistance.get();
		waterObeliskSeparation = SERVER.waterObeliskSeparation.get();
		ruinTries = SERVER.ruinTries.get();
		ruinMinRange = SERVER.ruinMinRange.get();
		ruinMaxRange = SERVER.ruinMaxRange.get();
		stonetoucherDuration = SERVER.stonetoucherDuration.get();
	}
	
	public static class ServerConfig {
		
		public ForgeConfigSpec.IntValue battlestaffDurability;
		
		public ForgeConfigSpec.BooleanValue generateRuins;
		
		public ForgeConfigSpec.BooleanValue generateEssence;
		
		public ForgeConfigSpec.BooleanValue generateObelisks;
		
		public ForgeConfigSpec.BooleanValue generateMonoliths;
		
		public ForgeConfigSpec.IntValue airObeliskDistance;
		
		public ForgeConfigSpec.IntValue airObeliskSeparation;
		
		public ForgeConfigSpec.IntValue earthObeliskDistance;
		
		public ForgeConfigSpec.IntValue earthObeliskSeparation;
		
		public ForgeConfigSpec.IntValue fireObeliskDistance;
		
		public ForgeConfigSpec.IntValue fireObeliskSeparation;
		
		public ForgeConfigSpec.IntValue waterObeliskDistance;
		
		public ForgeConfigSpec.IntValue waterObeliskSeparation;
		
		public ForgeConfigSpec.IntValue ruinTries;
		
		public ForgeConfigSpec.IntValue ruinMinRange;
		
		public ForgeConfigSpec.IntValue ruinMaxRange;
		
		public ForgeConfigSpec.IntValue stonetoucherDuration;
		
		private static final int readableMaximum = 2000000000;
		
		ServerConfig(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			battlestaffDurability = builder
					.comment("Maximum durability of a battlestaff")
					.defineInRange("battlestaff_durability", 500, 1, readableMaximum);
			stonetoucherDuration = builder
					.comment("Duration of stonetoucher effect (in seconds)")
					.defineInRange("stonetoucher_duration", 120, 1, readableMaximum);
			builder.pop();
			
			builder.push("world_generation");
			generateEssence = builder
					.comment("Whether to generate rune essence ore outside the mine")
					.define("generate_essence", true);
			generateMonoliths = builder
					.comment("Whether to generate black monoliths")
					.define("generate_monoliths", true);
			builder.push("ruins");
			generateRuins = builder
					.comment("Whether to generate ruins (WARNING: turning this off in a normal Minecraft world will make much of the mod inaccessible)")
					.define("generate_ruins", true);
			ruinTries = builder
					.comment("Number of attempts made on world creation to find a suitable location for a ruin. High values can potentially lead to longer world creation times")
					.defineInRange("ruin_tries", 100, 1, 100000);
			ruinMinRange = builder
					.comment("Minimum distance of ruins from world origin (in blocks)")
					.defineInRange("ruin_min_range", 500, 100, readableMaximum);
			ruinMaxRange = builder
					.comment("Maximum distance of ruins from world origin (in blocks)")
					.defineInRange("ruin_max_range", 5000, 500, readableMaximum);
			builder.pop();
			builder.push("obelisks");
			generateObelisks = builder
					.comment("Whether to generate obelisks")
					.define("generate_obelisks", true);
			airObeliskDistance = builder
					.comment("Average distance between air obelisks (in chunks)")
					.defineInRange("air_obelisk_distance", 32, 1, 1000);
			airObeliskSeparation = builder
					.comment("Minimum distance between air obelisks (in chunks)")
					.defineInRange("air_obelisk_separation", 1, 1, 1000);
			earthObeliskDistance = builder
					.comment("Average distance between earth obelisks (in chunks)")
					.defineInRange("earth_obelisk_distance", 32, 1, 1000);
			earthObeliskSeparation = builder
					.comment("Minimum distance between earth obelisks (in chunks)")
					.defineInRange("earth_obelisk_separation", 1, 1, 1000);
			fireObeliskDistance = builder
					.comment("Average distance between fire obelisks (in chunks)")
					.defineInRange("fire_obelisk_distance", 32, 1, 1000);
			fireObeliskSeparation = builder
					.comment("Minimum distance between fire obelisks (in chunks)")
					.defineInRange("fire_obelisk_separation", 1, 1, 1000);
			waterObeliskDistance = builder
					.comment("Average distance between water obelisks (in chunks)")
					.defineInRange("water_obelisk_distance", 32, 1, 1000);
			waterObeliskSeparation = builder
					.comment("Minimum distance between water obelisks (in chunks)")
					.defineInRange("water_obelisk_separation", 1, 1, 1000);
			builder.pop();
			builder.pop();
		}
	}
}
