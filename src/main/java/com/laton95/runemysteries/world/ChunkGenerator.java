package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChunkGenerator implements IWorldGenerator {
	private MapGenRuneAltar runeAltarGenerator;
	public static AltarTracker altarTracker;

	public ChunkGenerator() {
		LogHelper.info("Finite essence generator registered successfully");
		LogHelper.info("Rune altar generator registered successfully");
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionType()) {
		case OVERWORLD:
			// Ores
			OreGenerator.finiteEssenceGen(world, random, chunkX, chunkZ);

			// Structures
			runeAltarGenerator.generate(world, chunkX, chunkZ, new ChunkPrimer());

			break;
		case NETHER:
			// Ores

			// Structures

			break;
		case THE_END:
			// Ores

			// Structures

			break;
		default:
			break;
		}

	}
	
	@SubscribeEvent
	public void populate(PopulateChunkEvent.Populate event){
		switch (event.getWorld().provider.getDimensionType()) {
		case OVERWORLD:
			// Structures
			runeAltarGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));

			break;
		case NETHER:
			break;
		case THE_END:
			break;
		default:
			break;
		}
	}
	
	@SubscribeEvent
	public void setupGen(InitMapGenEvent event) {
		altarTracker = new AltarTracker();
		runeAltarGenerator = new MapGenRuneAltar();
	}
}
