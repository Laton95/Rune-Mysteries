package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.util.WorldHelper;

import net.minecraft.block.BlockSandStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;

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

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		BlockPos pos = new BlockPos(structureBoundingBoxIn.minX, structureBoundingBoxIn.minY, structureBoundingBoxIn.minZ);
		BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY() - depth, pos.getZ() - 11);
		BlockPos pos3 = new BlockPos(pos2.getX() - 1, pos2.getY() + 1, pos2.getZ() - 1);
		PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setReplacedBlock(Blocks.STRUCTURE_VOID).setChunk(new ChunkPos(pos));
		StructureHelper structureHelper = new StructureHelper(worldIn, "soul_altar_well", pos);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, room, pos2);
		structureHelper.generate();
		
		structureHelper = new StructureHelper(worldIn, name, pos3);
		structureHelper.generate();

		for (int i = 0; i < depth - 4; i++) {
			worldIn.setBlockState(pos.add(2, -i + 1, 2), Blocks.AIR.getDefaultState());
			worldIn.setBlockState(pos.add(3, -i + 1, 2), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(pos.add(1, -i + 1, 2), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(pos.add(2, -i + 1, 3), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
			worldIn.setBlockState(pos.add(2, -i + 1, 1), Blocks.SANDSTONE.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
		}
		return true;
	}
}