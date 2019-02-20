package com.laton95.runemysteries.block;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFourWay;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;

public class BlockRailing extends BlockFourWay {
	
	public static final BooleanProperty UP = BlockStateProperties.UP;
	
	private final boolean isTranslucent;
	
	private final VoxelShape[] shape = this.func_196408_a(3.0F, 2.0F, 16.0F, 0.0F, 14.0F);
	
	private final VoxelShape[] collisionShape = this.func_196408_a(3.0F, 2.0F, 19.0F, 0.0F, 19.0F);
	
	public BlockRailing(Block block) {
		this( block, false);
	}
	
	public BlockRailing(Block block, boolean isTranslucent) {
		super(0.0F, 2.0F, 0.0F, 14.0F, 19.0F, Properties.from(block));
		this.setDefaultState((this.stateContainer.getBaseState()).with(UP, true).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
		this.isTranslucent = isTranslucent;
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.get(UP) ? this.shape[this.getIndex(state)] : super.getShape(state, worldIn, pos);
	}
	
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.get(UP) ? this.collisionShape[this.getIndex(state)] : super.getCollisionShape(state, worldIn, pos);
	}
	
	@Override
	public boolean canBeConnectedTo(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing) {
		IBlockState other = world.getBlockState(pos.offset(facing));
		return attachesTo(other, other.getBlockFaceShape(world, pos.offset(facing), facing.getOpposite()));
	}
	
	private boolean attachesTo(IBlockState state, BlockFaceShape faceShape) {
		Block block = state.getBlock();
		boolean flag = faceShape == BlockFaceShape.MIDDLE_POLE_THICK || faceShape == BlockFaceShape.MIDDLE_POLE && block instanceof BlockFenceGate;
		return !isExceptBlockForAttachWithPiston(block) && faceShape == BlockFaceShape.SOLID || flag;
	}
	
	public static boolean isExceptBlockForAttachWithPiston(Block block) {
		return Block.isExceptBlockForAttachWithPiston(block) || block == Blocks.BARRIER || block == Blocks.MELON || block == Blocks.PUMPKIN || block == Blocks.CARVED_PUMPKIN || block == Blocks.JACK_O_LANTERN || block == Blocks.FROSTED_ICE || block == Blocks.TNT;
	}
	
	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if(stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		
		if(facing == EnumFacing.DOWN) {
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
		else {
			boolean connectNorth = facing == EnumFacing.NORTH ? this.attachesTo(facingState, facingState.getBlockFaceShape(worldIn, facingPos, facing.getOpposite())) : stateIn.get(NORTH);
			boolean connectEast = facing == EnumFacing.EAST ? this.attachesTo(facingState, facingState.getBlockFaceShape(worldIn, facingPos, facing.getOpposite())) : stateIn.get(EAST);
			boolean connectSouth = facing == EnumFacing.SOUTH ? this.attachesTo(facingState, facingState.getBlockFaceShape(worldIn, facingPos, facing.getOpposite())) : stateIn.get(SOUTH);
			boolean connectWest = facing == EnumFacing.WEST ? this.attachesTo(facingState, facingState.getBlockFaceShape(worldIn, facingPos, facing.getOpposite())) : stateIn.get(WEST);
			boolean isStraight = (!connectNorth || connectEast || !connectSouth || connectWest) && (connectNorth || !connectEast || connectSouth || !connectWest);
			return stateIn.with(UP, isStraight || !worldIn.isAirBlock(currentPos.up())).with(NORTH, connectNorth).with(EAST, connectEast).with(SOUTH, connectSouth).with(WEST, connectWest);
		}
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return face == EnumFacing.UP || face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.MIDDLE_POLE_THICK;
	}
	
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IWorldReaderBase iworldreaderbase = context.getWorld();
		BlockPos blockpos = context.getPos();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		boolean connectNorth = canWallConnectTo(iworldreaderbase, blockpos, EnumFacing.NORTH);
		boolean connectEast = canWallConnectTo(iworldreaderbase, blockpos, EnumFacing.EAST);
		boolean connectSouth = canWallConnectTo(iworldreaderbase, blockpos, EnumFacing.SOUTH);
		boolean connectWest = canWallConnectTo(iworldreaderbase, blockpos, EnumFacing.WEST);
		boolean isStraight = (!connectNorth || connectEast || !connectSouth || connectWest) && (connectNorth || !connectEast || connectSouth || !connectWest);
		return this.getDefaultState().with(UP, isStraight || !iworldreaderbase.isAirBlock(blockpos.up())).with(NORTH, connectNorth).with(EAST, connectEast).with(SOUTH, connectSouth).with(WEST, connectWest).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	private boolean canWallConnectTo(IBlockReader world, BlockPos pos, EnumFacing facing) {
		BlockPos off = pos.offset(facing);
		IBlockState other = world.getBlockState(off);
		return other.canBeConnectedTo(world, off, facing.getOpposite()) || attachesTo(other, other.getBlockFaceShape(world, off, facing.getOpposite()));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(UP, NORTH, EAST, WEST, SOUTH, WATERLOGGED);
	}
	
	public BlockRenderLayer getRenderLayer() {
		return isTranslucent ? BlockRenderLayer.TRANSLUCENT : super.getRenderLayer();
	}
}
