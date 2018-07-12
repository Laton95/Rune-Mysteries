package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.util.StructureHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class ComponentCenterStructure extends StructureComponent
{
	private final String structure;
	
	private boolean generated = false;
	
	private List<BlockPos> portals;
	
	public ComponentCenterStructure(String structure, int chunkX, int chunkZ, int yPos)
	{
		this(structure, chunkX, chunkZ, yPos, null);
	}
	
	public ComponentCenterStructure(String structure, int chunkX, int chunkZ, int yPos, List<BlockPos> portals)
	{
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(
				chunkX * 16 + 8,
				yPos,
				chunkZ * 16 + 8,
				0,
				0,
				0,
				16,
				32,
				16,
				EnumFacing.UP);
		this.structure = structure;
		this.portals = portals;
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
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setIgnoreEntities(false);
			
			StructureHelper structureHelper = new StructureHelper(worldIn, structure, pos);
			
			structureHelper.generate();
			
			if(portals != null)
			{
				for(BlockPos portalPos : portals)
				{
					worldIn.setBlockState(portalPos, ModBlocks.ALTAR_PORTAL.getDefaultState());
				}
			}
			
			generated = true;
		}
		return true;
	}
}
