package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileBase extends EntityThrowable
{

	protected boolean stationary = false;
	private final ResourceLocation texture;
	private final EnumParticleTypes trailParticles;
	private final EnumParticleTypes impactParticles;

	public SpellProjectileBase(World worldIn)
	{
		super(worldIn);
		this.texture = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/red-orange.png");
		this.trailParticles = EnumParticleTypes.CRIT;
		this.impactParticles = EnumParticleTypes.CRIT_MAGIC;
	}

	public SpellProjectileBase(World worldIn, EntityLivingBase throwerIn, ResourceLocation texture, EnumParticleTypes trailParticles, EnumParticleTypes impactParticles)
	{
		super(worldIn, throwerIn);
		this.texture = texture;
		this.trailParticles = trailParticles;
		this.impactParticles = impactParticles;
	}
	
	
	public ResourceLocation getTexture()
	{
		return texture;
	}

	@Override
	public void onEntityUpdate()
	{
		if (ticksExisted > 2)
		{
			for (int k = 0; k < 2; k++)
			{
				world.spawnParticle(trailParticles, posX + motionX * k / 4.0D, posY
						+ motionY * k / 4.0D, posZ + motionZ * k / 4.0D, -motionX, -motionY + 0.2D, -motionZ);
			}
		}

		if (stationary)
		{
			motionX = 0;
			motionY = 0;
			motionZ = 0;
		}

		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{

		for (int i = 0; i < 16; ++i)
		{
			world.spawnParticle(impactParticles, posX, posY, posZ, rand.nextDouble() * 2
					- 1, rand.nextDouble(), rand.nextDouble() * 2 - 1);
		}
	}

	protected void setStationary(boolean value)
	{
		stationary = value;
	}
}
