package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.entity.passive.ExExParrotEntity;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	
	public static EntityType<ExExParrotEntity> EX_EX_PARROT = EntityType.Builder.create(ExExParrotEntity::new, EntityClassification.AMBIENT).size(0.5F, 0.9F).build("ex_ex_parrot");
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		ModLog.info("Registering entities");
		EX_EX_PARROT.setRegistryName("ex_ex_parrot");
		event.getRegistry().register(EX_EX_PARROT);
		
	}
}
