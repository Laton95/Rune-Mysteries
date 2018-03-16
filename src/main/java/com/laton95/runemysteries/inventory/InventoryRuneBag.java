package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.item.ItemRune;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class InventoryRuneBag implements IItemHandlerModifiable
{
	
	private final ItemStack bagItemStack;
	private final IItemHandlerModifiable inventory;
	
	public InventoryRuneBag(ItemStack stack)
	{
		bagItemStack = stack;
		inventory = (IItemHandlerModifiable) bagItemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}
	
	public int getRuneCount(ItemRune rune, int metadata)
	{
		int count = 0;
		for (int i = 0; i < getSlots(); i++)
		{
			ItemStack stack = getStackInSlot(i);
			if (stack.getItem().equals(rune) && stack.getItemDamage() == metadata)
			{
				count += stack.getCount();
			}
		}
		return count;
	}
	
	public ITextComponent getDisplayName()
	{
		return new TextComponentString(bagItemStack.getDisplayName());
	}
	
	public int removeRune(ItemRune rune, int count, int metadata)
	{
		int i = 0;
		while (count > 0 && i < getSlots())
		{
			ItemStack itemstack = getStackInSlot(i);
			if (itemstack.getItem().equals(rune) && itemstack.getItemDamage() == metadata)
			{
				int temp = count;
				count -= itemstack.getCount();
				itemstack.shrink(temp);
			}
			i++;
		}
		return count;
	}
	
	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		inventory.setStackInSlot(slot, stack);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public int getSlots()
	{
		return inventory.getSlots();
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		return inventory.insertItem(slot, stack, simulate);
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		return inventory.extractItem(slot, amount, simulate);
	}
	
	@Override
	public int getSlotLimit(int slot)
	{
		return inventory.getSlotLimit(slot);
	}
}
