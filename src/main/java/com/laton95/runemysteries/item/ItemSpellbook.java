package com.laton95.runemysteries.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSpellbook extends RMModItem {

	public ItemSpellbook() {
		super("spellbook", true);
		setMaxStackSize(1);
	}

	private ItemStack findRunes(EntityPlayer player, ItemRune rune) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = player.inventory.getStackInSlot(i);

			if (itemstack.getItem().equals(rune)) {
				return itemstack;
			} else if (rune == null && itemstack.getItem() instanceof ItemRune) {
				return itemstack;
			}
		}

		return ItemStack.EMPTY;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack book = playerIn.getHeldItem(handIn);
		ItemStack rune = findRunes(playerIn, null);

		if (!playerIn.capabilities.isCreativeMode) {
			if (!rune.isEmpty()) {
				rune.setCount(rune.getCount() - 1);
				fireArrow(worldIn, playerIn);
				return new ActionResult(EnumActionResult.SUCCESS, book);
			} else {
				return new ActionResult(EnumActionResult.FAIL, book);
			}
		} else {
			fireArrow(worldIn, playerIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, book);
		}
	}

	private void fireArrow(World worldIn, EntityPlayer playerIn) {
		ItemStack itemstack = new ItemStack(Items.ARROW);
		ItemArrow arrow = (ItemArrow) Items.ARROW;
		EntityArrow entityarrow = arrow.createArrow(worldIn, itemstack, playerIn);
		entityarrow.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
		entityarrow.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
		worldIn.spawnEntity(entityarrow);
	}
}
