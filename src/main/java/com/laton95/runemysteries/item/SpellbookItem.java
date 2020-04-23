package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpellbookItem extends Item {
	
	
	public SpellbookItem() {
		super(new Properties().maxStackSize(1).group(RuneMysteries.RUNE_GROUP));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
