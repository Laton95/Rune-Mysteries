package com.laton95.runemysteries.client.renderer.sky;

import com.laton95.runemysteries.util.TextureRotationHelper;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.IRenderHandler;

public abstract class RenderSky extends IRenderHandler
{
	protected void draw(int size, int depth, int red, int green, int blue, int alpha, Tessellator tessellator, BufferBuilder bufferBuilder, TextureRotationHelper coords)
	{
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		bufferBuilder.pos(-size, -depth, -size).tex(coords.coordinates[0].u, coords.coordinates[0].v).color(red, green, blue, alpha).endVertex();
		bufferBuilder.pos(-size, -depth, size).tex(coords.coordinates[1].u, coords.coordinates[1].v).color(red, green, blue, alpha).endVertex();
		bufferBuilder.pos(size, -depth, size).tex(coords.coordinates[2].u, coords.coordinates[2].v).color(red, green, blue, alpha).endVertex();
		bufferBuilder.pos(size, -depth, -size).tex(coords.coordinates[3].u, coords.coordinates[3].v).color(red, green, blue, alpha).endVertex();
		tessellator.draw();
		GlStateManager.popMatrix();
		
		GlStateManager.depthMask(true);
		GlStateManager.enableTexture2D();
		GlStateManager.enableAlpha();
	}
	
}
