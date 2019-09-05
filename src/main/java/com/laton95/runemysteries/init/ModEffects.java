package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.potion.ModEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {
	
	public static final ModEffect STONETOUCHER = new ModEffect(EffectType.BENEFICIAL, 8171462);
	
	
	@SubscribeEvent
	public static void RegisterPotions(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(STONETOUCHER.setRegistryName(RuneMysteries.MOD_ID, "stonetoucher"));
	}
}
