package com.laton95.runemysteries.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class ExExParrotEntity extends ParrotEntity {
	
	public float flap;
	
	public float flapSpeed;
	
	public float oFlapSpeed;
	
	public float oFlap;
	
	private float flapping = 1.0F;
	
	private boolean partyParrot;
	
	private BlockPos jukeboxPosition;
	
	public ExExParrotEntity(EntityType<? extends ParrotEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new FlyingMovementController(this, 10, false);
		this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.COCOA, -1.0F);
	}
	
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setVariant(this.rand.nextInt(5));
		if(spawnDataIn == null) {
			spawnDataIn = new AgeableEntity.AgeableData();
			((AgeableEntity.AgeableData) spawnDataIn).func_226259_a_(false);
		}
		
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	protected void registerGoals() {
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(2, this.sitGoal);
		this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		//this.goalSelector.addGoal(3, new LandOnOwnersShoulderGoal(this));
		this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}
	
	protected void registerAttributes() {
		super.registerAttributes();
	}
	
	/**
	 * Returns new PathNavigateGround instance
	 */
	protected PathNavigator createNavigator(World worldIn) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanSwim(true);
		flyingpathnavigator.setCanEnterDoors(true);
		return flyingpathnavigator;
	}
	
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.6F;
	}
	
	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void livingTick() {
		if(this.jukeboxPosition == null || !this.jukeboxPosition.withinDistance(this.getPositionVec(), 3.46D) || this.world.getBlockState(this.jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.partyParrot = false;
			this.jukeboxPosition = null;
		}
		
		super.livingTick();
		this.calculateFlapping();
	}
	
	/**
	 * Called when a record starts or stops playing. Used to make parrots start or stop partying.
	 */
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
		this.flapSpeed = (float) ((double) this.flapSpeed + (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		if(!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}
		
		this.flapping = (float) ((double) this.flapping * 0.9D);
		Vec3d vec3d = this.getMotion();
		if(!this.onGround && vec3d.y < 0.0D) {
			this.setMotion(vec3d.mul(1.0D, 0.6D, 1.0D));
		}
		
		this.flap += this.flapping * 2.0F;
	}
	
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if(itemstack.getItem() instanceof SpawnEggItem) {
			return super.processInteract(player, hand);
		}
		else if(!this.world.isRemote && !this.isTamed()) {
			if(!net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
				this.setTamedBy(player);
				this.world.setEntityState(this, (byte) 7);
			}
			else {
				this.world.setEntityState(this, (byte) 6);
			}
			
			return true;
		}
		else if(!this.isFlying() && this.isTamed() && this.isOwner(player)) {
			if(!this.world.isRemote) {
				this.sitGoal.setSitting(!this.isSitting());
			}
			
			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}
	
	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}
	
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}
	
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}
	
	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	public boolean canMateWith(AnimalEntity otherAnimal) {
		return false;
	}
	
	@Nullable
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}
	
	@Nullable
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
	}
	
	protected float playFlySound(float volume) {
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return volume + this.flapSpeed / 2.0F;
	}
	
	protected boolean makeFlySound() {
		return true;
	}
	
	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return getPitch(this.rand);
	}
	
	private static float getPitch(Random random) {
		return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
	}
	
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed() {
		return true;
	}
	
	protected void collideWithEntity(Entity entityIn) {
		if(!(entityIn instanceof PlayerEntity)) {
			super.collideWithEntity(entityIn);
		}
	}
	
	/**
	 * Called when the entity is attacked.
	 */
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
	
	protected void registerData() {
		super.registerData();
	}
	
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	}
	
	public boolean isFlying() {
		return !this.onGround;
	}
}
