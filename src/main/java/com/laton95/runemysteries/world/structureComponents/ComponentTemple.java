package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.init.ModBlocks;
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

public class ComponentTemple extends StructureComponent
{
	
	private final String type;
	private final BlockPos portalPos;
	private boolean generated = false;
	
	public ComponentTemple()
	{
		type = null;
		portalPos = null;
	}
	
	public ComponentTemple(int x, int z, String type, BlockPos portalPos, int yStart)
	{
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x * 16, yStart, z * 16, 0, 0, 0, x * 16 + 16, yStart + 32, z * 16 + 16, EnumFacing.UP);
		this.type = type;
		this.portalPos = portalPos;
	}
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
	{
		if (!generated)
		{
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setChunk(new ChunkPos(pos)).setIgnoreEntities(false);
			StructureHelper structureHelper = new StructureHelper(worldIn, type + "_temple_se", pos);
			if (bBox.minX / 16 == 0 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, type + "_temple_se", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == 0)
			{
				structureHelper = new StructureHelper(worldIn, type + "_temple_sw", pos);
			} else if (bBox.minX / 16 == 0 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, type + "_temple_ne", pos);
			} else if (bBox.minX / 16 == -1 && bBox.minZ / 16 == -1)
			{
				structureHelper = new StructureHelper(worldIn, type + "_temple_nw", pos);
			}
			structureHelper.generate();
			worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
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
