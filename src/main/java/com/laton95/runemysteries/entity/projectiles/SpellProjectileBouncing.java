package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileBouncing extends SpellProjectileBase
{

	private int landedTick;
	
	private final static ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/blue-green.png");
	private final static EnumParticleTypes TRAIL_PARTICLE = EnumParticleTypes.END_ROD;
	private final static EnumParticleTypes IMPACT_PARTICLE = EnumParticleTypes.VILLAGER_HAPPY;

	public SpellProjectileBouncing(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn, TEXTURE, TRAIL_PARTICLE, IMPACT_PARTICLE);
	}
	
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	@Override
	public void onImpact(RayTraceResult result)
	{

		double yDecay = 1.0;
		double hBoost = 1.0;
		if (result.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			switch (result.sideHit)
			{
				case DOWN:
					motionY = -motionY;
					break;
				case EAST:
					motionX = -motionX * hBoost;
					break;
				case NORTH:
					motionZ = -motionZ * hBoost;
					break;
				case SOUTH:
					motionZ = -motionZ * hBoost;
					break;
				case UP:
					motionY = -motionY * yDecay;
					break;
				case WEST:
					motionX = -motionX * hBoost;
					break;
				default:
					break;
			}
		}
		else
		{
			motionY = Math.abs(motionY) * yDecay + result.entityHit.motionY;
			motionX = -motionX * hBoost + result.entityHit.motionX;
			motionZ = -motionZ * hBoost + result.entityHit.motionZ;
		}
		
		if (ticksExisted > 2000)
		{
			setDead();
		}
		
		
		
		super.onImpact(result);
	}
}
