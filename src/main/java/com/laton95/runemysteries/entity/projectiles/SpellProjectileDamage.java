package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileDamage extends SpellProjectileBase
{

	private final int damage;
	
	private final static ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/red-orange.png");
	private final static EnumParticleTypes TRAIL_PARTICLE = EnumParticleTypes.CRIT;
	private final static EnumParticleTypes IMPACT_PARTICLE = EnumParticleTypes.CRIT_MAGIC;

	public SpellProjectileDamage(World worldIn, EntityLivingBase throwerIn, int damage)
	{
		super(worldIn, throwerIn, TEXTURE, TRAIL_PARTICLE, IMPACT_PARTICLE);
		this.damage = damage;
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		if (!world.isRemote)
		{
			if (result.entityHit != null)
			{
				result.entityHit.attackEntityFrom(
						new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, getThrower()).setProjectile(),
						damage);
			}
			setDead();
		}
		super.onImpact(result);
	}
}
