package com.laton95.runemysteries.client.render;

import com.laton95.runemysteries.entity.projectiles.SpellProjectileBase;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class EntityRenderSpellProjectile extends Render<SpellProjectileBase>
{
	public EntityRenderSpellProjectile(RenderManager renderManager)
	{
		super(renderManager);
	}

	@Override
	public void doRender(SpellProjectileBase entity, double x, double y, double z, float entityYaw,
			float partialTicks) {
		bindEntityTexture(entity);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks
				- 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(entity.prevRotationPitch
				+ (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GlStateManager.enableRescaleNormal();

		GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(0.05625F, 0.05625F, 0.05625F);

		int state = 0;
		int animationDelay = 5;
		if (entity.ticksExisted % animationDelay > animationDelay / 2)
		{
			state = 1;
		}
		double size = 1.8D;

		int k = 1;
		GlStateManager.translate(size, 0, 0);
		for (int j = 0; j < 2; j++)
		{
			GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);
			bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
			// Left
			bufferbuilder.pos(-size, k * -size, -size).tex(0.0D, 0.5D * state).endVertex();
			// Bottom
			bufferbuilder.pos(-size, k * -size, size).tex(1.0D, 0.5D * state).endVertex();
			// Right
			bufferbuilder.pos(-size, k * size, size).tex(1.0D, 0.5D + 0.5D * state).endVertex();
			// Top
			bufferbuilder.pos(-size, k * size, -size).tex(0.0D, 0.5D + 0.5D * state).endVertex();
			tessellator.draw();
			k = -1;
		}
		GlStateManager.translate(-size, 0, 0);

		for (int j = 0; j < 4; ++j)
		{
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.glNormal3f(0.0F, 0.0F, 0.05625F);
			bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
			bufferbuilder.pos(-size, -size, 0.0D).tex(0.0D, 0.5D * state).endVertex();
			bufferbuilder.pos(size, -size, 0.0D).tex(1.0D, 0.5D * state).endVertex();
			bufferbuilder.pos(size, size, 0.0D).tex(1.0D, 0.5D + 0.5D * state).endVertex();
			bufferbuilder.pos(-size, size, 0.0D).tex(0.0D, 0.5D + 0.5D * state).endVertex();
			tessellator.draw();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(SpellProjectileBase entity) {
		return entity.getTexture();
	}

}
