package com.laton95.runemysteries;

import com.laton95.runemysteries.init.*;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.proxy.CommonProxy;
import com.laton95.runemysteries.reference.MiscReference;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.time.LocalDate;
import java.time.Month;

@Mod(modid = ModReference.MOD_ID, name = ModReference.MOD_NAME, version = ModReference.VERSION)
public class RuneMysteries
{
	
	@Instance(ModReference.MOD_ID)
	public static RuneMysteries instance;
	
	@SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS, serverSide = ModReference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	/**
	 * This is used to keep track of GUIs that we make
	 */
	private static int modGuiIndex = 0;
	
	/**
	 * Set our custom inventory Gui index to the next available Gui index
	 */
	public static final int GUI_ITEM_INV = modGuiIndex++;
	
	static
	{
		FluidRegistry.enableUniversalBucket();
		
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		proxy.registerProjectileRenders();
		
		if (LocalDate.now().getMonth() == Month.APRIL && LocalDate.now().getDayOfMonth() == 1)
		{
			LogHelper.info("It's April Fools!");
			MiscReference.isAprilFools = true;
		}
		if (LocalDate.now().getMonth() == Month.DECEMBER && LocalDate.now().getDayOfMonth() == 25)
		{
			LogHelper.info("Merry Christmas!");
			MiscReference.isChristmas = true;
		}
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new LootRegistry());
		WorldGenRegistry.registerWorldGen();
		DimensionRegistry.registerDimensions();
		OreDictRegistry.registerOres();
		VillagerRegistry.registerVillage();
		proxy.registerKeyBindings();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Spells.checkSpells();
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartedEvent event)
	{
		LogHelper.info("Loading altar tracker");
		if (WorldGenerator.altarTracker == null)
		{
			WorldGenerator.altarTracker = new AltarTracker();
		}
	}
	
	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		LogHelper.info("Unloading altar tracker");
		WorldGenerator.altarTracker = null;
	}
}
