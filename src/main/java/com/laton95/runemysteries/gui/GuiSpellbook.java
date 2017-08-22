package com.laton95.runemysteries.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.laton95.runemysteries.GuiHandler.GuiIDs;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.network.MessageSpellSelect;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.SpellBase;
import com.laton95.runemysteries.spells.SpellBase.SpellCost;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.ItemNBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
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
import net.minecraftforge.fml.client.config.HoverChecker;
import scala.tools.nsc.interpreter.IMain.StrippingTruncatingWriter;

public class GuiSpellbook extends RMModGuiScreen
{

	private final EntityPlayer player;
	private final ItemStack spellbook;
	private SpellBase newSpell;
	private SpellBase oldSpell;
	private SpellBase costSpell;
	private GuiButton buttonDone;
	private GuiButton buttonCancel;
	private GuiButton buttonExtra;

	private final ResourceLocation guiTexture = new ResourceLocation(ModReference.MOD_ID, "textures/gui/spellbook.png");
	private final ResourceLocation guiTexture2 = new ResourceLocation(ModReference.MOD_ID, "textures/items/spellbook.png");

	private int textureHeight = 256;
	private int textureWidth = 128;
	private int borderWidth = 3;
	private int offset = -37;

	public GuiSpellbook(EntityPlayer player, ItemStack spellbook)
	{
		this.player = player;
		this.spellbook = spellbook;

		if (spellbook.hasTagCompound())
		{
			oldSpell = ItemNBTHelper.getSpell(spellbook);
		}
		else
		{
			oldSpell = Spells.NONE_SPELL;
		}
		newSpell = oldSpell;
	}

	@Override
	public void initGui()
	{
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);

		int standardWidth = width / 2 - 49;
		int standardHeight = (height + textureHeight) / 2 + borderWidth + offset;
		buttonDone = this.addButton(new GuiButton(0, standardWidth
				- 50, standardHeight, 98, 20, I18n.format("gui.done")));
		buttonCancel = this.addButton(new GuiButton(1, standardWidth
				+ 50, standardHeight, 98, 20, I18n.format("gui.cancel")));
		buttonExtra = this.addButton(new GuiButton(2, standardWidth + 64, standardHeight
				- 35, 98, 20, I18n.format(NamesReference.Spellbook.COST_BUTTON)));
		buttonExtra.visible = false;

