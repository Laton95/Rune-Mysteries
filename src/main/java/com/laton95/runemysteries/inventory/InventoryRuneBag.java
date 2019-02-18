package com.laton95.runemysteries.inventory;

import com.laton95.runemysteries.client.gui.inventory.GuiRuneBag;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryRuneBag extends ItemStackHandler implements IInteractionObject {
	
	public static final int SIZE = 14;
	
	private final ItemStack bagStack;
	
	public InventoryRuneBag(ItemStack bagStack) {
		super(SIZE);
		this.bagStack = bagStack;
	}
	
	@Nonnull
	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if(!stack.isEmpty() && stack.getItem() instanceof ItemRune) {
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
	public ITextComponent getName() {
		return bagStack.getItem().getName();
	}
	
	@Override
	public boolean hasCustomName() {
		return bagStack.hasDisplayName();
	}
	
	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return bagStack.getDisplayName();
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		int protectedSlot = -1;
		if(playerIn.getHeldItemMainhand().getItem() == ModItems.RUNE_BAG) {
			protectedSlot = playerInventory.currentItem;
		}
		
		return new ContainerRuneBag(playerInventory, this, protectedSlot);
	}
	
	@Override
	public String getGuiID() {
		return GuiRuneBag.RUNE_BAG_GUI_ID.toString();
	}
}
