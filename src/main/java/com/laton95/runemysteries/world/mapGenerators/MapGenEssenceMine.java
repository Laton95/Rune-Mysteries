package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenEssenceMine extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(0, 100, 0)
			);
	
	public MapGenEssenceMine()
	{
		super("essence_mine", "essence_mine", 60, portals);
	}
}
