package com.laton95.runemysteries.world.mapGenerators;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceAltar;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar_SURFACE extends MapGenStructure
{

	private final List<Biome.SpawnListEntry> runeAltarSpawnList;

	public MapGenRuneAltar_SURFACE()
	{
		runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
	}

	@Override
	public String getStructureName()
	{
		return "RuneAltarSurface";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		if (WorldGenerator.altarTracker != null)
		{
			if (!WorldGenerator.altarTracker.overworldAltarsFound)
			{
				WorldGenerator.altarTracker.findOverworldLocations(world);
			}
		}
		else
		{
			WorldGenerator.altarTracker = new AltarTracker();
			WorldGenerator.altarTracker.findOverworldLocations(world);
		}

		return WorldGenerator.altarTracker.inGenerationRange(new ChunkPos(chunkX, chunkZ), 0,
				AltarTracker.Type.SURFACE);
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
	{
		return null;
	}

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new MapGenRuneAltar_SURFACE.Start(world, rand, chunkX, chunkZ);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList()
	{
		return runeAltarSpawnList;
	}

	public static class Start extends StructureStart
	{

		public Start()
		{
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ)
		{
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));

		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn)
		{
			super(chunkX, chunkZ);

			AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar(new ChunkPos(chunkX, chunkZ),
					worldIn.provider.getDimension());

			if (altar != null && !altar.isPlaced())
			{
				if (!altar.isBiomeDependant() || altar.isBiomeViable(biomeIn))
				{
					StructureBoundingBox bBox;
					BlockPos altarPos;
					ComponentSurfaceAltar componentRuneAltar = new ComponentSurfaceAltar(random,
							chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1, altar.getName());

					componentRuneAltar.offsetToAverageGroundLevel(worldIn, -1);

					bBox = componentRuneAltar.getBoundingBox();

					altarPos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
					if (WorldHelper.isFlat(worldIn, altarPos, bBox.getXSize(), bBox.getYSize(), bBox.getZSize(), 3, 1,
							altar.getFlatnessTolerance()))
					{
						// Altar generated
						altar.setPlaced(true);
						LogHelper.info(altar.toString());
						components.add(componentRuneAltar);
					}
					else
					{
						// Altar failed to generate because
						// ground was not flat
						panic(altar);
						componentRuneAltar = null;
					}
				}
				else
				{
					// Altar failed to generate because incorrect
					// biome
					panic(altar);
				}
			}
			updateBoundingBox();
		}

		private void panic(AltarTracker.RuneAltar altar)
		{
			altar.incrementFailureCount(1);
			if (altar.getFailureCount() > WorldGenerator.altarTracker.warningFailureCount)
			{
				altar.incrementPlacementRadius(5);
				altar.decrementFlatnessTolerance(0.02f);
				if (altar.getFailureCount() > WorldGenerator.altarTracker.panicFailureCount)
				{
					altar.incrementPlacementRadius(20);
					altar.setBiomeDependant(false);
					altar.setFlatnessTolerance(0.1f);
				}
			}
		}
	}
}
