package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.init.BlockRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class OreGenerator
{
	
	public static void finiteEssenceGen(World world, Random random, int chunkX, int chunkZ)
	{
		if (!ModConfig.WORLD_GENERATION.ores.generateFiniteEssence)
		{
			return;
		}
		int minHeight = ModConfig.WORLD_GENERATION.ores.finiteEssenceMinHeight;
		int maxHeight = ModConfig.WORLD_GENERATION.ores.finiteEssenceMaxHeight;
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
		{
			throw new IllegalArgumentException("Illigal Height Arguments for WorldGenerator");
		}
		
		WorldGenerator finiteEssenceGen = new WorldGenMinable(BlockRegistry.RUNE_ESSENCE_FINITE.getDefaultState(), ModConfig.WORLD_GENERATION.ores.finiteEssenceVeinSize);
		
		int heightDifference = maxHeight - minHeight + 1;
		for (int i = 0; i < ModConfig.WORLD_GENERATION.ores.finiteEssenceVeinsPerChunk; i++)
		{
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDifference);
			int z = chunkZ * 16 + random.nextInt(16);
			finiteEssenceGen.generate(world, random, new BlockPos(x, y, z));
		}
	}
}
