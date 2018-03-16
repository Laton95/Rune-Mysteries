package com.laton95.runemysteries.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.UUID;

public abstract class EntityModThrowable extends Entity implements IProjectile
{
	
	protected EntityLivingBase thrower;
	private int ignoreTime;
	private Entity ignoreEntity;
	private boolean hasReverseGravity = false;
	
	public EntityModThrowable(World worldIn)
	{
		super(worldIn);
		this.setSize(0.25f, 0.25f);
	}
	
	public EntityModThrowable(World worldIn, EntityLivingBase thrower)
	{
		super(worldIn);
		this.thrower = thrower;
		this.setPosition(thrower.posX, thrower.posY + (double) thrower.getEyeHeight() - 0.10000000149011612D, thrower.posZ);
	}
	
	@Override
	protected void entityInit()
	{
	
	}
	
	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy)
	{
		float f = MathHelper.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
		x /= f;
		y /= f;
		z /= f;
		double inaccuracyModifier = 0.007499999832361937d;
		x += this.rand.nextGaussian() * inaccuracyModifier * inaccuracy;
		y += this.rand.nextGaussian() * inaccuracyModifier * inaccuracy;
		z += this.rand.nextGaussian() * inaccuracyModifier * inaccuracy;
		x *= velocity;
		y *= velocity;
		z *= velocity;
		
		setVelocity(x, y, z);
	}
	
	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy)
	{
		float scaleConstant = 0.017453292f;
		float x = -MathHelper.sin(rotationYawIn * scaleConstant) * MathHelper.cos(rotationPitchIn * scaleConstant);
		float y = -MathHelper.sin((rotationPitchIn + pitchOffset) * scaleConstant);
		float z = MathHelper.cos(rotationYawIn * scaleConstant) * MathHelper.cos(rotationPitchIn * scaleConstant);
		shoot((double) x, (double) y, (double) z, velocity, inaccuracy);
		
		motionX += entityThrower.motionX;
		motionZ += entityThrower.motionZ;
		motionY += entityThrower.motionY;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z)
	{
		super.setVelocity(x, y, z);
		
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float f1 = MathHelper.sqrt(x * x + z * z);
			rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
			rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * (180D / Math.PI));
			prevRotationYaw = this.rotationYaw;
			prevRotationPitch = this.rotationPitch;
		}
	}
	
	@Override
	public void onUpdate()
	{
		//Update last tick positions
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		
		//Vector of current position
		Vec3d currentPosition = new Vec3d(posX, posY, posZ);
		//Vector of future position given current motion
		Vec3d futurePosition = currentPosition.addVector(motionX, motionY, motionZ);
		//Raytrace of what the projectile is going to hit
		RayTraceResult raytraceresult = this.world.rayTraceBlocks(currentPosition, futurePosition);
		
		//If the projectile is going to hit something, change future position to the point of impact
		if (raytraceresult != null)
		{
			futurePosition = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
		}
		
		Entity closestEntity = null;
		List<Entity> nearbyEntities = this.world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
		double closestDistance = Double.MAX_VALUE;
		boolean isIgnoreEntity = false;
		
		//Find the nearest entity to the future position, ignoring it if the projectile has just been fired
		for (Entity entity : nearbyEntities)
		{
			if (entity.canBeCollidedWith())
			{
				if (entity == ignoreEntity)
				{
					isIgnoreEntity = true;
				} else if (thrower != null && ticksExisted < 2 && ignoreEntity == null)
				{
					ignoreEntity = entity;
					isIgnoreEntity = true;
				} else
				{
					isIgnoreEntity = false;
					AxisAlignedBB entityBB = entity.getEntityBoundingBox().grow(0.30000001192092896D);
					RayTraceResult bBoxCollision = entityBB.calculateIntercept(currentPosition, futurePosition);
					
					if (bBoxCollision != null)
					{
						double entityDistance = currentPosition.squareDistanceTo(bBoxCollision.hitVec);
						
						if (entityDistance < closestDistance)
						{
							closestEntity = entity;
							closestDistance = entityDistance;
						}
					}
				}
			}
		}
		
		if (ignoreEntity != null)
		{
			if (isIgnoreEntity)
			{
				ignoreTime = 2;
			} else if (ignoreTime-- <= 0)
			{
				ignoreEntity = null;
			}
		}
		
		//Set the type of the RayTraceResult as an entity, with its target as the chosen entity
		if (closestEntity != null)
		{
			raytraceresult = new RayTraceResult(closestEntity);
		}
		
		//If the projetile hits a portal go through it, otherwise call the onImpact method
		if (raytraceresult != null)
		{
			if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && this.world.getBlockState(raytraceresult.getBlockPos()).getBlock() == Blocks.PORTAL)
			{
				this.setPortal(raytraceresult.getBlockPos());
			} else if (!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
			{
				if (raytraceresult.typeOfHit == RayTraceResult.Type.ENTITY && raytraceresult.entityHit == thrower)
				{
				
				} else
				{
					this.onImpact(raytraceresult);
				}
			}
		}
		
		//Update the projetile's position for the next tick
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		
		//Do some vector maths crap
		float f = MathHelper.sqrt(Math.pow(motionX, 2) + Math.pow(motionZ, 2));
		this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
		this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI));
		
		//What the hell is this all for
		while (this.rotationPitch - this.prevRotationPitch < -180.0F)
		{
			this.prevRotationPitch -= 360.0F;
		}
		
		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}
		
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		
		float airResistance = 0.99F;
		float gravityVelocity = hasReverseGravity ? -0.03f : 0.03f;
		
		if (this.isInWater())
		{
			for (int j = 0; j < 4; ++j)
			{
				this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
			}
			
			airResistance = 0.8F;
		}
		
		//Slow the projectile down based on air resistance
		this.motionX *= (double) airResistance;
		this.motionY *= (double) airResistance;
		this.motionZ *= (double) airResistance;
		
		if (!this.hasNoGravity())
		{
			this.motionY -= (double) gravityVelocity;
		}
		
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	protected abstract void onImpact(RayTraceResult result);
	
	public EntityLivingBase getThrower()
	{
		return thrower;
	}
	
	private EntityLivingBase getThrowerFromName(String name)
	{
		EntityLivingBase throwerEntity = this.world.getPlayerEntityByName(name);
		
		if (throwerEntity == null && this.world instanceof WorldServer)
		{
			try
			{
				Entity entity = ((WorldServer) this.world).getEntityFromUuid(UUID.fromString(name));
				
				if (entity instanceof EntityLivingBase)
				{
					throwerEntity = (EntityLivingBase) entity;
				}
			} catch (Throwable var2)
			{
				throwerEntity = null;
			}
		}
		
		return throwerEntity;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		String throwerName = compound.getString("throwerName");
		this.thrower = getThrowerFromName(throwerName);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setString("throwerName", thrower != null ? thrower.getName() : "");
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		return true;
	}
	
	public void setHasReverseGravity(boolean hasReverseGravity)
	{
		this.hasReverseGravity = hasReverseGravity;
	}
}
