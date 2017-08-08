package com.laton95.runemysteries.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.network.MessageSpellSelect;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.Spells.EnumSpell;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class GuiSpellbook extends RMModGuiScreen {
	private final EntityPlayer player;
	private final ItemStack spellbook;
	private EnumSpell newSpell;
	private EnumSpell oldSpell;
	private Spell costSpell;
	private GuiButton buttonDone;
	private GuiButton buttonCancel;
	private GuiButton buttonExtra;
	private GuiButton buttonEnderpearl;
	private GuiButton buttonSnowball;
	private GuiButton buttonExplosion;
	private GuiButton buttonDeath;

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
			oldSpell = ItemSpellbook.getCurrentSpell(spellbook);
		} else {
			oldSpell = EnumSpell.NONE;
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
		this.buttonEnderpearl = this.addButton(new GuiButton(2, standardWidth - 64, standardHeight - 180, 98, 20,
				I18n.format(NamesReference.Spells.ENDERPEARL_SPELL_NAME)));
		this.buttonSnowball = this.addButton(new GuiButton(3, standardWidth - 64, standardHeight - 160, 98, 20,
				I18n.format(NamesReference.Spells.SNOWBALL_SPELL_NAME)));
		this.buttonExplosion = this.addButton(new GuiButton(4, standardWidth - 64, standardHeight - 140, 98, 20,
				I18n.format(NamesReference.Spells.EXPLOSION_SPELL_NAME)));
		this.buttonDeath = this.addButton(new GuiButton(5, standardWidth - 64, standardHeight - 120, 98, 20,
				I18n.format(NamesReference.Spells.DEATH_SPELL_NAME)));
		this.buttonExtra = this.addButton(new GuiButton(6, standardWidth + 64, standardHeight - 35, 98, 20,
				I18n.format(NamesReference.Spellbook.COST_BUTTON)));
		this.buttonExtra.visible = false;
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
				newSpell = EnumSpell.ENDERPEARL_SPELL;
				break;
			case 3:
				newSpell = EnumSpell.SNOWBALL_SPELL;
				break;
			case 4:
				newSpell = EnumSpell.EXPLOSION_SPELL;
				break;
			case 5:
				newSpell = EnumSpell.DEATH_SPELL;
				break;
			case 6:
				this.mc.displayGuiScreen(new GuiExtraItems(player, spellbook, costSpell));
				break;
			}
		}
	}

	private void sendSpellToServer() {
		if (oldSpell != newSpell) {
			ItemSpellbook.setCurrentSpell(spellbook, newSpell);
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
		Spell tempSpell = Spells.getSpellFromEnum(newSpell);
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
					String itemFullName = cost.getItem().getRegistryName().toString();
					int colon = itemFullName.indexOf(":");
					String domain = itemFullName.substring(0, colon);
					String itemName = itemFullName.substring(colon + 1);
					ResourceLocation itemTexture = new ResourceLocation(domain, "textures/items/" + itemName + ".png");
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					this.mc.getTextureManager().bindTexture(itemTexture);
					drawModalRectWithCustomSizedTexture(x + xInterval * j, yInterval * i + y, 16, 16, 16, 16, 16, 16);
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
			Spell tempSpell = Spells.getSpellFromEnum(newSpell);
			if (tempSpell != null) {
				// Render spell costs
				int i = 0;
				int j = 0;
				int x = xStart + 4;
				int y = yStart + 4;
				int xInterval = 42;
				int yInterval = 18;
				for (SpellCost cost : tempSpell.getCosts()) {
					String itemFullName = cost.getItem().getRegistryName().toString();
					int colon = itemFullName.indexOf(":");
					String domain = itemFullName.substring(0, colon);
					String itemName = itemFullName.substring(colon + 1);
					ResourceLocation itemTexture = new ResourceLocation(domain, "textures/items/" + itemName + ".png");
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					this.mc.getTextureManager().bindTexture(itemTexture);
					drawModalRectWithCustomSizedTexture(x + xInterval * j, yInterval * i + y, 16, 16, 16, 16, 16, 16);
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
}
