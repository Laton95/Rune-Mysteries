package com.laton95.runemysteries.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderFriendlyZombie extends RenderLiving
{
	public RenderFriendlyZombie(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelZombie(), 0.5f);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return new ResourceLocation("minecraft:textures/entity/zombie/zombie.png");
	}
}
