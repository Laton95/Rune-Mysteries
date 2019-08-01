package com.laton95.runemysteries.client.renderer.entity;

import com.laton95.runemysteries.client.renderer.entity.model.ExExParrotModel;
import com.laton95.runemysteries.entity.passive.ExExParrotEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ExExParrotRender extends LivingRenderer<ExExParrotEntity, ExExParrotModel> {
	
	public ExExParrotRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ExExParrotModel(), 0.3F);
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(ExExParrotEntity entityExExParrot) {
		return new ResourceLocation("runemysteries:textures/entity/ex_ex_parrot/ex_ex_parrot.png");
	}
	
	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	public float handleRotationFloat(ExExParrotEntity livingBase, float partialTicks) {
		return this.getCustomBob(livingBase, partialTicks);
	}
	
	private float getCustomBob(ExExParrotEntity parrot, float p_192861_2_) {
		float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
		float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
