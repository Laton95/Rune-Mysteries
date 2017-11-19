package com.laton95.runemysteries.util;

import java.util.ArrayList;

import com.laton95.runemysteries.inventory.InventoryRuneBag;
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
	
	public static ArrayList<ItemStack> getRuneBags(EntityPlayer player)
	{
		ArrayList<ItemStack> bags = new ArrayList<>();
		
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
		{
			if (player.inventory.getStackInSlot(i).getItem() instanceof ItemRuneBag)
			{
				bags.add(player.inventory.getStackInSlot(i));
			}
		}
		
		return bags;
	}
	
	public static ArrayList<InventoryRuneBag> getBagInventories(EntityPlayer player)
	{
		ArrayList<ItemStack> bags = getRuneBags(player);
		ArrayList<InventoryRuneBag> bagInventories = new ArrayList<>();
		
		for (ItemStack bag : bags)
		{
			bagInventories.add(new InventoryRuneBag(bag));
		}
		
		return bagInventories;
	}

	@SubscribeEvent
	public static void pickupHandler(EntityItemPickupEvent event)
	{
		ItemStack originalStack = event.getItem().getItem();
		
		if (originalStack.getItem() instanceof ItemRune && originalStack.getCount() > 0 && originalStack.getItem().getMetadata(originalStack) != 14)
		{
			ItemStack tempStack = originalStack;
			ArrayList<ItemStack> bags = getRuneBags(event.getEntityPlayer());
			for (ItemStack bag : bags)
			{
				if (ItemNBTHelper.getBoolean(bag, "autoPickup", true))
				{
					IItemHandler bagInventory = bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
							null);
					
					for (int i = 0; i < bagInventory.getSlots(); i++)
					{
						{
							tempStack = bagInventory.insertItem(i, tempStack, false);
						}
					}
				}
			}
			
			/*
			 * Rest of method taken from Vaskii's Botania mod under the
			 * Botania licence
			 */
			int numPickedUp = originalStack.getCount() - tempStack.getCount();

			event.getItem().setItem(tempStack);

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
				//Shows the pick up animation
				((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(
						new SPacketCollectItem(event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), numPickedUp));
				//Updates the screen if the player is looking into the bag
				event.getEntityPlayer().openContainer.detectAndSendChanges();
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
