package com.laton95.runemysteries.utility;

import com.laton95.runemysteries.reference.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("runemysteries.config.title")
public class ModConfig {
	@Name("World Gen")
	@Comment("Worldgen options.")
	public static final WorldGeneration world = new WorldGeneration();
	@Name("Dimensions")
	@Comment("Dimension options.")
	public static final Dimensions dimensions = new Dimensions();

	public static class WorldGeneration {
		@Name("Rune Altar generation")
		@Comment("Rune altar generation options.")
		public final RuneAltars rune_altars = new RuneAltars();
		@Name("Ore generation")
		@Comment("Ore generation options.")
		public final Ores ores = new Ores();
		
		public static class RuneAltars {
			@Name("Generate Altars")
			@Comment("Generate rune altars in the world.")
			public boolean generateRuneAltars = true;;
			
			@Name("Max Altar Distance")
			@Comment("Maximum distance from 0,0 rune altars will generate.")
			@RangeInt(min = 1000)
			public int maxRuneAltarRange = 5000;
			
			@Name("Min Altar Distance")
			@Comment("Minimum distance from 0,0 rune altars will generate.")
			@RangeInt(min = 0)
			public int minRuneAltarRange = 0;
			
			@Name("Location Attempts")
			@Comment("Number of attempts each altar makes to find a correct biome for generation, insanely high values may cause initial world creation to take more time.")
			@RangeInt(min = 1)
			public int runeAltarTries = 300;
		}
		
		public static class Ores {
			@Name("Generate Finite Essence")
			@Comment("Generate finite essence in the world.")
			public boolean generateFiniteEssence = true;
			
			@Name("Finite Essence Vein Size")
			@Comment("Size of finite essence veins.")
			@RangeInt(min = 0)
			public int finiteEssenceVeinSize = 8;
			
			@Name("Finite Essence Veins Per Chunk")
			@Comment("Number of generation tries per chunk for finite essence veins.")
			@RangeInt(min = 0)
			public int finiteEssenceVeinsPerChunk = 10;
			
			@Name("Finite Essence Max Height")
			@Comment("Maximum height of finite essence veins.")
			@RangeInt(min = 0, max = 255)
			public int finiteEssenceMaxHeight = 65;
			
			@Name("Finite Essence Min Height")
			@Comment("Minimum height of finite essence veins.")
			@RangeInt(min = 0, max = 255)
			public int finiteEssenceMinHeight = 10;
		}
	}

	public static class Dimensions {
		@Name("Air Temple Dimension ID")
		@Comment("Dimension ID for air temple.")
		@RangeInt(min = 10)
		public int airTempleDimID = 169;
		
		@Name("Blood Temple Dimension ID")
		@Comment("Dimension ID for blood temple.")
		@RangeInt(min = 10)
		public int bloodTempleDimID = 170;
		
		@Name("Body Temple Dimension ID")
		@Comment("Dimension ID for body temple.")
		@RangeInt(min = 10)
		public int bodyTempleDimID = 171;
		
		@Name("Chaos Temple Dimension ID")
		@Comment("Dimension ID for chaos temple.")
		@RangeInt(min = 10)
		public int chaosTempleDimID = 172;
		
		@Name("Cosmic Temple Dimension ID")
		@Comment("Dimension ID for cosmic temple.")
		@RangeInt(min = 10)
		public int cosmicTempleDimID = 173;
		
		@Name("Death Temple Dimension ID")
		@Comment("Dimension ID for death temple.")
		@RangeInt(min = 10)
		public int deathTempleDimID = 174;
		
		@Name("Earth Temple Dimension ID")
		@Comment("Dimension ID for earth temple.")
		@RangeInt(min = 10)
		public int earthTempleDimID = 175;
		
		@Name("Fire Temple Dimension ID")
		@Comment("Dimension ID for fire temple.")
		@RangeInt(min = 10)
		public int fireTempleDimID = 176;
		
		@Name("Law Temple Dimension ID")
		@Comment("Dimension ID for law temple.")
		@RangeInt(min = 10)
		public int lawTempleDimID = 177;
		
		@Name("Mind Temple Dimension ID")
		@Comment("Dimension ID for mind temple.")
		@RangeInt(min = 10)
		public int mindTempleDimID = 178;
		
		@Name("Nature Temple Dimension ID")
		@Comment("Dimension ID for nature temple.")
		@RangeInt(min = 10)
		public int natureTempleDimID = 179;
		
		@Name("Soul Temple Dimension ID")
		@Comment("Dimension ID for soul temple.")
		@RangeInt(min = 10)
		public int soulTempleDimID = 180;
		
		@Name("Water Temple Dimension ID")
		@Comment("Dimension ID for water temple.")
		@RangeInt(min = 10)
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
