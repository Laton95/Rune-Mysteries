package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.utility.LogHelper;

import jline.internal.Log;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ChunkGenerator implements IWorldGenerator{

	public ChunkGenerator() {
		LogHelper.info("Finite essence generator registered successfully");
		LogHelper.info("Rune altar generator registered successfully");
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionType()) {
		case OVERWORLD:
			//Ores
			OreGenerator.finiteEssenceGen(world, random, chunkX, chunkZ);
			
			//Structures
			RuneAltar.runeAltarGen(world, random, chunkX, chunkZ);
			
			break;
		case NETHER:
			//Ores
			
			//Structures
			
			break;
		case THE_END:
			//Ores
			
			//Structures
			
			break;
		default:
			break;
		}
		
	}

}
