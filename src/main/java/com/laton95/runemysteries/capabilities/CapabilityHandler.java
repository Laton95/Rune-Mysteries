package com.laton95.runemysteries.capabilities;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CapabilityHandler
{
	public static final ResourceLocation LAST_LOCATION_CAPABILITY = new ResourceLocation(ModReference.MOD_ID, "player_last_location");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if(event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(LAST_LOCATION_CAPABILITY, new ProviderPlayerLastLocation());
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ICapabilityPlayerLastLocation location = player.getCapability(ProviderPlayerLastLocation.LAST_LOCATION_CAPABILITY, null);
		ICapabilityPlayerLastLocation oldLocation = event.getOriginal().getCapability(ProviderPlayerLastLocation.LAST_LOCATION_CAPABILITY, null);
		
		location.set(oldLocation);
	}
}
