package com.laton95.runemysteries.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends RMModItem
{
	private static int first = 1;
	
	private static int second = -2;
	
	public ItemDebug()
	{
		super("debug_item", true, 1);
		setMaxDamage(17);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		int radius = 10;
		for(int y = -radius; y < radius; y++)
		{
			for(int x = -radius; x < radius; x++)
			{
				for(int z = -radius; z < radius; z++)
				{
					BlockPos curPos = pos.add(x, y, z);
					if(worldIn.getBlockState(curPos).getBlock() == Blocks.STRUCTURE_BLOCK)
					{
						((TileEntityStructure) worldIn.getTileEntity(curPos)).save();
					}
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
}
