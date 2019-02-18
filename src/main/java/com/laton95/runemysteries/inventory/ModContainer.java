package com.laton95.runemysteries.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public abstract class ModContainer extends Container {
	
	protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y) {
		this.addPlayerSlots(playerInventory, x, y, -1);
	}
	
	protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y, int protectedSlot) {
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
	
	private class ProtectedSlot extends Slot {
		
		public ProtectedSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean canTakeStack(EntityPlayer playerIn) {
			return false;
		}
	}
}
