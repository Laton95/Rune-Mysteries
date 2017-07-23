package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChunkGenerator implements IWorldGenerator {
	private MapGenRuneAltar overworldAltarGenerator;
	private MapGenRuneAltar netherAltarGenerator;
	private MapGenRuneAltar endAltarGenerator;
	public static AltarTracker altarTracker;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (!world.isRemote) {
			switch (world.provider.getDimensionType()) {
			case OVERWORLD:
				// Ores
				OreGenerator.finiteEssenceGen(world, random, chunkX, chunkZ);

				// Structures
				if (overworldAltarGenerator != null) {
					overworldAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
				}
				break;
			case NETHER:
				// Ores

				// Structures
				if (netherAltarGenerator != null) {
					netherAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
				}
				break;
			case THE_END:
				// Ores

				// Structures
				if (endAltarGenerator != null) {
					endAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
				}
				break;
			default:
				break;
			}
		}
	}

	@SubscribeEvent
	public void populate(PopulateChunkEvent.Populate event) {
		if (event.getType() == EventType.DUNGEON && !event.getWorld().isRemote) {
			switch (event.getWorld().provider.getDimensionType()) {
			case OVERWORLD:
				// Structures
				if (overworldAltarGenerator != null) {
					overworldAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
				}
				break;
			case NETHER:
				if (netherAltarGenerator != null) {
					netherAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
				}
				break;
			default:
				break;
			}
		}
	}

	@SubscribeEvent
	public void populateEnd(PopulateChunkEvent.Post event) {
		switch (event.getWorld().provider.getDimensionType()) {
		case THE_END:
			if (endAltarGenerator != null) {
				endAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
						new ChunkPos(event.getChunkX(), event.getChunkZ()));
			}
			break;
		default:
			break;
		}
	}

	@SubscribeEvent
	public void start(WorldEvent.Load event) {
		switch (event.getWorld().provider.getDimensionType()) {
		case OVERWORLD:
			if (overworldAltarGenerator == null) {
				LogHelper.info("Overworld altar generator loaded");
				overworldAltarGenerator = new MapGenRuneAltar(WorldHelper.dimType.OVERWORLD, event.getWorld());
			}
			break;
		case NETHER:
			if (netherAltarGenerator == null) {
				LogHelper.info("Nether altar generator loaded");
				netherAltarGenerator = new MapGenRuneAltar(WorldHelper.dimType.NETHER, event.getWorld());
			}
			break;
		case THE_END:
			if (endAltarGenerator == null) {
				LogHelper.info("End altar generator loaded");
				endAltarGenerator = new MapGenRuneAltar(WorldHelper.dimType.END, event.getWorld());
			}
			break;
		default:

			break;
		}

	}

	@SubscribeEvent
	public void end(WorldEvent.Unload event) {
		switch (event.getWorld().provider.getDimensionType()) {
		case OVERWORLD:
			LogHelper.info("Overworld altar generator unloaded");
			overworldAltarGenerator = null;
			break;
		case NETHER:
			LogHelper.info("Nether altar generator unloaded");
			netherAltarGenerator = null;
			break;
		case THE_END:
			LogHelper.info("End altar generator unloaded");
			endAltarGenerator = null;
			break;
		default:

			break;
		}
	}
}
