package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockElementalObelisk extends RMModBlock
{
	
	private static final PropertyBool TOP = PropertyBool.create("top");
	
	private final Item shardDrop;
	
	private final Item orb;
	
	public BlockElementalObelisk(String name, Item shardDrop, Item orb)
	{
		super(name, Material.ROCK, 1.5f, 2000f, "pickaxe", 3);
		setDefaultState(blockState.getBaseState().withProperty(TOP, true));
		setLightLevel(0.9375F);
		this.shardDrop = shardDrop;
		this.orb = orb;
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		boolean isTop = worldIn.getBlockState(pos.up()).getBlock() != this;
		
		return state.withProperty(TOP, isTop);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return shardDrop;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if(playerIn.getHeldItem(hand).getItem() == Item.getItemFromBlock(this))
		{
			return false;
		}
		
		if(!worldIn.isRemote)
		{
			if(isCompleteObelisk(worldIn, pos))
			{
				if(playerIn.getHeldItem(hand).getItem() == ModItems.GLASS_ORB)
				{
					Triggers.CHARGE_ORB.trigger((EntityPlayerMP) playerIn);
					ItemStack stack = new ItemStack(orb, 1);
					playerIn.setHeldItem(hand, stack);
				}
				else
				{
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.OBELISK_INTERACT));
				}
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
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TOP);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1);
	}
	
	private boolean isCompleteObelisk(World world, BlockPos pos)
	{
		BlockPos bottom = pos;
		while(world.getBlockState(bottom.down()).getBlock() == this)
		{
			bottom = bottom.down();
		}
		
		int height = 1;
		
		while(world.getBlockState(bottom.up()).getBlock() == this)
		{
			height++;
			bottom = bottom.up();
		}
		
		return height >= 3;
	}
}
