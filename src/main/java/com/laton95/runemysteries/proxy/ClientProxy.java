package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.client.handler.KeyInputEventHandler;
import com.laton95.runemysteries.client.settings.Keybindings;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		LogHelper.info("Registering keybinds");
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
		for (Keybindings key : Keybindings.values()) {
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}
}
