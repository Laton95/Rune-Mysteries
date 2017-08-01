package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class RMModTileEntity extends BlockContainer{

	protected RMModTileEntity(String name, Material material, float hardness, Float resistance, String toolClass,
			int harvestLevel, boolean showInCreative) {
		super(material);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		if (showInCreative) {
			setCreativeTab(RMCreativeTab.RM_TAB);
		}
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
	}
}
