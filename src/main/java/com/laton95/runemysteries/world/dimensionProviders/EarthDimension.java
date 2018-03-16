package com.laton95.runemysteries.world.dimensionProviders;

import com.laton95.runemysteries.init.DimensionRegistry;
import com.laton95.runemysteries.world.chunkGenerators.ChunkGeneratorEarth;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EarthDimension extends WorldProvider
{

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
	public DimensionType getDimensionType()
	{
		return DimensionRegistry.EARTH;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
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
		return 6000;
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
		float f1 = 0.7529412F;
		float f2 = 0.84705883F;
		float f3 = 1.0F;
		f1 = f1 * (f * 0.94F + 0.06F);
		f2 = f2 * (f * 0.94F + 0.06F);
		f3 = f3 * (f * 0.91F + 0.09F);
		return new Vec3d(f1, f2, f3);
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorEarth(world, world.getSeed());
	}
}
