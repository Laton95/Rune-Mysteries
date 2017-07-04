package com.laton95.test.item;

import com.laton95.test.creativetab.CreativeTabTest;
import com.laton95.test.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTest extends Item{
	
	public ItemTest(String name, boolean showInCreative) {
		super();
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(CreativeTabTest.Test_TAB);
	}
}
