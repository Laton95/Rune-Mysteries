package com.laton95.runemysteries.client.renderer.entity;

import com.laton95.runemysteries.client.model.ModelExExParrot;
import com.laton95.runemysteries.entity.passive.EntityExExParrot;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class RenderExExParrot extends RenderLiving<EntityExExParrot>
{
	public RenderExExParrot(RenderManager rendermanagerIn)
	{
		super(rendermanagerIn, new ModelExExParrot(), 0.3f);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityExExParrot entity)
	{
		return new ResourceLocation("runemysteries:textures/entity/ex_ex_parrot/ex_ex_parrot.png");
	}
	
	@Override
	protected float handleRotationFloat(EntityExExParrot livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.flap - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.flapSpeed - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
