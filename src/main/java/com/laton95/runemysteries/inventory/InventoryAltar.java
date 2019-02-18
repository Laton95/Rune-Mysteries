package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.enums.EnumRuneType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

public class InventoryAltar implements IInventory {
	
	private ItemStack contents;
	
	private final EnumRuneType runeType;
	
	public InventoryAltar(ItemStack contents, EnumRuneType runeType) {
		this.contents = contents;
		this.runeType = runeType;
	}
	
	public void setContents(ItemStack contents) {
		this.contents = contents.copy();
	}
	
	public ItemStack getContents() {
		return contents;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public boolean isEmpty() {
		return contents == ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return contents;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		contents.shrink(count);
		return contents;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack output = contents.copy();
		contents = ItemStack.EMPTY;
		return output;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		contents = stack.copy();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void markDirty() {
	
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
	
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
	
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {
	
	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public void clear() {
		contents = ItemStack.EMPTY;
	}
	
	@Override
	public ITextComponent getName() {
		return new TextComponentString("you shouldn't be seeing this");
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return new TextComponentString("you shouldn't be seeing this");
	}
}
