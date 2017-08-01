package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.client.handler.KeyInputEventHandler;
import com.laton95.runemysteries.client.settings.Keybindings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
		for (Keybindings key : Keybindings.values()) {
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

}
