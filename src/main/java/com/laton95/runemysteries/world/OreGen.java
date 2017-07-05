package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGen implements IWorldGenerator {
	private WorldGenerator finiteEssenceGen;

	public OreGen() {
		finiteEssenceGen = new WorldGenMinable(BlockRegistry.runeEssenceFinite.getDefaultState(), Config.finiteEssenceVeinSize);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (worldIn.provider.getDimensionType()) {
		case OVERWORLD:
			if (Config.generateFiniteEssence) {
				runGenerator(finiteEssenceGen, worldIn, random, chunkX, chunkZ, Config.finiteEssenceVeinsPerChunk, Config.finiteEssenceMinHeight, Config.finiteEssenceMaxHeight);
			}
			break;
		case NETHER:

			break;
		case THE_END:

			break;
		default:
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World worldIn, Random random, int chunk_X, int chunk_Z,
			int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
			throw new IllegalArgumentException("Illigal Height Arguments for WorldGenerator");
		}

		int heightDifference = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDifference);
			int z = chunk_Z * 16 + random.nextInt(16);
			generator.generate(worldIn, random, new BlockPos(x, y, z));
		}
	}
}
