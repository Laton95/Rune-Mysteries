package com.laton95.runemysteries.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockParticleLight extends RMModBlock implements IMetaBlock
{
	
	private static final PropertyEnum<EnumDyeColor> COLOUR = PropertyEnum.create("colour", EnumDyeColor.class);
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.3D, 0.3D, 0.3D, 0.7D, 0.7D, 0.7D);
	
	public BlockParticleLight()
	{
		super("particle_Light", Material.BARRIER, 0f, 0f, "pickaxe", 0, true, false);
		lightValue = 15;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(COLOUR, EnumDyeColor.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumDyeColor type = state.getValue(COLOUR);
		return type.ordinal();
	}
	
	@SuppressWarnings("deprecation")
	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		EnumParticleTypes particle = EnumParticleTypes.REDSTONE;
		
		Colour colour = new Colour(255, 255, 255);
		
		switch(stateIn.getValue(COLOUR))
		{
			case WHITE:
				colour.set(228, 228, 228);
				break;
			case ORANGE:
				colour.set(234, 126, 53);
				break;
			case MAGENTA:
				colour.set(190, 73, 201);
				break;
			case LIGHT_BLUE:
				colour.set(99, 135, 210);
				break;
			case YELLOW:
				colour.set(194, 181, 28);
				break;
			case LIME:
				colour.set(57, 186, 46);
				break;
			case PINK:
				colour.set(217, 129, 153);
				break;
			case GRAY:
				colour.set(65, 65, 65);
				break;
			case SILVER:
				colour.set(160, 167, 167);
				break;
			case CYAN:
				colour.set(38, 113, 145);
				break;
			case PURPLE:
				colour.set(126, 52, 191);
				break;
			case BLUE:
				colour.set(37, 49, 147);
				break;
			case BROWN:
				colour.set(86, 51, 28);
				break;
			case GREEN:
				colour.set(54, 75, 24);
				break;
			case RED:
				colour.set(158, 43, 39);
				break;
			case BLACK:
				colour.set(24, 20, 20);
				break;
		}
		
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.5D;
		double d2 = pos.getZ() + 0.5D;
		for(int i = 0; i < 5; i++)
		{
			worldIn.spawnParticle(particle, d0, d1, d2, colour.getRed() / 255, colour.getGreen() / 255, colour.getBlue() / 255);
		}
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if(!worldIn.isRemote)
		{
			IBlockState newState;
			
			if(playerIn.getHeldItem(hand).getItem() instanceof ItemDye)
			{
				newState = getDefaultState().withProperty(COLOUR, EnumDyeColor.byDyeDamage(playerIn.getHeldItem(hand).getItemDamage()));
			}
			else
			{
				int oldColourOrdinal = state.getValue(COLOUR).ordinal();
				
				if(!playerIn.isSneaking())
				{
					if(oldColourOrdinal == 15)
					{ oldColourOrdinal = -1; }
					
					newState = getDefaultState().withProperty(COLOUR, EnumDyeColor.values()[oldColourOrdinal + 1]);
				}
				else
				{
					if(oldColourOrdinal == 0)
					{ oldColourOrdinal = 16; }
					
					newState = getDefaultState().withProperty(COLOUR, EnumDyeColor.values()[oldColourOrdinal - 1]);
				}
			}
			
			worldIn.setBlockState(pos, newState);
		}
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < EnumDyeColor.values().length; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, COLOUR);
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumDyeColor.values()[stack.getItemDamage()].toString();
	}
	
	private class Colour
	{
		private float red;
		
		private float green;
		
		private float blue;
		
		Colour(float red, float green, float blue)
		{
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public void set(float red, float green, float blue)
		{
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public float getRed()
		{
			return red;
		}
		
		public float getGreen()
		{
			return green;
		}
		
		public float getBlue()
		{
			return blue;
		}
	}
}
