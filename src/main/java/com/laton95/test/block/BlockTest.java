package com.laton95.test.block;

import com.laton95.test.creativetab.CreativeTabTest;
import com.laton95.test.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTest extends Block{

	public BlockTest(String name, Material material, float hardness, String toolClass, int harvestLevel, boolean showInCreative){
		super(material);
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(CreativeTabTest.Test_TAB);
		setHardness(hardness);
		setHarvestLevel(toolClass, harvestLevel);
		
	}
}
