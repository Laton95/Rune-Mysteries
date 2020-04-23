package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModVillagers {
	
	public static final PointOfInterestType WIZARD_POI = null;
	
	public static void registerPointsOfInterest(final RegistryEvent.Register<PointOfInterestType> event) {
		ModLog.info("Registering points of interest");
		
		//event.getRegistry().register();
	}
	
	public static void registerVillagerProfessions(final RegistryEvent.Register<VillagerProfession> event) {
		ModLog.info("Registering villager professions");
		
		//event.getRegistry().register();
	}
}
