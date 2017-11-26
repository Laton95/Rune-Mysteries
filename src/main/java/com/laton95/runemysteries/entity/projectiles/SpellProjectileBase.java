package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class SpellProjectileBase extends EntityThrowable
{

	protected boolean stationary = false;
	private ResourceLocation texture  = new ResourceLocation(ModReference.MOD_ID,
			"textures/entity/projectile/pink-purple.png");;
	private EnumParticleTypes impactParticles = EnumParticleTypes.END_ROD;;

	private float impactParticleSpeed = 2;

	public SpellProjectileBase(World worldIn)
	{
		super(worldIn);
	}

	public SpellProjectileBase(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	public SpellProjectileBase(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}

	public ResourceLocation getTexture()
	{
		return texture;
	}
	
	@Override
	public void onUpdate()
	{
		if (world.isRemote)
		{
			LogHelper.info("update on client");
		}
		super.onUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		for (int i = 0; i < 16; ++i)
		{
			world.spawnParticle(impactParticles, posX, posY, posZ,
					rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed,
					rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed,
					rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed);
		}
		
		setDead();
	}

	protected void setImpactParticleSpeed(float projectileSpeed)
	{
		impactParticleSpeed = projectileSpeed;
	}

	protected void setStationary(boolean value)
	{
		stationary = value;
	}
}
