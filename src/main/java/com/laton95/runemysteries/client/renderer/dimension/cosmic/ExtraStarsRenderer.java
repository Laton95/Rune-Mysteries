package com.laton95.runemysteries.client.renderer.dimension.cosmic;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.client.IRenderHandler;

import java.util.Random;

public class ExtraStarsRenderer implements IRenderHandler {
	
	private final VertexFormat skyVertexFormat = DefaultVertexFormats.POSITION;
	
	private VertexBuffer starVBO;
	
	@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
		generateStars();
		
		ActiveRenderInfo activeRenderInfo = mc.gameRenderer.getActiveRenderInfo();
		
		net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup cameraSetup = net.minecraftforge.client.ForgeHooksClient.onCameraSetup(mc.gameRenderer, activeRenderInfo, partialTicks);
		
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(cameraSetup.getRoll()));
		
		matrixStack.rotate(Vector3f.XP.rotationDegrees(activeRenderInfo.getPitch()));
		matrixStack.rotate(Vector3f.YP.rotationDegrees(activeRenderInfo.getYaw() + 180.0F));
		
		float starBrightness = 255f;
		RenderSystem.color4f(starBrightness, starBrightness, starBrightness, starBrightness);
		starVBO.bindBuffer();
		skyVertexFormat.setupBufferState(0L);
		
		starVBO.draw(matrixStack.getLast().getMatrix(), 7);
		VertexBuffer.unbindBuffer();
		skyVertexFormat.clearBufferState();
	}
	
	private void generateStars() {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		if(starVBO != null) {
			starVBO.close();
		}
		
		starVBO = new VertexBuffer(this.skyVertexFormat);
		renderStars(bufferbuilder);
		bufferbuilder.finishDrawing();
		starVBO.upload(bufferbuilder);
	}
	
	private void renderStars(BufferBuilder bufferBuilderIn) {
		Random random = new Random(10842L);
		bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);
		
		for(int i = 0; i < 4000; ++i) {
			double d0 = random.nextFloat() * 2.0F - 1.0F;
			double d1 = random.nextFloat() * 2.0F - 1.0F;
			double d2 = random.nextFloat() * 2.0F - 1.0F;
			double d3;
			d3 = 0.15F + random.nextFloat() * 0.1F;
			double d4 = d0 * d0 + d1 * d1 + d2 * d2;
			if(d4 < 1.0D && d4 > 0.01D) {
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
				
				for(int j = 0; j < 4; ++j) {
					double d18 = (double) ((j & 2) - 1) * d3;
					double d19 = (double) ((j + 1 & 2) - 1) * d3;
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
