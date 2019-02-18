package com.laton95.runemysteries;

import com.laton95.runemysteries.client.gui.inventory.GuiRuneBag;
import com.laton95.runemysteries.item.ItemRuneBag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler {
	
	public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer) {
		if(openContainer.getId().equals(GuiRuneBag.RUNE_BAG_GUI_ID)) {
			ItemStack stack = openContainer.getAdditionalData().readItemStack();
			return new GuiRuneBag(Minecraft.getInstance().player.inventory, ItemRuneBag.getInventory(stack));
		}
		
		return null;
	}
}
