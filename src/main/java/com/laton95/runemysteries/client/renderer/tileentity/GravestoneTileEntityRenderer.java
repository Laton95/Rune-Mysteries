package com.laton95.runemysteries.client.renderer.tileentity;

import com.laton95.runemysteries.block.GravestoneBlock;
import com.laton95.runemysteries.tileentity.GravestoneTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class GravestoneTileEntityRenderer extends TileEntityRenderer<GravestoneTileEntity> {
	
	public GravestoneTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	@Override
	public void render(GravestoneTileEntity gravestone, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		BlockState blockstate = gravestone.getBlockState();
		
		matrixStackIn.push();
		
		matrixStackIn.translate(0.5D, 0.5D, 0.5D);
		float rotationAngle = -blockstate.get(GravestoneBlock.FACING).getHorizontalAngle();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotationAngle));
		
		float scaleFactor = 0.02F;
		matrixStackIn.translate(0.0D, 3.0D / 16.0D, 3.0D / 16.0D);
		matrixStackIn.scale(scaleFactor, -scaleFactor, scaleFactor);
		
		FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
		int textColour = DyeColor.WHITE.getTextColor();
		double colourIntensity = 0.4D;
		int red = (int) ((double) NativeImage.getRed(textColour) * colourIntensity);
		int green = (int) ((double) NativeImage.getGreen(textColour) * colourIntensity);
		int blue = (int) ((double) NativeImage.getBlue(textColour) * colourIntensity);
		int colour = NativeImage.getCombined(0, blue, green, red);
		
		String text = gravestone.getRenderText((function) -> {
			List<ITextComponent> list = RenderComponentsUtil.splitText(function, 90, fontrenderer, false, true);
			return list.isEmpty() ? "" : list.get(0).getFormattedText();
		});
		if(text != null) {
			float x = (float) (-fontrenderer.getStringWidth(text) / 2);
			fontrenderer.renderString(text, x, 5, colour, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
		}
		
		matrixStackIn.pop();
	}
}
