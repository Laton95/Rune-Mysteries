package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class RMModBlock extends Block
{
	
	protected ItemStack drop = null;
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative)
	{
		super(material);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if (showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		
	}
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative, ItemStack drop)
	{
		super(material);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if (showInCreative)
		{
			setCreativeTab(RMModCreativeTab.RM_TAB);
		}
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		this.drop = drop;
		
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		drops.add(drop);
	}
}
