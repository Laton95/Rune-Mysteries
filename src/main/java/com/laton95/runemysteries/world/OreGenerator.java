package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.init.BlockRegistry;
import com.laton95.runemysteries.reference.ConfigReference;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class OreGenerator {

	public static void finiteEssenceGen(World world, Random random, int chunkX, int chunkZ) {
		int minHeight = ConfigReference.finiteEssenceMinHeight;
		int maxHeight = ConfigReference.finiteEssenceMaxHeight;
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
			throw new IllegalArgumentException("Illigal Height Arguments for WorldGenerator");
		}

		WorldGenerator finiteEssenceGen = new WorldGenMinable(BlockRegistry.runeEssenceFinite.getDefaultState(),
				ConfigReference.finiteEssenceVeinSize);

		int heightDifference = maxHeight - minHeight + 1;
		for (int i = 0; i < ConfigReference.finiteEssenceVeinsPerChunk; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDifference);
			int z = chunkZ * 16 + random.nextInt(16);
			finiteEssenceGen.generate(world, random, new BlockPos(x, y, z));
		}
	}
}
