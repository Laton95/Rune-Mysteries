package com.laton95.runemysteries.entity.passive;

import com.laton95.runemysteries.init.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.ShoulderRidingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;

public class ExExParrotEntity extends ParrotEntity {
	
	public float flap;
	
	public float flapSpeed;
	
	public float oFlapSpeed;
	
	public float oFlap;
	
	public float flapping = 1.0F;
	
	private boolean partyParrot;
	
	private BlockPos jukeboxPosition;
	
	public ExExParrotEntity(EntityType<? extends ExExParrotEntity> entityType, World world) {
		super(entityType, world);
		this.moveController = new FlyingMovementController(this);
	}
	
	@Override
	protected void registerGoals() {
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(2, this.sitGoal);
		this.goalSelector.addGoal(2, new FollowOwnerFlyingGoal(this, 1.0D, 5.0F, 1.0F));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LandOnOwnersShoulderGoal(this));
		this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue((double) 0.4F);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
	}
	
	@Override
	protected PathNavigator createNavigator(World world) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, world);
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanSwim(true);
		flyingpathnavigator.setCanEnterDoors(true);
		return flyingpathnavigator;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.6F;
	}
	
	@Override
	public void livingTick() {
		if (this.jukeboxPosition == null || !this.jukeboxPosition.withinDistance(this.getPositionVec(), 3.46D) || this.world.getBlockState(this.jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.partyParrot = false;
			this.jukeboxPosition = null;
		}
		
		super.livingTick();
		this.calculateFlapping();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void setPartying(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.partyParrot = isPartying;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isPartying() {
		return this.partyParrot;
	}
	
	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float)((double)this.flapSpeed + (double)(!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}
		
		this.flapping = (float)((double)this.flapping * 0.9D);
		Vec3d vec3d = this.getMotion();
		if (!this.onGround && vec3d.y < 0.0D) {
			this.setMotion(vec3d.mul(1.0D, 0.6D, 1.0D));
		}
		
		this.flap += this.flapping * 2.0F;
	}
	
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		if (!this.isTamed()) {
			if (!this.isSilent()) {
				this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
			}
			
			if (!this.world.isRemote) {
				if (this.rand.nextInt(10) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.setTamedBy(player);
					this.playTameEffect(true);
					this.world.setEntityState(this, (byte)7);
				} else {
					this.playTameEffect(false);
					this.world.setEntityState(this, (byte)6);
				}
			}
			
			return true;
		} else {
			if (!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player)) {
				this.sitGoal.setSitting(!this.isSitting());
			}
			
			return super.processInteract(player, hand);
		}
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
	}
	
	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}
	
	@Override
	public boolean canMateWith(AnimalEntity otherAnimal) {
		return false;
	}
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageableEntity) {
		return null;
	}
	
	@Override
	public void playAmbientSound() {
		if (!this.isSilent() && world.rand.nextInt(200) == 0) {
			world.playSound(null, this.posX, this.posY, this.posZ, getAmbientSound(), this.getSoundCategory(), 1.0F, getPitch(world.rand));
		}
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
	}
	
	@Override
	protected float playFlySound(float volume) {
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return volume + this.flapSpeed / 2.0F;
	}
	
	@Override
	protected boolean makeFlySound() {
		return true;
	}
	
	@Override
	protected float getSoundPitch() {
		return getPitch(this.rand);
	}
	
	private static float getPitch(Random random) {
		return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
	}
	
	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
	
	@Override
	protected void collideWithEntity(Entity entity) {
		if(!(entity instanceof PlayerEntity)) {
			super.collideWithEntity(entity);
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.isInvulnerableTo(source)) {
			return false;
		}
		else {
			if(this.sitGoal != null) {
				this.sitGoal.setSitting(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	@Override
	protected void registerData() {
		super.registerData();
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	}
	
	public boolean isFlying() {
		return !this.onGround;
	}
}
