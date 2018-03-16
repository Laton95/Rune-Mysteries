package com.laton95.runemysteries.init;

import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.mapGenerators.*;
import com.laton95.runemysteries.world.structureComponents.*;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

public class WorldGenRegistry
{
	
	private static Map<String, IWorldGenerator> genMap = new HashMap<>();
	private static Map<String, Class<? extends StructureStart>> structureMap = new HashMap<>();
	private static Map<String, Class<? extends StructureComponent>> componentMap = new HashMap<>();
	public static WorldGenerator chunkGenerator;
	
	public static void registerWorldGen()
	{
		LogHelper.info("Registering world-gen");
		makeGenMap();
		genMap.forEach((k, v) ->
		{
			GameRegistry.registerWorldGenerator(v, 0);
		});
		
		makeStructureMap();
		structureMap.forEach((k, v) ->
		{
			MapGenStructureIO.registerStructure(v, k);
		});
		
		makeComponentMap();
		componentMap.forEach((k, v) ->
		{
			MapGenStructureIO.registerStructureComponent(v, k);
		});
	}
	
	private static void makeGenMap()
	{
		chunkGenerator = new WorldGenerator();
		genMap.put("Chunk Generator", chunkGenerator);
		MinecraftForge.EVENT_BUS.register(chunkGenerator);
		MinecraftForge.TERRAIN_GEN_BUS.register(chunkGenerator);
	}
	
	private static void makeStructureMap()
	{
		structureMap.put("RuneAltar_SURFACE", MapGenRuneAltar_SURFACE.Start.class);
		structureMap.put("RuneAltar_UNDERGROUND", MapGenRuneAltar_UNDERGROUND.Start.class);
		structureMap.put("RuneAltar_SOUL", MapGenRuneAltar_SOUL.Start.class);
		structureMap.put("RuneAltar_NETHER", MapGenRuneAltar_NETHER.Start.class);
		structureMap.put("RuneAltar_END", MapGenRuneAltar_END.Start.class);
		structureMap.put("RuneTemple", MapGenRuneTemple.Start.class);
		structureMap.put("CosmicTemple", MapGenCosmicTemple.Start.class);
	}
	
	private static void makeComponentMap()
	{
		componentMap.put("SurfaceAltar", ComponentSurfaceAltar.class);
		componentMap.put("UndergroundAltar", ComponentUndergroundAltar.class);
		componentMap.put("SoulAltar", ComponentSoulAltar.class);
		componentMap.put("EndAltar", ComponentEndAltar.class);
		componentMap.put("NetherAltar", ComponentNetherAltar.class);
		componentMap.put("Temple", ComponentTemple.class);
		componentMap.put("CosmicTemple", ComponentCosmicTemple.class);
	}
}
