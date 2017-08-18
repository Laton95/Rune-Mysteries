package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.client.handler.KeyInputEventHandler;
import com.laton95.runemysteries.client.render.EntityRenderSpellProjectile;
import com.laton95.runemysteries.client.settings.Keybindings;
import com.laton95.runemysteries.entity.projectiles.SpellProjectileBase;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{

	@Override
	public void registerKeyBindings()
	{
		LogHelper.info("Registering keybinds");
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
		for (Keybindings key : Keybindings.values())
		{
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

	@Override
	public void registerProjectileRenders()
	{
		LogHelper.info("Registering projectile renders");
		RenderingRegistry.registerEntityRenderingHandler(SpellProjectileBase.class,
				new IRenderFactory<SpellProjectileBase>()
				{

					@Override
					public Render createRenderFor(RenderManager manager)
					{
						return new EntityRenderSpellProjectile(manager);
					}
				});
	}
}
