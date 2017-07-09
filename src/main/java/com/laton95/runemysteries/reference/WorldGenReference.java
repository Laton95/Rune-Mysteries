package com.laton95.runemysteries.reference;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class WorldGenReference {
	public static LinkedList<Biome> genericAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> swampAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> desertAltarSpawnBiomes = new LinkedList<Biome>();

	public static LinkedList<Biome> cosmicAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> chaosAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> astralAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> natureAltarSpawnBiomes = new LinkedList<Biome>();
	public static LinkedList<Biome> deathAltarSpawnBiomes = new LinkedList<Biome>();

	public static LinkedList<Biome> allAltarSpawnBiomes = new LinkedList<Biome>();

	public static void init() {
		setupRuneAltarSpawnBiomes();
	}

	public static void setupRuneAltarSpawnBiomes() {
		setupGenericAltarSpawnBiomes();
		setupSwampAltarSpawnBiomes();
		setupDesertAltarSpawnBiomes();
		setupCosmicAltarSpawnBiomes();
		setupChaosAltarSpawnBiomes();
		setupAstralAltarSpawnBiomes();
		setupNatureAltarSpawnBiomes();
		setupDeathAltarSpawnBiomes();

		allAltarSpawnBiomes.addAll(genericAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(swampAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(desertAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(cosmicAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(chaosAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(astralAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(natureAltarSpawnBiomes);
		allAltarSpawnBiomes.addAll(deathAltarSpawnBiomes);
	}

	public static List<Biome> getBiomeList(String altar) {
		switch (altar) {
		case "airaltar":
			return genericAltarSpawnBiomes;
		case "astralaltar":
			return astralAltarSpawnBiomes;
		case "bloodaltar":
			return swampAltarSpawnBiomes;
		case "bodyaltar":
			return genericAltarSpawnBiomes;
		case "deathaltar":
			return deathAltarSpawnBiomes;
		case "earthaltar":
			return genericAltarSpawnBiomes;
		case "firealtar":
			return desertAltarSpawnBiomes;
		case "lawaltar":
			return genericAltarSpawnBiomes;
		case "mindaltar":
			return genericAltarSpawnBiomes;
		case "naturealtar":
			return natureAltarSpawnBiomes;
		case "soulaltar":
			return desertAltarSpawnBiomes;
		case "wateraltar":
			return swampAltarSpawnBiomes;
		default:
			return allAltarSpawnBiomes;
		}
	}

	public static void setupGenericAltarSpawnBiomes() {
		genericAltarSpawnBiomes.add(Biomes.BIRCH_FOREST);
		genericAltarSpawnBiomes.add(Biomes.BIRCH_FOREST_HILLS);
		genericAltarSpawnBiomes.add(Biomes.FOREST);
		genericAltarSpawnBiomes.add(Biomes.FOREST_HILLS);
		genericAltarSpawnBiomes.add(Biomes.MUTATED_BIRCH_FOREST);
		genericAltarSpawnBiomes.add(Biomes.MUTATED_BIRCH_FOREST_HILLS);
		genericAltarSpawnBiomes.add(Biomes.MUTATED_FOREST);
		genericAltarSpawnBiomes.add(Biomes.MUTATED_PLAINS);
		genericAltarSpawnBiomes.add(Biomes.MUTATED_SAVANNA);
		genericAltarSpawnBiomes.add(Biomes.PLAINS);
		genericAltarSpawnBiomes.add(Biomes.SAVANNA);
		genericAltarSpawnBiomes.add(Biomes.SAVANNA_PLATEAU);
	}

	public static void setupSwampAltarSpawnBiomes() {
		swampAltarSpawnBiomes.add(Biomes.MUTATED_SWAMPLAND);
		swampAltarSpawnBiomes.add(Biomes.SWAMPLAND);
	}

	public static void setupDesertAltarSpawnBiomes() {
		desertAltarSpawnBiomes.add(Biomes.DESERT);
		desertAltarSpawnBiomes.add(Biomes.DESERT_HILLS);
		desertAltarSpawnBiomes.add(Biomes.MUTATED_DESERT);
	}

	public static void setupCosmicAltarSpawnBiomes() {
		cosmicAltarSpawnBiomes.add(Biomes.SKY);
	}

	public static void setupChaosAltarSpawnBiomes() {
		chaosAltarSpawnBiomes.add(Biomes.HELL);
	}

	public static void setupAstralAltarSpawnBiomes() {
		astralAltarSpawnBiomes.add(Biomes.COLD_TAIGA);
		astralAltarSpawnBiomes.add(Biomes.COLD_TAIGA_HILLS);
		astralAltarSpawnBiomes.add(Biomes.ICE_MOUNTAINS);
		astralAltarSpawnBiomes.add(Biomes.ICE_PLAINS);
		astralAltarSpawnBiomes.add(Biomes.MUTATED_ICE_FLATS);
		astralAltarSpawnBiomes.add(Biomes.TAIGA);
		astralAltarSpawnBiomes.add(Biomes.TAIGA_HILLS);
		astralAltarSpawnBiomes.add(Biomes.MUTATED_TAIGA);
		astralAltarSpawnBiomes.add(Biomes.MUTATED_TAIGA_COLD);
	}

	public static void setupNatureAltarSpawnBiomes() {
		natureAltarSpawnBiomes.add(Biomes.JUNGLE);
		natureAltarSpawnBiomes.add(Biomes.JUNGLE_EDGE);
		natureAltarSpawnBiomes.add(Biomes.JUNGLE_HILLS);
		natureAltarSpawnBiomes.add(Biomes.MUTATED_JUNGLE);
		natureAltarSpawnBiomes.add(Biomes.MUTATED_JUNGLE_EDGE);
	}

	public static void setupDeathAltarSpawnBiomes() {
		deathAltarSpawnBiomes.add(Biomes.EXTREME_HILLS);
		deathAltarSpawnBiomes.add(Biomes.EXTREME_HILLS_EDGE);
		deathAltarSpawnBiomes.add(Biomes.EXTREME_HILLS_WITH_TREES);
		deathAltarSpawnBiomes.add(Biomes.MUTATED_EXTREME_HILLS);
		deathAltarSpawnBiomes.add(Biomes.MUTATED_EXTREME_HILLS_WITH_TREES);
	}
}
