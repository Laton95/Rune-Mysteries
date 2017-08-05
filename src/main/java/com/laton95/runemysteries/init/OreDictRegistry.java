package com.laton95.runemysteries.init;

import com.laton95.runemysteries.util.LogHelper;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegistry {
	public static void registerOres() {
		LogHelper.info("Registering ore dictionary");
		OreDictionary.registerOre("RMRune", ItemRegistry.AIR_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.ASTRAL_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.BLOOD_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.BODY_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.CHAOS_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.COSMIC_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.DEATH_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.EARTH_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.FIRE_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.LAW_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.MIND_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.NATURE_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.SOUL_RUNE);
		OreDictionary.registerOre("RMRune", ItemRegistry.WATER_RUNE);
	}
}
