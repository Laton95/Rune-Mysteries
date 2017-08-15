package com.laton95.runemysteries.inventory;

import java.util.List;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import scala.swing.TextComponent;

public class InventoryRuneBag implements IItemHandlerModifiable {
	
	private final ItemStack bagItemStack;
	private final IItemHandlerModifiable inventory;

	public InventoryRuneBag(ItemStack stack) {
		bagItemStack = stack;
		inventory = (IItemHandlerModifiable) bagItemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}
	
	public int getRuneCount(ItemRune rune) {
		int count = 0;
		for (int i = 0; i < getSlots(); i++) {
			ItemStack stack = getStackInSlot(i);
			if (stack.getItem().equals(rune)) {
				count += stack.getCount();
			}
		}
		return count;
	}
	
	public ITextComponent getDisplayName() {
		return (ITextComponent)(new TextComponentString(bagItemStack.getDisplayName()));
	}
	
	public int removeRune(ItemRune rune, int count) {
		int i = 0;
		while (count > 0 && i < getSlots()) {
			ItemStack itemstack = getStackInSlot(i);
			if (itemstack.getItem().equals(rune)) {
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}
		return count;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		inventory.setStackInSlot(slot, stack);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}

	@Override
	public int getSlots() {
		return inventory.getSlots();
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return inventory.insertItem(slot, stack, simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return inventory.extractItem(slot, amount, simulate);
	}

	@Override
	public int getSlotLimit(int slot) {
		return inventory.getSlotLimit(slot);
	}
}
