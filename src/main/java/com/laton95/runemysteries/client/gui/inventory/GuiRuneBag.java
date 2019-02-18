package com.laton95.runemysteries.client.gui.inventory;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.inventory.ContainerRuneBag;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRuneBag extends GuiContainer {
	
	public static final ResourceLocation RUNE_BAG_GUI_ID = new ResourceLocation(RuneMysteries.MOD_ID, "gui_rune_bag");
	
	private static final ResourceLocation RUNE_BAG_GUI_TEXTURES = new ResourceLocation(RuneMysteries.MOD_ID, "textures/gui/container/rune_bag.png");
	
	private final InventoryPlayer playerInventory;
	
	private final InventoryRuneBag bagInventory;
	
	public GuiRuneBag(InventoryPlayer playerInventory, InventoryRuneBag bagInventory, int protectedSlot) {
		super(new ContainerRuneBag(playerInventory, bagInventory, protectedSlot));
		this.playerInventory = playerInventory;
		this.bagInventory = bagInventory;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(bagInventory.getDisplayName().getFormattedText(), 8.0F, 6.0F, 4210752);
		this.fontRenderer.drawString(playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 109), 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(RUNE_BAG_GUI_TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
