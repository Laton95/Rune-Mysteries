package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.common.BiomeDictionary;

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;
	private boolean foundLocations = false;
	private AltarTracker altarTracker;

	public MapGenRuneAltar(ChunkGenerator.dimType type) {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
		
		switch (type) {
		case OVERWORLD:
			this.altarTracker = new OverworldAltarTracker(this.world);
			break;
		case NETHER:
			
			break;
		case END:
	
	break;
		}
	}

	public String getStructureName() {
		return "RuneAltar";
	}

	public AltarTracker getAltarTracker() {
		return altarTracker;
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (!this.foundLocations) {
			altarTracker.findLocations(world);
			this.foundLocations = true;
		}

		return altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ));
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar.Start(this.world, this.rand, chunkX, chunkZ, this.altarTracker);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeAltarSpawnList;
	}
	
	public AltarTracker geTracker() {
		return altarTracker;
	}

	public static class Start extends StructureStart {
		private AltarTracker altarTracker;

		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, AltarTracker altarTracker) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)), altarTracker);
			
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn, AltarTracker altarTracker) {
			super(chunkX, chunkZ);
			
			this.altarTracker = altarTracker;

			AltarTracker.RuneAltar altar = altarTracker.getAltar(new ChunkPos(chunkX, chunkZ));

			if (altar != null && !altar.isPlaced()) {
				if (!altar.isBiomeDependant() || altar.isBiomeViable(biomeIn)) {
					ComponentRuneAltar componentRuneAltar = new ComponentRuneAltar(random,
							chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1, altar.getName());
					StructureBoundingBox bBox = componentRuneAltar.getBoundingBox();
					componentRuneAltar.offsetToAverageGroundLevel(worldIn, bBox, -1);

					BlockPos altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
					if (WorldHelper.isFlat(worldIn, altarPos, bBox.getXSize(), bBox.getYSize(), bBox.getZSize(), 3, 1,
							altar.getFlatnessTolerance())) {
						// Altar generated
						altarTracker.altarGenerated(altar);
						altar.setPlaced(true);
						altar.setPosition(altarPos);
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
			if (altar.getFailureCount() > altarTracker.warningFailureCount) {
				altar.incrementPlacementRadius(5);
				altar.decrementFlatnessTolerance(0.02f);
				if (altar.getFailureCount() > altarTracker.panicFailureCount) {
					altar.incrementPlacementRadius(20);
					altar.setBiomeDependant(false);
					altar.setFlatnessTolerance(0.1f);
				}
			}
		}
	}
}