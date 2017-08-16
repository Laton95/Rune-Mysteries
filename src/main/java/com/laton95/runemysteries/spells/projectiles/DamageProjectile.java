package com.laton95.runemysteries.spells.projectiles;

import com.laton95.runemysteries.util.LogHelper;

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

public class DamageProjectile extends EntityThrowable{
	private Entity thrower;
	private int damage;
	private int age = 0;
	
	public DamageProjectile(World worldIn) {
		super(worldIn);
	}
	
	public DamageProjectile(World worldIn, EntityLivingBase throwerIn, int damage) {
		super(worldIn, throwerIn);
		this.thrower = throwerIn;
		this.damage = damage;
	}
	
	@Override
	public void onEntityUpdate() {
		if (ticksExisted > 2) {
			for (int k = 0; k < 2; k++) {
				this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX + this.motionX * (double)k / 4.0D, this.posY + this.motionY * (double)k / 4.0D, this.posZ + this.motionZ * (double)k / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
			}
		}
		
//		double followSpeed = 0.1;
//		if (ticksExisted > 20) {
//			this.motionX = Math.copySign(followSpeed, thrower.posX- posX);
//			this.motionY = Math.copySign(followSpeed, thrower.posY- posY);
//			this.motionZ = Math.copySign(followSpeed, thrower.posZ- posZ);
//		}
		
//		this.motionX = 0;
//		this.motionY = 0;
//		this.motionZ = 0;
		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit != null) {
				result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("runeMagicDamageProjectile", this, thrower).setProjectile(), damage);
			}
			
			this.setDead();
		}
		for (int i = 0; i < 16; ++i)
        {
            this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX, this.posY, this.posZ, rand.nextDouble()*2 - 1, rand.nextDouble(), rand.nextDouble()*2 - 1);
        }
//		motionY = -motionY;
//		if (Math.abs(motionY) < 0.01) {
//			setDead();
//		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("throwerID", thrower.getEntityId());
		super.writeEntityToNBT(compound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		thrower = world.getEntityByID(compound.getInteger("throwerID"));
		super.readEntityFromNBT(compound);
	}
}
