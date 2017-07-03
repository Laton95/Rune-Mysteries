package com.laton95.test.handler;

import java.io.File;

import com.laton95.test.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	public static Configuration configuration;
	public static boolean testValue = false;
	public static boolean testValue2 = false;
	
	public static void init(File configFile){
		if(configuration == null){
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equalsIgnoreCase(Reference.MOD_ID)){
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration(){
		try{
			configuration.load();
			
			testValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "Example config.").getBoolean(true);
			testValue2 = configuration.get(Configuration.CATEGORY_GENERAL, "configValue2", true, "Example config 2.").getBoolean(true);
		}
		catch (Exception e){
			
		}
		finally{
			if(configuration.hasChanged()) {
				configuration.save();
			}
		}
	}
}
