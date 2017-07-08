package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentRuneAltar extends WorldHelper.ModFeature {

	public ComponentRuneAltar() {
	}

	public ComponentRuneAltar(Random rand, int x, int z) {
		super(rand, x, 64, z, 12, 3, 12);
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs,
	 * Mob Spawners, it closes Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		if (!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -1)) {
			return false;
		} else {
			StructureBoundingBox structureboundingbox = this.getBoundingBox();
			BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
			
			String name = "airaltar";
			String[] genericAltarNames = { "airaltar", "mindaltar", "earthaltar", "bodyaltar", "lawaltar" };
			String[] swampAltarNames = { "wateraltar", "bloodaltar" };
			String[] desertAltarNames = { "firealtar", "soulaltar" };
			
			Biome biome = worldIn.getBiome(blockpos);
			if (WorldGenReference.genericAltarSpawnBiomes.contains(biome)) {
				name = genericAltarNames[randomIn.nextInt(genericAltarNames.length)];
			} else if (WorldGenReference.swampAltarSpawnBiomes.contains(biome)) {
				name = swampAltarNames[randomIn.nextInt(swampAltarNames.length)];
			} else if (WorldGenReference.desertAltarSpawnBiomes.contains(biome)) {
				name = desertAltarNames[randomIn.nextInt(desertAltarNames.length)];
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

			Template structure = WorldHelper.getTemplate(worldIn, name);
			BlockPos structureSize = structure.getSize();
			PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
			WorldHelper.loadStructure(blockpos, worldIn, structure, settings);
			//LogHelper.info("Generated " + name + " at: [" + blockpos.getX() + "," + blockpos.getY() + "," + blockpos.getZ() + "]");
			return true;
		}
	}
}