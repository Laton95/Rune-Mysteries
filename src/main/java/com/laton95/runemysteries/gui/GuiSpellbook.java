package com.laton95.runemysteries.gui;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import com.laton95.runemysteries.inventory.ContainerRuneBag;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.network.MessageExplode;
import com.laton95.runemysteries.network.MessageSpellSelect;
import com.laton95.runemysteries.network.NetworkHandler;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.Spell.SpellCost;
import com.laton95.runemysteries.spells.Spells.EnumSpell;
import com.laton95.runemysteries.util.LogHelper;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class GuiSpellbook extends RMModGuiScreen {
    private final EntityPlayer player;
    private final ItemStack spellbook;
    private EnumSpell newSpell;
    private EnumSpell oldSpell;
    private GuiButton buttonDone;
    private GuiButton buttonCancel;
    private GuiButton buttonEnderpearl;
    private GuiButton buttonSnowball;
    private GuiButton buttonExplosion;
    private GuiButton buttonDeath;
    
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
        	oldSpell = ItemSpellbook.getCurrentSpell(spellbook);
        } else {
        	oldSpell = EnumSpell.NONE;
		}
        newSpell = oldSpell;
    }
	
	public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        int standardWidth = (this.width) / 2 - 49;
        int standardHeight = (this.height + textureHeight)/2 + borderWidth + offset;
        this.buttonDone = this.addButton(new GuiButton(0, standardWidth - 50, standardHeight, 98, 20, I18n.format("gui.done")));
        this.buttonCancel = this.addButton(new GuiButton(1, standardWidth + 50, standardHeight, 98, 20, I18n.format("gui.cancel")));
        this.buttonEnderpearl = this.addButton(new GuiButton(2, standardWidth - 64, standardHeight - 180, 98, 20, I18n.format(NamesReference.Spells.ENDERPEARL_SPELL_NAME)));
        this.buttonSnowball = this.addButton(new GuiButton(3, standardWidth - 64, standardHeight - 160, 98, 20, I18n.format(NamesReference.Spells.SNOWBALL_SPELL_NAME)));
        this.buttonExplosion = this.addButton(new GuiButton(4, standardWidth - 64, standardHeight - 140, 98, 20, I18n.format(NamesReference.Spells.EXPLOSION_SPELL_NAME)));
        this.buttonDeath = this.addButton(new GuiButton(5, standardWidth - 64, standardHeight - 120, 98, 20, I18n.format(NamesReference.Spells.DEATH_SPELL_NAME)));
    }
	
	public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }
	
	protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
        	switch (button.id) {
			case 0:
				sendBookToServer();
				this.mc.displayGuiScreen((GuiScreen)null);
				break;
			case 1:
				this.mc.displayGuiScreen((GuiScreen)null);		
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
			}
        }
    }
	
	private void sendBookToServer() throws IOException
    {
        if (oldSpell != newSpell)
        {
            ItemSpellbook.setCurrentSpell(spellbook, newSpell);
            NetworkHandler.sendToServer(new MessageSpellSelect(newSpell));
        }
    }
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
		
        this.mc.getTextureManager().bindTexture(guiTexture);
        
        int xStart = (this.width - textureHeight) / 2;
        int yStart = (this.height - textureWidth)/2 + offset;
        this.drawTexturedModalRect(xStart+1, yStart, 0, 0, 128, 192);
        this.drawTexturedModalRect(xStart+128, yStart, 128, 0, 128, 192);
        
        TextComponentTranslation temp1;
        TextComponentTranslation temp2;
        Spell tempSpell = Spells.getSpellFromEnum(newSpell);
        if (tempSpell != null) {
        	temp1 = new TextComponentTranslation(tempSpell.getName());
        	temp2 = new TextComponentTranslation(tempSpell.getDescription());
        	
        	if (tempSpell.getCosts().size() <= 6) {
        		//Render spell costs
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
                    drawModalRectWithCustomSizedTexture(x + xInterval*j, yInterval*i + y, 16, 16, 16, 16, 16, 16);
                    fontRenderer.drawString(" x " + cost.getCount(), x + 15 + xInterval*j, 3 + yInterval*i + y, 0);
                    i++;
                    if (i > 1) {
                    	i = 0;
    					j++;
    				}
    			}
			} else {
				//Handle more than 6 costs
			}
		} else {
			temp1 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_NAME);
			temp2 = new TextComponentTranslation(NamesReference.Spells.NO_SPELL_DESCRIPTION);
		}
        
        
        
        fontRenderer.drawString(TextFormatting.BLACK + temp1.getFormattedText() + TextFormatting.UNDERLINE, xStart + 192 - fontRenderer.getStringWidth(temp1.getFormattedText())/2, yStart + 30, 0, false);
		fontRenderer.drawSplitString(TextFormatting.BLACK + temp2.getFormattedText(), xStart + 140, yStart + 60, 107, 0);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
