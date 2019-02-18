package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.client.renderer.entity.RenderExExParrot;
import com.laton95.runemysteries.entity.passive.EntityExExParrot;
import com.laton95.runemysteries.util.ModLog;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRenders {
	
	@SubscribeEvent
	public static void registerRenders(final FMLClientSetupEvent event) {
		ModLog.info("Registering renders");
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExExParrot.class, RenderExExParrot::new);
	}
}
