package com.laton95.runemysteries.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityProjectileSpellBase extends EntityModThrowable
{
	
	private float impactParticleSpeed = 0.5f;

	private float trailParticleSpeed = 0.1f;
	
	public EntityProjectileSpellBase(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityProjectileSpellBase(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if(id == 3)
		{
			for(int i = 0; i < 24; ++i)
			{
				world.spawnParticle(getImpactParticles(), posX, posY, posZ, rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed, rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed, rand.nextDouble() * impactParticleSpeed * 2 - impactParticleSpeed);
			}
		}
	}
	
	protected abstract EnumParticleTypes getImpactParticles();
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		for(int i = 0; i < 4; ++i)
		{
			world.spawnParticle(getTrailParticles(), posX, posY, posZ, rand.nextDouble() * trailParticleSpeed * 2 - trailParticleSpeed, rand.nextDouble() * trailParticleSpeed * 2 - trailParticleSpeed, rand.nextDouble() * trailParticleSpeed * 2 - trailParticleSpeed);
		}
	}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if(!world.isRemote)
		{
			world.setEntityState(this, (byte) 3);
		}
		
	}
	
	protected abstract EnumParticleTypes getTrailParticles();
}
