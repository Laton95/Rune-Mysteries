package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.item.ItemRune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRuneBag extends RMModContainer {
	private final InventoryRuneBag bag;
	
	private static final int INV_START = InventoryRuneBag.INVENTORY_SIZE;
	private static final int INV_END = INV_START+26;
	private static final int HOTBAR_START = INV_END+1;
	private static final int HOTBAR_END = HOTBAR_START+8;

	public ContainerRuneBag(InventoryPlayer playerInventory, InventoryRuneBag bag) {
		this.bag = bag;
		
		for (int i = 0; i < 2; ++i)
        {
            for (int j = 0; j < 7; ++j)
            {
                this.addSlotToContainer(new RuneSlot(bag, j + i * 7, 26 + j * 18, 35 + i * 18));
            }
        }
		
		this.addPlayerSlots(playerInventory, 8, 84);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return bag.isUsableByPlayer(playerIn);
	}

	//Thanks to CoolAlias for this implementation!
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// If item is in our custom Inventory or armor slot
			if (index < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END+1, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom inventory or armor slots
			else
			{
				/*you have basically 2 choices:
				1. shift-clicking between player inventory and custom inventory
				2. shift-clicking between action bar and inventory
				 
				Be sure to choose only ONE of the following implementations!!!
				*/
				/**
				 * Implementation number 1: Shift-click into your custom inventory
				 */
				if (index >= INV_START)
				{
					// place in custom inventory
					if (!this.mergeItemStack(itemstack1, 0, INV_START, false))
					{
						return ItemStack.EMPTY;
					}
				}
			}

			if (itemstack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}
	
	private class RuneSlot extends Slot {

		public RuneSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			if (stack.getItem() instanceof ItemRune) {
				return true;
			} else {
				return false;
			}
		}
		
	}
}
