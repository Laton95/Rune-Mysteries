package com.laton95.runemysteries.gui;

import com.laton95.runemysteries.inventory.ContainerRuneBag;
import com.laton95.runemysteries.inventory.InventoryRuneBag;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public abstract class RMModGui extends GuiContainer {

	public RMModGui(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
}
