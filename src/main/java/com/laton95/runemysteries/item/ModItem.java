package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.item.Item;

public class ModItem extends Item {
	
	public ModItem(String name) {
		this(name, new Properties());
	}
	
	public ModItem(String name, Properties properties) {
		super(properties.group(RuneMysteries.RUNE_GROUP));
		setRegistryName(RuneMysteries.MOD_ID, name.toLowerCase());
	}
}
