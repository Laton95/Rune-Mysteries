package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.StructureHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.WorldGenerator;
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

public class ComponentUndergroundAltar extends StructureComponent
{
	
	private String name;
	
	private String room;
	
	private int yOffset;
	
	private boolean generated = false;
	
	public ComponentUndergroundAltar()
	{
	}
	
	public ComponentUndergroundAltar(Random rand, int x, int z, String name, String room, int yOffset)
	{
		boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, rand.nextInt(20) + 15, z, 0, 0, 0, 16, 5, 16, EnumFacing.UP);
		this.name = name;
		this.room = room;
		this.yOffset = yOffset;
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
		AltarTracker.RuneAltar altar = WorldGenerator.altarTracker.getAltar(name);
		if(!generated && !altar.isGenerated())
		{
			LogHelper.info("Generating altar");
			StructureBoundingBox bBox = boundingBox;
			structureBoundingBoxIn = boundingBox;
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			BlockPos pos2 = new BlockPos(pos.getX() + 3, pos.getY() + yOffset, pos.getZ() + 3);
			new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureBoundingBoxIn).setChunk(new ChunkPos(pos)).setIgnoreEntities(false);
			
			StructureHelper structureHelper = new StructureHelper(worldIn, room, pos);
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, "stone_circle", pos2);
			structureHelper.generate();
			
			structureHelper = new StructureHelper(worldIn, name, pos2);
			structureHelper.generate();
			generated = true;
			
			altar.setPosition(pos2.add(4, 1, 4));
			altar.setPlacementRadius(0);
			altar.setGenerated(true);
		}
		return true;
	}
}
