package com.laton95.runemysteries.capabilities;

import net.minecraft.util.math.BlockPos;

public class CapabilityPlayerLastLocation implements ICapabilityPlayerLastLocation
{
	private BlockPos playerLastPos;
	
	private int dimId;
	
	@Override
	public void set(int x, int y, int z, int dimId)
	{
		playerLastPos = new BlockPos(x, y, z);
		this.dimId = dimId;
	}
	
	@Override
	public void set(ICapabilityPlayerLastLocation old)
	{
		playerLastPos = old.getPosition();
		dimId = old.getDimId();
	}
	
	@Override
	public BlockPos getPosition()
	{
		return playerLastPos;
	}
	
	@Override
	public int getDimId()
	{
		return dimId;
	}
}
