package com.laton95.runemysteries.capability;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityRejected {
	
	@CapabilityInject(Rejected.class)
	public static Capability<Rejected> REJECTED = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(Rejected.class, new Capability.IStorage<Rejected>() {
			@Nullable
			@Override
			public INBTBase writeNBT(Capability<Rejected> capability, Rejected instance, EnumFacing side) {
				return new NBTTagInt(instance.isRejected() ? 1 : 0);
			}
			
			@Override
			public void readNBT(Capability<Rejected> capability, Rejected instance, EnumFacing side, INBTBase nbt) {
				if(((NBTTagInt) nbt).getInt() == 1) {
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
	
	public static Rejected get(EntityItem item) {
		return item.getCapability(CapabilityRejected.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
	}
	
	static class EventHandlers {
		
		@SubscribeEvent
		public void attach(AttachCapabilitiesEvent<Entity> event) {
			if(event.getObject() instanceof EntityItem) {
				event.addCapability(new ResourceLocation(RuneMysteries.MOD_ID, ""), new ICapabilityProvider() {
					final Rejected rejected = new Rejected();
					
					final LazyOptional<Rejected> rejectedInstance = LazyOptional.of(() -> rejected);
					
					@Nonnull
					@Override
					public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side) {
						return REJECTED.orEmpty(cap, rejectedInstance);
					}
				});
			}
		}
	}
}
