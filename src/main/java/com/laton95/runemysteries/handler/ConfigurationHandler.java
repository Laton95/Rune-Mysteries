package com.laton95.runemysteries.handler;

import java.io.File;

import com.laton95.runemysteries.reference.ConfigReference;
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

			// exampleValue = configuration.get(Configuration.CATEGORY_GENERAL,
			// "exampleValue", true, "Example config.").getBoolean(true);
			ConfigReference.generateFiniteEssence = configuration
					.get("World Generation", "generateFiniteEssence", true, "Generate finite essence veins in world:")
					.getBoolean(true);
			ConfigReference.finiteEssenceVeinSize = configuration
					.get("World Generation", "finiteEssenceVeinSize", 8, "Size of finite essence veins:").getInt(8);
			ConfigReference.finiteEssenceVeinsPerChunk = configuration.get("World Generation",
					"finiteEssenceVeinsPerChunk", 6, "Number of finite essence veins per chunk:").getInt(6);
			ConfigReference.finiteEssenceMaxHeight = configuration
					.get("World Generation", "finiteEssenceMaxHeight", 60, "Maximum height of finite essence veins:")
					.getInt(60);
			ConfigReference.finiteEssenceMinHeight = configuration
					.get("World Generation", "finiteEssenceMinHeight", 10, "Minimum height of finite essence veins:")
					.getInt(10);

			ConfigReference.generateRuneAltars = configuration
					.get("World Generation", "generateRuneAltars", true, "Generate rune altars in world:")
					.getBoolean(true);
			ConfigReference.maxRuneAltarRange = configuration
					.get("World Generation", "maxRuneAltarRange", 5000, "Maximum distance of rune altars from 0,0:")
					.getInt(5000);
			ConfigReference.minRuneAltarRange = configuration
					.get("World Generation", "minRuneAltarRange", 0, "Minimum distance of rune altars from 0,0:")
					.getInt(0);
			ConfigReference.runeAltarTries = configuration
					.get("World Generation", "runeAltarTries", 200,
							"Number of chunks the world generator searches to find the correct biome for rune altars, insanely high numbers will probably have an impact on performance at world creation:")
					.getInt(200);

			ConfigReference.airAltarDimensionID = configuration
					.get("Dimension ID's", "airAltar", 169, "Dimension ID for the air altar.").getInt(169);
			ConfigReference.astralAltarDimensionID = configuration
					.get("Dimension ID's", "astralAltar", 170, "Dimension ID for the astral altar.").getInt(170);
			ConfigReference.bloodAltarDimensionID = configuration
					.get("Dimension ID's", "bloodAltar", 171, "Dimension ID for the blood altar.").getInt(171);
			ConfigReference.bodyAltarDimensionID = configuration
					.get("Dimension ID's", "bodyAltar", 172, "Dimension ID for the body altar.").getInt(172);
			ConfigReference.chaosAltarDimensionID = configuration
					.get("Dimension ID's", "chaosAltar", 173, "Dimension ID for the chaos altar.").getInt(173);
			ConfigReference.cosmicAltarDimensionID = configuration
					.get("Dimension ID's", "cosmicAltar", 174, "Dimension ID for the cosmic altar.").getInt(174);
			ConfigReference.deathAltarDimensionID = configuration
					.get("Dimension ID's", "deathAltar", 175, "Dimension ID for the death altar.").getInt(175);
			ConfigReference.earthAltarDimensionID = configuration
					.get("Dimension ID's", "earthAltar", 176, "Dimension ID for the earth altar.").getInt(176);
			ConfigReference.fireAltarDimensionID = configuration
					.get("Dimension ID's", "fireAltar", 177, "Dimension ID for the fire altar.").getInt(177);
			ConfigReference.lawAltarDimensionID = configuration
					.get("Dimension ID's", "lawAltar", 178, "Dimension ID for the law altar.").getInt(178);
			ConfigReference.mindAltarDimensionID = configuration
					.get("Dimension ID's", "mindAltar", 179, "Dimension ID for the mind altar.").getInt(179);
			ConfigReference.natureAltarDimensionID = configuration
					.get("Dimension ID's", "natureAltar", 180, "Dimension ID for the nature altar.").getInt(180);
			ConfigReference.soulAltarDimensionID = configuration
					.get("Dimension ID's", "soulAltar", 181, "Dimension ID for the soul altar.").getInt(181);
			ConfigReference.waterAltarDimensionID = configuration
					.get("Dimension ID's", "waterAltar", 182, "Dimension ID for the water altar.").getInt(182);

		} catch (Exception e) {

		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}
	}
}
