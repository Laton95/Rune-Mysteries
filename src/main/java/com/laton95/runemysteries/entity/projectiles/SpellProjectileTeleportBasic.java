package com.laton95.runemysteries.entity.projectiles;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class SpellProjectileTeleportBasic extends SpellProjectileBase
{
	private final static ResourceLocation TEXTURE = new ResourceLocation(ModReference.MOD_ID, "textures/entity/projectile/pink-purple.png");
	private final static EnumParticleTypes TRAIL_PARTICLE = EnumParticleTypes.CRIT;
	private final static EnumParticleTypes IMPACT_PARTICLE = EnumParticleTypes.END_ROD;

    public SpellProjectileTeleportBasic(World worldIn)
    {
        super(worldIn);
    }

    public SpellProjectileTeleportBasic(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn, TEXTURE, TRAIL_PARTICLE, IMPACT_PARTICLE);
        setImpactParticleSpeed(0.1f);
    }

    protected void onImpact(RayTraceResult result)
    {
    	super.onImpact(result);
        EntityLivingBase thrower = this.getThrower();

        if (result.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            BlockPos blockpos = result.getBlockPos();
            TileEntity tileentity = this.world.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityEndGateway)
            {
                TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway)tileentity;

                if (thrower != null)
                {
                    if (thrower instanceof EntityPlayerMP)
                    {
                        CriteriaTriggers.ENTER_BLOCK.trigger((EntityPlayerMP)thrower, this.world.getBlockState(blockpos));
                    }

                    tileentityendgateway.teleportEntity(thrower);
                    this.setDead();
                    return;
                }

                tileentityendgateway.teleportEntity(this);
                return;
            }
        }

        if (!this.world.isRemote)
        {
            if (thrower instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)thrower;

                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.world == this.world && !entityplayermp.isPlayerSleeping())
                {
                    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
                    if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                    {

                    if (thrower.isRiding())
                    {
                        thrower.dismountRidingEntity();
                    }

                    thrower.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                    }
                }
            }
            else if (thrower != null)
            {
                thrower.setPositionAndUpdate(this.posX, this.posY, this.posZ);
            }

            this.setDead();
        }
    }

    public void onUpdate()
    {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive())
        {
            this.setDead();
        }
        else
        {
            super.onUpdate();
        }
    }
}
