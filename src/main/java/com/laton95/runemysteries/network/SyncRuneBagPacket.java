package com.laton95.runemysteries.network;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.RuneBagInventory;
import com.laton95.runemysteries.item.RuneBagItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncRuneBagPacket {
	
	private final NonNullList<ItemStack> stacks;
	
	private final int inventorySlot;
	
	public SyncRuneBagPacket(NonNullList<ItemStack> stacks, int inventorySlot) {
		this.stacks = stacks;
		this.inventorySlot = inventorySlot;
	}
	
	public static void encode(SyncRuneBagPacket message, PacketBuffer buffer) {
		buffer.writeInt(message.inventorySlot);
		buffer.writeInt(message.stacks.size());
		
		for(ItemStack stack : message.stacks) {
			buffer.writeItemStack(stack);
		}
	}
	
	public static SyncRuneBagPacket decode(PacketBuffer buffer) {
		int inventorySlot = buffer.readInt();
		
		int size = buffer.readInt();
		NonNullList<ItemStack> stacks = NonNullList.withSize(size, ItemStack.EMPTY);
		
		for(int item = 0; item < size; item++) {
			ItemStack itemStack = buffer.readItemStack();
			
			stacks.set(item, itemStack);
		}
		
		return new SyncRuneBagPacket(stacks, inventorySlot);
	}
	
	public static class Handler {
		
		public static void handle(final SyncRuneBagPacket message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				PlayerEntity player = RuneMysteries.proxy.getClientPlayer();
				
				if(player != null) {
					ItemStack stack = player.inventory.getStackInSlot(message.inventorySlot);
					
					if(stack.getItem() == ModItems.RUNE_BAG) {
						RuneBagInventory bagInventory = RuneBagItem.getInventory(stack);
						bagInventory.setStacks(message.stacks);
					}
				}
			});
			
			context.get().setPacketHandled(true);
		}
	}
}
