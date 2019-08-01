package com.laton95.runemysteries.item;

import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class DebugItem extends ModItem {
	
	public DebugItem() {
		super(new Properties().maxStackSize(1));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		return ActionResultType.SUCCESS;
	}
}
