package com.laton95.runemysteries.util;

import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemRuneBag;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.spells.Spells.EnumSpell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemHelper {
	public static ItemStack getRuneBag(EntityPlayer player) {
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
			if (player.inventory.getStackInSlot(i).getItem() instanceof ItemRuneBag) {
				return player.inventory.getStackInSlot(i);
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public static void pickupHandler(EntityItemPickupEvent event) {
		if (event.getItem().getItem().getItem() instanceof ItemRune) {
			ItemStack bag = getRuneBag(event.getEntityPlayer());
			if (!bag.hasTagCompound()) {
				bag.setTagCompound(new NBTTagCompound());
			}
			if (!bag.getTagCompound().hasKey("autoPickup")) {
				bag.getTagCompound().setBoolean("autoPickup", true);
			}
			if (bag.getTagCompound().getBoolean("autoPickup")) {
				InventoryRuneBag bagInventory = new InventoryRuneBag(bag);
				event.getItem().getItem().setCount(bagInventory.mergeStack(event.getItem().getItem()));
			}
		}
	}
	
	@SubscribeEvent
	public static void tooltipHandler(ItemTooltipEvent event) {
		if (event.getItemStack().getItem() instanceof ItemRuneBag) {
			if (event.getItemStack().hasTagCompound()) {
				if (event.getItemStack().getTagCompound().getBoolean("autoPickup")) {
					TextComponentTranslation string = new TextComponentTranslation(NamesReference.RuneBag.AUTO_ON);
					event.getToolTip().add(string.getFormattedText());
				} else {
					TextComponentTranslation string = new TextComponentTranslation(NamesReference.RuneBag.AUTO_OFF);
					event.getToolTip().add(string.getFormattedText());
				}
			} else {
				TextComponentTranslation string = new TextComponentTranslation(NamesReference.RuneBag.AUTO_ON);
				event.getToolTip().add(string.getFormattedText());
			}
		} else if (event.getItemStack().getItem() instanceof ItemSpellbook) {
			TextComponentTranslation string;
			if (event.getItemStack().hasTagCompound()) {
				EnumSpell spell = ItemSpellbook.getCurrentSpell(event.getItemStack());
				if (spell != null) {
					string = new TextComponentTranslation(Spells.getSpellFromEnum(spell).getName());
				} else {
					string = new TextComponentTranslation(NamesReference.Spells.NO_SPELL);
				}
				
			} else {
				string = new TextComponentTranslation(NamesReference.Spells.NO_SPELL);
			}
			event.getToolTip().add("Spell: " + string.getFormattedText());
		}
	}
}
