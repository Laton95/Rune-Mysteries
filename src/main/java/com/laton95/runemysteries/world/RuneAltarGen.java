package com.laton95.runemysteries.world;

import java.util.LinkedList;
import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.WorldGen;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RuneAltarGen implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (worldIn.provider.getDimensionType()) {
		case OVERWORLD:
			if (Config.generateRuneAltars) {
				runGenerator("runealtar", worldIn, random, chunkX, chunkZ, 10);
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

	private void runGenerator(String structure, World worldIn, Random random, int chunk_X, int chunk_Z, int rarity) {
		if (random.nextInt(100) <= rarity) {
			int x = chunk_X * 16 + random.nextInt(2);
			int z = chunk_Z * 16 + random.nextInt(2);
			BlockPos surface = findSurface(worldIn, x, z);getClass();
			if (surface != null && determineFlatness(worldIn, surface) > WorldGen.structureFlatnessTolerance) {
				//LogHelper.info(determineFlatness(worldIn, surface));
				StructureSpawner.loadStructure(surface, worldIn, structure);
			}
		}
	}
	
	private BlockPos findSurface(World worldIn, int x, int z) {
		for(int i = worldIn.getActualHeight(); i > 0; i--) {
			Block currentBlock = worldIn.getBlockState(new BlockPos(x, i, z)).getBlock();
			if (currentBlock.equals(Blocks.WATER) || currentBlock.equals(Blocks.LAVA)) {
				return null;
			}
			if (WorldGen.surfaceBlocks.contains(currentBlock)) {
				return new BlockPos(x, i, z);
			}
		}
		return null;
	}
	
	private float determineFlatness(World worldIn, BlockPos position) {
		int airBlocksBelow = 0;
		int solidBlocksAbove = 0;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				int x = position.getX() + i;
				int z = position.getZ() + j;
				if (!worldIn.isBlockNormalCube(new BlockPos(x,position.getY(),z), true)) {
					airBlocksBelow++;
				}
			}
		}
		for (int k = 1; k < 4; k++) {
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 13; j++) {
					int x = position.getX() + i;
					int z = position.getZ() + j;
					int y = position.getY() + k;
					if (worldIn.isBlockNormalCube(new BlockPos(x,y,z), true)) {
						solidBlocksAbove++;
					}
				}
			}
		}
		
		return 1 - ((airBlocksBelow*2 + solidBlocksAbove)/720.0f);
	}
}
