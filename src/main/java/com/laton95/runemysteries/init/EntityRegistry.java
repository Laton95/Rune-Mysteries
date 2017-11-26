package com.laton95.runemysteries.init;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileBase;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileBouncing;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileDamage;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileExplosive;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileFollowing;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileTeleportBasic;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber
public class EntityRegistry
{

	public static final List<EntityEntry> entityList = ImmutableList
			.of(
					new EntityEntry(SpellProjectileBase.class, "SpellProjectileBase").setRegistryName("SpellProjectileBase"),
					new EntityEntry(SpellProjectileBouncing.class, "SpellProjectileBouncing").setRegistryName("SpellProjectileBouncing"),
					new EntityEntry(SpellProjectileDamage.class, "SpellProjectileDamage").setRegistryName("SpellProjectileDamage"),
					new EntityEntry(SpellProjectileExplosive.class, "SpellProjectileExplosive").setRegistryName("SpellProjectileExplosive"),
					new EntityEntry(SpellProjectileFollowing.class, "SpellProjectileFollowing").setRegistryName("SpellProjectileFollowing"),
					new EntityEntry(SpellProjectileTeleportBasic.class, "SpellProjectileTeleportBasic").setRegistryName("SpellProjectileTeleportBasic")
				);

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		LogHelper.info("Registering entities");
		for (EntityEntry entity : entityList)
		{
			event.getRegistry().register(entity);
		}

	}
}
