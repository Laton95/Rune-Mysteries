package com.laton95.runemysteries.entity.passive;

import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public class EntityFriendlyZombie extends EntityCreature
{
	public EntityFriendlyZombie(World worldIn)
	{
		super(worldIn);
	}
	
	//	@Override
	//	protected void initEntityAI()
	//	{
	//		this.tasks.addTask(1, new EntityAISwimming(this));
	//		this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
	//		this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
	//		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
	//		this.tasks.addTask(10, new EntityAILookIdle(this));
	//		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityZombie.class, false));
	//	}
	//
	//	@Override
	//	protected void applyEntityAttributes()
	//	{
	//		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	//		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	//		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	//		super.applyEntityAttributes();
	//	}
}
