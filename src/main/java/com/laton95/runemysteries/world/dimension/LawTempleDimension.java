package com.laton95.runemysteries.world.dimension;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModBiomeProviders;
import com.laton95.runemysteries.init.ModChunkGenerators;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;

public class LawTempleDimension extends RuneTempleDimension {
	
	public LawTempleDimension(World world, DimensionType type) {
		super(world, type, EnumRuneType.LAW);
	}
	
	@Override
	public boolean hasSkyLight() {
		return true;
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return ModChunkGenerators.SOLID_WORLD.create(world, ModBiomeProviders.LAW_TEMPLE.create(ModBiomeProviders.LAW_TEMPLE.createSettings()), ModChunkGenerators.SOLID_WORLD.createSettings());
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
		return false;
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
		return new Vec3d((double) f1, (double) f2, (double) f3);
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
