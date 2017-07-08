package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.Template;

@Deprecated
public class RuneAltar {
	public static void runeAltarGen(World worldIn, Random random, int chunk_X, int chunk_Z) {
		if (random.nextInt(100) <= ConfigReference.runeAltarRarity) {
			String name = "";
			String[] genericAltarNames = { "airaltar", "mindaltar", "earthaltar", "bodyaltar", "lawaltar" };
			String[] swampAltarNames = { "wateraltar", "bloodaltar" };
			String[] desertAltarNames = { "firealtar", "soulaltar" };
			
			BlockPos pos = new BlockPos(chunk_X * 16 + 8, 64, chunk_Z * 16 + 8);
			Biome biome = worldIn.getBiome(pos);
			if (WorldGenReference.genericAltarSpawnBiomes.contains(biome)) {
				name = genericAltarNames[random.nextInt(genericAltarNames.length)];
			} else if (WorldGenReference.swampAltarSpawnBiomes.contains(biome)) {
				name = swampAltarNames[random.nextInt(swampAltarNames.length)];
			} else if (WorldGenReference.desertAltarSpawnBiomes.contains(biome)) {
				name = desertAltarNames[random.nextInt(desertAltarNames.length)];
			} else if (WorldGenReference.cosmicAltarSpawnBiomes.contains(biome)) {
				name = "cosmicaltar";
			} else if (WorldGenReference.chaosAltarSpawnBiomes.contains(biome)) {
				name = "chaosaltar";
			} else if (WorldGenReference.natureAltarSpawnBiomes.contains(biome)) {
				name = "naturealtar";
			} else if (WorldGenReference.astralAltarSpawnBiomes.contains(biome)) {
				name = "astralaltar";
			} else if (WorldGenReference.deathAltarSpawnBiomes.contains(biome)) {
				name = "deathaltar";
			}

			
			
			if (!name.equals("")) {
				Template structure = WorldHelper.getTemplate(worldIn, name);
				BlockPos structureSize = structure.getSize();

				int x = chunk_X * 16 + random.nextInt(16 - structureSize.getX());
				int z = chunk_Z * 16 + random.nextInt(16 - structureSize.getZ());

				BlockPos surface = WorldHelper.findSurface(worldIn, x, z);

				if (surface != null && WorldHelper.determineFlatness(worldIn, surface, structureSize.getX(),
						structureSize.getY(), structureSize.getZ()) > WorldGenReference.structureFlatnessTolerance) {
					WorldHelper.loadStructure(surface, worldIn, structure);
					//LogHelper.info("Spawned " + name + " in " + biome.getBiomeName() + " at [" + surface.getX() + "," + surface.getY() + "," + surface.getZ() + "]");
				}
			}

		}
	}
}
