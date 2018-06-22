package com.laton95.runemysteries.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StoragePlayerLastLocation implements Capability.IStorage<ICapabilityPlayerLastLocation>
{
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<ICapabilityPlayerLastLocation> capability, ICapabilityPlayerLastLocation instance, EnumFacing side)
	{
		BlockPos pos = instance.getPosition();
		int dimId = instance.getDimId();
		if(pos != null)
		{
			return new NBTTagIntArray(new int[] {pos.getX(), pos.getY(), pos.getZ(), dimId});
		}
		else
		{
			return new NBTTagIntArray(new int[] {0, 100, 0, 0});
		}
	}
	
	@Override
	public void readNBT(Capability<ICapabilityPlayerLastLocation> capability, ICapabilityPlayerLastLocation instance, EnumFacing side, NBTBase nbt)
	{
		try
		{
			int x = ((NBTTagIntArray) nbt).getIntArray()[0];
			int y = ((NBTTagIntArray) nbt).getIntArray()[1];
			int z = ((NBTTagIntArray) nbt).getIntArray()[2];
			int dimId = ((NBTTagIntArray) nbt).getIntArray()[3];
			
			instance.set(x, y, z, dimId);
		}
		catch(Exception e)
		{
			instance.set(0, 100, 0, 0);
		}
	}
}
