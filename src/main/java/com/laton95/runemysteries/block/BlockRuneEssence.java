package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRuneEssence extends RMModBlock
{
	public static final PropertyBool FINITE = PropertyBool.create("finite");
	
	public BlockRuneEssence()
	{
		super("rune_Essence_Block", Material.ROCK, 1.5f, 2000f, "pickaxe", 0, true);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		if(meta == 0)
		{
			return getDefaultState().withProperty(FINITE, true);
		}
		
		return getDefaultState().withProperty(FINITE, false);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		if(state.getValue(FINITE))
		{
			return 0;
		}
		
		return 1;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1 + random.nextInt(4);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return ModItems.RUNE;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
		if(!state.getValue(FINITE))
		{
			worldIn.setBlockState(pos, state);
		}
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return EnumRuneType.ESSENCE.ordinal();
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return this.quantityDropped(random) + random.nextInt(fortune + 1);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FINITE);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
}
