package com.laton95.runemysteries.client.renderer.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

import java.util.Random;

public class RenderSkyCosmic extends RenderSky
{
	private final boolean vboEnabled;
	
	private final VertexFormat vertexBufferFormat;
	
	private VertexBuffer starVBO;
	
	private int starGLCallList = -1;
	
	public RenderSkyCosmic()
	{
		vboEnabled = OpenGlHelper.useVbo();
		this.vertexBufferFormat = new VertexFormat();
		this.vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
	}
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc)
	{
		generateStars();
		
		GlStateManager.pushMatrix();
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		
		float starBrightness = 0.85f;
		
		GlStateManager.color(starBrightness * 0.8f, starBrightness, starBrightness, starBrightness);
		
		if(this.vboEnabled)
		{
			this.starVBO.bindBuffer();
			GlStateManager.glEnableClientState(32884);
			GlStateManager.glVertexPointer(3, 5126, 12, 0);
			this.starVBO.drawArrays(7);
			this.starVBO.unbindBuffer();
			GlStateManager.glDisableClientState(32884);
		}
		else
		{
			GlStateManager.callList(this.starGLCallList);
		}
		
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableFog();
		GlStateManager.popMatrix();
	}
	
	private void generateStars()
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		
		if(starVBO != null)
		{
			starVBO.deleteGlBuffers();
		}
		
		if(starGLCallList >= 0)
		{
			GLAllocation.deleteDisplayLists(starGLCallList);
			starGLCallList = -1;
		}
		
		if(vboEnabled)
		{
			starVBO = new VertexBuffer(vertexBufferFormat);
			renderStars(bufferbuilder);
			bufferbuilder.finishDrawing();
			bufferbuilder.reset();
			starVBO.bufferData(bufferbuilder.getByteBuffer());
		}
		else
		{
			starGLCallList = GLAllocation.generateDisplayLists(1);
			GlStateManager.pushMatrix();
			GlStateManager.glNewList(starGLCallList, 4864);
			renderStars(bufferbuilder);
			tessellator.draw();
			GlStateManager.glEndList();
			GlStateManager.popMatrix();
		}
	}
	
	private void renderStars(BufferBuilder bufferBuilderIn)
	{
		Random random = new Random(10842L);
		bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);
		
		for(int i = 0; i < 3000; ++i)
		{
			double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
			double d3 = (double) (0.15F + random.nextFloat() * 0.1F);
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;
			
			if(d4 < 1.0D && d4 > 0.01D)
			{
				d4 = 1.0D / Math.sqrt(d4);
				d0 = d0 * d4;
				d1 = d1 * d4;
				d2 = d2 * d4;
				double d5 = d0 * 100.0D;
				double d6 = d1 * 100.0D;
				double d7 = d2 * 100.0D;
				double d8 = Math.atan2(d0, d2);
				double d9 = Math.sin(d8);
				double d10 = Math.cos(d8);
				double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
				double d12 = Math.sin(d11);
				double d13 = Math.cos(d11);
				double d14 = random.nextDouble() * Math.PI * 2.0D;
				double d15 = Math.sin(d14);
				double d16 = Math.cos(d14);
				
				for(int j = 0; j < 4; ++j)
				{
					double d17 = 0.0D;
					double d18 = (double) ((j & 2) - 1) * d3;
					double d19 = (double) ((j + 1 & 2) - 1) * d3;
					double d20 = 0.0D;
					double d21 = d18 * d16 - d19 * d15;
					double d22 = d19 * d16 + d18 * d15;
					double d23 = d21 * d12 + 0.0D * d13;
					double d24 = 0.0D * d12 - d21 * d13;
					double d25 = d24 * d9 - d22 * d10;
					double d26 = d22 * d9 + d24 * d10;
					bufferBuilderIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
				}
			}
		}
	}
}
