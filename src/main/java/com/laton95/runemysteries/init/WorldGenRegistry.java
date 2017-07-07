package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.ChunkGenerator;
import com.laton95.runemysteries.world.ComponentRuneAltar;
import com.laton95.runemysteries.world.MapGenRuneAltar;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenRegistry {
	private static Map<String, IWorldGenerator> genMap = new HashMap<String, IWorldGenerator>();
	private static Map<String, Class<? extends StructureStart>> structureMap = new HashMap<String, Class<? extends StructureStart>>();
	private static Map<String, Class<? extends StructureComponent>> componentMap = new HashMap<String, Class<? extends StructureComponent>>();

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
		genMap.put("Chunk Generator", new ChunkGenerator());
	}

	private static void makeStructureMap() {
		structureMap.put("Rune Altar Generator", MapGenRuneAltar.Start.class);
	}

	private static void makeComponentMap() {
		componentMap.put("Rune Altar Component", ComponentRuneAltar.class);
	}
}