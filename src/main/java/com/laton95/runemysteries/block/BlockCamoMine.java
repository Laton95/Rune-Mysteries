package com.laton95.runemysteries.block;

import com.laton95.runemysteries.tileentity.TileEntityCamoMine;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCamoMine extends RMModBlock implements ITileEntityProvider{

	public BlockCamoMine(String name, Material material, float hardness, Float resistance, String toolClass,
			int harvestLevel, boolean showInCreative) {
		super(name, material, hardness, resistance, toolClass, harvestLevel, showInCreative);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		LogHelper.info("tile entity created");
		return new TileEntityCamoMine();
	}

}
