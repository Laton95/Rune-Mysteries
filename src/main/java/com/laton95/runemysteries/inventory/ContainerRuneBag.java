package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.item.ItemRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerRuneBag extends RMModContainer
{
	
	private final InventoryRuneBag bag;
	
	public ContainerRuneBag(InventoryPlayer playerInventory, InventoryRuneBag bag)
	{
		this.bag = bag;
		
		for(int i = 0; i < 2; ++i)
		{
			for(int j = 0; j < 7; ++j)
			{
				addSlotToContainer(new RuneSlot(bag, j + i * 7, 26 + j * 18, 35 + i * 18));
			}
		}
		
		addPlayerSlots(playerInventory, 8, 84);
	}
	
	// Thanks to CoolAlias for this implementation!
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		int invStart = bag.getSlots();
		int invEnd = invStart + 26;
		int hotbarStart = invEnd + 1;
		int hotbarEnd = hotbarStart + 8;
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// If item is in our custom inventory
			if(index < invStart)
			{
				// try to place in player inventory / action bar
				if(!mergeItemStack(itemstack1, invStart, hotbarEnd + 1, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom
			// inventory
			else
			{
				if(!mergeItemStack(itemstack1, 0, invStart, false))
				{
					return ItemStack.EMPTY;
				}
			}
			
			if(itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
			
			if(itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, itemstack1);
		}
		
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	private class RuneSlot extends SlotItemHandler
	{
		
		public RuneSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
		{
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack)
		{
			if(stack.getItem() instanceof ItemRune)
			{
				return super.isItemValid(stack);
			}
			else
			{
				return false;
			}
		}
		
	}
}
