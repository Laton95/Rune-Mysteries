package com.laton95.runemysteries.init;

import com.laton95.runemysteries.datastructures.TileEntityEntry;
import com.laton95.runemysteries.tileentity.TileEntityAltarPortal;
import com.laton95.runemysteries.util.LogHelper;
import javafx.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

@Mod.EventBusSubscriber
public class TileEntityRegistry
{
	
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering tile entities");
		List<Pair<String, Class<? extends TileEntity>>> entities;
		
		TileEntityEntry[] entrires = {new TileEntityEntry("altar_portal", TileEntityAltarPortal.class)};
		
		for (TileEntityEntry entry : entrires)
		{
			GameRegistry.registerTileEntity(entry.entityClass, entry.key);
		}
	}
}
