package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.init.ModContainers;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.RuneItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class RuneBagContainer extends Container {
	
	private RuneBagInventory bagInventory;
	
	public RuneBagContainer(int id, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
		this(id, playerInventory, new RuneBagInventory(new ItemStack(ModItems.RUNE_BAG)), -1);
	}
	
	public RuneBagContainer(int id, PlayerInventory playerInventory, RuneBagInventory bagInventory, int protectedSlot) {
		super(ModContainers.RUNE_BAG, id);
		this.bagInventory = bagInventory;
		
		int xOffset = 26;
		int yOffset = 20;
		
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 7; ++j) {
				addSlot(new RuneSlot(this.bagInventory, j + i * 7, xOffset + j * 18, yOffset + i * 18));
			}
		}
		
		addPlayerSlots(playerInventory, 8, 69, protectedSlot);
	}
	
	protected void addPlayerSlots(PlayerInventory playerInventory, int x, int y, int protectedSlot) {
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				int index = j + i * 9 + 9;
				if(index != protectedSlot) {
					addSlot(new Slot(playerInventory, index, x + j * 18, y + i * 18));
				}
				else {
					addSlot(new ProtectedSlot(playerInventory, index, x + j * 18, y + i * 18));
				}
				
			}
		}
		
		for(int i = 0; i < 9; ++i) {
			if(i != protectedSlot) {
				addSlot(new Slot(playerInventory, i, x + i * 18, y + 58));
			}
			else {
				addSlot(new ProtectedSlot(playerInventory, i, x + i * 18, y + 58));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		
		int invStart = bagInventory.getSlots();
		int invEnd = invStart + 26;
		int hotbarStart = invEnd + 1;
		int hotbarEnd = hotbarStart + 8;
		
		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			// If item is in our custom inventory
			if(index < invStart) {
				// try to place in player inventory / action bar
				if(!mergeItemStack(itemstack1, invStart, hotbarEnd + 1, true)) {
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom
			// inventory
			else {
				if(!mergeItemStack(itemstack1, 0, invStart, false)) {
					return ItemStack.EMPTY;
				}
			}
			
			if(itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
			
			if(itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, itemstack1);
		}
		
		return itemstack;
	}
	
	private class RuneSlot extends SlotItemHandler {
		
		public RuneSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			if(stack.getItem() instanceof RuneItem) {
				return super.isItemValid(stack);
			}
			else {
				return false;
			}
		}
	}
	
	private class ProtectedSlot extends Slot {
		
		public ProtectedSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean canTakeStack(PlayerEntity playerIn) {
			return false;
		}
	}
}
