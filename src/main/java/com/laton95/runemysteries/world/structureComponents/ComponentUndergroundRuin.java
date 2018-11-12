package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.world.RuinTracker;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentUndergroundRuin extends StructureComponent
{
	private RuinTracker.Ruin ruin;
	
	private boolean generated = false;
	
	public ComponentUndergroundRuin()
	{
	
	}
	
	public ComponentUndergroundRuin(RuinTracker.Ruin ruin, int chunkX, int chunkZ, int yPos)
	{
		int offset = 8;
		
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(
				chunkX * 16 + offset,
				yPos,
				chunkZ * 16 + offset,
				0,
				0,
				0,
				20,
				10,
				20,
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
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setIgnoreEntities(false);
			
			StructureHelper structureHelper = new StructureHelper(worldIn, ruin.getName() + "_room", pos);
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, "stone_circle", pos.add(4, 1, 4));
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, ruin.getName(), pos.add(4, 1, 4));
			structureHelper.generate();
			
			ruin.setGenerated(true);
			ruin.setRuinPos(pos.add(10, 1, 10), worldIn);
			
			generated = true;
		}
		return true;
	}
}
