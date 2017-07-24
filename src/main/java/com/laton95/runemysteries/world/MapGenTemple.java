package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.handler.ModConfig;
import com.laton95.runemysteries.world.structureComponents.ComponentAirTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBloodTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBodyTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentDeathTemple;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenTemple extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeTempleSpawnList;

	public MapGenTemple(World world) {
		this.world = world;
		this.runeTempleSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "RuneTemple";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (chunkX == -1 && chunkZ == -1) {
			return true;
		} else
			return false;
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenTemple.Start(this.world, this.rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune temples
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeTempleSpawnList;
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
			if (dimensionID == ModConfig.dimensions.airTempleDimID) {
				ComponentAirTemple componentAirTemple = new ComponentAirTemple(random, -16, -16);
				this.components.add(componentAirTemple);
			} else if (dimensionID == ModConfig.dimensions.bloodTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.bodyTempleDimID) {
				ComponentBodyTemple componentBodyTemple = new ComponentBodyTemple(random, -13, -14);
				this.components.add(componentBodyTemple);
			} else if (dimensionID == ModConfig.dimensions.chaosTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.cosmicTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.deathTempleDimID) {
				ComponentDeathTemple componentDeathTemple = new ComponentDeathTemple(random, -16, -16);
				this.components.add(componentDeathTemple);
			} else if (dimensionID == ModConfig.dimensions.earthTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.fireTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.lawTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.mindTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.natureTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.soulTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.waterTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				this.components.add(componentBloodTemple);
			}
			
			this.updateBoundingBox();
		}
	}
}