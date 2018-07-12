package com.laton95.runemysteries.world.mapGenerators;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class MapGenCosmicTemple extends MapGenCenterStructure
{
	private static List<BlockPos> portals = ImmutableList.of
			(
					new BlockPos(-14, 65, 8),
					new BlockPos(7, 65, 29),
					new BlockPos(29, 65, 7),
					new BlockPos(8, 65, -14)
			);
	
	public MapGenCosmicTemple()
	{
		super("cosmic_temple", "cosmic_temple", 62, portals);
	}
}
