package com.laton95.runemysteries.item;

import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.ModConfig;
import com.laton95.runemysteries.util.TeleportHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemScroll extends RMModItem
{

	public ItemScroll(String name, boolean showInCreative)
	{
		super(name, showInCreative);
		maxStackSize = 16;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		if (!worldIn.isRemote)
		{
			if (worldIn.provider.getDimension() != ModConfig.DIMENSIONS.essenceMineDimID)
			{
				if (!playerIn.isCreative())
				{
					playerIn.getHeldItem(handIn).shrink(1);
				}
				playerIn.getCooldownTracker().setCooldown(this, 500);
				TeleportHelper.teleportEntity(playerIn, ModConfig.DIMENSIONS.essenceMineDimID, 0, 64, 0);
			}
			else
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Scroll.FAIL));
			}

		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
