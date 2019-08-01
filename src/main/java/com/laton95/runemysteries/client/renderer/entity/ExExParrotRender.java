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
	
	public float handleRotationFloat(ExExParrotEntity parrot, float partialTicks) {
		float flap = MathHelper.lerp(partialTicks, parrot.oFlap, parrot.flap);
		float flapSpeed = MathHelper.lerp(partialTicks, parrot.oFlapSpeed, parrot.flapSpeed);
		return (MathHelper.sin(flap) + 1.0F) * flapSpeed;
	}
}
