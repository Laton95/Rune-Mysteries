package com.laton95.runemysteries.capability;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RejectedCapability {
	
	@CapabilityInject(Rejected.class)
	public static Capability<Rejected> REJECTED = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(Rejected.class, new Capability.IStorage<Rejected>() {
			@Override
			public INBT writeNBT(Capability<Rejected> capability, Rejected instance, Direction direction) {
				return IntNBT.valueOf(instance.isRejected() ? 1 : 0);
			}
			
			@Override
			public void readNBT(Capability<Rejected> capability, Rejected instance, Direction direction, INBT nbt) {
				if(((IntNBT) nbt).getInt() == 1) {
					instance.setRejected();
				}
			}
		}, () -> null);
		
		MinecraftForge.EVENT_BUS.register(new EventHandlers());
	}
	
	public static class Rejected {
		
		private boolean rejected = false;
		
		public void setRejected() {
			rejected = true;
		}
		
		public boolean isRejected() {
			return rejected;
		}
	}
	
	public static Rejected get(ItemEntity item) {
		return item.getCapability(RejectedCapability.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
	}
	
	static class EventHandlers {
		
		@SubscribeEvent
		public void attach(AttachCapabilitiesEvent<Entity> event) {
			if(event.getObject() instanceof ItemEntity) {
				event.addCapability(new ResourceLocation(RuneMysteries.MOD_ID, ""), new ICapabilityProvider() {
					final Rejected rejected = new Rejected();
					
					final LazyOptional<Rejected> rejectedInstance = LazyOptional.of(() -> rejected);
					
					@Override
					public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
						return REJECTED.orEmpty(cap, rejectedInstance);
					}
				});
			}
		}
	}
}
