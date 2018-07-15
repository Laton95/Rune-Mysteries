package com.laton95.runemysteries.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public abstract class ModStructureComponent extends StructureComponent
{
	protected int horizontalPos = -1;
	
	public ModStructureComponent()
	{
	
	}
	
	protected ModStructureComponent(int type)
	{
		super(type);
	}
	
	/**
	 * Calculates and offsets this structure boundingbox to average ground level
	 */
	protected boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset, boolean submerge)
	{
		if(this.horizontalPos >= 0)
		{
			return true;
		}
		else
		{
			int i = 0;
			int j = 0;
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			
			for(int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
			{
				for(int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
				{
					blockpos$mutableblockpos.setPos(l, 64, k);
					
					if(structurebb.isVecInside(blockpos$mutableblockpos))
					{
						if(submerge)
						{
							i += WorldHelper.getTopSolidBlock(worldIn, blockpos$mutableblockpos).getY();
						}
						else
						{
							i += worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY();
						}
						
						++j;
					}
				}
			}
			
			if(j == 0)
			{
				return false;
			}
			else
			{
				this.horizontalPos = i / j;
				this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
				return true;
			}
		}
	}
}
