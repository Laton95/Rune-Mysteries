package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.item.ItemFood;

public class ModFood extends ItemFood {
	
	public ModFood(String name, int healAmountIn, float saturation, boolean meat) {
		super(healAmountIn, saturation, meat, new Properties().group(RuneMysteries.RUNE_GROUP));
		setRegistryName(RuneMysteries.MOD_ID, name.toLowerCase());
	}
}
