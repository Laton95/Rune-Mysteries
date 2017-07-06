package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.WorldGen;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;

@Deprecated
public class RuneAltar implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World worldIn, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (worldIn.provider.getDimensionType()) {
		case OVERWORLD:
			if (Config.generateRuneAltars) {
				LogHelper.info("Attempting to generate rune altar");
				runSurfaceGenerator(worldIn, random, chunkX, chunkZ, 10);
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

	private void runSurfaceGenerator(World worldIn, Random random, int chunk_X, int chunk_Z, int rarity) {
		if (random.nextInt(100) <= rarity) {
			int x = chunk_X * 16 + random.nextInt(2);
			int z = chunk_Z * 16 + random.nextInt(2);
			Template structure = WorldHelper.getTemplate(worldIn, "runealtar");
			BlockPos surface = WorldHelper.findSurface(worldIn, x, z);
			BlockPos structureSize = structure.getSize();
			if (surface != null && WorldHelper.determineFlatness(worldIn, surface, structureSize.getX(), structureSize.getY(), structureSize.getZ()) > WorldGen.structureFlatnessTolerance) {
				LogHelper.info("Rune altar generated");
				WorldHelper.loadStructure(surface, worldIn, structure);
			}
		}
	}
}
