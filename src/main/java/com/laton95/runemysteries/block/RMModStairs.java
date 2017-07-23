package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class RMModStairs extends BlockStairs{
	
	public RMModStairs(String name, float hardness, Float resistance, String toolClass,
			int harvestLevel, boolean showInCreative, IBlockState modelState) {
		super(modelState);
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(RMCreativeTab.RM_TAB);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		this.useNeighborBrightness = true;
	}

}
