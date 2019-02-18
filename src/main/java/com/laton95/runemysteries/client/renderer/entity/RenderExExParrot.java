package com.laton95.runemysteries.client.renderer.entity;

import com.laton95.runemysteries.client.renderer.entity.model.ModelExExParrot;
import com.laton95.runemysteries.entity.passive.EntityExExParrot;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderExExParrot extends RenderLiving<EntityExExParrot> {
	
	public RenderExExParrot(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelExExParrot(), 0.3F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityExExParrot entity) {
		return new ResourceLocation("runemysteries:textures/entity/ex_ex_parrot/ex_ex_parrot.png");
	}
	
	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	public float handleRotationFloat(EntityExExParrot livingBase, float partialTicks) {
		return this.getCustomBob(livingBase, partialTicks);
	}
	
	private float getCustomBob(EntityExExParrot parrot, float p_192861_2_) {
		float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
		float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
