package com.laton95.runemysteries.init;

import com.laton95.runemysteries.init.InitDataStructures.TileEntityEntry;
import com.laton95.runemysteries.tileentity.TileEntityAltarPortal;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ModTileEntities
{
	
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering tile entities");
		
		TileEntityEntry[] entrires = {new InitDataStructures().new TileEntityEntry("altar_portal", TileEntityAltarPortal.class)};
		
		for (TileEntityEntry entry : entrires)
		{
			GameRegistry.registerTileEntity(entry.entityClass, entry.key);
		}
	}
}
