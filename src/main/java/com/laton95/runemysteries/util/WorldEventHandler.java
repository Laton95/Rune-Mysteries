package com.laton95.runemysteries.util;

import com.laton95.runemysteries.world.RuinTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class WorldEventHandler
{
	@SubscribeEvent
	public static void StetupRuinTracker(WorldEvent.Load event)
	{
		if(!event.getWorld().isRemote)
		{
			switch(event.getWorld().provider.getDimensionType())
			{
				case OVERWORLD:
					LogHelper.warn(String.format("Loading %s ruin tracker.", event.getWorld().provider.getDimensionType().toString().toLowerCase()));
					WorldGenerator.ruinTracker = new RuinTracker();
					WorldGenerator.ruinTracker.updateOverworldLocations(event.getWorld());
					break;
				case NETHER:
					LogHelper.warn(String.format("Loading %s ruin tracker.", event.getWorld().provider.getDimensionType().toString().toLowerCase()));
					WorldGenerator.ruinTracker.updateNetherLocations(event.getWorld());
					break;
				case THE_END:
					LogHelper.warn(String.format("Loading %s ruin tracker.", event.getWorld().provider.getDimensionType().toString().toLowerCase()));
					WorldGenerator.ruinTracker.updateEndLocations(event.getWorld());
					break;
			}
		}
	}
}
