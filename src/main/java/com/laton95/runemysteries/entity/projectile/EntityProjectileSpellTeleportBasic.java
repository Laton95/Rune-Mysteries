package com.laton95.runemysteries.entity.projectile;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityProjectileSpellTeleportBasic extends EntityProjectileSpellBase
{
	public EntityProjectileSpellTeleportBasic(World worldIn)
	{
		super(worldIn);
	}

	public EntityProjectileSpellTeleportBasic(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	protected void onImpact(RayTraceResult result)
	{
		super.onImpact(result);
		EntityLivingBase thrower = getThrower();

		if (result.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			BlockPos blockpos = result.getBlockPos();
			TileEntity tileentity = world.getTileEntity(blockpos);

			if (tileentity instanceof TileEntityEndGateway)
			{
				TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway) tileentity;

				if (thrower != null)
				{
					if (thrower instanceof EntityPlayerMP)
					{
						CriteriaTriggers.ENTER_BLOCK.trigger((EntityPlayerMP) thrower, world.getBlockState(blockpos));
					}

					tileentityendgateway.teleportEntity(thrower);
					super.onImpact(result);
					return;
				}

				tileentityendgateway.teleportEntity(this);
				return;
			}
		}

		if (!world.isRemote)
		{
			if (thrower instanceof EntityPlayerMP)
			{
				EntityPlayerMP entityplayermp = (EntityPlayerMP) thrower;

				if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.world == world && !entityplayermp.isPlayerSleeping())
				{
					net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(entityplayermp, posX, posY, posZ, 5.0F);
					if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
					{

						if (thrower.isRiding())
						{
							thrower.dismountRidingEntity();
						}

						thrower.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
					}
				}
			} else if (thrower != null)
			{
				thrower.setPositionAndUpdate(posX, posY, posZ);
			}
			
			setDead();
		}
		
		
		super.onImpact(result);
	}
	
	@Override
	protected EnumParticleTypes getImpactParticles()
	{
		return EnumParticleTypes.PORTAL;
	}
	
	@Override
	protected EnumParticleTypes getTrailParticles()
	{
		return EnumParticleTypes.CRIT_MAGIC;
	}
	
	public void onUpdate()
	{
		EntityLivingBase entitylivingbase = getThrower();

		if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive())
		{
			setDead();
		} else
		{
			super.onUpdate();
		}
	}
}
