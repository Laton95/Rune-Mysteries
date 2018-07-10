package com.laton95.runemysteries.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStationStone extends RMModBlock
{
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.125, 0, 0.125, 0.875, 0.9375, 0.875);
	
	public BlockStationStone()
	{
		super("station_stone", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
}
