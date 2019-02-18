package com.laton95.runemysteries.entity.passive;

import com.laton95.runemysteries.init.ModEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityShoulderRiding;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityExExParrot extends EntityShoulderRiding implements IFlyingAnimal {
	
	public float flap;
	
	public float flapSpeed;
	
	public float oFlapSpeed;
	
	public float oFlap;
	
	public float flapping = 1.0F;
	
	private boolean partyParrot;
	
	private BlockPos jukeboxPosition;
	
	public EntityExExParrot(World worldIn) {
		super(ModEntities.EX_EX_PARROT, worldIn);
		this.setSize(0.5F, 0.9F);
		this.moveHelper = new EntityFlyHelper(this);
	}
	
	protected void initEntityAI() {
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(2, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
		this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
	}
	
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue((double) 0.4F);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
	}
	
	/**
	 * Returns new PathNavigateGround instance
	 */
	protected PathNavigate createNavigator(World worldIn) {
		PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
		pathnavigateflying.setCanOpenDoors(false);
		pathnavigateflying.setCanSwim(true);
		pathnavigateflying.setCanEnterDoors(true);
		return pathnavigateflying;
	}
	
	@Nullable
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE;
	}
	
	@Nullable
	protected ResourceLocation getLootTable() {
		return LootTableList.ENTITIES_PARROT;
	}
	
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData entityLivingData, @Nullable NBTTagCompound itemNbt) {
		return super.onInitialSpawn(difficulty, entityLivingData, itemNbt);
	}
	
	public boolean attackEntityAsMob(Entity entityIn) {
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}
	
	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void livingTick() {
		if(this.jukeboxPosition == null || this.jukeboxPosition.distanceSq(this.posX, this.posY, this.posZ) > 12.0D || this.world.getBlockState(this.jukeboxPosition).getBlock() != Blocks.JUKEBOX) {
			this.partyParrot = false;
			this.jukeboxPosition = null;
		}
		
		super.livingTick();
		this.calculateFlapping();
	}
	
	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float) ((double) this.flapSpeed + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		if(!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}
		
		this.flapping = (float) ((double) this.flapping * 0.9D);
		if(!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		
		this.flap += this.flapping * 2.0F;
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.isInvulnerableTo(source)) {
			return false;
		}
		else {
			if(this.aiSit != null) {
				this.aiSit.setSitting(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	public boolean canSpawn(IWorld worldIn) {
		return false;
	}
	
	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}
	
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if(!this.isTamed()) {
			if(!this.world.isRemote) {
				if(this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.setTamedBy(player);
					this.playTameEffect(true);
					this.world.setEntityState(this, (byte) 7);
				}
				else {
					this.playTameEffect(false);
					this.world.setEntityState(this, (byte) 6);
				}
			}
			
			return true;
		}
		else {
			if(!this.world.isRemote && !this.isFlying() && this.isTamed() && this.isOwner(player)) {
				this.aiSit.setSitting(!this.isSitting());
			}
			
			return super.processInteract(player, hand);
		}
	}
	
	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	public boolean canMateWith(EntityAnimal otherAnimal) {
		return false;
	}
	
	public boolean isFlying() {
		return !this.onGround;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isPartying() {
		return this.partyParrot;
	}
	
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	public void fall(float distance, float damageMultiplier) {
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
	
	protected void collideWithEntity(Entity entityIn) {
		if(!(entityIn instanceof EntityPlayer)) {
			super.collideWithEntity(entityIn);
		}
	}
	
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed() {
		return true;
	}
	
	/**
	 * Called when a record starts or stops playing. Used to make parrots start or stop partying.
	 */
	@OnlyIn(Dist.CLIENT)
	public void setPartying(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.partyParrot = isPartying;
	}
	
	@Nullable
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
	
	protected void playStepSound(BlockPos pos, IBlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
	}
	
	protected float playFlySound(float volume) {
		this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
		return volume + this.flapSpeed / 2.0F;
	}
	
	protected boolean makeFlySound() {
		return true;
	}
	
	public float getEyeHeight() {
		return this.height * 0.6F;
	}
	
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	protected void registerData() {
		super.registerData();
	}
	
	/**
	 * Writes the extra NBT data specific to this type of entity. Should <em>not</em> be called from outside this class;
	 * use {@link #writeUnlessPassenger} or {@link #writeWithoutTypeId} instead.
	 */
	public void writeAdditional(NBTTagCompound compound) {
		super.writeAdditional(compound);
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(NBTTagCompound compound) {
		super.readAdditional(compound);
	}
}
