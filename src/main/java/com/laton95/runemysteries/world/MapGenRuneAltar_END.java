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
import scala.xml.dtd.impl.Base.Alt;

public class MapGenRuneAltar_END extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar_END() {
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	public String getStructureName() {
		return "RuneAltarEnd";
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (ChunkGenerator.altarTracker != null) {
			if (!ChunkGenerator.altarTracker.endAltarsFound) {
				ChunkGenerator.altarTracker.findEndLocations(world);
			}
		} else {
			ChunkGenerator.altarTracker = new AltarTracker();
			ChunkGenerator.altarTracker.findEndLocations(world);
		}

		return ChunkGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ),
				1, AltarTracker.Type.END);
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar_END.Start(this.world, this.rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeAltarSpawnList;
	}

	public static class Start extends StructureStart {

		public Start(World worldIn, Random random, int chunkX, int chunkZ) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn) {
			super(chunkX, chunkZ);

			AltarTracker.RuneAltar altar = ChunkGenerator.altarTracker.getAltar("cosmic_altar");
			
			if (altar != null && !altar.isPlaced()) {
				StructureBoundingBox bBox;
				BlockPos altarPos;
				BlockPos altarPos2;

				int randX = random.nextInt(4) + 1;
				int randZ = random.nextInt(4) + 1;
				boolean isFloating = !WorldHelper.isOverGround(worldIn,
						new BlockPos(chunkX * 16 + randX, 0, chunkZ * 16 + randZ), 10, 10);

				ComponentEndAltar componentRuneAltar = new ComponentEndAltar(random, chunkX * 16 + randX,
						chunkZ * 16 + randZ, altar.getName());
				bBox = componentRuneAltar.getBoundingBox();

				if (!isFloating) {
					componentRuneAltar.offsetToAverageGroundLevel(worldIn, bBox, -1);
				}

				altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
				// Altar generated
				altar.setPlaced(true);
				altar.setPosition(new BlockPos(altarPos.getX() + 4, altarPos.getY() + 1, altarPos.getZ() + 4));
				altar.setPlacementRadius(0);
				LogHelper.info(altar.toString());
				this.components.add(componentRuneAltar);
			}
			this.updateBoundingBox();
		}
	}
}