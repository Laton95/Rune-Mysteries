package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModEffects;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.state.properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElderCatalystBlock extends Block {
	
	public static final EnumProperty CORNER = ModBlockStateProperties.CORNER;
	
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	
	private static final VoxelShape[] topShapes = new VoxelShape[] {
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 14.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 0.0D, 16.0D, 12.0D, 14.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 14.0D, 12.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			};
	
	private static final VoxelShape[] bottomShapes = new VoxelShape[] {
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 14.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 0.0D, 16.0D, 16.0D, 14.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 14.0D, 16.0D, 14.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			};
	
	public ElderCatalystBlock() {
		super(Properties.create(Material.ROCK).tickRandomly().hardnessAndResistance(50.0F, 1200.0F));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CORNER, TOP);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		int cornerIndex = ((EnumCorner) state.get(CORNER)).ordinal();
		return state.get(TOP) ? topShapes[cornerIndex] : bottomShapes[cornerIndex];
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IWorld world = context.getWorld();
		BlockPos pos = context.getPos();
		return canPlace(context) ? this.getDefaultState().with(CORNER, getCorner(world, pos)).with(TOP, isTop(world, pos)) : null;
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if(!world.isRemote) {
			Direction facing = placer != null ? placer.getHorizontalFacing() : Direction.NORTH;
			
			for(int dx = 0; dx < 2; dx++) {
				for(int dy = 0; dy < 2; dy++) {
					for(int dz = 0; dz < 2; dz++) {
						BlockPos setPos = pos.up(dy).offset(facing, dx).offset(facing.rotateY(), dz);
						world.setBlockState(setPos, getDefaultState(), 2);
					}
				}
			}
		}
	}
	
	private boolean canPlace(BlockItemUseContext context) {
		BlockPos pos = context.getPos();
		
		for(int dx = 0; dx < 2; dx++) {
			for(int dy = 0; dy < 2; dy++) {
				for(int dz = 0; dz < 2; dz++) {
					BlockPos checkPos = pos.up(dy).offset(context.getPlacementHorizontalFacing(), dx).offset(context.getPlacementHorizontalFacing().rotateY(), dz);
					if(!context.getWorld().getBlockState(checkPos).isReplaceable(context)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		return state.with(CORNER, getCorner(world, currentPos)).with(TOP, isTop(world, currentPos));
	}
	
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onReplaced(state, world, pos, newState, isMoving);
		
		if(state.getBlock() != newState.getBlock()) {
			for(Direction direction : Direction.values()) {
				if(world.getBlockState(pos.offset(direction)).getBlock() == this) {
					world.destroyBlock(pos.offset(direction), false);
				}
			}
		}
	}
	
	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
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
	
	private boolean isTop(IWorld world, BlockPos pos) {
		return world.getBlockState(pos.up()).getBlock() != this;
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		List<BlockPos> stonePositions = new ArrayList<>();
		
		int radius = 10;
		for(int y = -radius; y < radius; y++) {
			for(int x = -radius; x < radius; x++) {
				for(int z = -radius; z < radius; z++) {
					BlockPos curPos = pos.add(x, y, z);
					if(world.getBlockState(curPos) == Blocks.STONE.getDefaultState()) {
						stonePositions.add(curPos);
					}
				}
			}
		}
		
		if(stonePositions.size() > 0) {
			BlockPos essPos = stonePositions.get(random.nextInt(stonePositions.size()));
			
			world.setBlockState(essPos, ModBlocks.RUNE_ESSENCE_BLOCK.getDefaultState().with(RuneEssenceBlock.INFINITE, false));
		}
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(!worldIn.isRemote) {
			ModCriteriaTriggers.ELDER_CATALYST.trigger((ServerPlayerEntity) player);
			
			player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.ELDER_CATALYST_INTERACT, player.getDisplayName()));
			
			player.addPotionEffect(new EffectInstance(ModEffects.STONETOUCHER, 20 * Config.stonetoucherDuration, 0, false, true));
		}
		
		return ActionResultType.SUCCESS;
	}
}
