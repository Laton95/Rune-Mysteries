package com.laton95.runemysteries.init;

import com.laton95.runemysteries.util.LogHelper;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictRegistry {
	public static void registerOres() {
		LogHelper.info("Registering ore dictionary");
		OreDictionary.registerOre("RMRune", ItemRegistry.RUNE);
	}
}
