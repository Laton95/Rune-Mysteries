package com.laton95.runemysteries.block;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAltarEntrance extends ModBlock implements IBucketPickupHandler, ILiquidContainer {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape shape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	
	private final EnumRuneType runeType;
	
	public BlockAltarEntrance(EnumRuneType runeType) {
		super(runeType.toString().toLowerCase() + "_altar_entrance", Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F), false, false);
		this.runeType = runeType;
		this.setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
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
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if(player.getHeldItemMainhand().getItem() == runeType.getTalisman() || player.getHeldItemOffhand().getItem() == runeType.getTalisman()) {
				player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.ALTAR_ENTER));
				
				//TODO add teleport
				//TeleportHelper.teleportEntity(player, runeType.getTempleDimId(), runeType.getTempleEntrancePoint());
			}
			else {
				player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.ALTAR_INTERACT));
			}
		}
		return true;
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public IFluidState getFluidState(IBlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(WATERLOGGED);
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
}
