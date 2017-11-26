package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.tileentity.TileEntityAltarPortal;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class TileEntityRegistry
{

	private static Map<String, Class<? extends TileEntity>> tileEntityMap = new HashMap<>();

	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<Block> event)
	{
		LogHelper.info("Registering tile entities");
		makeTileEntityMap();
		tileEntityMap.forEach((k, v) ->
		{
			GameRegistry.registerTileEntity(v, k);
		});
	}

	private static void makeTileEntityMap()
	{
		tileEntityMap.put(ModReference.MOD_ID + ":" + "altar_portal", TileEntityAltarPortal.class);
	}
}
