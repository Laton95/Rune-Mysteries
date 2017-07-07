package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.WorldGen;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
			BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
					structureboundingbox.minZ);

			Template structure = WorldHelper.getTemplate(worldIn, "runealtar");
			BlockPos structureSize = structure.getSize();
			if (WorldHelper.determineFlatness(worldIn, blockpos, structureSize.getX(), structureSize.getY(),
					structureSize.getZ()) > WorldGen.structureFlatnessTolerance) {
				PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID)
						.setBoundingBox(structureboundingbox);
				WorldHelper.loadStructure(blockpos, worldIn, structure, settings);
				LogHelper.info("Generated altar at: [" + blockpos.getX() + "," + blockpos.getY() + "," + blockpos.getZ()
						+ "]");
				return true;
			} else
				return false;
		}
	}
}