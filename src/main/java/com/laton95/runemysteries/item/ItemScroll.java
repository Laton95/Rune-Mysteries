package com.laton95.runemysteries.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemScroll extends ModItem {
	
	private final int dimensionId;
	
	public ItemScroll(int dimensionId) {
		super(new Properties().maxStackSize(16));
		this.dimensionId = dimensionId;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentString("//TODO add scroll"));
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
