package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.RunElement;

import org.w3c.dom.css.ElementCSSInlineStyle;

import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;
import com.laton95.runemysteries.utility.AltarNBTHelper;
import com.mojang.realmsclient.dto.WorldDownload;
import com.sun.jna.platform.unix.X11;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureMineshaftPieces.Room;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.Constants.NBT;
import scala.collection.generic.BitOperations.Int;
import scala.reflect.api.Trees.ReturnExtractor;
import scala.sys.process.processInternal;
import scala.tools.nsc.interpreter.IMain.TruncatingWriter;

public class AltarTracker {
	protected List<RuneAltar> runeAltars = new ArrayList<>();
	protected List<RuneAltar> generatedAltars = new ArrayList<>();
	//Default radius an altar can spawn around the selected placement point, in chunks
	protected int defaultAltarRadius = 4;
	protected float defaultAltarFlatnessTolerance = 0.8f;
	//Minimum distance between altars, in chunks
	protected int altarMinSpread = 10;
	//Number of generation tries before altar placement tolerance is increased
	public int warningFailureCount = 12;
	//Number of generation tries before altar placement tolerance is set to maximum
	public int panicFailureCount = 81;
	
	protected World world;
	protected AltarNBTHelper altarNBTHelper;
	
	public AltarTracker(World world){
		this.world = world;
	}
	
	public boolean inGenerationRange(ChunkPos pos){
		for (RuneAltar altar : generatedAltars) {
			if (WorldHelper.isNearby(pos, new ChunkPos(altar.getPosition()), altar.getPlacementRadius())) {
				return true;
			}
		}
		return false;
	}
	
	public RuneAltar getAltar(ChunkPos pos) {
		List<RuneAltar> nearbyAltars = new ArrayList<>();
		for (RuneAltar altar : generatedAltars) {
			ChunkPos pos2 = new ChunkPos(altar.getPosition());
			if (WorldHelper.isNearby(pos, pos2, altar.getPlacementRadius())) {
				nearbyAltars.add(altar);
			}
		}
		RuneAltar altar = nearbyAltars.get(new Random().nextInt(nearbyAltars.size()));
		return altar;
	}
	
	public RuneAltar getAltar(String name) {
		for (RuneAltar altar : generatedAltars) {
			if (altar.getName().equals(name)) {
				return altar;
			}
		}
		return null;
	}
	
	public void altarGenerated(RuneAltar altar) {
		runeAltars.remove(altar);
	}
	
	public void findLocations(World world) {
		this.world = world;
		altarNBTHelper = AltarNBTHelper.get(world);
		
		List<RuneAltar> outStandingAltars = new LinkedList<>();
		List<BiomeDictionary.Type> allowedBiomes = new LinkedList<>();
		Random random = new Random();
		
		if (!altarNBTHelper.altarsGenerated) {
			LogHelper.info("Finding altar locations");
			outStandingAltars.addAll(runeAltars);
			random.setSeed(world.getSeed() * 2845);
			for (RuneAltar altar : outStandingAltars) {
				allowedBiomes.addAll(altar.getBiomes());
			}
		} else {
			LogHelper.info("Loading altar locations");
			for (RuneAltar runeAltar : runeAltars) {
				runeAltar.updatePosition();
				if (runeAltar.position != null) {
					generatedAltars.add(runeAltar);
				}
				
			}
		}
		
		while (!outStandingAltars.isEmpty()) {
			RuneAltar pickedAltar = null;
			List<BiomeDictionary.Type> outStandingBiomes = new LinkedList<>();
			for (RuneAltar altar : outStandingAltars) {
				outStandingBiomes.addAll(altar.getBiomes());
			}
			
			BlockPos pos = findBiomePosition(outStandingBiomes, random, ConfigReference.runeAltarTries, ConfigReference.runeAltarRange/16);
			
			if (pos != null) {
				for (RuneAltar altar : outStandingAltars) {
					if (altar.isBiomeViable(world.getBiome(pos))) {
						altar.setPosition(pos);
						LogHelper.info(altar);
						generatedAltars.add(altar);
						pickedAltar = altar;
						break;
					}
				}
			} else {
				for (RuneAltar altar : outStandingAltars) {
					LogHelper.info("Could not find biome, placing " + altar.getName() + " randomly");
					pos = findBiomePosition(allowedBiomes, random, ConfigReference.runeAltarTries, ConfigReference.runeAltarRange/16);
					if (pos != null) {
						altar.setPosition(pos);
						LogHelper.info(altar);
						generatedAltars.add(altar);
						pickedAltar = altar;
						break;
					} else {
						LogHelper.info("Unable to find compadible biome, but this altar is going down someplace goddammit!");
						pos = findBiomePosition(new LinkedList<BiomeDictionary.Type>(), random, ConfigReference.runeAltarTries, ConfigReference.runeAltarRange/16);
						altar.setPosition(pos);
						LogHelper.info(altar);
						generatedAltars.add(altar);
						pickedAltar = altar;
						break;
					}
				}
			}
			if (pickedAltar != null) {
				outStandingAltars.remove(pickedAltar);
			}
		}
		
		altarNBTHelper.altarsGenerated = true;
		altarNBTHelper.markDirty();
	}
	
