package com.laton95.runemysteries;

import com.laton95.runemysteries.handler.ConfigurationHandler;
import com.laton95.runemysteries.init.LootRegistry;
import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.proxy.IProxy;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class RuneMysteries {
	@Instance(Reference.MOD_ID)
	public static RuneMysteries instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		MinecraftForge.EVENT_BUS.register(new LootRegistry());
		LogHelper.info("Pre-initialization complete.");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LogHelper.info("Initialization complete.");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		WorldGenReference.init();
		WorldGenRegistry.registerWorldGen();

		LogHelper.info("Post-initialization complete.");
	}
}
