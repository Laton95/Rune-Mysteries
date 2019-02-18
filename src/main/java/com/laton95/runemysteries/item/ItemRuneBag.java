package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.InventoryRuneBag;
import com.laton95.runemysteries.network.PacketSyncRuneBag;
import com.laton95.runemysteries.network.RunemysteriesPacketHandler;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.util.PlayerInventoryHelper;
import com.laton95.runemysteries.util.ItemNBTHelper;
import com.laton95.runemysteries.util.ModLog;
import io.netty.buffer.Unpooled;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
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
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID)
public class ItemRuneBag extends ModItem {
	
	public static final String AUTO_PICKUP_NBT = "autoPickup";
	
	public ItemRuneBag() {
		super("rune_bag", new Properties().maxStackSize(1));
	}
	
	@SubscribeEvent
	public static void onItemPickup(EntityItemPickupEvent event) {
		ItemStack originalStack = event.getItem().getItem();
		
		if(!originalStack.isEmpty() && originalStack.getItem() instanceof ItemRune) {
			ItemStack tempStack = originalStack;
			List<Integer> bagSlots = PlayerInventoryHelper.getSlotsWithItem(event.getEntityPlayer(), ModItems.RUNE_BAG);
			for(int slot : bagSlots) {
				if(tempStack.getCount() == 0) {
					break;
				}
				ItemStack bagStack = event.getEntityPlayer().inventory.getStackInSlot(slot);
				if(ItemNBTHelper.getBoolean(bagStack, AUTO_PICKUP_NBT, true)) {
					boolean bagChanged = false;
					InventoryRuneBag bagInventory = getInventory(bagStack);

					for(int i = 0; i < bagInventory.getSlots(); i++) {
						if(tempStack.getCount() == 0) {
							break;
						}
						tempStack = bagInventory.insertItem(i, tempStack, false);
						bagChanged = true;
					}
					
					if(bagChanged) {
						RunemysteriesPacketHandler.sendTo(new PacketSyncRuneBag(bagInventory.getStacks(), slot), (EntityPlayerMP) event.getEntityPlayer());
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
					event.getItem().world.playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((event.getItem().world.rand.nextFloat() - event.getItem().world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				}
				// Shows the pick up animation
				((EntityPlayerMP) event.getEntityPlayer()).connection.sendPacket(new SPacketCollectItem(event.getItem().getEntityId(), event.getEntityPlayer().getEntityId(), numPickedUp));
				// Updates the screen if the player is looking into the bag
				event.getEntityPlayer().openContainer.detectAndSendChanges();
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote) {
			if(!playerIn.isSneaking()) {
				InventoryRuneBag bagInventory = getInventory(stack);
				PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
				buffer.writeItemStack(stack);
				buffer.writeInt(handIn == EnumHand.MAIN_HAND ? playerIn.inventory.currentItem : -1);
				NetworkHooks.openGui((EntityPlayerMP) playerIn, bagInventory, buffer);
			}
			else {
				if(ItemNBTHelper.toggleBoolean(stack, AUTO_PICKUP_NBT, true)) {
					playerIn.sendMessage(new TextComponentTranslation(StringReference.RuneBag.PICKUP_ON));
				}
				else {
					playerIn.sendMessage(new TextComponentTranslation(StringReference.RuneBag.PICKUP_OFF));
				}
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		TextComponentTranslation pickupTooltip = new TextComponentTranslation(ItemNBTHelper.getBoolean(stack, AUTO_PICKUP_NBT, true) ? StringReference.RuneBag.PICKUP_ON : StringReference.RuneBag.PICKUP_OFF);
		tooltip.add(pickupTooltip.applyTextStyle(TextFormatting.GRAY));
	}
	
	public static InventoryRuneBag getInventory(ItemStack stack) {
		return (InventoryRuneBag) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseThrow(() -> new RuntimeException("Rune bag inventory missing"));
	}
	
	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new RuneBagInventoryProvider(stack);
	}
	
	private class RuneBagInventoryProvider implements ICapabilitySerializable<INBTBase> {
		
		private LazyOptional<IItemHandler> itemStackHandler;
		
		public RuneBagInventoryProvider(ItemStack bagStack) {
			itemStackHandler = LazyOptional.of(() -> new InventoryRuneBag(bagStack));
		}
		
		@Nonnull
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, itemStackHandler);
		}
		
		@Override
		public INBTBase serializeNBT() {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(itemStackHandler.orElseThrow(NullPointerException::new), null);
		}
		
		@Override
		public void deserializeNBT(INBTBase nbt) {
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(itemStackHandler.orElseThrow(NullPointerException::new), null, nbt);
		}
	}
}
