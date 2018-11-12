package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenTempleFire extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(-6, 107, -4),
					new BlockPos(21, 107, 20)
			);
	
	public MapGenTempleFire()
	{
		super("fire_temple", "fire_temple", 84, portals);
	}
}
