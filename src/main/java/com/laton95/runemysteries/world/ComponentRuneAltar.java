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
	private String name;
	
	public ComponentRuneAltar() {
	}

	public ComponentRuneAltar(Random rand, int x, int z, String name) {
		super(rand, x, 64, z, 10, 3, 10);
		this.name = name;
	}

	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = this.getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);

		Template structure = WorldHelper.getTemplate(worldIn, name);
		BlockPos structureSize = structure.getSize();
		PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, structure, settings);
		return true;
	}
}