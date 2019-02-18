package com.laton95.runemysteries.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ModPotion extends Potion {
	
	protected final ResourceLocation texture;
	
	public ModPotion(String name, boolean isBadEffect, boolean isBeneficial, int liquidColor, ResourceLocation texture) {
		super(isBadEffect, liquidColor);
		setRegistryName(name);
		this.texture = texture;
		if(isBeneficial) { setBeneficial(); }
	}
	
	@Override
	public Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
		return super.setIconIndex(p_76399_1_, p_76399_2_);
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		render(x + 6, y + 7, 1);
	}
	
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
		render(x + 3, y + 3, alpha);
	}
	
	private void render(int x, int y, float alpha) {
		Minecraft.getInstance().textureManager.bindTexture(texture);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		GlStateManager.color4f(1, 1, 1, alpha);
		
		buf.pos(x, y + 18, 0).tex(1, 1).endVertex();
		buf.pos(x + 18, y + 18, 0).tex(0, 1).endVertex();
		buf.pos(x + 18, y, 0).tex(0, 0).endVertex();
		buf.pos(x, y, 0).tex(1, 0).endVertex();
		
		tessellator.draw();
	}
	
	public boolean hasEffect(EntityLivingBase entity) {
		return entity.getActivePotionEffect(this) != null;
	}
}
