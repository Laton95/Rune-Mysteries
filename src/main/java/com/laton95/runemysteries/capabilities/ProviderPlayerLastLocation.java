package com.laton95.runemysteries.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderPlayerLastLocation implements ICapabilitySerializable<NBTBase>
{
	@CapabilityInject(ICapabilityPlayerLastLocation.class)
	public static final Capability<ICapabilityPlayerLastLocation> LAST_LOCATION_CAPABILITY = null;
	
	private ICapabilityPlayerLastLocation instance = LAST_LOCATION_CAPABILITY.getDefaultInstance();
	
	@Override
	public boolean hasCapability(
			@Nonnull
					Capability<?> capability,
			@Nullable
					EnumFacing facing)
	{
		return capability == LAST_LOCATION_CAPABILITY;
	}
	
	@Nullable
	@Override
	public <T> T getCapability(
			@Nonnull
					Capability<T> capability,
			@Nullable
					EnumFacing facing)
	{
		return capability == LAST_LOCATION_CAPABILITY ? LAST_LOCATION_CAPABILITY.cast(instance) : null;
	}
	
	@Override
	public NBTBase serializeNBT()
	{
		return LAST_LOCATION_CAPABILITY.getStorage().writeNBT(LAST_LOCATION_CAPABILITY, instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		LAST_LOCATION_CAPABILITY.getStorage().readNBT(LAST_LOCATION_CAPABILITY, instance, null, nbt);
	}
}
