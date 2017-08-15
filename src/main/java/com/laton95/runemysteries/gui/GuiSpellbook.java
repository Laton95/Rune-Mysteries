package com.laton95.runemysteries.gui;

import java.io.IOException;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.lwjgl.input.Keyboard;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.google.common.base.Enums;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.network.MessageSpellSelect;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.ItemNBTHelper;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class GuiSpellbook extends RMModGuiScreen {
	private final EntityPlayer player;
	private final ItemStack spellbook;
	private Spell newSpell;
	private Spell oldSpell;
	private Spell costSpell;
	private GuiButton buttonDone;
	private GuiButton buttonCancel;
	private GuiButton buttonExtra;

	private final ResourceLocation guiTexture = new ResourceLocation(ModReference.MOD_ID, "textures/gui/spellbook.png");
	private final ResourceLocation guiTexture2 = new ResourceLocation(ModReference.MOD_ID,
			"textures/items/spellbook.png");

	private int textureHeight = 256;
	private int textureWidth = 128;
	private int borderWidth = 3;
	private int offset = -37;

	public GuiSpellbook(EntityPlayer player, ItemStack spellbook) {
		this.player = player;
		this.spellbook = spellbook;

		if (spellbook.hasTagCompound()) {
			oldSpell = ItemNBTHelper.getSpell(spellbook);
		} else {
			oldSpell = Spells.NONE_SPELL;
		}
		newSpell = oldSpell;
	}

	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		int standardWidth = (this.width) / 2 - 49;
		int standardHeight = (this.height + textureHeight) / 2 + borderWidth + offset;
		this.buttonDone = this
				.addButton(new GuiButton(0, standardWidth - 50, standardHeight, 98, 20, I18n.format("gui.done")));
		this.buttonCancel = this
				.addButton(new GuiButton(1, standardWidth + 50, standardHeight, 98, 20, I18n.format("gui.cancel")));
		this.buttonExtra = this.addButton(new GuiButton(2, standardWidth + 64, standardHeight - 35, 98, 20,
				I18n.format(NamesReference.Spellbook.COST_BUTTON)));
		this.buttonExtra.visible = false;
		
		int idIterator = 3;
		int i = 0;
		int j = 0;
		int xStart = standardWidth - 73;
		int yStart = standardHeight - 190;
		int xInterval = 17;
		int yInterval = 17;
		for (Spell spell : Spells.spellList) {
			if (!spell.equals(Spells.NONE_SPELL)) {
				this.addButton(new SpellButton(idIterator, xStart + i*xInterval, yStart + j*yInterval, spell));
				i++;
				if (i > 6) {
					i = 0;
					j++;
				}
				idIterator++;
			}
		}
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			switch (button.id) {
			case 0:
				sendSpellToServer();
				this.mc.displayGuiScreen((GuiScreen) null);
				break;
			case 1:
				this.mc.displayGuiScreen((GuiScreen) null);
				break;
			case 2:
				this.mc.displayGuiScreen(new GuiExtraItems(player, spellbook, costSpell));
				break;
			default:
				newSpell = ((SpellButton)buttonList.get(button.id)).getSpell();
				break;
			}
		}
	}

	private void sendSpellToServer() {
		if (oldSpell != newSpell) {
			ItemNBTHelper.setSpell(spellbook, newSpell);
			NetworkHandler.sendToServer(new MessageSpellSelect(newSpell));
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.buttonExtra.visible = false;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		int xStart = (this.width - textureHeight) / 2;
		int yStart = (this.height - textureWidth) / 2 + offset;
		this.mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect(xStart + 1, yStart, 0, 0, 128, 192);
		this.drawTexturedModalRect(xStart + 128, yStart, 128, 0, 128, 192);

		TextComponentTranslation temp1;
		TextComponentTranslation temp2;
		Spell tempSpell = newSpell;
		if (tempSpell != null) {
			temp1 = new TextComponentTranslation(tempSpell.getName());
			temp2 = new TextComponentTranslation(tempSpell.getDescription());

			if (tempSpell.getCosts().size() <= 6) {
				// Render spell costs
				int i = 0;
				int j = 0;
				int x = xStart + 137;
				int y = yStart + 150;
				int xInterval = 37;
				int yInterval = 18;
				for (SpellCost cost : tempSpell.getCosts()) {
					RenderHelper.enableGUIStandardItemLighting();
					ItemStack stack = new ItemStack(cost.getItem(), 1, cost.getMetadata());
					mc.getRenderItem().renderItemIntoGUI(stack, x + xInterval * j, yInterval * i + y);
					RenderHelper.disableStandardItemLighting();
					fontRenderer.drawString(" x " + cost.getCount(), x + 15 + xInterval * j, 3 + yInterval * i + y, 0);
					i++;
					if (i > 1) {
						i = 0;
						j++;
					}
				}
			} else {
				costSpell = tempSpell;
				this.buttonExtra.visible = true;
			}
		} else {
			temp1 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_NAME);
			temp2 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_DESCRIPTION);
		}

		fontRenderer.drawString(TextFormatting.BLACK + temp1.getFormattedText() + TextFormatting.UNDERLINE,
				xStart + 192 - fontRenderer.getStringWidth(temp1.getFormattedText()) / 2, yStart + 30, 0, false);
		fontRenderer.drawSplitString(TextFormatting.BLACK + temp2.getFormattedText(), xStart + 140, yStart + 60, 107,
				0);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private class GuiExtraItems extends RMModGuiScreen {
		private final EntityPlayer player;
		private final ItemStack spellbook;
		private GuiButton buttonDone;

		public GuiExtraItems(EntityPlayer player, ItemStack spellbook, Spell spell) {
			this.player = player;
			this.spellbook = spellbook;
		}

		public void initGui() {
			this.buttonList.clear();

			int standardWidth = (this.width) / 2 - 49;
			int standardHeight = (this.height + textureHeight) / 2 + borderWidth + offset;
			this.buttonDone = this
					.addButton(new GuiButton(0, standardWidth, standardHeight, 98, 20, I18n.format("gui.done")));
		}

		protected void actionPerformed(GuiButton button) throws IOException {
			if (button.enabled) {
				player.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), player.getEntityWorld(),
						(int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}

		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			this.mc.getTextureManager().bindTexture(guiTexture);

			int xStart = (this.width - textureHeight / 2) / 2;
			int yStart = (this.height - textureWidth) / 2 + offset;
			this.drawTexturedModalRect(xStart, yStart, 0, 0, 128, 192);
			Spell tempSpell = newSpell;
			if (tempSpell != null) {
				// Render spell costs
				int i = 0;
				int j = 0;
				int x = xStart + 4;
				int y = yStart + 4;
				int xInterval = 42;
				int yInterval = 18;
				for (SpellCost cost : tempSpell.getCosts()) {
					RenderHelper.enableGUIStandardItemLighting();
					ItemStack stack = new ItemStack(cost.getItem(), 1, cost.getMetadata());
					mc.getRenderItem().renderItemIntoGUI(stack, x + xInterval * j, yInterval * i + y);
					RenderHelper.disableStandardItemLighting();
					fontRenderer.drawString(" x " + cost.getCount(), x + 15 + xInterval * j, 3 + yInterval * i + y, 0);
					i++;
					if (i > 9) {
						i = 0;
						j++;
					}
				}
				super.drawScreen(mouseX, mouseY, partialTicks);
			}
		}
	}
	
	private class SpellButton extends GuiButton{
		private final ResourceLocation buttonTexture = new ResourceLocation(ModReference.MOD_ID, "textures/gui/widgets.png");
		private final ResourceLocation spellTexture;
		private final Spell spell;
		public SpellButton(int buttonId, int x, int y, Spell spell) {
			super(buttonId, x, y, I18n.format(spell.getName()));
			this.spell = spell;
			this.width = 16;
			this.height = 16;
			this.spellTexture = spell.getGuiTexture();
		}
		
		public Spell getSpell() {
			return spell;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (this.visible)
	        {
	            FontRenderer fontrenderer = mc.fontRenderer;
	            mc.getTextureManager().bindTexture(buttonTexture);
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            int i = this.getHoverState(this.hovered);
	            GlStateManager.enableBlend();
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            this.drawTexturedModalRect(this.x, this.y, 0, i * 16, this.width / 2, this.height);
	            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, i * 16, this.width / 2, this.height);
	            this.mouseDragged(mc, mouseX, mouseY);
	            int j = 14737632;
	            
	            mc.getTextureManager().bindTexture(spellTexture);
	            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height, this.width, this.height);

	            if (packedFGColour != 0)
	            {
	                j = packedFGColour;
	            }
	            else
	            if (!this.enabled)
	            {
	                j = 10526880;
	            }
	            else if (this.hovered)
	            {
	                j = 16777120;
	            }

	            //this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
	        }
		}
		
		@Override
		protected int getHoverState(boolean mouseOver) {
			if (mouseOver) {
				return 1;
			} else return 0;
		}
	}
}
