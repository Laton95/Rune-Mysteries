package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.entity.passive.EntityExExParrot;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.reference.StringReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBlackMonolith extends ModBlock implements IBucketPickupHandler, ILiquidContainer {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
	
	private static final VoxelShape Z_AXIS_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 6.0D, 14.0D, 16.0D, 10.0D);
	
	private static final VoxelShape X_AXIS_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 2.0D, 10.0D, 16.0D, 14.0D);
	
	public BlockBlackMonolith() {
		super("black_monolith", Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F));
		this.setDefaultState(stateContainer.getBaseState().with(FACING, EnumFacing.NORTH).with(WATERLOGGED, false));
	}
	
	@Override
	public IBlockState rotate(IBlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		switch(face) {
			case NORTH:
				return BlockFaceShape.UNDEFINED;
			case SOUTH:
				return BlockFaceShape.UNDEFINED;
			case WEST:
				return BlockFaceShape.UNDEFINED;
			case EAST:
				return BlockFaceShape.UNDEFINED;
			default:
				return BlockFaceShape.SOLID;
		}
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		EnumFacing enumfacing = state.get(FACING);
		return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ModCriteriaTriggers.TOUCH_MONOLITH.trigger((EntityPlayerMP) player);
			
			if(player.getHeldItem(hand).getItem() == ModItems.EX_PARROT) {
				worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, (double) pos.getX(), (double) pos.getY() + 1.0f, (double) pos.getZ(), true));
				ModCriteriaTriggers.EX_PARROT.trigger((EntityPlayerMP) player);
				if(!player.isCreative()) {
					player.getHeldItem(hand).shrink(1);
				}
				
				EntityExExParrot parrot = new EntityExExParrot(worldIn);
				parrot.setTamedBy(player);
				parrot.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				worldIn.spawnEntity(parrot);
			}
			else {
				player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.MONOLITH_INTERACT));
				worldIn.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_AMBIENT, SoundCategory.BLOCKS, 1F, 0.6f);
			}
		}
		
		return true;
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		EnumFacing facing = context.getPlacementHorizontalFacing().getOpposite();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		
		return getDefaultState().with(FACING, facing).with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public IFluidState getFluidState(IBlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(FACING, WATERLOGGED);
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
