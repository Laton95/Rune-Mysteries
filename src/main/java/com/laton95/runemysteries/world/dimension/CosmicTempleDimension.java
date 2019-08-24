package com.laton95.runemysteries.world.dimension;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModBiomeProviders;
import com.laton95.runemysteries.init.ModChunkGenerators;
import com.laton95.runemysteries.util.ModLog;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.client.IRenderHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class CosmicTempleDimension extends RuneTempleDimension {
	
	private ExtraStarsRenderer starsRenderer = new ExtraStarsRenderer();
	
	private NoCloudsRenderer cloudsRenderer = new NoCloudsRenderer();
	
	public CosmicTempleDimension(World world, DimensionType type) {
		super(world, type, EnumRuneType.COSMIC);
	}
	
	@Override
	public boolean hasSkyLight() {
		return true;
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return ModChunkGenerators.VOID_WORLD.create(world, ModBiomeProviders.COSMIC_TEMPLE.create(ModBiomeProviders.COSMIC_TEMPLE.createSettings()), ModChunkGenerators.VOID_WORLD.createSettings());
	}
	
	@Nullable
	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}
	
	@Nullable
	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5f;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		float f = MathHelper.cos(celestialAngle * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0.7529412F;
		float f2 = 0.84705883F;
		float f3 = 1.0F;
		f1 = f1 * (f * 0.94F + 0.06F);
		f2 = f2 * (f * 0.94F + 0.06F);
		f3 = f3 * (f * 0.91F + 0.09F);
		return new Vec3d((double)f1, (double)f2, (double)f3);
	}
	
	@Override
	public boolean canRespawnHere() {
		return false;
	}
	
	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}
	
	@Override
	public float getCloudHeight() {
		return 30000;
	}
	
	@Nullable
	@Override
	public IRenderHandler getCloudRenderer() {
		return cloudsRenderer;
	}
	
	@Nullable
	@Override
	public IRenderHandler getSkyRenderer() {
		return starsRenderer;
	}
	
	private class NoCloudsRenderer implements IRenderHandler {
		
		@Override
		public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
			ModLog.info("render");
		}
	}
	
	private class ExtraStarsRenderer implements IRenderHandler {
		private final boolean vboEnabled = true;
		
		private final VertexFormat vertexBufferFormat = new VertexFormat().addElement(new VertexFormatElement(0, VertexFormatElement.Type.FLOAT, VertexFormatElement.Usage.POSITION, 3));
		
		private VertexBuffer starVBO;
		
		private int starGLCallList = -1;
		
		@Override
		public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
			generateStars();
			
			GlStateManager.pushMatrix();
			GlStateManager.disableFog();
			GlStateManager.disableAlphaTest();
			GlStateManager.enableBlend();
			GlStateManager.disableTexture();
			
			float starBrightness = 0.85f;
			
			GlStateManager.color4f(starBrightness * 0.8f, starBrightness, starBrightness, starBrightness);
			
			if(this.vboEnabled)
			{
				this.starVBO.bindBuffer();
				GlStateManager.enableClientState(32884);
				GlStateManager.vertexPointer(3, 5126, 12, 0);
				this.starVBO.drawArrays(7);
				this.starVBO.unbindBuffer();
				GlStateManager.disableClientState(32884);
			}
			else
			{
				GlStateManager.callList(this.starGLCallList);
			}
			
			GlStateManager.enableTexture();
			GlStateManager.disableBlend();
			GlStateManager.enableAlphaTest();
			GlStateManager.enableFog();
			GlStateManager.popMatrix();
		}
		
		private void generateStars() {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			if(this.starVBO != null) {
				this.starVBO.deleteGlBuffers();
			}
			
			if(this.starGLCallList >= 0) {
				GLAllocation.deleteDisplayLists(this.starGLCallList);
				this.starGLCallList = -1;
			}
			
			if(this.vboEnabled) {
				this.starVBO = new VertexBuffer(this.vertexBufferFormat);
				this.renderStars(bufferbuilder);
				bufferbuilder.finishDrawing();
				bufferbuilder.reset();
				this.starVBO.bufferData(bufferbuilder.getByteBuffer());
			}
			else {
				this.starGLCallList = GLAllocation.generateDisplayLists(1);
				GlStateManager.pushMatrix();
				GlStateManager.newList(this.starGLCallList, 4864);
				this.renderStars(bufferbuilder);
				tessellator.draw();
				GlStateManager.endList();
				GlStateManager.popMatrix();
			}
		}
		
		private void renderStars(BufferBuilder bufferBuilderIn) {
			Random random = new Random(10842L);
			bufferBuilderIn.begin(7, DefaultVertexFormats.POSITION);
			
			for(int i = 0; i < 6000; ++i) {
				double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
				double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
				double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
				double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
				double d4 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d4 < 1.0D && d4 > 0.01D) {
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
						double d17 = 0.0D;
						double d18 = (double)((j & 2) - 1) * d3;
						double d19 = (double)((j + 1 & 2) - 1) * d3;
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
}
