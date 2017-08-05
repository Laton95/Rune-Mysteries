package com.laton95.runemysteries.inventory;

import java.util.List;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.reference.NamesReference;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import scala.swing.TextComponent;

public class InventoryRuneBag implements IInventory {
	private String customName;
	private final ItemStack bagItemStack;
	
	public final static int INVENTORY_SIZE = 14;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(INVENTORY_SIZE, ItemStack.EMPTY);

	public InventoryRuneBag(ItemStack stack) {
		bagItemStack = stack;
		
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		} else {
			ItemStackHelper.loadAllItems(bagItemStack.getTagCompound(), inventory);
		}
	}

	@Override
	public String getName() {
		return "rune_bag";
	}

	@Override
	public boolean hasCustomName() {
		return customName != null;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation(bagItemStack.getDisplayName());
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = getStackInSlot(index);

		if(itemstack != null)
		{
			if(itemstack.getCount() > count)
			{
				itemstack = itemstack.splitStack(count);
				markDirty();
			}
			else
			{
				setInventorySlotContents(index, ItemStack.EMPTY);
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.set(index, stack);

        if (stack != null && stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 80;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).getCount() == 0) {
				inventory.set(i, ItemStack.EMPTY);
			}
		}
				
		ItemStackHelper.saveAllItems(bagItemStack.getTagCompound(), inventory);
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (stack.getItem() instanceof ItemRune) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inventory.clear();
	}

}
