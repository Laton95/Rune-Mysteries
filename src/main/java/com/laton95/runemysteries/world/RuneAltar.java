package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.WorldGen;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import jline.internal.Log;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RuneAltar {
	public static void runeAltarGen(World worldIn, Random random, int chunk_X, int chunk_Z) {
		if (random.nextInt(100) <= Config.runeAltarRarity) {
			int x = chunk_X * 16 + random.nextInt(2);
			int z = chunk_Z * 16 + random.nextInt(2);
			Template structure = WorldHelper.getTemplate(worldIn, "runealtar");
			BlockPos surface = WorldHelper.findSurface(worldIn, x, z);
			BlockPos structureSize = structure.getSize();
			if (surface != null && WorldHelper.determineFlatness(worldIn, surface, structureSize.getX(), structureSize.getY(), structureSize.getZ()) > WorldGen.structureFlatnessTolerance) {
				WorldHelper.loadStructure(surface, worldIn, structure);
			}
		}
	}
}
