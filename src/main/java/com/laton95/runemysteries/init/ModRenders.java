package com.laton95.runemysteries.init;

import com.laton95.runemysteries.client.renderer.entity.RenderExExParrot;
import com.laton95.runemysteries.client.renderer.entity.RenderFriendlyZombie;
import com.laton95.runemysteries.client.renderer.entity.RenderProjectileSpell;
import com.laton95.runemysteries.entity.passive.EntityExExParrot;
import com.laton95.runemysteries.entity.passive.EntityFriendlyZombie;
import com.laton95.runemysteries.entity.projectile.*;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModRenders
{
	private static ResourceLocation blueGreen = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/blue-green.png");
	
	private static ResourceLocation pinkPurple = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/pink-purple.png");
	
	private static ResourceLocation redOrange = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/red-orange.png");
	
	public static void registerRenders()
	{
		LogHelper.info("Registering entity rendering");
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellBouncing.class, renderManager -> new RenderProjectileSpell(renderManager, blueGreen));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellDamage.class, renderManager -> new RenderProjectileSpell(renderManager, redOrange));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellExplosive.class, renderManager -> new RenderProjectileSpell(renderManager, redOrange));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellFollowing.class, renderManager -> new RenderProjectileSpell(renderManager, pinkPurple));
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileSpellTeleportBasic.class, renderManager -> new RenderProjectileSpell(renderManager, pinkPurple));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityExExParrot.class, renderManager -> new RenderExExParrot(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, renderManager -> new RenderFriendlyZombie(renderManager));
	}
}
