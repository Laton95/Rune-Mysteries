package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.util.LogHelper;
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

public class ComponentCosmicTemple extends StructureComponent
{
	private boolean generated = false;
	
	public ComponentCosmicTemple()
	{
	}
	
	public ComponentCosmicTemple(int x, int z)
	{
		ChunkPos chunkPos = new ChunkPos(x, z);
		BlockPos pos = chunkPos.getBlock(0, 0, 0);
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(pos.getX(), 85, pos.getZ(), 0, 0, 0, 1, 1, 1, EnumFacing.UP);
	}
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		if (!generated)
		{
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			LogHelper.info(bBox.minX / 16 + "," + bBox.minZ / 16);
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setChunk(new ChunkPos(pos)).setIgnoreEntities(false);
			StructureHelper structureHelper = new StructureHelper(worldIn, "cosmic_temple_se", pos);
			if (bBox.minX / 16 == 0 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_se", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_sw", pos);
			} else if (bBox.minX / 16 == 0 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_ne", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_nw", pos);
			} else if (bBox.minX / 16 == -2 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_w2", pos);
			} else if (bBox.minX / 16 == -2 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_w1", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == -2)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_n1", pos);
			} else if (bBox.minX / 16 == 0 && bBox.minZ / 16 == -2)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_n2", pos);
			} else if (bBox.minX / 16 == 1 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_e1", pos);
			} else if (bBox.minX / 16 == 1 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_e2", pos);
			} else if (bBox.minX / 16 == 0 && bBox.minZ / 16 == 1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_s2", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == 1)
			{
				structureHelper = new StructureHelper(worldIn, "cosmic_temple_s1", pos);
			}
			
			structureHelper.generate();
			worldIn.setBlockState(new BlockPos(-1, 87, 22), ModBlocks.ALTAR_PORTAL.getDefaultState());
			worldIn.setBlockState(new BlockPos(22, 87, 0), ModBlocks.ALTAR_PORTAL.getDefaultState());
			worldIn.setBlockState(new BlockPos(-23, 87, -1), ModBlocks.ALTAR_PORTAL.getDefaultState());
			worldIn.setBlockState(new BlockPos(0, 87, -23), ModBlocks.ALTAR_PORTAL.getDefaultState());
			generated = true;
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
}
