package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.client.handler.KeyInputEventHandler;
import com.laton95.runemysteries.client.render.EntityRenderSpellProjectile;
import com.laton95.runemysteries.client.settings.Keybindings;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.spells.projectiles.DamageProjectile;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindings() {
		LogHelper.info("Registering keybinds");
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
		for (Keybindings key : Keybindings.values()) {
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

	@Override
	public void registerProjectileRenders() {
		LogHelper.info("Registering projectile renders");
		RenderingRegistry.registerEntityRenderingHandler(DamageProjectile.class, new IRenderFactory<DamageProjectile>() {

			@Override
			public Render createRenderFor(RenderManager manager) {
				return new EntityRenderSpellProjectile(manager, new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/test_animated.png"));
			}
		});
	}
}
