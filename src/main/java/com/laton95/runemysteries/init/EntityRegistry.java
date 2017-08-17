package com.laton95.runemysteries.init;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.spells.projectiles.SpellProjectileBase;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber
public class EntityRegistry {
	public static final List<EntityEntry> entityList = ImmutableList.of(
			new EntityEntry(SpellProjectileBase.class, "DamageProjectile").setRegistryName("DamageProjectile")
			);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<EntityEntry> event) {
		LogHelper.info("Registering entities");
		for (EntityEntry entity : entityList) {
			event.getRegistry().register(entity);
		}
		
		
	}
}
