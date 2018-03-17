package com.laton95.runemysteries.init;

import com.laton95.runemysteries.entity.projectile.*;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber
public class ModEntities
{
	
	private static int entityID = 0;
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		LogHelper.info("Registering entities");
		
		EntityEntry[] entries = {createBuilder("spell_projectile_bouncing").entity(EntityProjectileSpellBouncing.class).tracker(64, 20, true).build(),
				createBuilder("spell_projectile_damage").entity(EntityProjectileSpellDamage.class).tracker(64, 20, true).build(),
				createBuilder("spell_projectile_explosive").entity(EntityProjectileSpellExplosive.class).tracker(64, 20, true).build(),
				createBuilder("spell_projectile_following").entity(EntityProjectileSpellFollowing.class).tracker(64, 1, true).build(),
				createBuilder("spell_projectile_teleport_basic").entity(EntityProjectileSpellTeleportBasic.class).tracker(64, 20, true).build()};
		
		event.getRegistry().registerAll(entries);
		
	}
	
	private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name)
	{
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation registryName = new ResourceLocation(ModReference.MOD_ID, name);
		return builder.id(registryName, entityID++).name(registryName.toString());
	}
}
