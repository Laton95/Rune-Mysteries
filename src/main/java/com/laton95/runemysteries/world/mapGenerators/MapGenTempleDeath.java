package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenTempleDeath extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(0, 100, 0)
			);
	
	public MapGenTempleDeath()
	{
		super("death_temple", "death_temple", 60, portals);
	}
}
