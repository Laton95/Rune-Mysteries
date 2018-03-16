package com.laton95.runemysteries.item;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RMModItem extends Item
{
	
	private Class<? extends Enum<?>> values;
	
	public RMModItem(String name, boolean showInCreative)
	{
		super();
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if (showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
	}
	
	public RMModItem(String name, boolean showInCreative, Class<? extends Enum<?>> values)
	{
		this(name, showInCreative);
		setHasSubtypes(true);
		this.values = values;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (hasSubtypes && values != null && tab == getCreativeTab())
		{
			for (int i = 0; i < values.getEnumConstants().length; i++)
			{
				items.add(new ItemStack(this, 1, i));
			}
		} else
		{
			super.getSubItems(tab, items);
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (hasSubtypes && values != null)
		{
			for (int i = 0; i < values.getEnumConstants().length; i++)
			{
				if (stack.getItemDamage() == i)
				{
					return this.getUnlocalizedName() + "." + values.getEnumConstants()[i];
				}
			}
			return this.getUnlocalizedName() + "." + values.getEnumConstants()[0];
		} else
		{
			return super.getUnlocalizedName();
		}
	}
	
	public Class<? extends Enum<?>> getValues()
	{
		return values;
	}
}
