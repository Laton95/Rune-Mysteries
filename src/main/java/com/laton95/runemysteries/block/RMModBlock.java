package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RMModBlock extends Block
{
	public final boolean hasItem;
	
	private BlockRenderLayer renderLayer;
	
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
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative)
	{
		this(name, material, hardness, resistance, toolClass, harvestLevel, showInCreative, true);
	}
	
	public RMModBlock(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel)
	{
		this(name, material, hardness, resistance, toolClass, harvestLevel, true, true);
	}
	
	public RMModBlock setRenderLayer(BlockRenderLayer layer)
	{
		renderLayer = layer;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		if(renderLayer != null)
		{
			return renderLayer;
		}
		else
		{
			return super.getBlockLayer();
		}
	}
}
