package com.laton95.runemysteries.init;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.item.RMModItem;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber
public class EntityRegistry {
	public static final List<EntityEntry> entityList = ImmutableList.of(
			new EntityEntry(DamageProjectile.class, "DamageProjectile").setRegistryName("DamageProjectile")
			);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<EntityEntry> event) {
		LogHelper.info("Registering entities");
		for (EntityEntry entity : entityList) {
			event.getRegistry().register(entity);
		}
		
		
	}
}
