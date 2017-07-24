package com.laton95.runemysteries.handler;

import com.laton95.runemysteries.reference.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("runemysteries.config.title")
public class ModConfig {
	public static final WorldGeneration world = new WorldGeneration();
	public static final Dimensions dimensions = new Dimensions();

	public static class WorldGeneration {
		public final RuneAltars rune_altars = new RuneAltars();
		public final Ores ores = new Ores();
		
		public static class RuneAltars {
			@Config.Comment("Generate rune altars in the world.")
			public boolean generateRuneAltars = true;;
			
			@Config.Comment("Maximum distance from 0,0 rune altars will generate.")
			public int maxRuneAltarRange = 5000;
			
			@Config.Comment("Minimum distance from 0,0 rune altars will generate.")
			public int minRuneAltarRange = 0;
			
			@Config.Comment("Number of attempts each altar makes to find a correct biome for generation, insanely high values may cause initial world creation to take more time.")
			public int runeAltarTries = 300;
		}
		
		public static class Ores {
			@Config.Comment("Generate finite essence in the world.")
			public static boolean generateFiniteEssence = true;
			
			@Config.Comment("Size of finite essence veins.")
			public int finiteEssenceVeinSize = 8;
			
			@Config.Comment("Number of generation tries per chunk for finite essence veins.")
			public int finiteEssenceVeinsPerChunk = 10;
			
			@Config.Comment("Maximum height of finite essence veins.")
			public int finiteEssenceMaxHeight = 65;
			
			@Config.Comment("Minimum height of finite essence veins.")
			public int finiteEssenceMinHeight = 10;
		}
	}

	public static class Dimensions {
		@Config.Comment("Dimension ID for air temple.")
		public int airTempleDimID = 169;
		
		@Config.Comment("Dimension ID for blood temple.")
		public int bloodTempleDimID = 170;
		
		@Config.Comment("Dimension ID for body temple.")
		public int bodyTempleDimID = 171;
		
		@Config.Comment("Dimension ID for chaos temple.")
		public int chaosTempleDimID = 172;
		
		@Config.Comment("Dimension ID for cosmic temple.")
		public int cosmicTempleDimID = 173;
		
		@Config.Comment("Dimension ID for death temple.")
		public int deathTempleDimID = 174;
		
		@Config.Comment("Dimension ID for earth temple.")
		public int earthTempleDimID = 175;
		
		@Config.Comment("Dimension ID for fire temple.")
		public int fireTempleDimID = 176;
		
		@Config.Comment("Dimension ID for law temple.")
		public int lawTempleDimID = 177;
		
		@Config.Comment("Dimension ID for mind temple.")
		public int mindTempleDimID = 178;
		
		@Config.Comment("Dimension ID for nature temple.")
		public int natureTempleDimID = 179;
		
		@Config.Comment("Dimension ID for soul temple.")
		public int soulTempleDimID = 180;
		
		@Config.Comment("Dimension ID for water temple.")
		public int waterTempleDimID = 181;
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
