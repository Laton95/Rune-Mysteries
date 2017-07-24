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

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;
	private final WorldHelper.dimType type;

	public MapGenRuneAltar(WorldHelper.dimType type, World world) {
		this.world = world;
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
		this.type = type;
	}

	public String getStructureName() {
		return "RuneAltar";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		switch (type) {
		case OVERWORLD:
			if (ChunkGenerator.altarTracker != null) {
				if (!ChunkGenerator.altarTracker.overworldAltarsFound) {
					ChunkGenerator.altarTracker.findOverworldLocations(world);
				}
			} else {
				ChunkGenerator.altarTracker = new AltarTracker();
				ChunkGenerator.altarTracker.findOverworldLocations(world);
			}
			break;
		case NETHER:
			if (ChunkGenerator.altarTracker != null) {
				if (!ChunkGenerator.altarTracker.netherAltarsFound) {
					ChunkGenerator.altarTracker.findNetherLocations(world);
				}
			} else {
				ChunkGenerator.altarTracker = new AltarTracker();
				ChunkGenerator.altarTracker.findNetherLocations(world);
			}
			break;
		case END:
			if (ChunkGenerator.altarTracker != null) {
				if (!ChunkGenerator.altarTracker.endAltarsFound) {
					ChunkGenerator.altarTracker.findEndLocations(world);
				}
			} else {
				ChunkGenerator.altarTracker = new AltarTracker();
				ChunkGenerator.altarTracker.findEndLocations(world);
			}
			break;
		}

		return ChunkGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ),
				world.provider.getDimension());
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar.Start(this.world, this.rand, chunkX, chunkZ);
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

			boolean flag = false;
			switch (worldIn.provider.getDimensionType()) {
			case OVERWORLD:
				if (altar.getType() == Type.SURFACE || altar.getType() == Type.UNDERGROUND
						|| altar.getType() == Type.SOUL) {
					flag = true;
				}
				break;
			case NETHER:
				if (altar.getType() == Type.NETHER) {
					flag = true;
				}
				break;
			case THE_END:
				if (altar.getType() == Type.END) {
					flag = true;
				}
				break;
			default:
				break;
			}

			if (altar != null && !altar.isPlaced() && flag) {
				if (!altar.isBiomeDependant() || altar.isBiomeViable(biomeIn)) {
					StructureBoundingBox bBox;
					BlockPos altarPos;
					BlockPos altarPos2;

					switch (altar.getType()) {
					case SURFACE:
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
							// Altar failed to generate because ground was not
							// flat
							panic(altar);
							componentRuneAltar = null;
						}
						break;
					case UNDERGROUND:
						ComponentUndergroundAltar componentRuneAltarU = new ComponentUndergroundAltar(random,
								chunkX * 16, chunkZ * 16, altar.getName(), altar.getRoom(), altar.getYOffset());
						bBox = componentRuneAltarU.getBoundingBox();

						altarPos = new BlockPos(bBox.minX, random.nextInt(25) + 15, bBox.minZ);
						altarPos2 = new BlockPos(bBox.maxX, altarPos.getY() + bBox.getYSize(), bBox.maxZ);
						componentRuneAltarU.setBoundingBox(new StructureBoundingBox(altarPos, altarPos2));
						// Altar generated
						altar.setPlaced(true);
						altar.setPosition(new BlockPos(altarPos.getX() + 7, altarPos.getY() + 2, altarPos.getZ() + 7));
						altar.setPlacementRadius(0);
						LogHelper.info(altar.toString());
						this.components.add(componentRuneAltarU);
						break;
					case SOUL:
						int depth = random.nextInt(10) + 10;
						ComponentSoulAltar componentRuneAltarS = new ComponentSoulAltar(random,
								chunkX * 16 + random.nextInt(6) + 1, chunkZ * 16 + random.nextInt(6) + 1,
								altar.getName(), altar.getRoom(), depth);
						bBox = componentRuneAltarS.getBoundingBox();

						componentRuneAltarS.offsetToAverageGroundLevel(worldIn, bBox, -1);

						altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
						if (WorldHelper.isFlat(worldIn, altarPos, bBox.getXSize(), bBox.getYSize(), bBox.getZSize(), 3,
								1, altar.getFlatnessTolerance())) {

							// Altar generated
							altar.setPlaced(true);
							altar.setPosition(
									new BlockPos(altarPos.getX() + 2, altarPos.getY() - depth, altarPos.getZ() - 8));
							altar.setPlacementRadius(0);
							LogHelper.info(altar.toString());
							this.components.add(componentRuneAltarS);
						} else {
							// Altar failed to generate because ground was not
							// flat
							panic(altar);
							componentRuneAltarS = null;
						}
						break;
					case NETHER:
						ComponentNetherAltar componentRuneAltarN = new ComponentNetherAltar(random,
								chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1,
								altar.getName());
						bBox = componentRuneAltarN.getBoundingBox();
						altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
						if (!WorldHelper.isInsideCave(worldIn, altarPos, bBox.getXSize(), bBox.getZSize())) {
							// Altar generated
							altar.setPlaced(true);
							altar.setPosition(
									new BlockPos(altarPos.getX() + 4, altarPos.getY() + 1, altarPos.getZ() + 4));
							altar.setPlacementRadius(0);
							LogHelper.info(altar.toString());
							this.components.add(componentRuneAltarN);
						} else {
							panic(altar);
						}
						break;
					case END:
						int randX = random.nextInt(4) + 1;
						int randZ = random.nextInt(4) + 1;
						boolean isFloating = !WorldHelper.isOverGround(worldIn,
								new BlockPos(chunkX * 16 + randX, 0, chunkZ * 16 + randZ), 10, 10);

						ComponentEndAltar componentRuneAltarE = new ComponentEndAltar(random, chunkX * 16 + randX,
								chunkZ * 16 + randZ, altar.getName());
						bBox = componentRuneAltarE.getBoundingBox();

						if (!isFloating) {
							componentRuneAltarE.offsetToAverageGroundLevel(worldIn, bBox, -1);
						}

						altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
						// Altar generated
						altar.setPlaced(true);
						altar.setPosition(new BlockPos(altarPos.getX() + 4, altarPos.getY() + 1, altarPos.getZ() + 4));
						altar.setPlacementRadius(0);
						LogHelper.info(altar.toString());
						this.components.add(componentRuneAltarE);
						break;
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