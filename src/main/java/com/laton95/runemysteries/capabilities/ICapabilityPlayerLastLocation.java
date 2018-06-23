package com.laton95.runemysteries.capabilities;

import net.minecraft.util.math.BlockPos;

public interface ICapabilityPlayerLastLocation
{
	void set(int x, int y, int z, int dimId);
	
	void set(ICapabilityPlayerLastLocation old);
	
	BlockPos getPosition();
	
	int getDimId();
}
