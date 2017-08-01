package com.laton95.runemysteries.init;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_END;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_NETHER;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_SOUL;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_SURFACE;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_UNDERGROUND;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentAirTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBloodTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBodyTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentChaosTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentCosmicTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentDeathTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentEarthTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentEndAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentFireTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentLawTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentMindTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentNatureTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentNetherAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSoulAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSoulTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentUndergroundAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentWaterTemple;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenRegistry {
	private static Map<String, IWorldGenerator> genMap = new HashMap<>();
	private static Map<String, Class<? extends StructureStart>> structureMap = new HashMap<>();
	private static Map<String, Class<? extends StructureComponent>> componentMap = new HashMap<>();
	public static WorldGenerator chunkGenerator;

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
		chunkGenerator = new WorldGenerator();
		genMap.put("Chunk Generator", chunkGenerator);
		MinecraftForge.EVENT_BUS.register(chunkGenerator);
		MinecraftForge.TERRAIN_GEN_BUS.register(chunkGenerator);
	}

	private static void makeStructureMap() {
		structureMap.put("RuneAltar_SURFACE", MapGenRuneAltar_SURFACE.Start.class);
		structureMap.put("RuneAltar_UNDERGROUND", MapGenRuneAltar_UNDERGROUND.Start.class);
		structureMap.put("RuneAltar_SOUL", MapGenRuneAltar_SOUL.Start.class);
		structureMap.put("RuneAltar_NETHER", MapGenRuneAltar_NETHER.Start.class);
		structureMap.put("RuneAltar_END", MapGenRuneAltar_END.Start.class);
		structureMap.put("RuneTemple", MapGenRuneTemple.Start.class);
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
		componentMap.put("ChaosTemple", ComponentChaosTemple.class);
		componentMap.put("CosmicTemple", ComponentCosmicTemple.class);
		componentMap.put("DeathTemple", ComponentDeathTemple.class);
		componentMap.put("EarthTemple", ComponentEarthTemple.class);
		componentMap.put("FireTemple", ComponentFireTemple.class);
		componentMap.put("LawTemple", ComponentLawTemple.class);
		componentMap.put("MindTemple", ComponentMindTemple.class);
		componentMap.put("NatureTemple", ComponentNatureTemple.class);
		componentMap.put("SoulTemple", ComponentSoulTemple.class);
		componentMap.put("WaterTemple", ComponentWaterTemple.class);
	}
}