package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.client.renderer.entity.ExExParrotRenderer;
import com.laton95.runemysteries.client.renderer.tileentity.GravestoneTileEntityRenderer;
import com.laton95.runemysteries.util.ModLog;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

//import com.laton95.runemysteries.client.renderer.entity.ExExParrotRender;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRenders {
	
	@SubscribeEvent
	public static void registerRenders(final FMLClientSetupEvent event) {
		ModLog.info("Registering renders");
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.EX_EX_PARROT, ExExParrotRenderer::new);
		
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.GRAVESTONE, GravestoneTileEntityRenderer::new);
	}
}
