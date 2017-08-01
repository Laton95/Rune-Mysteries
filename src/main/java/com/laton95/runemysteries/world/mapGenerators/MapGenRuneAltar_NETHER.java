package com.laton95.runemysteries.world.mapGenerators;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.AltarTracker.RuneAltar;
import com.laton95.runemysteries.world.AltarTracker.Type;
import com.laton95.runemysteries.world.structureComponents.ComponentNetherAltar;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar_NETHER extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar_NETHER() {
		runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	@Override
	public String getStructureName() {
		return "RuneAltarNether";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (WorldGenerator.altarTracker != null) {
			if (!WorldGenerator.altarTracker.netherAltarsFound) {
				WorldGenerator.altarTracker.findNetherLocations(world);
			}
		} else {
			WorldGenerator.altarTracker = new AltarTracker();
			WorldGenerator.altarTracker.findNetherLocations(world);
		}

		return WorldGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ), -1,
				AltarTracker.Type.NETHER);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar_NETHER.Start(world, rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return runeAltarSpawnList;
	}

	public static class Start extends StructureStart {

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);

			AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar("chaos_altar");

			if (altar != null && !altar.isPlaced()) {
				StructureBoundingBox bBox;
				BlockPos altarPos;
				ComponentNetherAltar componentRuneAltar = new ComponentNetherAltar(random,
						chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1, altar.getName());
				bBox = componentRuneAltar.getBoundingBox();
				altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
				if (!WorldHelper.isInsideCave(worldIn, altarPos, bBox.getXSize(), bBox.getZSize())) {
					// Altar generated
					altar.setPlaced(true);
					altar.setPosition(new BlockPos(altarPos.getX() + 4, altarPos.getY() + 1, altarPos.getZ() + 4));
					altar.setPlacementRadius(0);
					LogHelper.info(altar.toString());
					components.add(componentRuneAltar);
				} else {
					// Altar failed to generate because inside nether wall
					panic(altar);
				}
				updateBoundingBox();
			}
		}

		private void panic(AltarTracker.RuneAltar altar) {
			altar.incrementFailureCount(1);
			if (altar.getFailureCount() > WorldGenerator.altarTracker.warningFailureCount) {
				altar.incrementPlacementRadius(5);
				altar.decrementFlatnessTolerance(0.02f);
				if (altar.getFailureCount() > WorldGenerator.altarTracker.panicFailureCount) {
					altar.incrementPlacementRadius(20);
					altar.setBiomeDependant(false);
					altar.setFlatnessTolerance(0.1f);
				}
			}
		}
	}
}