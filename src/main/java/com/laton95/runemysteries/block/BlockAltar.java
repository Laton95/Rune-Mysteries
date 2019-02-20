package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.capability.CapabilityRejected;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.init.ModPotions;
import com.laton95.runemysteries.inventory.InventoryAltar;
import com.laton95.runemysteries.item.crafting.AltarRecipe;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.state.properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBubbleColumn;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BlockAltar extends ModBlock implements IBucketPickupHandler, ILiquidContainer {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final EnumProperty CORNER = ModBlockStateProperties.CORNER;
	
	private static final VoxelShape shape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	
	private static final int bubbleTickRate = 20;
	
	private final EnumRuneType runeType;
	
	public BlockAltar(EnumRuneType runeType) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F));
		this.runeType = runeType;
		this.setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if(stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		
		return stateIn.with(CORNER, getCorner(worldIn, currentPos));
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, bubbleTickRate);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		if(face == EnumFacing.UP) {
			return BlockFaceShape.UNDEFINED;
		}
		return super.getBlockFaceShape(worldIn, state, pos, face);
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return shape;
	}
	
	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double blockCenterX = pos.getX() + 0.5D;
		double blockCenterY = pos.getY() + 1D;
		double blockCenterZ = pos.getZ() + 0.5D;
		
		IParticleData particle;
		
		if(!runeType.isOurania()) {
			particle = runeType.getRuneColor();
		}
		else {
			particle = EnumRuneType.getRandomType(rand).getRuneColor();
		}
		
		float xSpeed = 0;
		float ySpeed = 0;
		float zSpeed = 0;
		
		double xOffset = rand.nextFloat() - 0.5f;
		double zOffset = rand.nextFloat() - 0.5f;
		
		worldIn.spawnParticle(particle, blockCenterX + xOffset, blockCenterY, blockCenterZ + zOffset, xSpeed, ySpeed, zSpeed);
	}
	
	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
			IBlockState iblockstate = worldIn.getBlockState(pos.offset(enumfacing));
			Block block = iblockstate.getBlock();
			if(block == this && iblockstate.get(CORNER) != EnumCorner.NONE) {
				return false;
			}
		}
		
		return true;
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return getDefaultState().with(CORNER, getCorner(context.getWorld(), context.getPos())).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(!worldIn.isRemote && entityIn instanceof EntityItem && !entityIn.removed && !isRejected((EntityItem) entityIn)) {
			EntityItem item = (EntityItem) entityIn;
			ItemStack stack = item.getItem();
			
			UUID uuid = item.getThrowerId();
			
			if(!stack.isEmpty()) {
				if(runeType.isOurania() && stack.getItem() == ModItems.RUNE_ESSENCE) {
					grantAdvancements(worldIn, uuid, item);
					stack.shrink(1);
					ItemStack output = new ItemStack(EnumRuneType.getRandomType(worldIn.rand).getRune(), getMultiplier(worldIn, uuid, item));
					EntityItem outputItem = new EntityItem(worldIn, item.posX, item.posY, item.posZ, output);
					outputItem.setThrowerId(uuid);
					worldIn.spawnEntity(outputItem);
				}
				else {
					InventoryAltar inventory = new InventoryAltar(stack, runeType);
					AltarRecipe recipe = worldIn.getRecipeManager().getRecipe(inventory, worldIn, AltarRecipe.RUNE_ALTAR_TYPE);
					if(recipe != null) {
						grantAdvancements(worldIn, uuid, item);
						stack.shrink(1);
						ItemStack output = recipe.getCraftingResult(inventory);
						output.setCount(output.getCount() * getMultiplier(worldIn, uuid, item));
						EntityItem outputItem = new EntityItem(worldIn, item.posX, item.posY, item.posZ, output);
						outputItem.setThrowerId(uuid);
						worldIn.spawnEntity(outputItem);
					}
					else {
						setRejected((EntityItem) entityIn);
					}
				}
			}
		}
	}
	
	private boolean isRejected(EntityItem item) {
		CapabilityRejected.Rejected cap = item.getCapability(CapabilityRejected.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
		return cap.isRejected();
	}
	
	private void setRejected(EntityItem item) {
		CapabilityRejected.Rejected cap = item.getCapability(CapabilityRejected.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
		cap.setRejected();
	}
	
	@Override
	public void onBlockAdded(IBlockState state, World worldIn, BlockPos pos, IBlockState oldState) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, bubbleTickRate);
	}
	
	private void grantAdvancements(World worldIn, UUID uuid, EntityItem item) {
		if(uuid == null) {
			return;
		}
		
		EntityPlayer entity = worldIn.getPlayerEntityByUUID(uuid);
		if(entity != null) {
			ModCriteriaTriggers.CRAFT_RUNE.trigger((EntityPlayerMP) entity);
			if(runeType.isOurania()) {
				ModCriteriaTriggers.OURANIA.trigger((EntityPlayerMP) entity);
			}
		}
		else {
			for(EntityPlayerMP player : worldIn.getEntitiesWithinAABB(EntityPlayerMP.class, item.getBoundingBox().grow(20.0D))) {
				ModCriteriaTriggers.DOLPHIN_CRAFT.trigger(player);
			}
		}
	}
	
	private int getMultiplier(World worldIn, UUID uuid, EntityItem item) {
		if(uuid == null) {
			return 1;
		}
		
		EntityPlayer entity = worldIn.getPlayerEntityByUUID(uuid);
		if(entity != null && ModPotions.STONETOUCHER.hasEffect(entity) && item.getItem().getItem() == ModItems.RUNE_ESSENCE) {
			return 2;
		}
		
		return 1;
	}
	
	@Override
	public IFluidState getFluidState(IBlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(CORNER, WATERLOGGED);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(StringReference.BlockTooltip.ALTAR_TOOLTIP).applyTextStyle(TextFormatting.GRAY));
	}
	
	private EnumCorner getCorner(IWorld worldIn, BlockPos pos) {
		boolean hasNorth = worldIn.getBlockState(pos.north()).getBlock() == this;
		boolean hasSouth = worldIn.getBlockState(pos.south()).getBlock() == this;
		boolean hasWest = worldIn.getBlockState(pos.west()).getBlock() == this;
		boolean hasEast = worldIn.getBlockState(pos.east()).getBlock() == this;
		
		EnumCorner corner = EnumCorner.NONE;
		
		if(hasNorth && hasWest) {
			corner = EnumCorner.SOUTH_EAST;
		}
		
		if(hasNorth && hasEast) {
			corner = EnumCorner.SOUTH_WEST;
		}
		
		if(hasSouth && hasWest) {
			corner = EnumCorner.NORTH_EAST;
		}
		
		if(hasSouth && hasEast) {
			corner = EnumCorner.NORTH_WEST;
		}
		
		return corner;
	}
	
	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, IBlockState state) {
		if(state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, false), 3);
			return Fluids.WATER;
		}
		else {
			return Fluids.EMPTY;
		}
	}
	
	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, IBlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, IBlockState state, IFluidState fluidStateIn) {
		if(!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if(!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, true), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if(state.get(WATERLOGGED)) {
			BlockBubbleColumn.placeBubbleColumn(worldIn, pos.up(), true);
		}
	}
}
