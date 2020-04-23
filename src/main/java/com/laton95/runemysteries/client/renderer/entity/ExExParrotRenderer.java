package com.laton95.runemysteries.client.renderer.entity;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.client.renderer.entity.model.ExExParrotModel;
import com.laton95.runemysteries.entity.passive.ExExParrotEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ExExParrotRenderer extends MobRenderer<ExExParrotEntity, ExExParrotModel> {
	
	public ExExParrotRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ExExParrotModel(), 0.3F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(ExExParrotEntity entity) {
		return new ResourceLocation(RuneMysteries.MOD_ID, "textures/entity/ex_ex_parrot/ex_ex_parrot.png");
	}
	
	@Override
	protected float handleRotationFloat(ExExParrotEntity livingBase, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
