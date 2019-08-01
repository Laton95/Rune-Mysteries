package com.laton95.runemysteries.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ScrollItem extends ModItem {
	
	private final int dimensionId;
	
	public ScrollItem(int dimensionId) {
		super(new Properties().maxStackSize(16));
		this.dimensionId = dimensionId;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if(!world.isRemote) {
			player.sendMessage(new StringTextComponent("//TODO add scroll"));
		}
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
