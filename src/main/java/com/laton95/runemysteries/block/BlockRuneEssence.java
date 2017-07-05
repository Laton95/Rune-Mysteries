package com.laton95.runemysteries.block;

import javax.annotation.Nullable;

import com.laton95.runemysteries.init.ItemRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRuneEssence extends RMModBlock {

	public BlockRuneEssence() {
		super("rune_Essence_Block", Material.ROCK, 1.5f, 2000f, "pickaxe", 1, true);
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state,
			@Nullable TileEntity te, ItemStack stack) {
		if (!player.isCreative()) {
			player.addExhaustion(0.005F);

			ItemStack itemstack = new ItemStack(ItemRegistry.runeEssence);
			spawnAsEntity(worldIn, pos, itemstack);

			worldIn.setBlockState(pos, state);
		}
	}
}
