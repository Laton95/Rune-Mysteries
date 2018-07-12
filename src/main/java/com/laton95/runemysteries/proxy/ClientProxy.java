package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.init.ModRenders;
import com.laton95.runemysteries.init.Modkeybinds;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy implements IProxy
{
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ModRenders.registerRenders();
		Modkeybinds.registerKeybinds();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
	
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
	
	}
	
	@Override
	public void serverStarting(FMLServerStartingEvent event)
	{
	
	}
	
	@Override
	public void serverStopping(FMLServerStoppingEvent event)
	{
	
	}
}
