package com.laton95.runemysteries.creativetab;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RMModCreativeTab
{
	
	public static final CreativeTabs RM_TAB = new CreativeTabs(ModReference.MOD_ID)
	{
		
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ItemRegistry.RUNE, 1, EnumRuneType.FIRE.ordinal());
		}
	};
}
