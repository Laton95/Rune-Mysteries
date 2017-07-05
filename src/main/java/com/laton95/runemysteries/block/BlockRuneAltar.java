package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneAltar extends RMModBlock {
	private ItemRune rune;

	public BlockRuneAltar(String name, ItemRune rune) {
		super(name, Material.ROCK, 0, 2000f, null, 0, true);
		this.rune = rune;
		setBlockUnbreakable();
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityItem) {
			Item itemType = ((EntityItem) entityIn).getItem().getItem();

			if (itemType.equals(ItemRegistry.runeEssence)) {
				spawnItem(worldIn, entityIn, rune);
			}

			if (itemType.equals(Items.BOOK)) {
				spawnItem(worldIn, entityIn, ItemRegistry.spellbook);
			}
		}
	}

	protected void spawnItem(World worldIn, Entity entityIn, Item item) {
		while (((EntityItem) entityIn).getItem().getCount() > 0) {
			ItemStack itemstack = new ItemStack(item);
			spawnAsEntity(worldIn, entityIn.getPosition(), itemstack);
			((EntityItem) entityIn).getItem().setCount(((EntityItem) entityIn).getItem().getCount() - 1);
		}
		entityIn.setDead();

	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BoundingBox;
	}
}
