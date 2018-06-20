package com.laton95.runemysteries.potion;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RMModPotion extends Potion
{
	protected final ResourceLocation texture;
	
	protected RMModPotion(String name, boolean isBadEffectIn, int liquidColorIn, ResourceLocation texture)
	{
		super(isBadEffectIn, liquidColorIn);
		setPotionName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		this.texture = texture;
	}
	
	public boolean hasEffect(EntityLivingBase entity) {
		return hasEffect(entity, this);
	}
	
	public boolean hasEffect(EntityLivingBase entity, Potion potion) {
		return entity.getActivePotionEffect(potion) != null;
	}
	
	@Override
	public boolean hasStatusIcon()
	{
		return true;
	}
	
	@Override
	public boolean shouldRenderHUD(PotionEffect effect)
	{
		return true;
	}
	
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc)
	{
		render(x + 6, y + 7, 1);
	}
	
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha)
	{
		render(x + 3, y + 3, alpha);
	}
	
	@SideOnly(Side.CLIENT)
	private void render(int x, int y, float alpha) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		GlStateManager.color(1, 1, 1, alpha);
		
		int iconIndex = 1;
		int textureX = iconIndex % 8 * 18;
		int textureY = 198 + iconIndex / 8 * 18;
		
		buf.pos(x, y + 18, 0).tex(1, 1).endVertex();
		buf.pos(x + 18, y + 18, 0).tex(0, 1).endVertex();
		buf.pos(x + 18, y, 0).tex(0, 0).endVertex();
		buf.pos(x, y, 0).tex(1, 0).endVertex();
		
		tessellator.draw();
	}
}
