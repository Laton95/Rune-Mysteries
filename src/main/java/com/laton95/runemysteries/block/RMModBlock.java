package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.CreativeTabTest;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class RMModBlock extends Block{

	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative){
		super(material);
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(CreativeTabTest.Test_TAB);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		
	}
}
