package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenTempleWater extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(6, 65, 4)
			);
	
	public MapGenTempleWater()
	{
		super("water_temple", "water_temple", 41, portals);
	}
}
