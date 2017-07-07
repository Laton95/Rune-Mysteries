package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.reference.WorldGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar() {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "RuneAltar";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		return true;
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		this.world = worldIn;
		return findNearestStructurePosBySpacing(worldIn, this, pos, 16, 8, 1024, false,
				100, findUnexplored);
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar.Start(this.world, this.rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for scattered features
	 */
	public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList() {
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

			ComponentRuneAltar componentRuneAltar = new ComponentRuneAltar(random, chunkX * 16, chunkZ * 16);
			this.components.add(componentRuneAltar);

			this.updateBoundingBox();
		}
	}
}