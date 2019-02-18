package com.laton95.runemysteries.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
	
	public static final ServerConfig SERVER;
	
	public static final ForgeConfigSpec SERVER_SPEC;
	
	static {
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		SERVER_SPEC = specPair.getRight();
		SERVER = specPair.getLeft();
	}
	
	public static int battlestaffDurability = 500;
	
	public static void load() {
		battlestaffDurability = SERVER.battleStaffDurability.get();
	}
	
	public static class ServerConfig {
		
		public ForgeConfigSpec.IntValue battleStaffDurability;
		
		ServerConfig(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			battleStaffDurability = builder
					.comment("How many runes a newly made battlestaff can provide")
					.translation("text.runemysteries.config.battlestaff_durability")
					.defineInRange("battlestaff_durability", 500, 1, Integer.MAX_VALUE - 1);
			builder.pop();
		}
	}
}
