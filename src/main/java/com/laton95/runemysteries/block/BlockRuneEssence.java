package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockRuneEssence extends RMModBlock
{
	
	public BlockRuneEssence()
	{
		super("rune_Essence_Block", Material.ROCK, 1.5f, 2000f, "pickaxe", 1, false);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		if (!player.isCreative())
		{
			player.addExhaustion(0.005F);
			
			ItemStack itemstack = new ItemStack(ModItems.RUNE, worldIn.rand.nextInt(4) + 1, EnumRuneType.ESSENCE.ordinal());
			spawnAsEntity(worldIn, pos, itemstack);
			
			worldIn.setBlockState(pos, state);
		}
	}
}