	protected BlockPos findBiomePosition(List<BiomeDictionary.Type> biomes, Random rand, int attempts, int radius) {
		for (int i = 0; i < attempts; i++) {
			ChunkPos pos = new ChunkPos(rand.nextInt(radius*2)-radius, rand.nextInt(radius*2)-radius);
			BlockPos pos2 = new BlockPos(pos.getXStart(), 0, pos.getZStart());
			Biome biome = world.getBiome(pos2);
			if (!biomes.isEmpty()) {
				for (BiomeDictionary.Type type : biomes) {
					if (BiomeDictionary.hasType(biome, type)) {
						return pos2;
					}
				}
			} else return pos2;
		}
		return null;
	}
	
	@Override
	public String toString() {
		int placed = 0;
		int total = 0;
		for (RuneAltar altar : runeAltars) {
			total++;
			if (altar.isPlaced()) {
				placed++;
			}
		}
		
		return placed + "/" + total;
	}
	
	public class RuneAltar {
		private final String name;
		private final String room;
		private boolean placed;
		private boolean biomeDependant;
		private int placementRadius;
		private Float flatnessTolerance;
		private int failureCount;
		private BlockPos position;
		private List<BiomeDictionary.Type> biomes;
		private List<BiomeDictionary.Type> biomesN;
		private Type type;
		
		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes, List<BiomeDictionary.Type> biomesN, Type type) {
			this.name = name;
			this.placed = false;
			this.biomeDependant = true;
			this.placementRadius = placementRadius;
			this.flatnessTolerance = flatnessTolerance;
			this.failureCount = 0;
			this.biomes = biomes;
			this.type = type;
			this.room = null;
			this.biomesN = biomesN;
		}
		
		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes, List<BiomeDictionary.Type> biomesN, Type type, String room) {
			this.name = name;
			this.placed = false;
			this.biomeDependant = true;
			this.placementRadius = placementRadius;
			this.flatnessTolerance = flatnessTolerance;
			this.failureCount = 0;
			this.biomes = biomes;
			this.type = type;
			this.room = room;
			this.biomesN = biomesN;
		}

		public boolean isPlaced() {
			return placed;
		}

		public void setPlaced(boolean placed) {
			this.placed = placed;
		}

		public boolean isBiomeDependant() {
			return biomeDependant;
		}

		public void setBiomeDependant(boolean biomeDependant) {
			this.biomeDependant = biomeDependant;
		}

		public int getPlacementRadius() {
			return placementRadius;
		}

		public void setPlacementRadius(int placementRadius) {
			this.placementRadius = placementRadius;
		}
		
		public void incrementPlacementRadius(int radius) {
			this.placementRadius += radius;
		}

		public Float getFlatnessTolerance() {
			return flatnessTolerance;
		}

		public void setFlatnessTolerance(Float flatnessTolerance) {
			this.flatnessTolerance = flatnessTolerance;
		}
		
		public void decrementFlatnessTolerance(Float tolerance) {
			this.flatnessTolerance -= tolerance;
		}

		public int getFailureCount() {
			return failureCount;
		}

		public void incrementFailureCount(int count) {
			this.failureCount += count;
		}

		public String getName() {
			return name;
		}
		
		public String getRoom() {
			return room;
		}

		public BlockPos getPosition() {
			return position;
		}

		public void setPosition(BlockPos position) {
			this.position = position;
			altarNBTHelper.posMap.put(name, position);
			altarNBTHelper.markDirty();
		}
		
		public void updatePosition() {
			this.position = altarNBTHelper.posMap.get(name);
		}
		
		public List<BiomeDictionary.Type> getBiomes() {
			return biomes;
		}
		
		public boolean isBiomeViable(Biome biome) {
			for (BiomeDictionary.Type type : biomes) {
				if (!biomesN.isEmpty()) {
					for (BiomeDictionary.Type typeN : biomesN) {
						if (BiomeDictionary.hasType(biome, type) && !BiomeDictionary.hasType(biome, typeN)) {
							return true;
						}
					}
				} else if (BiomeDictionary.hasType(biome, type)) {
					return true;
				}
				
			}
			return false;
		}
		
		public Type getType() {
			return this.type;
		}

		@Override
		public String toString() {
			return name + " at " + position;
		}
		
		
	}

	public enum Type {
	    SURFACE, UNDERGROUND, SOUL, NETHER, END
	}
}
