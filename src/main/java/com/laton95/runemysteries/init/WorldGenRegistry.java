package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.ChunkGenerator;
import com.laton95.runemysteries.world.ComponentSoulAltar;
import com.laton95.runemysteries.world.ComponentSurfaceAltar;
import com.laton95.runemysteries.world.ComponentUndergroundAltar;
import com.laton95.runemysteries.world.MapGenRuneAltar;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenRegistry {
	private static Map<String, IWorldGenerator> genMap = new HashMap<String, IWorldGenerator>();
	private static Map<String, Class<? extends StructureStart>> structureMap = new HashMap<String, Class<? extends StructureStart>>();
	private static Map<String, Class<? extends StructureComponent>> componentMap = new HashMap<String, Class<? extends StructureComponent>>();
	public static ChunkGenerator chunkGenerator;

	public static void registerWorldGen() {

		LogHelper.info("Registering worldgen...");
		makeGenMap();
		genMap.forEach((k, v) -> {
			GameRegistry.registerWorldGenerator(v, 0);
			LogHelper.info(k + " registered successfully");
		});

		LogHelper.info("Registering structures...");
		makeStructureMap();
		structureMap.forEach((k, v) -> {
			MapGenStructureIO.registerStructure(v, k);
			LogHelper.info(k + " registered successfully");
		});
		LogHelper.info("All structures registered successfully");

		LogHelper.info("Registering structure components...");
		makeComponentMap();
		componentMap.forEach((k, v) -> {
			MapGenStructureIO.registerStructureComponent(v, k);
			LogHelper.info(k + " registered successfully");
		});
		LogHelper.info("All structures components registered successfully");

		LogHelper.info("All worldgen registered successfully");
	}

	private static void makeGenMap() {
		chunkGenerator = new ChunkGenerator();
		genMap.put("Chunk Generator", chunkGenerator);
		MinecraftForge.EVENT_BUS.register(chunkGenerator);
		MinecraftForge.TERRAIN_GEN_BUS.register(chunkGenerator);
	}

	private static void makeStructureMap() {
		structureMap.put("RuneAltar", MapGenRuneAltar.Start.class);
	}

	private static void makeComponentMap() {
		componentMap.put("SurfaceAltar", ComponentSurfaceAltar.class);
		componentMap.put("UndergroundAltar", ComponentUndergroundAltar.class);
		componentMap.put("SoulAltar", ComponentSoulAltar.class);
	}
}