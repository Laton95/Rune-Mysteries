package com.laton95.runemysteries.item;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.item.Item;

public class RMModItem extends Item {

	public RMModItem(String name, boolean showInCreative) {
		super();
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if (showInCreative) {
			setCreativeTab(RMCreativeTab.RM_TAB);
		}
	}
}
