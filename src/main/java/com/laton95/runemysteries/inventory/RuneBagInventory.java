package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.RuneItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RuneBagInventory extends ItemStackHandler implements INamedContainerProvider {
	
	public static final int SIZE = 14;
	
	private final ItemStack bagStack;
	
	public RuneBagInventory(ItemStack bagStack) {
		super(SIZE);
		this.bagStack = bagStack;
	}
	
	@Nonnull
	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if(!stack.isEmpty() && stack.getItem() instanceof RuneItem) {
			return super.insertItem(slot, stack, simulate);
		}
		else {
			return stack;
		}
	}
	
	public NonNullList<ItemStack> getStacks() {
		return stacks;
	}
	
	public void setStacks(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return bagStack.getDisplayName();
	}
	
	@Nullable
	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
		int protectedSlot = -1;
		if(player.getHeldItemMainhand().getItem() == ModItems.RUNE_BAG) {
			protectedSlot = playerInventory.currentItem;
		}
		
		return new RuneBagContainer(id, playerInventory, this, protectedSlot);
	}
}
