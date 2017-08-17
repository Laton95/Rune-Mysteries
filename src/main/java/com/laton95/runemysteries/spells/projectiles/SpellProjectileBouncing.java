package com.laton95.runemysteries.spells.projectiles;

import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileBouncing extends SpellProjectileBase {

	private boolean isLanded = false;
	private int landedTick;
	
	public SpellProjectileBouncing(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	@Override
	public void onImpact(RayTraceResult result) {

		if (landedTick > 200 || ticksExisted > 1200){
			this.setDead();
		}
		if (Math.abs(this.motionY) < 0.1) {
			landedTick++;
			this.motionY = 0;
			this.motionZ = 0;
			this.motionX = 0;
		} else {
//			landedTick = 0;
			double yDecay = 1.5;
			if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
				switch (result.sideHit) {
				case DOWN:
					this.motionY = -this.motionY;
					break;
				case EAST:
					this.motionX = -this.motionX;
					break;
				case NORTH:
					this.motionZ = -this.motionZ;
					break;
				case SOUTH:
					this.motionZ = -this.motionZ;
					break;
				case UP:
					this.motionY = -this.motionY/yDecay;
					break;
				case WEST:
					this.motionX = -this.motionX;
					break;
				default:
					break;
				}
			} else {
				this.motionY = -this.motionY/yDecay;
				this.motionX = -this.motionX;
				this.motionZ = -this.motionZ;
			}
			super.onImpact(result);
		}
	}
}
