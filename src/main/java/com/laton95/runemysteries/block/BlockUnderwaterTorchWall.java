package com.laton95.runemysteries.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Fluids;
import net.minecraft.init.Particles;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class BlockUnderwaterTorchWall extends BlockUnderwaterTorch {
	
	public static final DirectionProperty HORIZONTAL_FACING = BlockHorizontal.HORIZONTAL_FACING;
	private static final Map<EnumFacing, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(EnumFacing.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), EnumFacing.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), EnumFacing.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), EnumFacing.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
	
	public BlockUnderwaterTorchWall() {
		super("underwater_wall_torch");
		this.setDefaultState((this.stateContainer.getBaseState()).with(HORIZONTAL_FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
	}
	
	public String getTranslationKey() {
		return this.asItem().getTranslationKey();
	}
	
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPES.get(state.get(HORIZONTAL_FACING));
	}
	
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		IFluidState ifluidstate = worldIn.getFluidState(pos);
		EnumFacing enumfacing = state.get(HORIZONTAL_FACING);
		BlockPos blockpos = pos.offset(enumfacing.getOpposite());
		IBlockState iblockstate = worldIn.getBlockState(blockpos);
		return iblockstate.getBlockFaceShape(worldIn, blockpos, enumfacing) == BlockFaceShape.SOLID && !isExceptBlockForAttachWithPiston(iblockstate.getBlock()) && ifluidstate.getFluid() == Fluids.WATER;
	}
	
	@Nullable
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		IBlockState iblockstate = this.getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
		IWorldReaderBase iworldreaderbase = context.getWorld();
		BlockPos blockpos = context.getPos();
		EnumFacing[] aenumfacing = context.getNearestLookingDirections();
		
		for(EnumFacing enumfacing : aenumfacing) {
			if (enumfacing.getAxis().isHorizontal()) {
				EnumFacing enumfacing1 = enumfacing.getOpposite();
				iblockstate = iblockstate.with(HORIZONTAL_FACING, enumfacing1);
				if (iblockstate.isValidPosition(iworldreaderbase, blockpos)) {
					return iblockstate;
				}
			}
		}
		
		return null;
	}
	
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		EnumFacing enumfacing = stateIn.get(HORIZONTAL_FACING);
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		EnumFacing enumfacing1 = enumfacing.getOpposite();
		worldIn.spawnParticle(Particles.BUBBLE, d0 + 0.27D * (double)enumfacing1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getZOffset(), 0.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(Particles.BUBBLE_COLUMN_UP, d0 + 0.27D * (double)enumfacing1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getZOffset(), 0.0D, 0.0D, 0.0D);
	}
	
	public IBlockState rotate(IBlockState state, Rotation rot) {
		return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
	}
	
	public IBlockState mirror(IBlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		super.fillStateContainer(builder.add(HORIZONTAL_FACING));
	}
}
