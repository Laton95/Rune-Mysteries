package com.laton95.runemysteries.handler;

import java.io.File;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	public static Configuration configuration;

	public static void init(File configFile) {
		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
			loadConfiguration();
		}
	}

	private static void loadConfiguration() {
		try {
			configuration.load();

			// exampleValue = configuration.get(Configuration.CATEGORY_GENERAL, "exampleValue", true, "Example config.").getBoolean(true);
			Config.generateFiniteEssence = configuration.get("World Generation", "generateFiniteEssence", true, "Generate finite essence veins in world:").getBoolean(true);
			Config.finiteEssenceVeinSize = configuration.get("World Generation", "finiteEssenceVeinSize", 8, "Size of finite essence veins:").getInt(8);
			Config.finiteEssenceVeinsPerChunk = configuration.get("World Generation", "finiteEssenceVeinsPerChunk", 6, "Number of finite essence veins per chunk:").getInt(6);
			Config.finiteEssenceMaxHeight = configuration.get("World Generation", "finiteEssenceMaxHeight", 60, "Maximum height of finite essence veins:").getInt(60);
			Config.finiteEssenceMinHeight = configuration.get("World Generation", "finiteEssenceMinHeight", 10, "Minimum height of finite essence veins:").getInt(10);
			Config.generateRuneAltars = configuration.get("World Generation", "generateRuneAltars", true, "Generate rune altars in world:").getBoolean(true);
		} catch (Exception e) {

		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}
	}
}
