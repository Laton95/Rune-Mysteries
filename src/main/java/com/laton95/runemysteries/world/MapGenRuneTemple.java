package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.utility.ModConfig;
import com.laton95.runemysteries.world.structureComponents.ComponentAirTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBloodTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentBodyTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentChaosTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentCosmicTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentDeathTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentEarthTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentFireTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentLawTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentMindTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentNatureTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentSoulTemple;
import com.laton95.runemysteries.world.structureComponents.ComponentWaterTemple;

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
		if (chunkX == -1 && chunkZ == -1) {
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
			if (dimensionID == ModConfig.dimensions.airTempleDimID) {
				ComponentAirTemple componentAirTemple = new ComponentAirTemple(random, -16, -16);
				components.add(componentAirTemple);
			} else if (dimensionID == ModConfig.dimensions.bloodTempleDimID) {
				ComponentBloodTemple componentBloodTemple = new ComponentBloodTemple(random, -16, -16);
				components.add(componentBloodTemple);
			} else if (dimensionID == ModConfig.dimensions.bodyTempleDimID) {
				ComponentBodyTemple componentBodyTemple = new ComponentBodyTemple(random, -13, -14);
				components.add(componentBodyTemple);
			} else if (dimensionID == ModConfig.dimensions.chaosTempleDimID) {
				ComponentChaosTemple componentChaosTemple = new ComponentChaosTemple(random, -4, -4);
				components.add(componentChaosTemple);
			} else if (dimensionID == ModConfig.dimensions.cosmicTempleDimID) {
				ComponentCosmicTemple componentCosmicTemple = new ComponentCosmicTemple(random);
				components.add(componentCosmicTemple);
			} else if (dimensionID == ModConfig.dimensions.deathTempleDimID) {
				ComponentDeathTemple componentDeathTemple = new ComponentDeathTemple(random, -16, -16);
				components.add(componentDeathTemple);
			} else if (dimensionID == ModConfig.dimensions.earthTempleDimID) {
				ComponentEarthTemple componentEarthTemple = new ComponentEarthTemple(random, -16, -16);
				components.add(componentEarthTemple);
			} else if (dimensionID == ModConfig.dimensions.fireTempleDimID) {
				ComponentFireTemple componentFireTemple = new ComponentFireTemple(random, -16, -16);
				components.add(componentFireTemple);
			} else if (dimensionID == ModConfig.dimensions.lawTempleDimID) {
				ComponentLawTemple componentLawTemple = new ComponentLawTemple(random, -14, -14);
				components.add(componentLawTemple);
			} else if (dimensionID == ModConfig.dimensions.mindTempleDimID) {
				ComponentMindTemple componentMindTemple = new ComponentMindTemple(random, -16, -16);
				components.add(componentMindTemple);
			} else if (dimensionID == ModConfig.dimensions.natureTempleDimID) {
				ComponentNatureTemple componentNatureTemple = new ComponentNatureTemple(random, -11, -11);
				components.add(componentNatureTemple);
			} else if (dimensionID == ModConfig.dimensions.soulTempleDimID) {
				ComponentSoulTemple componentSoulTemple = new ComponentSoulTemple(random, -16, -16);
				components.add(componentSoulTemple);
			} else if (dimensionID == ModConfig.dimensions.waterTempleDimID) {
				ComponentWaterTemple componentWaterTemple = new ComponentWaterTemple(random, -16, -16);
				components.add(componentWaterTemple);
			}

			updateBoundingBox();
		}
	}
}