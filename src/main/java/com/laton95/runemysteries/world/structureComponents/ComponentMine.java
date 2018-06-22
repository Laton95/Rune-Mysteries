package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModLoot;
import com.laton95.runemysteries.util.StructureHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentMine extends StructureComponent
{
	private boolean generated = false;
	
	public ComponentMine()
	{
	}
	
	public ComponentMine(int x, int z, BlockPos portalPos, int yStart)
	{
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x * 16, yStart, z * 16, 0, 0, 0, x * 16 + 16, yStart + 32, z * 16 + 16, EnumFacing.UP);
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
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		if(!generated)
		{
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setChunk(new ChunkPos(pos)).setIgnoreEntities(false);
			StructureHelper structureHelper = new StructureHelper(worldIn, "essence_mine_se", pos, ModLoot.ESSENCE_MINE);
			if(bBox.minX / 16 == 0 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "essence_mine_se", pos, ModLoot.ESSENCE_MINE);
				BlockPos portalPos = new BlockPos(7, 64, 2);
				worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
			}
			else if(bBox.minX / 16 == -1 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "essence_mine_sw", pos, ModLoot.ESSENCE_MINE);
				BlockPos portalPos = new BlockPos(-8, 64, 2);
				worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
			}
			else if(bBox.minX / 16 == 0 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "essence_mine_ne", pos, ModLoot.ESSENCE_MINE);
				BlockPos portalPos = new BlockPos(12, 64, -8);
				worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
			}
			else if(bBox.minX / 16 == -1 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "essence_mine_nw", pos, ModLoot.ESSENCE_MINE);
				BlockPos portalPos = new BlockPos(-5, 64, -11);
				worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
			}
			
			
			structureHelper.generate();
			generated = true;
		}
		return true;
	}
}
