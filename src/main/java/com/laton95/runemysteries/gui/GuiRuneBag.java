package com.laton95.runemysteries.gui;

import com.laton95.runemysteries.inventory.ContainerRuneBag;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiRuneBag extends RMModGuiContainer
{

	private final ResourceLocation guiTexture = new ResourceLocation(ModReference.MOD_ID, "textures/gui/rune_bag.png");
	private final IInventory playerInventory;
	private final InventoryRuneBag bagInventory;

	public GuiRuneBag(InventoryPlayer playerInv, InventoryRuneBag bag)
	{
		super(new ContainerRuneBag(playerInv, bag));
		playerInventory = playerInv;
		bagInventory = bag;
		allowUserInput = false;
		ySize = 151;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		fontRenderer.drawString(bagInventory.getDisplayName().getUnformattedText(), 8, 23, 4210752);
		fontRenderer.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 78, 4210752);
	}

	/**
	 * Draws the background layer of this container (behind the items).
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(guiTexture);
		int i = (width - xSize) / 2;
		int j = (height - ySize) / 2;
		this.drawTexturedModalRect(i, j + 15, 0, 0, xSize, ySize);
	}
}
