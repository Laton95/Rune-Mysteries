package com.laton95.runemysteries.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventoryHelper {
	
	public static List<Integer> getSlotsWithItem(EntityPlayer player, Item item) {
		List<Integer> slots = new ArrayList<>();
		
		for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			if(player.inventory.getStackInSlot(i).getItem() == item) {
				slots.add(i);
			}
		}
		
		return slots;
	}
}
