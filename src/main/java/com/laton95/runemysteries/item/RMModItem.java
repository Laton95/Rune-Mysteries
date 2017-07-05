package com.laton95.runemysteries.item;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.item.Item;

public class RMModItem extends Item {

	public RMModItem(String name, boolean showInCreative) {
		super();
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		if(showInCreative){
			setCreativeTab(RMCreativeTab.RM_TAB);
		}
	}
}
