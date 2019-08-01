package com.laton95.runemysteries.inventory.crafting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class WorldCraftingInventory implements IInventory {
	
	@Override
	public int getSizeInventory() {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int i, int i1) {
		return null;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int i) {
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
	
	}
	
	@Override
	public void markDirty() {
	
	}
	
	@Override
	public boolean isUsableByPlayer(PlayerEntity playerEntity) {
		return false;
	}
	
	@Override
	public void clear() {
	
	}
}
