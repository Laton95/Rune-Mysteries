package com.laton95.runemysteries.world.structureComponents;

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

public class ComponentIslandRuin extends ModStructureComponent
{
	private RuinTracker.Ruin ruin;
	
	private boolean generated = false;
	
	public ComponentIslandRuin()
	{
	}
	
	
	public ComponentIslandRuin(RuinTracker.Ruin ruin, int chunkX, int chunkZ, int yPos, Random rand)
	{
		super(0);
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(
				chunkX * 16 + 8 + rand.nextInt(5),
				yPos,
				chunkZ * 16 + 8 + rand.nextInt(5),
				0,
				0,
				0,
				12,
				10,
				12,
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
			StructureBoundingBox structureboundingbox = this.getBoundingBox();
			BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
			PlacementSettings placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
			
			StructureHelper structureHelper = new StructureHelper(worldIn, ruin.getIsland(), blockpos, placementsettings);
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, "stone_circle", blockpos.add(0, 5, 0), placementsettings);
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, ruin.getName(), blockpos.add(0, 5, 0), placementsettings);
			structureHelper.generate();
			
			ruin.setGenerated(true);
			ruin.setRuinPos(blockpos.add(6, 6, 6), worldIn);
			
			generated = true;
		}
		return true;
	}
}
