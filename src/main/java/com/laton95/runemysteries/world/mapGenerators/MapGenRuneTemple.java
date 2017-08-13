package com.laton95.runemysteries.world.mapGenerators;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.ModConfig;
import com.laton95.runemysteries.world.structureComponents.ComponentCosmicTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentTemple;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneTemple extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeTempleSpawnList;

	public MapGenRuneTemple(World world) {
		this.world = world;
		runeTempleSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	@Override
	public String getStructureName() {
		return "RuneTemple";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if ((chunkX == 0 && chunkZ == 0) 
				|| (chunkX == -1 && chunkZ == 0) 
				|| (chunkX == 0 && chunkZ == -1) 
				|| (chunkX == -1 && chunkZ == -1)) 
		{
			return true;
		} else {
			return false;
		}
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneTemple.Start(world, rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune temples
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return runeTempleSpawnList;
	}

	public static class Start extends StructureStart {
		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);
			int dimensionID = worldIn.provider.getDimension();
			if (dimensionID == ModConfig.DIMENSIONS.airTempleDimID) {
				ComponentTemple componentAirTemple = new ComponentTemple(chunkX, chunkZ, "air", new BlockPos(-5, 87, -6), 63);
				components.add(componentAirTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.bloodTempleDimID) {
				ComponentTemple componentBloodTemple = new ComponentTemple(chunkX, chunkZ, "blood", new BlockPos(-7, 86, 6), 84);
				components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.bodyTempleDimID) {
				ComponentTemple componentBodyTemple = new ComponentTemple(chunkX, chunkZ, "body", new BlockPos(7, 87, 3), 86);
				components.add(componentBodyTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.chaosTempleDimID) {
				ComponentTemple componentChaosTemple = new ComponentTemple(chunkX, chunkZ, "chaos", new BlockPos(3, 86, -3), 86);
				components.add(componentChaosTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.deathTempleDimID) {
				ComponentTemple componentDeathTemple = new ComponentTemple(chunkX, chunkZ, "death", new BlockPos(3, 86, 6), 84);
				components.add(componentDeathTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.earthTempleDimID) {
				ComponentTemple componentEarthTemple = new ComponentTemple(chunkX, chunkZ, "earth", new BlockPos(9, 87, 0), 86);
				components.add(componentEarthTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.fireTempleDimID) {
				ComponentTemple componentFireTemple = new ComponentTemple(chunkX, chunkZ, "fire", new BlockPos(0, 86, -9), 83);
				components.add(componentFireTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.lawTempleDimID) {
				ComponentTemple componentLawTemple = new ComponentTemple(chunkX, chunkZ, "law", new BlockPos(5, 87, -6), 86);
				components.add(componentLawTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.mindTempleDimID) {
				ComponentTemple componentMindTemple = new ComponentTemple(chunkX, chunkZ, "mind", new BlockPos(-4, 87, 1), 86);
				components.add(componentMindTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.natureTempleDimID) {
				ComponentTemple componentNatureTemple = new ComponentTemple(chunkX, chunkZ, "nature", new BlockPos(-7, 86, -6), 84);
				components.add(componentNatureTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.soulTempleDimID) {
				ComponentTemple componentSoulTemple = new ComponentTemple(chunkX, chunkZ, "soul", new BlockPos(-12, 86, 0), 80);
				components.add(componentSoulTemple);
			} else if (dimensionID == ModConfig.DIMENSIONS.waterTempleDimID) {
				ComponentTemple componentWaterTemple = new ComponentTemple(chunkX, chunkZ, "water", new BlockPos(0, 86, -9), 83);
				components.add(componentWaterTemple);
			}

			updateBoundingBox();
		}
	}
}