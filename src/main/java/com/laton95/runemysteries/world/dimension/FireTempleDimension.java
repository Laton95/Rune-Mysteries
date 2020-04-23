package com.laton95.runemysteries.world.dimension;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModBiomes;
import com.laton95.runemysteries.world.biome.provider.RuneTempleBiomeProvider;
import com.laton95.runemysteries.world.gen.SurfaceGenerationSettings;
import com.laton95.runemysteries.world.gen.SurfaceWorldChunkGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;

public class FireTempleDimension extends RuneTempleDimension {
	
	public FireTempleDimension(World world, DimensionType type) {
		super(world, type, EnumRuneType.FIRE);
	}
	
	@Override
	public boolean hasSkyLight() {
		return true;
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		SurfaceGenerationSettings settings = new SurfaceGenerationSettings();
		settings.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return new SurfaceWorldChunkGenerator(world, new RuneTempleBiomeProvider(world.getWorldInfo(), ModBiomes.FIRE_TEMPLE), settings);
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
		return 0.0f;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		float f = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
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
	public boolean canRespawnHere() {
		return false;
	}
	
	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}
}
