package com.laton95.test.item;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.laton95.test.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBlood_Rune extends ItemTest{
	public ItemBlood_Rune(){
		super();
		setName("blood_Rune");
		setCreativeTab(CreativeTabs.MATERIALS);
	}
}
