package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.tools.nsc.doc.model.Public;

@Mod.EventBusSubscriber
public class ChunkGenerator implements IWorldGenerator {
	private MapGenRuneAltar runeAltarGenerator;

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
			if (runeAltarGenerator == null) {
				runeAltarGenerator = new MapGenRuneAltar(world);
				runeAltarGenerator = (MapGenRuneAltar)net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(runeAltarGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CUSTOM);
			}
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
			if (runeAltarGenerator == null) {
				//runeAltarGenerator = new MapGenRuneAltar(event.getWorld());
			} else runeAltarGenerator.generateStructure(event.getWorld(), event.getRand(), new ChunkPos(event.getChunkX(), event.getChunkZ()));

			break;
		case NETHER:
			break;
		case THE_END:
			break;
		default:
			break;
		}
	}
}
