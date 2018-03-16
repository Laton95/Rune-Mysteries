package com.laton95.runemysteries.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockStationStone extends RMModBlock
{
	
	public BlockStationStone()
	{
		super("station_Stone_Block", Material.ROCK, 1.5f, 10.0f, "pickaxe", 1, true);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.9, 0.9);
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
}
