package com.laton95.runemysteries.item;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.item.ItemFood;

public class RMModFood extends ItemFood
{
	public RMModFood(String name, boolean showInCreative, int amount, float saturation)
	{
		this(name, showInCreative, amount, saturation, false);
	}
	
	public RMModFood(String name, boolean showInCreative, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if(showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
	}
}
