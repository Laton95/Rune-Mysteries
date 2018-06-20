package com.laton95.runemysteries.init;

import com.laton95.runemysteries.potion.PotionStonetoucher;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;

@Mod.EventBusSubscriber
public class ModPotions
{
	public static final PotionStonetoucher STONETOUCHER = new PotionStonetoucher();
	
	@SubscribeEvent
	public static void RegisterPotions(RegistryEvent.Register<Potion> event)
	{
		event.getRegistry().registerAll(STONETOUCHER);
	}
}
