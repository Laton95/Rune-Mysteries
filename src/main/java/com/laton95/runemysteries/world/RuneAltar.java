package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.WorldGen;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.Template;

public class RuneAltar {
	public static void runeAltarGen(World worldIn, Random random, int chunk_X, int chunk_Z) {
		if (random.nextInt(100) <= Config.runeAltarRarity) {
			String name = "";
			String[] genericAltarNames = { "airaltar", "mindaltar", "earthaltar", "bodyaltar", "lawaltar" };
			String[] swampAltarNames = { "wateraltar", "bloodaltar" };
			String[] desertAltarNames = { "firealtar", "soulaltar" };
			
			BlockPos pos = new BlockPos(chunk_X * 16 + 8, 64, chunk_Z * 16 + 8);
			Biome biome = worldIn.getBiome(pos);
			if (WorldGen.genericAltarSpawnBiomes.contains(biome)) {
				name = genericAltarNames[random.nextInt(genericAltarNames.length)];
			} else if (WorldGen.swampAltarSpawnBiomes.contains(biome)) {
				name = swampAltarNames[random.nextInt(swampAltarNames.length)];
			} else if (WorldGen.desertAltarSpawnBiomes.contains(biome)) {
				name = desertAltarNames[random.nextInt(desertAltarNames.length)];
			} else if (WorldGen.cosmicAltarSpawnBiomes.contains(biome)) {
				name = "cosmicaltar";
			} else if (WorldGen.chaosAltarSpawnBiomes.contains(biome)) {
				name = "chaosaltar";
			} else if (WorldGen.natureAltarSpawnBiomes.contains(biome)) {
				name = "naturealtar";
			} else if (WorldGen.astralAltarSpawnBiomes.contains(biome)) {
				name = "astralaltar";
			} else if (WorldGen.deathAltarSpawnBiomes.contains(biome)) {
				name = "deathaltar";
			}

			
			
			if (!name.equals("")) {
				Template structure = WorldHelper.getTemplate(worldIn, name);
				BlockPos structureSize = structure.getSize();

				int x = chunk_X * 16 + random.nextInt(16 - structureSize.getX());
				int z = chunk_Z * 16 + random.nextInt(16 - structureSize.getZ());

				BlockPos surface = WorldHelper.findSurface(worldIn, x, z);

				if (surface != null && WorldHelper.determineFlatness(worldIn, surface, structureSize.getX(),
						structureSize.getY(), structureSize.getZ()) > WorldGen.structureFlatnessTolerance) {
					WorldHelper.loadStructure(surface, worldIn, structure);
					//LogHelper.info("Spawned " + name + " in " + biome.getBiomeName() + " at [" + surface.getX() + "," + surface.getY() + "," + surface.getZ() + "]");
				}
			}

		}
	}
}
