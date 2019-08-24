package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.world.biome.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModBiomes {
	
	public static final Biome AIR_TEMPLE = null;
	
	public static final Biome BLOOD_TEMPLE = null;
	
	public static final Biome BODY_TEMPLE = null;
	
	public static final Biome CHAOS_TEMPLE = null;
	
	public static final Biome COSMIC_TEMPLE = null;
	
	public static final Biome DEATH_TEMPLE = null;
	
	public static final Biome EARTH_TEMPLE = null;
	
	public static final Biome FIRE_TEMPLE = null;
	
	public static final Biome LAW_TEMPLE = null;
	
	public static final Biome MIND_TEMPLE = null;
	
	public static final Biome NATURE_TEMPLE = null;
	
	public static final Biome SOUL_TEMPLE = null;
	
	public static final Biome WATER_TEMPLE = null;
	
	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		ModLog.info("Registering biomes");
		event.getRegistry().register(new AirTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "air_temple"));
		event.getRegistry().register(new BloodTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "blood_temple"));
		event.getRegistry().register(new BodyTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "body_temple"));
		event.getRegistry().register(new ChaosTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "chaos_temple"));
		event.getRegistry().register(new CosmicTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "cosmic_temple"));
		event.getRegistry().register(new DeathTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "death_temple"));
		event.getRegistry().register(new EarthTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "earth_temple"));
		event.getRegistry().register(new FireTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "fire_temple"));
		event.getRegistry().register(new LawTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "law_temple"));
		event.getRegistry().register(new MindTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "mind_temple"));
		event.getRegistry().register(new NatureTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "nature_temple"));
		event.getRegistry().register(new SoulTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "soul_temple"));
		event.getRegistry().register(new WaterTempleBiome().setRegistryName(RuneMysteries.MOD_ID, "water_temple"));
		
	}
	
}
