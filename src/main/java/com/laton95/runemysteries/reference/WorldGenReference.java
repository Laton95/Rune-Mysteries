package com.laton95.runemysteries.reference;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class WorldGenReference {
	public static List<BiomeDictionary.Type> airAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> airAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> astralAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> astralAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> bloodAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> bloodAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> bodyAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> bodyAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> deathAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> deathAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> earthAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> earthAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> fireAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> fireAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> lawAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> lawAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> mindAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> mindAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> natureAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> natureAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> soulAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> soulAltarBiomesN = new LinkedList<>();
	public static List<BiomeDictionary.Type> waterAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> waterAltarBiomesN = new LinkedList<>();
	
	public static List<BiomeDictionary.Type> cosmicAltarBiomes = new LinkedList<>();
	public static List<BiomeDictionary.Type> chaosAltarBiomes = new LinkedList<>();

	public static void init() {
		setupRuneAltarSpawnBiomes();
	}

	public static void setupRuneAltarSpawnBiomes() {
//		BiomeDictionary.Type.CONIFEROUS
//		BiomeDictionary.Type.FOREST
//		BiomeDictionary.Type.JUNGLE
//		BiomeDictionary.Type.MESA
//		BiomeDictionary.Type.MOUNTAIN
//		BiomeDictionary.Type.MUSHROOM
//		BiomeDictionary.Type.PLAINS
//		BiomeDictionary.Type.SANDY
//		BiomeDictionary.Type.SAVANNA
//		BiomeDictionary.Type.SNOWY
//		BiomeDictionary.Type.SWAMP
		
		airAltarBiomes.add(BiomeDictionary.Type.MESA);
		airAltarBiomes.add(BiomeDictionary.Type.FOREST);
		airAltarBiomes.add(BiomeDictionary.Type.PLAINS);
		
		astralAltarBiomes.add(BiomeDictionary.Type.MUSHROOM);
		astralAltarBiomes.add(BiomeDictionary.Type.SNOWY);
		
		bloodAltarBiomes.add(BiomeDictionary.Type.SWAMP);
		
		bodyAltarBiomes.add(BiomeDictionary.Type.CONIFEROUS);
		bodyAltarBiomes.add(BiomeDictionary.Type.FOREST);
		bodyAltarBiomes.add(BiomeDictionary.Type.PLAINS);
		
		deathAltarBiomes.add(BiomeDictionary.Type.MOUNTAIN);
		
		earthAltarBiomes.add(BiomeDictionary.Type.SAVANNA);
		earthAltarBiomes.add(BiomeDictionary.Type.FOREST);
		earthAltarBiomes.add(BiomeDictionary.Type.PLAINS);
		
		fireAltarBiomes.add(BiomeDictionary.Type.SANDY);
		
		lawAltarBiomes.add(BiomeDictionary.Type.FOREST);
		lawAltarBiomes.add(BiomeDictionary.Type.PLAINS);
		
		mindAltarBiomes.add(BiomeDictionary.Type.PLAINS);
		mindAltarBiomes.add(BiomeDictionary.Type.DEAD);
		mindAltarBiomes.add(BiomeDictionary.Type.WASTELAND);
		
		natureAltarBiomes.add(BiomeDictionary.Type.JUNGLE);
		
		soulAltarBiomes.add(BiomeDictionary.Type.SANDY);
		soulAltarBiomesN.add(BiomeDictionary.Type.MESA);
		
		waterAltarBiomes.add(BiomeDictionary.Type.SWAMP);
		
		cosmicAltarBiomes.add(BiomeDictionary.Type.END);
		
		chaosAltarBiomes.add(BiomeDictionary.Type.NETHER);
	}
}
