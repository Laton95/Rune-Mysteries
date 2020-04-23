package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ScrollItem extends Item {
	
	private final int dimensionId;
	
	public ScrollItem(int dimensionId) {
		super(new Properties().maxStackSize(16).group(RuneMysteries.RUNE_GROUP));
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
