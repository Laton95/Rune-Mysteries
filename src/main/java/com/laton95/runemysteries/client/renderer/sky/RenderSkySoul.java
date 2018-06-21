package com.laton95.runemysteries.client.renderer.sky;

import com.laton95.runemysteries.util.TextureRotationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class RenderSkySoul extends RenderSky
{
	private static int vortexTracker = 0;
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc)
	{
		vortexTracker++;
		
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.depthMask(false);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		
		for(int k1 = 0; k1 < 6; ++k1)
		{
			GlStateManager.pushMatrix();
			TextureRotationHelper coords = new TextureRotationHelper(1);
			
			//Down
			if(k1 == 0)
			{
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_bottom.png"));
			}
			
			//North
			if(k1 == 1)
			{
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_north.png"));
			}
			
			//South
			if(k1 == 2)
			{
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_south.png"));
			}
			
			
			//East
			if(k1 == 3)
			{
				GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_east.png"));
			}
			
			//West
			if(k1 == 4)
			{
				GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_west.png"));
			}
			
			if(k1 == 5)
			{
				GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
				coords.flip(TextureRotationHelper.Alignment.VERTICAL);
				coords.flip(TextureRotationHelper.Alignment.HORIZONTAL);
				mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_sky_top.png"));
			}
			
			draw(100, 100, 255, 255, 255, 255, tessellator, bufferbuilder, coords);
		}
		
		GlStateManager.pushMatrix();
		float frameRotate = 0.1F;
		int rot = vortexTracker % (int) (360 * 1 / frameRotate);
		
		
		GlStateManager.rotate(rot * frameRotate, 0.0F, 1.0F, 0.0F);
		
		TextureRotationHelper coords = new TextureRotationHelper(1);
		mc.renderEngine.bindTexture(new ResourceLocation("runemysteries:textures/enviroment/soul_vortex.png"));
		draw(230, 99, 255, 255, 255, 255, tessellator, bufferbuilder, coords);
	}
	
	
}
