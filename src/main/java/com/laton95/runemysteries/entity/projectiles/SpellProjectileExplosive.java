package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileExplosive extends SpellProjectileBase
{

	private final int power;
	
	private final static ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/red-orange.png");
	private final static EnumParticleTypes TRAIL_PARTICLE = EnumParticleTypes.FLAME;
	private final static EnumParticleTypes IMPACT_PARTICLE = EnumParticleTypes.EXPLOSION_HUGE;

	public SpellProjectileExplosive(World worldIn, EntityLivingBase throwerIn, int power)
	{
		super(worldIn, throwerIn, TEXTURE, TRAIL_PARTICLE, IMPACT_PARTICLE);
		this.power = power;
	}

	@Override
	public void onImpact(RayTraceResult result)
	{
		if (!world.isRemote)
		{
			world.createExplosion(getThrower(), posX, posY, posZ, power, true);
			setDead();
		}

		super.onImpact(result);
	}
}
