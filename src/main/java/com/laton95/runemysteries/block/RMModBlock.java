package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RMModBlock extends Block
{
	public boolean hasItem;
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative, boolean hasItem)
	{
		super(material);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if(showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		this.hasItem = hasItem;
	}
}
