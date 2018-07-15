package com.laton95.runemysteries;

import com.laton95.runemysteries.capabilities.CapabilityHandler;
import com.laton95.runemysteries.init.*;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.proxy.IProxy;
import com.laton95.runemysteries.reference.MiscReference;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.LogHelper;
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
	
	@SidedProxy(clientSide = ModReference.CLIENT_PROXY_CLASS)
	public static IProxy proxy;
	
	static
	{
		FluidRegistry.enableUniversalBucket();
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		ModCapabilities.RegisterCapabilities();
		
		if(LocalDate.now().getMonth() == Month.APRIL && LocalDate.now().getDayOfMonth() == 1)
		{
			LogHelper.info("It's April Fools!");
			MiscReference.isAprilFools = true;
		}
		if(LocalDate.now().getMonth() == Month.DECEMBER && LocalDate.now().getDayOfMonth() == 25)
		{
			LogHelper.info("Merry Christmas!");
			MiscReference.isChristmas = true;
		}
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		MinecraftForge.EVENT_BUS.register(new ModLoot());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		ModWorldGen.registerWorldGen();
		ModDimensions.registerDimensions();
		ModOreDict.registerOres();
		ModVillagers.registerVillage();
		ModAdvancements.registerAdvancementTriggers();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
		Spells.checkSpells();
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting(event);
	}
	
	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		proxy.serverStopping(event);
	}
}
