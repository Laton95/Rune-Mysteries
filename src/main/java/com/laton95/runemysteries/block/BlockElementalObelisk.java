package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.IMetaEnum;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockElementalObelisk extends RMModBlock implements IMetaBlock
{
	private static final PropertyEnum<EnumObeliskElement> ELEMENT = PropertyEnum.create("element", EnumObeliskElement.class);
	
	private static final PropertyBool TOP = PropertyBool.create("top");
	
	public BlockElementalObelisk()
	{
		super("elemental_obelisk", Material.ROCK, 1.5f, 2000f, "pickaxe", 3, true);
		setDefaultState(blockState.getBaseState().withProperty(ELEMENT, EnumObeliskElement.AIR).withProperty(TOP, true));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		boolean isTop = meta < 4;
		return getDefaultState().withProperty(ELEMENT, EnumObeliskElement.values()[meta % 4]).withProperty(TOP, isTop);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int ordinal = state.getValue(ELEMENT).ordinal();
		return ordinal + (state.getValue(TOP) ? 0 : 4);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		boolean isSameElement = true;
		boolean isTop = worldIn.getBlockState(pos.up()).getBlock() != this;
		
		if(!isTop)
		{
			isSameElement = worldIn.getBlockState(pos.up()).getValue(ELEMENT) == state.getValue(ELEMENT);
		}
		
		return state.withProperty(TOP, isTop || !isSameElement);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModItems.OBELISK_SHARD;
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		switch(state.getValue(ELEMENT))
		{
			case AIR:
				return 0;
			case EARTH:
				return 1;
			case FIRE:
				return 2;
			case WATER:
				return 3;
			default:
				return 0;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if(playerIn.getHeldItem(hand).getItem() == Item.getItemFromBlock(this))
		{
			boolean isSameElement = Item.getItemFromBlock(this).getMetadata(playerIn.getHeldItem(hand)) == getMetaFromState(state);
			if(isSameElement)
			{
				return false;
			}
		}
		
		if(!worldIn.isRemote)
		{
			if(isCompleteObelisk(worldIn, pos, state.getValue(ELEMENT)))
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.OBELISK_INTERACT));
			}
			else
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.OBELISK_INTERACT_FAIL));
			}
		}
		
		return true;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		if(fortune > 0)
		{
			return this.quantityDropped(random) + random.nextInt(fortune - 1);
		}
		else
		{
			return this.quantityDropped(random);
		}
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < EnumObeliskElement.values().length; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, ELEMENT, TOP);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
	
	private boolean isCompleteObelisk(World world, BlockPos pos, EnumObeliskElement element)
	{
		BlockPos bottom = pos;
		while(world.getBlockState(bottom.down()).getBlock() == this && world.getBlockState(bottom.down()).getValue(ELEMENT) == element)
		{
			bottom = bottom.down();
		}
		
		int height = 1;
		
		while(world.getBlockState(bottom.up()).getBlock() == this && world.getBlockState(bottom.up()).getValue(ELEMENT) == element)
		{
			height++;
			bottom = bottom.up();
		}
		
		return height >= 3;
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumObeliskElement.values()[stack.getItemDamage()].toString();
	}
	
	public enum EnumObeliskElement implements IMetaEnum
	{
		AIR,
		EARTH,
		FIRE,
		WATER;
		
		private final String name;
		
		EnumObeliskElement()
		{
			this.name = super.toString().toLowerCase();
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
		
		@Override
		public String getID()
		{
			return "element_type";
		}
	}
}
