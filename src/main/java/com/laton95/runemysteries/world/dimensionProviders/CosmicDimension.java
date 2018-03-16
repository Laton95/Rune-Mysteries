package com.laton95.runemysteries.world.dimensionProviders;

import com.laton95.runemysteries.init.DimensionRegistry;
import com.laton95.runemysteries.world.chunkGenerators.ChunkGeneratorCosmic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CosmicDimension extends WorldProvider
{
	
	IRenderHandler clouds = new NoClouds();
	
	/**
	 * Creates a new {@link BiomeProvider} for the WorldProvider, and also sets
	 * the values of {@link #hasSkylight} and {@link #hasNoSky} appropriately.
	 * <p>
	 * Note that subclasses generally override this method without calling the
	 * parent version.
	 */
	@Override
	public void init()
	{
		biomeProvider = new BiomeProviderSingle(Biomes.VOID);
		hasSkyLight = false;
	}
	
	@Override
	public IRenderHandler getSkyRenderer()
	{
		return super.getSkyRenderer();
	}
	
	@Override
	public float getSunBrightness(float par1)
	{
		return 0;
	}
	
	@Override
	public IRenderHandler getCloudRenderer()
	{
		return clouds;
	}
	
	private class NoClouds extends IRenderHandler
	{
		
		@Override
		public void render(float partialTicks, WorldClient world, Minecraft mc)
		{
			//Do nothing to renderer no clouds
		}
		
	}
	
	@Override
	public DimensionType getDimensionType()
	{
		return DimensionRegistry.COSMIC;
	}
	
	@Override
	public float getCloudHeight()
	{
		return 0f;
	}
	
	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int x, int z)
	{
		return false;
	}
	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
		return false;
	}
	
	@Override
	public long getWorldTime()
	{
		return 18000;
	}
	
	@Override
	public boolean isDaytime()
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
	{
		float f = MathHelper.cos(p_76562_1_ * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0F;
		float f2 = 0F;
		float f3 = 0F;
		return new Vec3d(f1, f2, f3);
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorCosmic(world, world.getSeed());
	}
}
