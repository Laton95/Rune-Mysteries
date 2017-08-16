package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneAltar extends RMModBlock {
	private EnumRuneType type;
	protected Item item = ItemRegistry.RUNE;

	public BlockRuneAltar(String name, EnumRuneType type) {
		super(name, Material.ROCK, 0, 2000f, null, 0, true);
		this.type = type;
		setBlockUnbreakable();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityItem) {
			Item itemType = ((EntityItem) entityIn).getItem().getItem();
			if (itemType.equals(ItemRegistry.RUNE_ESSENCE)) {
				giveAdvancements((EntityItem) entityIn, worldIn);
				spawnItem(worldIn, entityIn, item, type.ordinal());
			}

			if (itemType.equals(Items.BOOK)) {
				spawnItem(worldIn, entityIn, ItemRegistry.SPELLBOOK, 0);
			}
		}
	}

	protected void giveAdvancements(EntityItem entityIn, World worldIn) {
		String thrower = entityIn.getThrower();
		if (thrower != null) {
			EntityPlayer player = worldIn.getPlayerEntityByName(thrower);
			if (player instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player, new ItemStack(ItemRegistry.RUNE_ESSENCE));
			}
		}
	}

	protected void spawnItem(World worldIn, Entity entityIn, Item item, int metadata) {
		while (((EntityItem) entityIn).getItem().getCount() > 0) {
			ItemStack itemstack = new ItemStack(item, 1, metadata);
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
