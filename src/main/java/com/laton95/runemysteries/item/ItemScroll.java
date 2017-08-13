package com.laton95.runemysteries.item;

import com.laton95.runemysteries.util.TeleportHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemScroll extends RMModItem {

	public ItemScroll(String name, boolean showInCreative) {
		super(name, showInCreative);
		maxStackSize = 16;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			if (!playerIn.isCreative()) {
				playerIn.getHeldItem(handIn).shrink(1);
			}
			TeleportHelper.teleportEntity(playerIn, 30, 0, 64, 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
