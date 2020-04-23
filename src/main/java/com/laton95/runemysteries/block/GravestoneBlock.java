package com.laton95.runemysteries.block;

import com.laton95.runemysteries.tileentity.GravestoneTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GravestoneBlock extends ContainerBlock implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public static final VoxelShape PLINTH_SHAPE_Z = Block.makeCuboidShape(0, 0, 4, 16, 3, 12);
	
	public static final VoxelShape PLINTH_SHAPE_X = Block.makeCuboidShape(4, 0, 0, 12, 3, 16);
	
	public static final VoxelShape HEADSTONE_SHAPE_Z = Block.makeCuboidShape(1, 3, 5, 15, 14, 11);
	
	public static final VoxelShape HEADSTONE_SHAPE_X = Block.makeCuboidShape(5, 3, 1, 11, 14, 15);
	
	public static final VoxelShape HEADSTONE_TOP_SHAPE_Z = Block.makeCuboidShape(3, 14, 5, 13, 16, 11);
	
	public static final VoxelShape HEADSTONE_TOP_SHAPE_X = Block.makeCuboidShape(5, 14, 3, 11, 16, 13);
	
	public static final VoxelShape X_SHAPE = VoxelShapes.or(PLINTH_SHAPE_X, HEADSTONE_SHAPE_X, HEADSTONE_TOP_SHAPE_X);
	
	public static final VoxelShape Z_SHAPE = VoxelShapes.or(PLINTH_SHAPE_Z, HEADSTONE_SHAPE_Z, HEADSTONE_TOP_SHAPE_Z);
	
	public GravestoneBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f));
		this.setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Direction facing = state.get(FACING);
		return facing.getAxis() == Direction.Axis.X ? X_SHAPE : Z_SHAPE;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction facing = context.getPlacementHorizontalFacing().getOpposite();
		IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
		
		return getDefaultState().with(FACING, facing).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
	
	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new GravestoneTileEntity();
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState p_149645_1_) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		GravestoneTileEntity gravestone = (GravestoneTileEntity) world.getTileEntity(pos);
		if(gravestone != null) {
			if(!world.isRemote) {
				ITextComponent text = gravestone.getText();
				if(text.getString().length() > 0) {
					player.sendMessage(text);
				}
			}
		}
		return ActionResultType.SUCCESS;
	}
}
