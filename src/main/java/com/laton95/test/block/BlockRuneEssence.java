package com.laton95.test.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.laton95.test.init.ItemRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRuneEssence extends BlockTest{

	public BlockRuneEssence() {
		super("rune_Essence_Block", Material.ROCK,  1.5f, "pickaxe", 1, true);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		return ItemRegistry.runeEssence;
    }
	
	@Override
	public int quantityDropped(Random random)
    {
        return random.nextInt(3);
    }
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
		if(!player.isCreative()){
			player.addExhaustion(0.005F);

			ItemStack itemstack = new ItemStack(ItemRegistry.runeEssence);
			spawnAsEntity(worldIn, pos, itemstack);
        
			worldIn.setBlockState(pos, state);
		}
    }
}
