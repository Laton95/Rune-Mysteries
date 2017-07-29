package com.laton95.runemysteries;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.init.DimensionRegistry;
import com.laton95.runemysteries.init.LiquidRegistry;
import com.laton95.runemysteries.init.LootRegistry;
import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.proxy.CommonProxy;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.ChunkGenerator;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class RuneMysteries {
	@Instance(Reference.MOD_ID)
	public static RuneMysteries instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	/** This is used to keep track of GUIs that we make */
	private static int modGuiIndex = 0;

	/** Set our custom inventory Gui index to the next available Gui index */
	public static final int GUI_ITEM_INV = modGuiIndex++;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new LootRegistry());
		WorldGenRegistry.registerWorldGen();
		DimensionRegistry.registerDimensions();
		BlockRegistry.setupDimIDs();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LiquidRegistry.registerFluidContainers();
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartedEvent event) {
		LogHelper.info("Loading altar tracker");
		if (ChunkGenerator.altarTracker == null) {
			ChunkGenerator.altarTracker = new AltarTracker();
		}
	}

	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event) {
		LogHelper.info("Unloading altar tracker");
		ChunkGenerator.altarTracker = null;
	}
}
