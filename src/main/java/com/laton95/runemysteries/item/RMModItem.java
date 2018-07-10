package com.laton95.runemysteries.item;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.item.Item;

public class RMModItem extends Item
{
	
	public RMModItem(String name, boolean showInCreative)
	{
		this(name, showInCreative, 64);
	}
	
	public RMModItem(String name, boolean showInCreative, int maxStackSize)
	{
		super();
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if(showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
		setMaxStackSize(maxStackSize);
	}
}
