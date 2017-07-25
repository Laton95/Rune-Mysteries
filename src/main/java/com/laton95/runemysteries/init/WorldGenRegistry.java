package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.ChunkGenerator;
import com.laton95.runemysteries.world.MapGenRuneAltar;
import com.laton95.runemysteries.world.MapGenTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentAirTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBloodTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBodyTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentCosmicTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentDeathTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentEarthTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentEndAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentLawTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentNatureTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentNetherAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSoulAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentUndergroundAltar;

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
		LogHelper.info("Registering world-gen");
		makeGenMap();
		genMap.forEach((k, v) -> {
			GameRegistry.registerWorldGenerator(v, 0);
		});

		makeStructureMap();
		structureMap.forEach((k, v) -> {
			MapGenStructureIO.registerStructure(v, k);
		});

		makeComponentMap();
		componentMap.forEach((k, v) -> {
			MapGenStructureIO.registerStructureComponent(v, k);
		});
	}

	private static void makeGenMap() {
		chunkGenerator = new ChunkGenerator();
		genMap.put("Chunk Generator", chunkGenerator);
		MinecraftForge.EVENT_BUS.register(chunkGenerator);
		MinecraftForge.TERRAIN_GEN_BUS.register(chunkGenerator);
	}

	private static void makeStructureMap() {
		structureMap.put("RuneAltar", MapGenRuneAltar.Start.class);
		structureMap.put("RuneTemple", MapGenTemple.Start.class);
	}

	private static void makeComponentMap() {
		componentMap.put("SurfaceAltar", ComponentSurfaceAltar.class);
		componentMap.put("UndergroundAltar", ComponentUndergroundAltar.class);
		componentMap.put("SoulAltar", ComponentSoulAltar.class);
		componentMap.put("EndAltar", ComponentEndAltar.class);
		componentMap.put("NetherAltar", ComponentNetherAltar.class);
		componentMap.put("AirTemple", ComponentAirTemple.class);
		componentMap.put("BloodTemple", ComponentBloodTemple.class);
		componentMap.put("BodyTemple", ComponentBodyTemple.class);
		//componentMap.put("ChaosTemple", ComponentChaosTemple.class);
		componentMap.put("CosmicTemple", ComponentCosmicTemple.class);
		componentMap.put("DeathTemple", ComponentDeathTemple.class);
		componentMap.put("EarthTemple", ComponentEarthTemple.class);
		//componentMap.put("FireTemple", ComponentFireTemple.class);
		componentMap.put("LawTemple", ComponentLawTemple.class);
		//componentMap.put("MindTemple", ComponentMindTemple.class);
		componentMap.put("NatureTemple", ComponentNatureTemple.class);
	}
}