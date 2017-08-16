package com.laton95.runemysteries.world.mapGenerators;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.structureComponents.ComponentUndergroundAltar;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar_UNDERGROUND extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar_UNDERGROUND() {
		runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	@Override
	public String getStructureName() {
		return "RuneAltarUnderground";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (WorldGenerator.altarTracker != null) {
			if (!WorldGenerator.altarTracker.overworldAltarsFound) {
				WorldGenerator.altarTracker.findOverworldLocations(world);
			}
		} else {
			WorldGenerator.altarTracker = new AltarTracker();
			WorldGenerator.altarTracker.findOverworldLocations(world);
		}

		return WorldGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ), 0,
				AltarTracker.Type.UNDERGROUND);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar_UNDERGROUND.Start(world, rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return runeAltarSpawnList;
	}

	public static class Start extends StructureStart {
		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);

			AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar(new ChunkPos(chunkX, chunkZ),
					worldIn.provider.getDimension());

			if (altar != null && !altar.isPlaced()) {
				if (!altar.isBiomeDependant() || altar.isBiomeViable(biomeIn)) {
					StructureBoundingBox bBox;
					BlockPos altarPos;
					BlockPos altarPos2;

					ComponentUndergroundAltar componentRuneAltar = new ComponentUndergroundAltar(random, chunkX * 16,
							chunkZ * 16, altar.getName(), altar.getRoom(), altar.getYOffset());
					bBox = componentRuneAltar.getBoundingBox();

					altarPos = new BlockPos(bBox.minX, random.nextInt(25) + 15, bBox.minZ);
					altarPos2 = new BlockPos(bBox.maxX, altarPos.getY() + bBox.getYSize(), bBox.maxZ);
					componentRuneAltar.setBoundingBox(new StructureBoundingBox(altarPos, altarPos2));
					// Altar generated
					altar.setPlaced(true);
					altar.setPosition(new BlockPos(altarPos.getX() + 7, altarPos.getY() + 2, altarPos.getZ() + 7));
					altar.setPlacementRadius(0);
					LogHelper.info(altar.toString());
					components.add(componentRuneAltar);
				} else {
					// Altar failed to generate because incorrect biome
					panic(altar);
				}
			}
			updateBoundingBox();
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