		int idIterator = 3;
		int i = 0;
		int j = 0;
		int xStart = standardWidth - 73;
		int yStart = standardHeight - 190;
		int xInterval = 17;
		int yInterval = 17;
		for (SpellBase spell : Spells.spellList)
		{
			if (!spell.equals(Spells.NONE_SPELL))
			{
				this.addButton(
						new SpellButton(idIterator, xStart + i * xInterval, yStart + j * yInterval, spell));
				i++;
				if (i > 6)
				{
					i = 0;
					j++;
				}
				idIterator++;
			}
		}
	}

	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.enabled)
		{
			switch (button.id)
			{
				case 0:
					sendSpellToServer();
					mc.displayGuiScreen((GuiScreen) null);
					break;
				case 1:
					mc.displayGuiScreen((GuiScreen) null);
					break;
				case 2:
					mc.displayGuiScreen(new GuiExtraItems(player, spellbook, costSpell));
					break;
				default:
					newSpell = ((SpellButton) buttonList.get(button.id)).getSpell();
					break;
			}
		}
	}

	private void sendSpellToServer()
	{
		if (oldSpell != newSpell)
		{
			ItemNBTHelper.setSpell(spellbook, newSpell);
			NetworkHandler.sendToServer(new MessageSpellSelect(newSpell));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		buttonExtra.visible = false;
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		int xStart = (width - textureHeight) / 2;
		int yStart = (height - textureWidth) / 2 + offset;
		mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect(xStart + 1, yStart, 0, 0, 128, 192);
		this.drawTexturedModalRect(xStart + 128, yStart, 128, 0, 128, 192);

		TextComponentTranslation temp1;
		TextComponentTranslation temp2;
		SpellBase tempSpell = newSpell;
		if (tempSpell != null)
		{
			temp1 = new TextComponentTranslation(tempSpell.getName());
			temp2 = new TextComponentTranslation(tempSpell.getDescription());

			if (tempSpell.getCosts().size() <= 6)
			{
				// Render spell costs
				int i = 0;
				int j = 0;
				int x = xStart + 137;
				int y = yStart + 150;
				int xInterval = 37;
				int yInterval = 18;
				for (SpellCost cost : tempSpell.getCosts())
				{
					RenderHelper.enableGUIStandardItemLighting();
					ItemStack stack = new ItemStack(cost.getItem(), 1, cost.getMetadata());
					mc.getRenderItem().renderItemIntoGUI(stack, x + xInterval * j, yInterval * i + y);
					RenderHelper.disableStandardItemLighting();
					fontRenderer.drawString(" x " + cost.getCount(), x + 15 + xInterval * j,
							3 + yInterval * i + y, 0);
					i++;
					if (i > 1)
					{
						i = 0;
						j++;
					}
				}
			}
			else
			{
				costSpell = tempSpell;
				buttonExtra.visible = true;
			}
		}
		else
		{
			temp1 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_NAME);
			temp2 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_DESCRIPTION);
		}
		
		fontRenderer.drawString(TextFormatting.BLACK + temp1.getFormattedText() + TextFormatting.UNDERLINE, xStart + 192
				- fontRenderer.getStringWidth(temp1.getFormattedText()) / 2, yStart + 30, 0, false);
		fontRenderer.drawSplitString(TextFormatting.BLACK + temp2.getFormattedText(), xStart + 140, yStart
				+ 60, 107, 0);

		super.drawScreen(mouseX, mouseY, partialTicks);
		
		for (GuiButton button : this.buttonList)
		{
			if (button instanceof SpellButton)
			{
				HoverChecker hoverChecker = new HoverChecker(button, 10);
				if (hoverChecker.checkHover(mouseX, mouseY))
				{
					TextComponentTranslation tooltip = new TextComponentTranslation(((SpellButton) button).getSpell().getName());
					drawHoveringText(tooltip.getFormattedText(), button.x, button.y);
				}
			}
		}
	}

	private class GuiExtraItems extends RMModGuiScreen
	{

		private final EntityPlayer player;
		private GuiButton buttonDone;

		public GuiExtraItems(EntityPlayer player, ItemStack spellbook, SpellBase spell)
		{
			this.player = player;
		}

		@Override
		public void initGui()
		{
			buttonList.clear();

			int standardWidth = width / 2 - 49;
			int standardHeight = (height + textureHeight) / 2 + borderWidth + offset;
			buttonDone = this.addButton(
					new GuiButton(0, standardWidth, standardHeight, 98, 20, I18n.format("gui.done")));
		}

		@Override
		protected void actionPerformed(GuiButton button) throws IOException
		{
			if (button.enabled)
			{
				player.openGui(RuneMysteries.instance, GuiIDs.SPELLBOOK.ordinal(), player.getEntityWorld(),
						(int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}

		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks)
		{
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			mc.getTextureManager().bindTexture(guiTexture);

			int xStart = (width - textureHeight / 2) / 2;
			int yStart = (height - textureWidth) / 2 + offset;
			this.drawTexturedModalRect(xStart, yStart, 0, 0, 128, 192);
			SpellBase tempSpell = newSpell;
			if (tempSpell != null)
			{
				// Render spell costs
				int i = 0;
				int j = 0;
				int x = xStart + 4;
				int y = yStart + 4;
				int xInterval = 42;
				int yInterval = 18;
				for (SpellCost cost : tempSpell.getCosts())
				{
					RenderHelper.enableGUIStandardItemLighting();
					ItemStack stack = new ItemStack(cost.getItem(), 1, cost.getMetadata());
					mc.getRenderItem().renderItemIntoGUI(stack, x + xInterval * j, yInterval * i + y);
					RenderHelper.disableStandardItemLighting();
					fontRenderer.drawString(" x " + cost.getCount(), x + 15 + xInterval * j,
							3 + yInterval * i + y, 0);
					i++;
					if (i > 9)
					{
						i = 0;
						j++;
					}
				}
				super.drawScreen(mouseX, mouseY, partialTicks);
			}
		}
	}

	private class SpellButton extends GuiButton
	{

		private final ResourceLocation buttonTexture = new ResourceLocation(ModReference.MOD_ID, "textures/gui/widgets.png");
		private final ResourceLocation spellTexture;
		private final SpellBase spell;

		public SpellButton(int buttonId, int x, int y, SpellBase spell)
		{
			super(buttonId, x, y, I18n.format(spell.getName()));
			this.spell = spell;
			width = 16;
			height = 16;
			spellTexture = spell.getGuiTexture();
		}

		public SpellBase getSpell()
		{
			return spell;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			if (visible)
			{
				mc.getTextureManager().bindTexture(buttonTexture);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
				int i = getHoverState(hovered);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
						GlStateManager.DestFactor.ZERO);
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
				this.drawTexturedModalRect(x, y, 0, i * 16, width / 2, height);
				this.drawTexturedModalRect(x + width / 2, y, 200 - width / 2, i * 16, width / 2, height);
				mouseDragged(mc, mouseX, mouseY);
				mc.getTextureManager().bindTexture(spellTexture);
				drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);

				if (packedFGColour != 0)
				{
				}
				else if (!enabled)
				{
				}
				else if (hovered)
				{
				}

				// this.drawCenteredString(fontrenderer,
				// this.displayString, this.x + this.width / 2, this.y +
				// (this.height - 8) / 2, j);
			}
		}

		@Override
		protected int getHoverState(boolean mouseOver)
		{
			if (mouseOver)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
}
