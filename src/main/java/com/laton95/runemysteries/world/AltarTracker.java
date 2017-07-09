package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;
import com.mojang.realmsclient.dto.WorldDownload;
import com.sun.jna.platform.unix.X11;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import scala.collection.generic.BitOperations.Int;

public class AltarTracker {
	private Map<String, BlockPos> altarLocations = new HashMap<>();
	private Map<String, Boolean> altarPlaced = new HashMap<>();
	private Map<String, Boolean> altarBiomeDependant = new HashMap<>();
	private Map<String, Integer> altarRadius = new HashMap<>();
	private Map<String, Float> altarFlatnessTolerance = new HashMap<>();
	private Map<String, Integer> altarFailureCount = new HashMap<>();
	//Default radius an altar can spawn around the selected placement point, in chunks
	private int defaultAltarRadius = 4;
	private float defaultAltarFlatnessTolerance = 0.8f;
	
	private boolean flag;
	private String altar;
	private List<String> altars = new ArrayList<>();
	
	public AltarTracker() {
		altarPlaced.put("astralaltar", false);
		altarPlaced.put("deathaltar", false);
		altarPlaced.put("bodyaltar", false);
		altarPlaced.put("bloodaltar", false);
		altarPlaced.put("airaltar", false);
		altarPlaced.put("mindaltar", false);
		altarPlaced.put("earthaltar", false);
		altarPlaced.put("wateraltar", false);
		altarPlaced.put("soulaltar", false);
		altarPlaced.put("lawaltar", false);
		altarPlaced.put("firealtar", false);
		altarPlaced.put("naturealtar", false);
		
		altarBiomeDependant.put("astralaltar", true);
		altarBiomeDependant.put("deathaltar", true);
		altarBiomeDependant.put("bodyaltar", true);
		altarBiomeDependant.put("bloodaltar", true);
		altarBiomeDependant.put("airaltar", true);
		altarBiomeDependant.put("mindaltar", true);
		altarBiomeDependant.put("earthaltar", true);
		altarBiomeDependant.put("wateraltar", true);
		altarBiomeDependant.put("soulaltar", true);
		altarBiomeDependant.put("lawaltar", true);
		altarBiomeDependant.put("firealtar", true);
		altarBiomeDependant.put("naturealtar", true);
		
		altarRadius.put("astralaltar", defaultAltarRadius);
		altarRadius.put("deathaltar", defaultAltarRadius);
		altarRadius.put("bodyaltar", defaultAltarRadius);
		altarRadius.put("bloodaltar", defaultAltarRadius);
		altarRadius.put("airaltar", defaultAltarRadius);
		altarRadius.put("mindaltar", defaultAltarRadius);
		altarRadius.put("earthaltar", defaultAltarRadius);
		altarRadius.put("wateraltar", defaultAltarRadius);
		altarRadius.put("soulaltar", defaultAltarRadius);
		altarRadius.put("lawaltar", defaultAltarRadius);
		altarRadius.put("firealtar", defaultAltarRadius);
		altarRadius.put("naturealtar", defaultAltarRadius);
		
		altarFlatnessTolerance.put("astralaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("deathaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("bodyaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("bloodaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("airaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("mindaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("earthaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("wateraltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("soulaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("lawaltar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("firealtar", defaultAltarFlatnessTolerance);
		altarFlatnessTolerance.put("naturealtar", defaultAltarFlatnessTolerance);
		
		altarFailureCount.put("astralaltar", 0);
		altarFailureCount.put("deathaltar", 0);
		altarFailureCount.put("bodyaltar", 0);
		altarFailureCount.put("bloodaltar", 0);
		altarFailureCount.put("airaltar", 0);
		altarFailureCount.put("mindaltar", 0);
		altarFailureCount.put("earthaltar", 0);
		altarFailureCount.put("wateraltar", 0);
		altarFailureCount.put("soulaltar", 0);
		altarFailureCount.put("lawaltar", 0);
		altarFailureCount.put("firealtar", 0);
		altarFailureCount.put("naturealtar", 0);
	}
	
	public void printAltarChunks() {
		altarLocations.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			LogHelper.info(k + " at " + vPos.getXStart() + "," + vPos.getZStart());
		});
	}
	
	public void printAltar(String altar) {
		BlockPos pos = altarLocations.get(altar);
		LogHelper.info(altar + " at " + pos.getX() + "," + pos.getY() + "," + pos.getZ());
	}
	
	public boolean isCloseToChunks(ChunkPos pos){
		flag = false;
		altarLocations.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			if (WorldHelper.isNearby(vPos, pos, getAltarPlacementRadius(k))) {
				flag = true;
			}
		});
		return flag;
	}
	
	public String getAltar(ChunkPos pos) {
		altar = "";
		altars.clear();
		altarLocations.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			if (WorldHelper.isNearby(pos, vPos, getAltarPlacementRadius(k))) {
				altars.add(k);
			}
		});
		altar = altars.get(new Random().nextInt(altars.size()));
		return altar;
	}
	
	public void addAltar(String altar, ChunkPos chunkPos){
		BlockPos pos = new BlockPos(chunkPos.getXStart(), 64, chunkPos.getZStart());
		altarLocations.put(altar, pos);
	}
	
	public void updateAltarPosition(String altar, BlockPos pos) {
		altarLocations.put(altar, pos);
	}
	
	public boolean isAltarPlaced(String altar) {
		return altarPlaced.get(altar);
	}
	
	public void setAltarPlaced(String altar, boolean placed) {
		altarPlaced.put(altar, placed);
	}
	
	public int getAltarPlacementRadius(String altar) {
		return altarRadius.get(altar);
	}
	
	public void setAltarPlacementRadius(String altar, int radius) {
		altarRadius.put(altar, radius);
	}
	
	public float getAltarFlatnessTolerance(String altar) {
		return altarFlatnessTolerance.get(altar);
	}
	
	public void setAltarFlatnessTolerance(String altar, Float tolerance) {
		altarFlatnessTolerance.put(altar, tolerance);
	}
	
	public boolean getBiomeDependancy(String altar) {
		return altarBiomeDependant.get(altar);
	}
	
	public void setBiomeDependancy(String altar, boolean dependancy) {
		altarBiomeDependant.put(altar, dependancy);
	}
	
	public void incrementFailureCount(String altar) {
		altarFailureCount.put(altar, altarFailureCount.get(altar) + 1);
	}	
	
	public int getFailureCount(String altar) {
		return altarFailureCount.get(altar);
	}
	
	public void findLocations(World world) {
		LinkedList<Biome> outStandingAltarSpawnBiomes = new LinkedList<Biome>();
		outStandingAltarSpawnBiomes.addAll(WorldGenReference.allAltarSpawnBiomes);
		outStandingAltarSpawnBiomes.removeAll(WorldGenReference.chaosAltarSpawnBiomes);
		outStandingAltarSpawnBiomes.removeAll(WorldGenReference.cosmicAltarSpawnBiomes);
		
		List<String> genericAltars = new ArrayList<>();
		genericAltars.add("airaltar");
		genericAltars.add("mindaltar");
		genericAltars.add("earthaltar");
		genericAltars.add("bodyaltar");
		genericAltars.add("lawaltar");
		
		List<String> swampAltars = new ArrayList<>();
		swampAltars.add("wateraltar");
		swampAltars.add("bloodaltar");
		
		List<String> desertAltars = new ArrayList<>();
		desertAltars.add("firealtar");
		desertAltars.add("soulaltar");
		
		List<String> outStandingAltars = new ArrayList<>();
		outStandingAltars.addAll(genericAltars);
		outStandingAltars.addAll(swampAltars);
		outStandingAltars.addAll(desertAltars);
		outStandingAltars.add("naturealtar");
		outStandingAltars.add("astralaltar");
		outStandingAltars.add("deathaltar");
		
		Random random = new Random();
		random.setSeed(world.getSeed() * 1337);
		
		while (!outStandingAltarSpawnBiomes.isEmpty()) {
			BlockPos blockpos = world.getBiomeProvider().findBiomePosition(0,0, ConfigReference.runeAltarRange, outStandingAltarSpawnBiomes, random);
			
			if (blockpos != null) {
				Biome biome = world.getBiome(blockpos);
				
				if (WorldGenReference.genericAltarSpawnBiomes.contains(biome)) {
					addAltar(genericAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + genericAltars.get(0) + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(genericAltars.get(0));
					genericAltars.remove(0);
					if (genericAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.genericAltarSpawnBiomes);
					}
				} else if (WorldGenReference.swampAltarSpawnBiomes.contains(biome)) {
					addAltar(swampAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + swampAltars.get(0) + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(swampAltars.get(0));
					swampAltars.remove(0);
					if (swampAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.swampAltarSpawnBiomes);
					}
				} else if (WorldGenReference.desertAltarSpawnBiomes.contains(biome)) {
					addAltar(desertAltars.get(0), new ChunkPos(blockpos));
					LogHelper.info("Found " + desertAltars.get(0) + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(desertAltars.get(0));
					desertAltars.remove(0);
					if (desertAltars.isEmpty()) {
						outStandingAltarSpawnBiomes.removeAll(WorldGenReference.desertAltarSpawnBiomes);
					}
				} else if (WorldGenReference.natureAltarSpawnBiomes.contains(biome)) {
					String name = "naturealtar";
					LogHelper.info("Found " + name + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(name);
					addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.natureAltarSpawnBiomes);
				} else if (WorldGenReference.astralAltarSpawnBiomes.contains(biome)) {
					String name = "astralaltar";
					LogHelper.info("Found " + name + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(name);
					addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.astralAltarSpawnBiomes);
				} else if (WorldGenReference.deathAltarSpawnBiomes.contains(biome)) {
					String name = "deathaltar";
					LogHelper.info("Found " + name + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
					outStandingAltars.remove(name);
					addAltar(name, new ChunkPos(blockpos));
					outStandingAltarSpawnBiomes.removeAll(WorldGenReference.deathAltarSpawnBiomes);
				}
			} else {
				LogHelper.info("Could not find biome, placing " + outStandingAltars.get(0) + " randomly");
				blockpos = world.getBiomeProvider().findBiomePosition(0,0, ConfigReference.runeAltarRange, WorldGenReference.allAltarSpawnBiomes, world.rand);
				String name = outStandingAltars.get(0);
				Biome biome = world.getBiomeProvider().getBiome(blockpos);
				LogHelper.info("Found " + name + " in " + biome.getBiomeName() + " at " + blockpos.getX() + "," + blockpos.getZ());
				outStandingAltars.remove(name);
				addAltar(name, new ChunkPos(blockpos));
				
				if(outStandingAltars.isEmpty()) {
					outStandingAltarSpawnBiomes.clear();
				}
			}
		}
	}
}
