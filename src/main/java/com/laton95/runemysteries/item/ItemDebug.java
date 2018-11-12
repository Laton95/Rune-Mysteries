package com.laton95.runemysteries.item;

import com.laton95.runemysteries.block.BlockRuneAltarEntrance;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.util.ItemNBTHelper;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends RMModItem
{
	public ItemDebug()
	{
		super("debug_item", true, 1);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(worldIn.getBlockState(pos).getBlock() instanceof BlockRuneAltarEntrance)
		{
			int i = worldIn.getBlockState(pos).getBlock().getRegistryName().toString().indexOf('_');
			int j = worldIn.getBlockState(pos).getBlock().getRegistryName().toString().indexOf(':') + 1;
			String runeName = worldIn.getBlockState(pos).getBlock().getRegistryName().toString().substring(j, i).toUpperCase();
			int runeOrdinal = EnumRuneType.valueOf(runeName).ordinal() + 1;
			
			if(runeOrdinal == 1)
			{
				runeOrdinal = 2;
			}
			if(runeOrdinal >= EnumRuneType.values().length)
			{
				runeOrdinal = 0;
			}
			
			BlockRuneAltarEntrance entrance = ModBlocks.AIR_ALTAR_ENTRANCE;
			
			switch(EnumRuneType.values()[runeOrdinal])
			{
				case BLOOD:
					entrance = ModBlocks.BLOOD_ALTAR_ENTRANCE;
					break;
				case BODY:
					entrance = ModBlocks.BODY_ALTAR_ENTRANCE;
					break;
				case CHAOS:
					entrance = ModBlocks.CHAOS_ALTAR_ENTRANCE;
					break;
				case COSMIC:
					entrance = ModBlocks.COSMIC_ALTAR_ENTRANCE;
					break;
				case DEATH:
					entrance = ModBlocks.DEATH_ALTAR_ENTRANCE;
					break;
				case EARTH:
					entrance = ModBlocks.EARTH_ALTAR_ENTRANCE;
					break;
				case FIRE:
					entrance = ModBlocks.FIRE_ALTAR_ENTRANCE;
					break;
				case LAW:
					entrance = ModBlocks.LAW_ALTAR_ENTRANCE;
					break;
				case MIND:
					entrance = ModBlocks.MIND_ALTAR_ENTRANCE;
					break;
				case NATURE:
					entrance = ModBlocks.NATURE_ALTAR_ENTRANCE;
					break;
				case SOUL:
					entrance = ModBlocks.SOUL_ALTAR_ENTRANCE;
					break;
				case WATER:
					entrance = ModBlocks.WATER_ALTAR_ENTRANCE;
					break;
			}
			
			worldIn.setBlockState(pos, entrance.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 1), entrance.getDefaultState());
			worldIn.setBlockState(pos.add(1, 0, 0), entrance.getDefaultState());
			worldIn.setBlockState(pos.add(0, 0, 1), entrance.getDefaultState());
		}
		else if(worldIn.getBlockState(pos).getBlock() == Blocks.STONE)
		{
			try
			{
				worldIn.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
				TileEntity tileentity = worldIn.getTileEntity(pos);
				
				if(tileentity instanceof TileEntityMobSpawner)
				{
					((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setEntityId(new ResourceLocation("zombie"));
				}
			}
			catch(Exception e)
			{
				LogHelper.error(e);
			}
		}
		else
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
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	private DebugMode getDebugMode(ItemStack stack)
	{
		return DebugMode.valueOf(ItemNBTHelper.getString(stack, "mode"));
	}
	
	private void setDebugMode(ItemStack stack, DebugMode mode)
	{
		ItemNBTHelper.setString(stack, "mode", mode.toString());
	}
	
	enum DebugMode
	{
		SAVE
	}
}
