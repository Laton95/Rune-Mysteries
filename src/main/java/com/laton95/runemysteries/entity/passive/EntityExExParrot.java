package com.laton95.runemysteries.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntityExExParrot extends EntityTameable implements EntityFlying
{
	public float flap;

	public float flapSpeed;

	public float oFlapSpeed;

	public float oFlap;

	public float flapping = 1.0F;
	
	public EntityExExParrot(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.9F);
		this.moveHelper = new EntityFlyHelper(this);
	}
	
	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
		this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}
	
	@Override
	protected PathNavigate createNavigator(World worldIn)
	{
		PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
		pathnavigateflying.setCanOpenDoors(false);
		pathnavigateflying.setCanFloat(true);
		pathnavigateflying.setCanEnterDoors(true);
		return pathnavigateflying;
	}
	
	@Nullable
	@Override
	public SoundEvent getAmbientSound()
	{
		return SoundEvents.E_PARROT_IM_ZOMBIE;
	}
	
	@Nullable
	@Override
	protected ResourceLocation getLootTable()
	{
		return LootTableList.ENTITIES_PARROT;
	}
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.calculateFlapping();
	}
	
	private void calculateFlapping()
	{
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		
		if(!this.onGround && this.flapping < 1.0F)
		{
			this.flapping = 1.0F;
		}
		
		this.flapping = (float) ((double) this.flapping * 0.9D);
		
		if(!this.onGround && this.motionY < 0.0D)
		{
			this.motionY *= 0.6D;
		}
		
		this.flap += this.flapping * 2.0F;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if(this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			if(this.aiSit != null)
			{
				this.aiSit.setSitting(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		if(!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player))
		{
			this.aiSit.setSitting(!this.isSitting());
		}
		
		return super.processInteract(player, hand);
	}
	
	public boolean isFlying()
	{
		return !onGround;
	}
	
	@Override
	public boolean canMateWith(EntityAnimal otherAnimal)
	{
		return false;
	}
	
	@Nullable
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
	}
	
	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	@Nullable
	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	@Override
	public void fall(float distance, float damageMultiplier)
	{
	
	}
	
	@Override
	protected float getSoundPitch()
	{
		return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
	}
	
	protected float playFlySound(float p_191954_1_)
	{
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return p_191954_1_ + this.flapSpeed / 2.0F;
	}
	
	@Override
	protected boolean makeFlySound()
	{
		return true;
	}
	
	@Override
	public float getEyeHeight()
	{
		return this.height * 0.6f;
	}
	
	@Override
	public SoundCategory getSoundCategory()
	{
		return SoundCategory.NEUTRAL;
	}
}
