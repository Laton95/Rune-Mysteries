package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class RailingBlock extends FourWayBlock {
	
	public static final BooleanProperty UP = BlockStateProperties.UP;
	
	private final boolean isTranslucent;
	
	private final VoxelShape[] shape = this.makeShapes(3.0F, 2.0F, 16.0F, 0.0F, 14.0F);
	
	private final VoxelShape[] collisionShapes = this.makeShapes(3.0F, 2.0F, 19.0F, 0.0F, 19.0F);
	
	public RailingBlock(Block block) {
		this(block, false);
	}
	
	public RailingBlock(Block block, boolean isTranslucent) {
		super(0.0F, 2.0F, 0.0F, 14.0F, 19.0F, Properties.from(block));
		this.setDefaultState((this.stateContainer.getBaseState()).with(UP, true).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
		this.isTranslucent = isTranslucent;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return state.get(UP) ? this.shape[this.getIndex(state)] : super.getShape(state, world, pos, context);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return state.get(UP) ? this.collisionShapes[this.getIndex(state)] : super.getCollisionShape(state, world, pos, context);
		
	}
	
	@Override
	public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
		return false;
	}
	
	private boolean connectsTo(BlockState state, boolean isSolid, Direction face) {
		Block block = state.getBlock();
		return !cannotAttach(block) && isSolid || block instanceof RailingBlock;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IWorldReader world = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = context.getWorld().getFluidState(context.getPos());
		BlockPos northPos = pos.north();
		BlockPos eastPos = pos.east();
		BlockPos southPos = pos.south();
		BlockPos westPos = pos.west();
		BlockState northState = world.getBlockState(northPos);
		BlockState eastState = world.getBlockState(eastPos);
		BlockState southState = world.getBlockState(southPos);
		BlockState westState = world.getBlockState(westPos);
		boolean connectNorth = connectsTo(northState, northState.isSolidSide(world, northPos, Direction.SOUTH), Direction.SOUTH);
		boolean connectEast = connectsTo(eastState, eastState.isSolidSide(world, eastPos, Direction.WEST), Direction.WEST);
		boolean connectSouth = connectsTo(southState, southState.isSolidSide(world, southPos, Direction.NORTH), Direction.NORTH);
		boolean connectWest = connectsTo(westState, westState.isSolidSide(world, westPos, Direction.EAST), Direction.EAST);
		boolean hasUp = (!connectNorth || connectEast || !connectSouth || connectWest) && (connectNorth || !connectEast || connectSouth || !connectWest);
		
		return this.getDefaultState().with(UP, hasUp || !world.isAirBlock(pos.up())).with(NORTH, connectNorth).with(EAST, connectEast).with(SOUTH, connectSouth).with(WEST, connectWest).with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if(state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		if(facing == Direction.DOWN) {
			return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		}
		else {
			Direction opposite = facing.getOpposite();
			boolean connectsNorth = facing == Direction.NORTH ? connectsTo(facingState, facingState.isSolidSide(world, facingPos, opposite), opposite) : state.get(NORTH);
			boolean connectsEast = facing == Direction.EAST ? connectsTo(facingState, facingState.isSolidSide(world, facingPos, opposite), opposite) : state.get(EAST);
			boolean connectsSouth = facing == Direction.SOUTH ? connectsTo(facingState, facingState.isSolidSide(world, facingPos, opposite), opposite) : state.get(SOUTH);
			boolean connectsWest = facing == Direction.WEST ? connectsTo(facingState, facingState.isSolidSide(world, facingPos, opposite), opposite) : state.get(WEST);
			boolean hasUp = (!connectsNorth || connectsEast || !connectsSouth || connectsWest) && (connectsNorth || !connectsEast || connectsSouth || !connectsWest);
			return state.with(UP, hasUp || !world.isAirBlock(currentPos.up())).with(NORTH, connectsNorth).with(EAST, connectsEast).with(SOUTH, connectsSouth).with(WEST, connectsWest);
		}
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(UP, NORTH, EAST, WEST, SOUTH, WATERLOGGED);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return isTranslucent ? BlockRenderType.INVISIBLE : super.getRenderType(state);
	}
}
