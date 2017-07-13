package com.laton95.runemysteries.world;

import java.util.Random;

import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.block.BlockSandStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentSoulAltar extends WorldHelper.ModFeature {
	private String name;
	private String room;
	private int depth;
	
	public ComponentSoulAltar() {
	}

	public ComponentSoulAltar(Random rand, int x, int z, String name, String room, int depth) {
		super(rand, x, 64, z, 5, 6, 5);
		this.name = name;
		this.room = room;
		this.depth = depth;
	}

	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = this.getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY-2, structureboundingbox.minZ);
		BlockPos blockpos2 = new BlockPos(blockpos.getX()-1, blockpos.getY()-depth, blockpos.getZ()-11);
		BlockPos blockpos3 = new BlockPos(blockpos2.getX()-1, blockpos2.getY()+1, blockpos2.getZ()-1);
		
		Template well = WorldHelper.getTemplate(worldIn, "soul_altar_well");
		Template structure = WorldHelper.getTemplate(worldIn, room);
		Template altar = WorldHelper.getTemplate(worldIn, name);
		PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, well, settings);
		WorldHelper.loadStructure(blockpos2, worldIn, structure, settings);
		WorldHelper.loadStructure(blockpos3, worldIn, altar, settings);
		
		for (int i = 0; i < depth-4; i++) {
			worldIn.setBlockState(new BlockPos(blockpos.getX()+2, blockpos.getY()-i + 1, blockpos.getZ()+2), Blocks.AIR.getDefaultState());
			worldIn.setBlockState(new BlockPos(blockpos.getX()+3, blockpos.getY()-i + 1, blockpos.getZ()+2), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(new BlockPos(blockpos.getX()+1, blockpos.getY()-i + 1, blockpos.getZ()+2), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(new BlockPos(blockpos.getX()+2, blockpos.getY()-i + 1, blockpos.getZ()+3), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(new BlockPos(blockpos.getX()+2, blockpos.getY()-i + 1, blockpos.getZ()+1), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
		}
		
		return true;
	}
}