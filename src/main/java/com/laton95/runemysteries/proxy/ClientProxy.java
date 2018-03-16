package com.laton95.runemysteries.proxy;

import com.laton95.runemysteries.client.handler.KeyInputEventHandler;
import com.laton95.runemysteries.client.renderer.entity.RenderProjectileSpell;
import com.laton95.runemysteries.client.settings.Keybindings;
import com.laton95.runemysteries.entity.projectile.*;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

	private ResourceLocation blueGreen = new ResourceLocation(ModReference.MOD_ID,
			"textures/entity/projectile/blue-green.png");
	private ResourceLocation pinkPurple = new ResourceLocation(ModReference.MOD_ID,
			"textures/entity/projectile/pink-purple.png");
	private ResourceLocation redOrange = new ResourceLocation(ModReference.MOD_ID,
			"textures/entity/projectile/red-orange.png");

	@Override
	public void registerProjectileRenders()
	{
		LogHelper.info("Registering projectile renders");
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellBouncing.class, renderManager -> new RenderProjectileSpell(renderManager, blueGreen));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellDamage.class, renderManager -> new RenderProjectileSpell(renderManager, redOrange));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellExplosive.class, renderManager -> new RenderProjectileSpell(renderManager, redOrange));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellFollowing.class, renderManager -> new RenderProjectileSpell(renderManager, pinkPurple));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellTeleportBasic.class, renderManager -> new RenderProjectileSpell(renderManager, pinkPurple));
	}
}
