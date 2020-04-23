package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.capability.RejectedCapability;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModEffects;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.inventory.crafting.RuneCraftingInventory;
import com.laton95.runemysteries.item.crafting.AltarRecipe;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.state.properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AltarBlock extends Block implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final EnumProperty CORNER = ModBlockStateProperties.CORNER;
	
	private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	
	private final EnumRuneType runeType;
	
	public AltarBlock(EnumRuneType runeType) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F));
		this.runeType = runeType;
		this.setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if(state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return state.with(CORNER, getCorner(world, currentPos));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		for(Direction direction : Direction.Plane.HORIZONTAL) {
			BlockState adjacentState = world.getBlockState(pos.offset(direction));
			Block block = adjacentState.getBlock();
			if(block == this && adjacentState.get(CORNER) != EnumCorner.NONE) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return getDefaultState().with(CORNER, getCorner(context.getWorld(), context.getPos())).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if(!world.isRemote && entity instanceof ItemEntity && !entity.removed && !isRejected((ItemEntity) entity)) {
			ItemEntity item = (ItemEntity) entity;
			ItemStack stack = item.getItem();
			
			UUID uuid = item.getThrowerId();
			
			if(!stack.isEmpty()) {
				if(runeType.isOurania() && stack.getItem() == ModItems.RUNE_ESSENCE) {
					grantAdvancements(world, uuid, item);
					stack.shrink(1);
					ItemStack output = new ItemStack(EnumRuneType.getRandomType(world.rand).getRune(), getMultiplier(world, uuid, item));
					ItemEntity outputItem = new ItemEntity(world, item.getPosX(), item.getPosY(), item.getPosZ(), output);
					outputItem.setThrowerId(uuid);
					world.addEntity(outputItem);
				}
				else {
					AltarRecipe recipe = getRecipe(world, stack, runeType);
					if(recipe != null) {
						grantAdvancements(world, uuid, item);
						stack.shrink(1);
						ItemStack output = recipe.getCraftingResult();
						output.setCount(output.getCount() * getMultiplier(world, uuid, item));
						ItemEntity outputItem = new ItemEntity(world, item.getPosX(), item.getPosY(), item.getPosZ(), output);
						outputItem.setThrowerId(uuid);
						world.addEntity(outputItem);
					}
					else {
						setRejected((ItemEntity) entity);
					}
				}
			}
		}
	}
	
	private AltarRecipe getRecipe(World world, ItemStack input, EnumRuneType runeType) {
		RuneCraftingInventory inventory = new RuneCraftingInventory(input, runeType);
		Optional<AltarRecipe> recipe = world.getRecipeManager().getRecipe(AltarRecipe.RUNE_ALTAR_RECIPE, inventory, world);
		return recipe.orElse(null);
	}
	
	private boolean isRejected(ItemEntity item) {
		RejectedCapability.Rejected cap = item.getCapability(RejectedCapability.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
		return cap.isRejected();
	}
	
	private void setRejected(ItemEntity item) {
		RejectedCapability.Rejected cap = item.getCapability(RejectedCapability.REJECTED).orElseThrow(() -> new RuntimeException("Did not find capability"));
		cap.setRejected();
	}
	
	private void grantAdvancements(World world, UUID uuid, ItemEntity item) {
		if(uuid == null) {
			return;
		}
		
		PlayerEntity entity = world.getPlayerByUuid(uuid);
		if(entity != null) {
			ModCriteriaTriggers.CRAFT_RUNE.trigger((ServerPlayerEntity) entity);
			if(runeType.isOurania()) {
				ModCriteriaTriggers.OURANIA.trigger((ServerPlayerEntity) entity);
			}
		}
		else {
			for(ServerPlayerEntity player : world.getEntitiesWithinAABB(ServerPlayerEntity.class, item.getBoundingBox().grow(20.0D))) {
				ModCriteriaTriggers.DOLPHIN_CRAFT.trigger(player);
			}
		}
	}
	
	private int getMultiplier(World world, UUID uuid, ItemEntity item) {
		if(uuid == null) {
			return 1;
		}
		
		PlayerEntity entity = world.getPlayerByUuid(uuid);
		if(entity != null && ModEffects.STONETOUCHER.hasEffect(entity) && item.getItem().getItem() == ModItems.RUNE_ESSENCE) {
			return 2;
		}
		
		return 1;
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CORNER, WATERLOGGED);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent(StringReference.BlockTooltip.ALTAR_TOOLTIP).applyTextStyle(TextFormatting.GRAY));
	}
	
	private EnumCorner getCorner(IWorld world, BlockPos pos) {
		boolean hasNorth = world.getBlockState(pos.north()).getBlock() == this;
		boolean hasSouth = world.getBlockState(pos.south()).getBlock() == this;
		boolean hasWest = world.getBlockState(pos.west()).getBlock() == this;
		boolean hasEast = world.getBlockState(pos.east()).getBlock() == this;
		
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
}
