package com.laton95.runemysteries.world.structureComponents;

import com.laton95.runemysteries.util.ModStructureComponent;
import com.laton95.runemysteries.util.StructureHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class ComponentElementalObelisks
{
	abstract static class Obelisk extends ModStructureComponent
	{
		protected String structureNBTName;
		
		/** The size of the bounding box for this feature in the X axis */
		protected int width;
		
		/** The size of the bounding box for this feature in the Y axis */
		protected int height;
		
		/** The size of the bounding box for this feature in the Z axis */
		protected int depth;
		
		protected Obelisk(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ, String structureNBTName)
		{
			super(0);
			this.structureNBTName = structureNBTName;
			this.width = sizeX;
			this.height = sizeY;
			this.depth = sizeZ;
			this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));
			
			if(this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z)
			{
				this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
			}
			else
			{
				this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
			}
		}
		
		protected Obelisk()
		{
		}
		
		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound)
		{
			tagCompound.setInteger("Width", this.width);
			tagCompound.setInteger("Height", this.height);
			tagCompound.setInteger("Depth", this.depth);
			tagCompound.setInteger("HPos", this.horizontalPos);
		}
		
		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_)
		{
			this.width = tagCompound.getInteger("Width");
			this.height = tagCompound.getInteger("Height");
			this.depth = tagCompound.getInteger("Depth");
			this.horizontalPos = tagCompound.getInteger("HPos");
		}
	}
	
	abstract static class ComponentUndergroundObelisk extends Obelisk
	{
		
		protected ComponentUndergroundObelisk(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ, String structureNBTName)
		{
			super(rand, x, y, z, sizeX, sizeY, sizeZ, structureNBTName);
		}
		
		protected ComponentUndergroundObelisk()
		{
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			StructureBoundingBox structureboundingbox = this.getBoundingBox();
			BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
			PlacementSettings placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
			StructureHelper stucture = new StructureHelper(worldIn, structureNBTName, blockpos, placementsettings);
			stucture.generate();
			
			return true;
		}
	}
	
	abstract static class ComponentSurfaceObelisk extends Obelisk
	{
		
		protected ComponentSurfaceObelisk(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ, String structureNBTName)
		{
			super(rand, x, y, z, sizeX, sizeY, sizeZ, structureNBTName);
		}
		
		protected ComponentSurfaceObelisk()
		{
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			if(!this.offsetToAverageGroundLevel(worldIn, structureBoundingBoxIn, -1, true))
			{
				return false;
			}
			else
			{
				StructureBoundingBox structureboundingbox = this.getBoundingBox();
				BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
				PlacementSettings placementsettings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID).setBoundingBox(structureboundingbox);
				StructureHelper stucture = new StructureHelper(worldIn, structureNBTName, blockpos, placementsettings);
				stucture.generate();
				
				return true;
			}
		}
	}
	
	public static class ComponentAirObelisk extends ComponentSurfaceObelisk
	{
		public ComponentAirObelisk()
		{
		
		}
		
		public ComponentAirObelisk(Random rand, int x, int z)
		{
			super(rand, x, 64, z, 3, 5, 3, "air_obelisk");
		}
	}
	
	public static class ComponentEarthObelisk extends ComponentUndergroundObelisk
	{
		public ComponentEarthObelisk()
		{
		
		}
		
		public ComponentEarthObelisk(Random rand, int x, int z)
		{
			super(rand, x, 48, z, 5, 5, 5, "earth_obelisk");
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			boolean b = super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn);
			
			StructureBoundingBox structureboundingbox = this.getBoundingBox();
			BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY, structureboundingbox.minZ);
			
			for(int x = blockpos.getX(); x < blockpos.getX() + 5; x++)
			{
				for(int z = blockpos.getZ(); z < blockpos.getZ() + 5; z++)
				{
					BlockPos flowerPos = new BlockPos(x, blockpos.getY() + 1, z);
					if(worldIn.getBlockState(flowerPos) == Blocks.AIR.getDefaultState())
					{
						worldIn.setBlockState(flowerPos, Blocks.RED_FLOWER.getStateFromMeta(randomIn.nextInt(9)));
					}
				}
			}
			
			return b;
		}
	}
	
	public static class ComponentFireObelisk extends ComponentUndergroundObelisk
	{
		public ComponentFireObelisk()
		{
		
		}
		
		public ComponentFireObelisk(Random rand, int x, int z)
		{
			super(rand, x, 10, z, 5, 5, 5, "fire_obelisk");
		}
	}
	
	public static class ComponentWaterObelisk extends ComponentSurfaceObelisk
	{
		public ComponentWaterObelisk()
		{
		
		}
		
		public ComponentWaterObelisk(Random rand, int x, int z)
		{
			super(rand, x, 64, z, 3, 5, 3, "water_obelisk");
		}
	}
}
