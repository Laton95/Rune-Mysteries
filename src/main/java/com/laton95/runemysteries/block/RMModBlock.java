package com.laton95.runemysteries.block;

import java.util.Random;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class RMModBlock extends Block {
	protected Item drop = null;
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass,
			int harvestLevel, boolean showInCreative) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(RMCreativeTab.RM_TAB);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);

	}
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass,
			int harvestLevel, boolean showInCreative, Item drop) {
		super(material);
		setUnlocalizedName(Reference.MOD_ID + ":" + name);
		setRegistryName(Reference.MOD_ID, name.toLowerCase());
		setCreativeTab(RMCreativeTab.RM_TAB);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		this.drop = drop;

	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if(drop != null){
			return drop;
		} else return Item.getItemFromBlock(this);
    }
}
