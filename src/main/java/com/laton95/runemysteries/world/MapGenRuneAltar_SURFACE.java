package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;
import com.laton95.runemysteries.world.AltarTracker.Type;
import com.laton95.runemysteries.world.structureComponents.ComponentEndAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentNetherAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSoulAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceAltar;
import com.laton95.runemysteries.world.structureComponents.ComponentUndergroundAltar;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar_SURFACE extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar_SURFACE() {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "RuneAltarSurface";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (ChunkGenerator.altarTracker != null) {
			if (!ChunkGenerator.altarTracker.overworldAltarsFound) {
				ChunkGenerator.altarTracker.findOverworldLocations(world);
			}
		} else {
			ChunkGenerator.altarTracker = new AltarTracker();
			ChunkGenerator.altarTracker.findOverworldLocations(world);
		}

		return ChunkGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ),
				0, AltarTracker.Type.SURFACE);
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar_SURFACE.Start(this.world, this.rand, chunkX, chunkZ);
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

			AltarTracker.RuneAltar altar = ChunkGenerator.altarTracker.getAltar(new ChunkPos(chunkX, chunkZ),
					worldIn.provider.getDimension());

			if (altar != null && !altar.isPlaced()) {
				if (!altar.isBiomeDependant() || altar.isBiomeViable(biomeIn)) {
					StructureBoundingBox bBox;
					BlockPos altarPos;
					BlockPos altarPos2;

					ComponentSurfaceAltar componentRuneAltar = new ComponentSurfaceAltar(random,
							chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1,
							altar.getName());
					bBox = componentRuneAltar.getBoundingBox();

					componentRuneAltar.offsetToAverageGroundLevel(worldIn, bBox, -1);

					altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
					if (WorldHelper.isFlat(worldIn, altarPos, bBox.getXSize(), bBox.getYSize(), bBox.getZSize(), 3,
							1, altar.getFlatnessTolerance())) {

						// Altar generated
						altar.setPlaced(true);
						altar.setPosition(
								new BlockPos(altarPos.getX() + 4, altarPos.getY() + 1, altarPos.getZ() + 4));
						altar.setPlacementRadius(0);
						LogHelper.info(altar.toString());
						this.components.add(componentRuneAltar);
					} else {
						// Altar failed to generate because ground was not flat
						panic(altar);
						componentRuneAltar = null;
					}
				} else {
					// Altar failed to generate because incorrect biome
					panic(altar);
				}
			}
			this.updateBoundingBox();
		}

		private void panic(AltarTracker.RuneAltar altar) {
			altar.incrementFailureCount(1);
			if (altar.getFailureCount() > ChunkGenerator.altarTracker.warningFailureCount) {
				altar.incrementPlacementRadius(5);
				altar.decrementFlatnessTolerance(0.02f);
				if (altar.getFailureCount() > ChunkGenerator.altarTracker.panicFailureCount) {
					altar.incrementPlacementRadius(20);
					altar.setBiomeDependant(false);
					altar.setFlatnessTolerance(0.1f);
				}
			}
		}
	}
}