package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.world.gen.SolidGenerationSettings;
import com.laton95.runemysteries.world.gen.SolidWorldChunkGenerator;
import com.laton95.runemysteries.world.gen.SurfaceWorldChunkGenerator;
import com.laton95.runemysteries.world.gen.VoidWorldChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModChunkGenerators {
	
	public static final ChunkGeneratorType SURFACE_WORLD = null;
	
	public static final ChunkGeneratorType VOID_WORLD = null;
	
	public static final ChunkGeneratorType<SolidGenerationSettings, SolidWorldChunkGenerator> SOLID_WORLD = null;
	
	@SubscribeEvent
	public static void registerChunkGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
		event.getRegistry().register(new ChunkGeneratorType<>(SurfaceWorldChunkGenerator::new, false, OverworldGenSettings::new).setRegistryName(RuneMysteries.MOD_ID, "surface_world"));
		event.getRegistry().register(new ChunkGeneratorType<>(VoidWorldChunkGenerator::new, false, GenerationSettings::new).setRegistryName(RuneMysteries.MOD_ID, "void_world"));
		event.getRegistry().register(new ChunkGeneratorType<>(SolidWorldChunkGenerator::new, false, SolidGenerationSettings::new).setRegistryName(RuneMysteries.MOD_ID, "solid_world"));
	}
}
