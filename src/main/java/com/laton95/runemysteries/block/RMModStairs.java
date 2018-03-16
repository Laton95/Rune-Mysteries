package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class RMModStairs extends BlockStairs
{
	
	public RMModStairs(String name, String toolClass, int harvestLevel, boolean showInCreative, IBlockState modelState)
	{
		super(modelState);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		setCreativeTab(RMModCreativeTab.RM_TAB);
		setHarvestLevel(toolClass, harvestLevel);
		useNeighborBrightness = true;
	}
	
}
