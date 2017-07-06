package com.laton95.runemysteries.reference;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class WorldGen {
	public static LinkedList<Block> surfaceBlocks = new LinkedList<Block>();
	public static LinkedList<Biome> runeAltarSpawnBiomes = new LinkedList<Biome>() ;
	public static float structureFlatnessTolerance = 0.0f;
	
	public static void init() {
		setupSurfaceBlocks();
		setupRuneAltarSpawnBiomes();
	}
	
	public static void setupSurfaceBlocks() {
		surfaceBlocks.add(Blocks.SAND);
		surfaceBlocks.add(Blocks.GRASS);
		surfaceBlocks.add(Blocks.STONE);
		surfaceBlocks.add(Blocks.GRAVEL);
		surfaceBlocks.add(Blocks.DIRT);
	}
	
	public static void setupRuneAltarSpawnBiomes() {
		runeAltarSpawnBiomes.add(Biomes.BIRCH_FOREST);
		runeAltarSpawnBiomes.add(Biomes.COLD_TAIGA);
		runeAltarSpawnBiomes.add(Biomes.DESERT);
		runeAltarSpawnBiomes.add(Biomes.FOREST);
		runeAltarSpawnBiomes.add(Biomes.JUNGLE);
		runeAltarSpawnBiomes.add(Biomes.MESA);
		runeAltarSpawnBiomes.add(Biomes.MESA_CLEAR_ROCK);
		runeAltarSpawnBiomes.add(Biomes.MESA_ROCK);
		runeAltarSpawnBiomes.add(Biomes.MUSHROOM_ISLAND);
		runeAltarSpawnBiomes.add(Biomes.PLAINS);
		runeAltarSpawnBiomes.add(Biomes.REDWOOD_TAIGA);
		runeAltarSpawnBiomes.add(Biomes.ROOFED_FOREST);
		runeAltarSpawnBiomes.add(Biomes.SAVANNA);
		runeAltarSpawnBiomes.add(Biomes.SAVANNA_PLATEAU);
		runeAltarSpawnBiomes.add(Biomes.SWAMPLAND);
		runeAltarSpawnBiomes.add(Biomes.TAIGA);
	}
}
