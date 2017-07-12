package com.laton95.runemysteries.item;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
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
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		ItemStack rune = findRunes(playerIn, null);
		
		if (!playerIn.capabilities.isCreativeMode && rune.getCount() > 0)
        {
			rune.shrink(1);
			playerIn.getCooldownTracker().setCooldown(this, 20);
			fireSpell(worldIn, playerIn);
        } else if (playerIn.capabilities.isCreativeMode) {
        	playerIn.getCooldownTracker().setCooldown(this, 20);
			fireSpell(worldIn, playerIn);
		} else {
			if (!worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.spellbook.norunes"));
			}
			
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
		
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	private void fireSpell(World worldIn, EntityPlayer playerIn) {
		if (!worldIn.isRemote)
        {
            EntityEnderPearl entityenderpearl = new EntityEnderPearl(worldIn, playerIn);
            entityenderpearl.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityenderpearl);
        }
	}
}
