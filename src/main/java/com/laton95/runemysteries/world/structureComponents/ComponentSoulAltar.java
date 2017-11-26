package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;

import net.minecraft.block.BlockSandStone;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class ComponentSoulAltar extends StructureComponent
{

	private String name;
	private String room;
	private int depth;

	public ComponentSoulAltar()
	{
	}

	public ComponentSoulAltar(Random rand, int x, int z, String name, String room, int depth)
	{
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, 0, z, 0, 0, 0, 5, 5, 5, EnumFacing.UP);
		this.name = name;
		this.room = room;
		this.depth = depth;
	}

	private boolean generated = false;

	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{

		AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar(name);
		if (!generated && !altar.isGenerated())
		{
			LogHelper.info("Generating altar");
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			BlockPos pos2 = pos.add(-1, -depth, -11);
			BlockPos pos3 = pos2.add(-1, 1, -1);

			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn)
			.setChunk(new ChunkPos(pos)).setIgnoreEntities(false);

			StructureHelper structureHelper = new StructureHelper(worldIn, "soul_altar_well", pos);
			structureHelper.generate();

			structureHelper = new StructureHelper(worldIn, room, pos2);
			structureHelper.generate();

			structureHelper = new StructureHelper(worldIn, name, pos3);
			structureHelper.generate();

			for (int i = 0; i < depth - 4; i++)
			{
				if (i > 0)
				{
					worldIn.setBlockState(pos.add(2, -i + 1, 2),
							Blocks.LADDER.getDefaultState().withRotation(Rotation.CLOCKWISE_90));
					worldIn.setBlockState(pos.add(3, -i + 1, 2), Blocks.SANDSTONE.getDefaultState()
							.withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
					worldIn.setBlockState(pos.add(1, -i + 1, 2), Blocks.SANDSTONE.getDefaultState()
							.withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
					worldIn.setBlockState(pos.add(2, -i + 1, 3), Blocks.SANDSTONE.getDefaultState()
							.withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
					worldIn.setBlockState(pos.add(2, -i + 1, 1), Blocks.SANDSTONE.getDefaultState()
							.withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), 0);
				}
				else
				{
					worldIn.setBlockState(pos.add(2, -i + 1, 2), Blocks.WATER.getDefaultState());
				}

			}

			generated = true;

			altar.setPosition(pos3.add(4, 1, 4));
			altar.setPlacementRadius(0);
			altar.setGenerated(true);
		}
		return true;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound tagCompound)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
	{
		// TODO Auto-generated method stub

	}

	private int getAverageGroundLevel(World worldIn)
	{
		int i = 0;
		int j = 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for (int k = boundingBox.minZ; k <= boundingBox.maxZ; ++k)
		{
			for (int l = boundingBox.minX; l <= boundingBox.maxX; ++l)
			{
				blockpos$mutableblockpos.setPos(l, 64, k);
				i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(),
						worldIn.provider.getAverageGroundLevel() - 1);
				++j;
			}
		}

		if (j == 0)
		{
			return -1;
		}
		else
		{
			return i / j;
		}
	}

	public void offsetToAverageGroundLevel(World worldIn, int i)
	{
		int groundLevel = getAverageGroundLevel(worldIn);
		if (groundLevel < 0)
		{
			return;
		}
		boundingBox.offset(0, groundLevel - boundingBox.maxY + 2, 0);

	}
}
