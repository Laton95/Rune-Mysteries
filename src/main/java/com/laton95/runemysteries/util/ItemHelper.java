package com.laton95.runemysteries.util;

import java.util.Random;

import com.laton95.runemysteries.item.ItemRune;
import com.laton95.runemysteries.item.ItemRuneBag;
import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber
public class ItemHelper
{

	private static Random random = new Random();

	public static ItemStack getRuneBag(EntityPlayer player)
	{
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			if (player.inventory.getStackInSlot(i).getItem() instanceof ItemRuneBag)
			{
				return player.inventory.getStackInSlot(i);
			}
		}
		return null;
	}

	@SubscribeEvent
	public static void pickupHandler(EntityItemPickupEvent event)
	{
		ItemStack stack = event.getItem().getItem();
		if (stack.getItem() instanceof ItemRune && stack.getCount() > 0)
		{
			ItemStack bag = getRuneBag(event.getEntityPlayer());
			if (bag != null && ItemNBTHelper.getBoolean(bag, "autoPickup", true))
			{
				IItemHandler bagInventory = bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						null);
				ItemStack result = null;
				for (int i = 0; i < bagInventory.getSlots(); i++)
				{
					if (result == null)
					{
						result = bagInventory.insertItem(i, stack, false);
					}
					else
					{
						result = bagInventory.insertItem(i, result, false);
					}
				}

				/*
				 * Rest of method taken from Vaskii's Botania mod under the
				 * Botania licence
				 */
				int numPickedUp = stack.getCount() - result.getCount();

				event.getItem().setItem(result);

				if (numPickedUp > 0)
				{
					event.setCanceled(true);
					if (!event.getItem().isSilent())
					{
						event.getItem().world.playSound(null, event.getEntityPlayer().posX,
								event.getEntityPlayer().posY, event.getEntityPlayer().posZ,
								SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F,
								((event.getItem().world.rand.nextFloat()
										- event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F)
										* 2.0F);
					}
					((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(
							new SPacketCollectItem(event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), numPickedUp));
					event.getEntityPlayer().openContainer.detectAndSendChanges();

					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void tooltipHandler(ItemTooltipEvent event)
	{
		ItemStack stack = event.getItemStack();
		// Rune bag tooltip
		if (stack.getItem() instanceof ItemRuneBag)
		{
			if (ItemNBTHelper.getBoolean(stack, "autoPickup", true))
			{
				TextComponentTranslation string = new TextComponentTranslation(NamesReference.RuneBag.AUTO_ON);
				event.getToolTip().add(string.getFormattedText());
			}
			else
			{
				TextComponentTranslation string = new TextComponentTranslation(NamesReference.RuneBag.AUTO_OFF);
				event.getToolTip().add(string.getFormattedText());
			}
		}
		else if (stack.getItem() instanceof ItemSpellbook)
		{
			// Spellbook tooltip
			TextComponentTranslation string = new TextComponentTranslation(ItemNBTHelper.getSpell(stack).getName());
			event.getToolTip().add("Spell: " + string.getFormattedText());
		}
	}
}
