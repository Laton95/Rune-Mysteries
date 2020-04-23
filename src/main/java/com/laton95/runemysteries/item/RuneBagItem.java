package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.RuneBagInventory;
import com.laton95.runemysteries.network.RunemysteriesPacketHandler;
import com.laton95.runemysteries.network.SyncRuneBagPacket;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.util.ItemNBTHelper;
import com.laton95.runemysteries.util.PlayerInventoryHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.play.server.SCollectItemPacket;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID)
public class RuneBagItem extends Item {
	
	public static final String AUTO_PICKUP_NBT = "autoPickup";
	
	public RuneBagItem() {
		super(new Properties().maxStackSize(1).group(RuneMysteries.RUNE_GROUP));
	}
	
	@SubscribeEvent
	public static void onItemPickup(EntityItemPickupEvent event) {
		ItemStack originalStack = event.getItem().getItem();
		
		if(!originalStack.isEmpty() && originalStack.getItem() instanceof RuneItem) {
			ItemStack tempStack = originalStack;
			List<Integer> bagSlots = PlayerInventoryHelper.getSlotsWithItem(event.getPlayer(), ModItems.RUNE_BAG);
			for(int slot : bagSlots) {
				if(tempStack.getCount() == 0) {
					break;
				}
				ItemStack bagStack = event.getPlayer().inventory.getStackInSlot(slot);
				if(ItemNBTHelper.getBoolean(bagStack, AUTO_PICKUP_NBT, true)) {
					boolean bagChanged = false;
					RuneBagInventory bagInventory = getInventory(bagStack);
					
					for(int i = 0; i < bagInventory.getSlots(); i++) {
						if(tempStack.getCount() == 0) {
							break;
						}
						tempStack = bagInventory.insertItem(i, tempStack, false);
						bagChanged = true;
					}
					
					if(bagChanged) {
						RunemysteriesPacketHandler.sendTo(new SyncRuneBagPacket(bagInventory.getStacks(), slot), (ServerPlayerEntity) event.getPlayer());
					}
				}
			}
			
			/*
			 * Rest of method taken from Vaskii's Botania mod under the Botania licence
			 */
			int numPickedUp = originalStack.getCount() - tempStack.getCount();
			
			event.getItem().setItem(tempStack);
			
			if(numPickedUp > 0) {
				event.setCanceled(true);
				if(!event.getItem().isSilent()) {
					event.getItem().world.playSound(null, event.getPlayer().getPosX(), event.getPlayer().getPosY(), event.getPlayer().getPosZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((event.getItem().world.rand.nextFloat() - event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				}
				// Shows the pick up animation
				((ServerPlayerEntity) event.getPlayer()).connection.sendPacket(new SCollectItemPacket(event.getItem().getEntityId(), event.getPlayer().getEntityId(), numPickedUp));
				// Updates the screen if the player is looking into the bag
				event.getPlayer().openContainer.detectAndSendChanges();
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(!world.isRemote) {
			if(!player.isCrouching()) {
				RuneBagInventory bagInventory = getInventory(stack);
				NetworkHooks.openGui((ServerPlayerEntity) player, bagInventory, buffer -> {
					buffer.writeItemStack(stack);
					buffer.writeInt(hand == Hand.MAIN_HAND ? player.inventory.currentItem : -1);
				});
			}
			else {
				if(ItemNBTHelper.toggleBoolean(stack, AUTO_PICKUP_NBT, true)) {
					player.sendMessage(new TranslationTextComponent(StringReference.RuneBag.PICKUP_ON));
				}
				else {
					player.sendMessage(new TranslationTextComponent(StringReference.RuneBag.PICKUP_OFF));
				}
			}
		}
		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		TranslationTextComponent pickupTooltip = new TranslationTextComponent(ItemNBTHelper.getBoolean(stack, AUTO_PICKUP_NBT, true) ? StringReference.RuneBag.PICKUP_ON : StringReference.RuneBag.PICKUP_OFF);
		tooltip.add(pickupTooltip.applyTextStyle(TextFormatting.GRAY));
	}
	
	public static RuneBagInventory getInventory(ItemStack stack) {
		return (RuneBagInventory) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseThrow(() -> new RuntimeException("Rune bag inventory missing"));
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return new RuneBagInventoryProvider(stack);
	}
	
	private class RuneBagInventoryProvider implements ICapabilitySerializable<INBT> {
		
		private final LazyOptional<IItemHandler> itemStackHandler;
		
		public RuneBagInventoryProvider(ItemStack bagStack) {
			itemStackHandler = LazyOptional.of(() -> new RuneBagInventory(bagStack));
		}
		
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, itemStackHandler);
		}
		
		@Override
		public INBT serializeNBT() {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(itemStackHandler.orElseThrow(NullPointerException::new), null);
		}
		
		@Override
		public void deserializeNBT(INBT nbt) {
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(itemStackHandler.orElseThrow(NullPointerException::new), null, nbt);
		}
	}
}
