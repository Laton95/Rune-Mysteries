package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.util.ModStructureComponent;
import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.world.RuinTracker;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentSurfaceRuin extends ModStructureComponent
{
	private RuinTracker.Ruin ruin;
	
	private boolean generated = false;
	
	public ComponentSurfaceRuin()
	{
	}
	
	
	public ComponentSurfaceRuin(RuinTracker.Ruin ruin, int chunkX, int chunkZ, int yPos)
	{
		super(0);
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(
				chunkX * 16 + 6,
				yPos,
				chunkZ * 16 + 6,
				0,
				0,
				0,
				10,
				3,
				10,
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
			if(!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -1, false))
			{
				return false;
			}
			else
			{
				StructureBoundingBox structureboundingbox = this.getBoundingBox();
				BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
				PlacementSettings placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
				
				StructureHelper structureHelper = new StructureHelper(worldIn, "stone_circle", blockpos, placementsettings);
				structureHelper.generate();
				
				structureHelper = new StructureHelper(worldIn, ruin.getName(), blockpos, placementsettings);
				structureHelper.generate();
				
				ruin.setGenerated(true);
				ruin.setRuinPos(blockpos.add(5, 1, 5), worldIn);
				
				generated = true;
			}
		}
		return true;
	}
}
