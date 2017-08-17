package com.laton95.runemysteries.spells.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpellProjectileBase extends EntityThrowable{
	
	protected boolean stationary = false;
	
	public SpellProjectileBase(World worldIn) {
		super(worldIn);
	}
	
	public SpellProjectileBase(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}
	
	@Override
	public void onEntityUpdate() {
		if (ticksExisted > 2) {
			for (int k = 0; k < 2; k++) {
				this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX + this.motionX * (double)k / 4.0D, this.posY + this.motionY * (double)k / 4.0D, this.posZ + this.motionZ * (double)k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
			}
		}
		
		if (this.stationary) {
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ = 0;
		}
		
		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result) {

		for (int i = 0; i < 16; ++i)
        {
            this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, rand.nextDouble()*2 - 1, rand.nextDouble(), rand.nextDouble()*2 - 1);
        }
	}
	
	protected void setStationary(boolean value) {
		this.stationary = value;
	}
}
