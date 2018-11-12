package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenTempleBlood extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(17, 65, 17)
			);
	
	public MapGenTempleBlood()
	{
		super("blood_temple", "blood_temple", 60, portals);
	}
}
