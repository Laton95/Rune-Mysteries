package com.laton95.runemysteries.inventory.crafting;

import com.laton95.runemysteries.enums.EnumRuneType;
import net.minecraft.item.ItemStack;

public class RuneCraftingInventory extends WorldCraftingInventory {
	
	private final ItemStack input;
	
	private final EnumRuneType runeType;
	
	public RuneCraftingInventory(ItemStack input, EnumRuneType runeType) {
		this.input = input;
		this.runeType = runeType;
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
}
