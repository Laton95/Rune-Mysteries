package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.world.RuinTracker;
import net.minecraft.block.BlockLadder;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentSoulRuin extends ModStructureComponent
{
	private RuinTracker.Ruin ruin;
	
	private boolean generated = false;
	
	public ComponentSoulRuin()
	{
	}
	
	
	public ComponentSoulRuin(RuinTracker.Ruin ruin, int chunkX, int chunkZ, int yPos, Random rand)
	{
		super(0);
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(
				chunkX * 16 + 8 + rand.nextInt(5),
				yPos,
				chunkZ * 16 + 8 + rand.nextInt(5),
				0,
				0,
				0,
				5,
				10,
				5,
				EnumFacing.UP);
		this.ruin = ruin;
	}
	
	@Override
	protected void writeStructureToNBT(NBTTagCompound tagCompound)
	{
	
	}
	
	@Override
	protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
	{
	
	}
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		if(!generated)
		{
			if(!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -2, false))
			{
				return false;
			}
			else
			{
				StructureBoundingBox structureboundingbox = this.getBoundingBox();
				BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
				PlacementSettings placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
				
				StructureHelper structureHelper = new StructureHelper(worldIn, ruin.getName() + "_well", blockpos, placementsettings);
				structureHelper.generate();
				
				int depth = 20;
				
				for(int y = blockpos.getY(); y > blockpos.getY() - depth; y--)
				{
					BlockPos pos = new BlockPos(blockpos.getX() + 2, y, blockpos.getZ() + 2);
					
					worldIn.setBlockState(pos.add(1, 0, 0), Blocks.SANDSTONE.getDefaultState());
					worldIn.setBlockState(pos.add(-1, 0, 0), Blocks.SANDSTONE.getDefaultState());
					worldIn.setBlockState(pos.add(0, 0, 1), Blocks.SANDSTONE.getDefaultState());
					worldIn.setBlockState(pos.add(0, 0, -1), Blocks.SANDSTONE.getDefaultState());
					
					worldIn.setBlockState(pos, Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH));
				}
				
				placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID);
				structureHelper = new StructureHelper(worldIn, ruin.getName() + "_room", blockpos.add(0, -depth, -3), placementsettings);
				structureHelper.generate();
				
				structureHelper = new StructureHelper(worldIn, "stone_circle", blockpos.add(5, -depth + 1, -2), placementsettings);
				structureHelper.generate();
				
				structureHelper = new StructureHelper(worldIn, ruin.getName(), blockpos.add(5, -depth + 1, -2), placementsettings);
				structureHelper.generate();
				
				worldIn.setBlockState(blockpos.add(7, -depth + 2, 0), ModBlocks.WHITE_LIGHT.getDefaultState());
				worldIn.setBlockState(blockpos.add(14, -depth + 2, 0), ModBlocks.WHITE_LIGHT.getDefaultState());
				worldIn.setBlockState(blockpos.add(14, -depth + 2, 7), ModBlocks.WHITE_LIGHT.getDefaultState());
				worldIn.setBlockState(blockpos.add(7, -depth + 2, 7), ModBlocks.WHITE_LIGHT.getDefaultState());
				
				ruin.setGenerated(true);
				ruin.setRuinPos(blockpos.add(11, -depth + 1, 4), worldIn);
				
				generated = true;
			}
		}
		return true;
	}
}
