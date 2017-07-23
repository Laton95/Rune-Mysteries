package com.laton95.runemysteries.world.altarStructures;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenBloodTemple extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenBloodTemple(World world) {
		this.world = world;
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "AirMountain";
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
		return new MapGenBloodTemple.Start(this.world, this.rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeAltarSpawnList;
	}

	public static class Start extends StructureStart {
		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);
			ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
			this.components.add(componentBloodTemple);
			this.updateBoundingBox();
		}
	}
}