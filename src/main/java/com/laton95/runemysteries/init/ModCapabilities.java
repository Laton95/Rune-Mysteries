package com.laton95.runemysteries.init;

import com.laton95.runemysteries.capabilities.CapabilityPlayerLastLocation;
import com.laton95.runemysteries.capabilities.ICapabilityPlayerLastLocation;
import com.laton95.runemysteries.capabilities.StoragePlayerLastLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities
{
	public static void RegisterCapabilities()
	{
		CapabilityManager.INSTANCE.register(ICapabilityPlayerLastLocation.class, new StoragePlayerLastLocation(), CapabilityPlayerLastLocation ::new);
	}
}
