package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_END;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_NETHER;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_SOUL;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_SURFACE;
import com.laton95.runemysteries.world.mapGenerators.MapGenRuneAltar_UNDERGROUND;
import com.laton95.runemysteries.world.mapGenerators.OreGenerator;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class WorldGenerator implements IWorldGenerator
{

	private MapGenRuneAltar_SURFACE surfaceAltarGenerator = new MapGenRuneAltar_SURFACE();
	private MapGenRuneAltar_UNDERGROUND undergroundAltarGenerator = new MapGenRuneAltar_UNDERGROUND();
	private MapGenRuneAltar_SOUL soulAltarGenerator = new MapGenRuneAltar_SOUL();
	private MapGenRuneAltar_NETHER netherAltarGenerator = new MapGenRuneAltar_NETHER();
	private MapGenRuneAltar_END endAltarGenerator = new MapGenRuneAltar_END();
	public static AltarTracker altarTracker;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (!world.isRemote)
		{
			switch (world.provider.getDimensionType())
			{
				case OVERWORLD:
					// Ores
					OreGenerator.finiteEssenceGen(world, random, chunkX, chunkZ);

					// Structures
					surfaceAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					undergroundAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					soulAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				case NETHER:
					// Ores

					// Structures
					netherAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				case THE_END:
					// Ores

					// Structures
					endAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());
					break;
				default:
					break;
			}
		}
	}

	@SubscribeEvent
	public void populate(PopulateChunkEvent.Populate event)
	{
		if (event.getType() == EventType.DUNGEON && !event.getWorld().isRemote)
		{
			switch (event.getWorld().provider.getDimensionType())
			{
				case OVERWORLD:
					// Structures
					surfaceAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
					undergroundAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
					soulAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
					break;
				case NETHER:
					netherAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
							new ChunkPos(event.getChunkX(), event.getChunkZ()));
					break;
				default:
					break;
			}
		}
	}

	@SubscribeEvent
	public void populateEnd(PopulateChunkEvent.Post event)
	{
		switch (event.getWorld().provider.getDimensionType())
		{
			case THE_END:
				endAltarGenerator.generateStructure(event.getWorld(), event.getRand(),
						new ChunkPos(event.getChunkX(), event.getChunkZ()));
				break;
			default:
				break;
		}
	}
}
