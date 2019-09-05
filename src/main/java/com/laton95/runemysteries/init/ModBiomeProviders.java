package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.world.biome.provider.RuneTempleBiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModBiomeProviders {
	
	public static final BiomeProviderType AIR_TEMPLE = null;
	
	public static final BiomeProviderType BLOOD_TEMPLE = null;
	
	public static final BiomeProviderType BODY_TEMPLE = null;
	
	public static final BiomeProviderType CHAOS_TEMPLE = null;
	
	public static final BiomeProviderType COSMIC_TEMPLE = null;
	
	public static final BiomeProviderType DEATH_TEMPLE = null;
	
	public static final BiomeProviderType EARTH_TEMPLE = null;
	
	public static final BiomeProviderType FIRE_TEMPLE = null;
	
	public static final BiomeProviderType LAW_TEMPLE = null;
	
	public static final BiomeProviderType MIND_TEMPLE = null;
	
	public static final BiomeProviderType NATURE_TEMPLE = null;
	
	public static final BiomeProviderType SOUL_TEMPLE = null;
	
	public static final BiomeProviderType WATER_TEMPLE = null;
	
	@SubscribeEvent
	public static void registerBiomeProviders(RegistryEvent.Register<BiomeProviderType<?, ?>> event) {
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.AIR_TEMPLE)).setRegistryName("air_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.BLOOD_TEMPLE)).setRegistryName("blood_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.BODY_TEMPLE)).setRegistryName("body_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.CHAOS_TEMPLE)).setRegistryName("chaos_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.COSMIC_TEMPLE)).setRegistryName("cosmic_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.DEATH_TEMPLE)).setRegistryName("death_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.EARTH_TEMPLE)).setRegistryName("earth_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.FIRE_TEMPLE)).setRegistryName("fire_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.LAW_TEMPLE)).setRegistryName("law_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.MIND_TEMPLE)).setRegistryName("mind_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.NATURE_TEMPLE)).setRegistryName("nature_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.SOUL_TEMPLE)).setRegistryName("soul_temple"));
		event.getRegistry().register(new BiomeProviderType<>(RuneTempleBiomeProvider::new, () -> BiomeProviderType.FIXED.createSettings().setBiome(ModBiomes.WATER_TEMPLE)).setRegistryName("water_temple"));
		
	}
}
