package com.laton95.runemysteries.item;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRuneBag extends RMModItem {

	public ItemRuneBag() {
		super("rune_bag", true);
		setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			playerIn.openGui(RuneMysteries.instance, GuiIDs.RUNE_BAG.ordinal(), worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
