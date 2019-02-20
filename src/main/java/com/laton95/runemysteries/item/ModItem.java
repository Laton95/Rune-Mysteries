package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.item.Item;

public class ModItem extends Item {
	
	public ModItem() {
		this(new Properties());
	}
	
	public ModItem(Properties properties) {
		super(properties.group(RuneMysteries.RUNE_GROUP));
	}
}
