package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.utility.AltarNBTHelper;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class AltarTracker {
	protected List<RuneAltar> runeAltars = new ArrayList<>();
	protected List<RuneAltar> generatedAltars = new ArrayList<>();
	// Default radius an altar can spawn around the selected placement point, in
	// chunks
	protected int defaultAltarRadius = 4;
	protected float defaultAltarFlatnessTolerance = 0.8f;
	// Minimum distance between altars, in chunks
	protected int altarMinSpread = 10;
	// Number of generation tries before altar placement tolerance is increased
	public int warningFailureCount = 12;
	// Number of generation tries before altar placement tolerance is set to
	// maximum
	public int panicFailureCount = 81;

	protected World world;
	protected AltarNBTHelper altarNBTHelper;

	public AltarTracker(World world) {
		this.world = world;
	}

	public boolean inGenerationRange(ChunkPos pos) {
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
		int randomDimSeed = 0;
		switch (world.provider.getDimensionType()) {
		case OVERWORLD:
			randomDimSeed = 2849;
			break;
		case NETHER:
			randomDimSeed = 4311;
			break;
		case THE_END:
			randomDimSeed = 1337;
			break;
		}

		if (!altarNBTHelper.overworldAltarsGenerated && world.provider.getDimensionType() == DimensionType.OVERWORLD
				|| !altarNBTHelper.netherAltarsGenerated && world.provider.getDimensionType() == DimensionType.NETHER
				|| !altarNBTHelper.endAltarsGenerated && world.provider.getDimensionType() == DimensionType.THE_END) {
			LogHelper.info("Finding altar locations");
			outStandingAltars.addAll(runeAltars);
			random.setSeed(world.getSeed() * randomDimSeed);
			for (RuneAltar altar : outStandingAltars) {
				allowedBiomes.addAll(altar.getBiomes());
			}
			switch (world.provider.getDimensionType()) {
			case OVERWORLD:
				altarNBTHelper.overworldAltarsGenerated = true;
				break;
			case NETHER:
				altarNBTHelper.netherAltarsGenerated = true;
				break;
			case THE_END:
				altarNBTHelper.endAltarsGenerated = true;
				break;
			}
			altarNBTHelper.markDirty();
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

			BlockPos pos = findBiomePosition(outStandingBiomes, random, ConfigReference.runeAltarTries,
					ConfigReference.maxRuneAltarRange / 16, ConfigReference.minRuneAltarRange / 16);

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
					pos = findBiomePosition(allowedBiomes, random, ConfigReference.runeAltarTries,
							ConfigReference.maxRuneAltarRange / 16, ConfigReference.minRuneAltarRange / 16);
					if (pos != null) {
						altar.setPosition(pos);
						LogHelper.info(altar);
						generatedAltars.add(altar);
						pickedAltar = altar;
						altar.setBiomeDependant(false);
						break;
					} else {
						LogHelper.info(
								"Unable to find compadible biome, but this altar is going down someplace goddammit!");
						pos = findBiomePosition(new LinkedList<BiomeDictionary.Type>(), random,
								ConfigReference.runeAltarTries, ConfigReference.maxRuneAltarRange / 16, ConfigReference.minRuneAltarRange / 16);
						altar.setPosition(pos);
						LogHelper.info(altar);
						generatedAltars.add(altar);
						pickedAltar = altar;
						altar.setBiomeDependant(false);
						break;
					}
				}
			}
			if (pickedAltar != null) {
				outStandingAltars.remove(pickedAltar);
			}
		}
	}

	protected BlockPos findBiomePosition(List<BiomeDictionary.Type> biomes, Random rand, int attempts, int radius, int min) {
		for (int i = 0; i < attempts; i++) {
			int chunkX = (rand.nextInt(radius-min) + min)*(rand.nextBoolean() ? 1 : -1);
			int chunkZ = (rand.nextInt(radius-min) + min)*(rand.nextBoolean() ? 1 : -1);
			
			ChunkPos pos = new ChunkPos(chunkX, chunkZ);
			BlockPos pos2 = new BlockPos(pos.getXStart(), 0, pos.getZStart());
			Biome biome = world.getBiome(pos2);
			if (!biomes.isEmpty()) {
				for (BiomeDictionary.Type type : biomes) {
					if (BiomeDictionary.hasType(biome, type)) {
						return pos2;
					}
				}
			} else
				return pos2;
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
		private int yOffset;

		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes,
				List<BiomeDictionary.Type> biomesN, Type type) {
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
			this.yOffset = 0;
		}

		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes,
				List<BiomeDictionary.Type> biomesN, Type type, String room, int yOffset) {
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
			this.yOffset = yOffset;
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
		
		public int getYOffset() {
			return yOffset;
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
			if (biomes.isEmpty()) {
				for (BiomeDictionary.Type typeN : biomesN) {
					if (BiomeDictionary.hasType(biome, typeN)) {
						return false;
					}
				}
				return true;
			}
			
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
