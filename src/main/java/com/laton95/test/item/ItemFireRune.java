package com.laton95.test.item;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.laton95.test.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemFireRune extends ItemTest{
	public ItemFireRune(){
		super();
		setName("fire_Rune");
		setCreativeTab(CreativeTabs.MATERIALS);
	}
}
