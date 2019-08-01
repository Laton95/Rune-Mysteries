package com.laton95.runemysteries.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpellbookItem extends ModItem {
	
	
	public SpellbookItem() {
		super(new Properties().maxStackSize(1));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
