package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.potion.ModPotion;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {
	
	public static final ModPotion STONETOUCHER = new ModPotion("stonetoucher", false, true, 0, new ResourceLocation(RuneMysteries.MOD_ID, "textures/gui/potion/stonetoucher.png"));
	
	@SubscribeEvent
	public static void RegisterPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(STONETOUCHER);
	}
}
