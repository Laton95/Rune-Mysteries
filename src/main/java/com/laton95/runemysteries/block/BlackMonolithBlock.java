package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.entity.passive.ExExParrotEntity;
import com.laton95.runemysteries.init.ModEntities;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.reference.StringReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlackMonolithBlock extends ModBlock implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape Z_AXIS_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 6.0D, 14.0D, 16.0D, 10.0D);
	
	private static final VoxelShape X_AXIS_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 2.0D, 10.0D, 16.0D, 14.0D);
	
	public BlackMonolithBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F));
		this.setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Direction facing = state.get(FACING);
		return facing.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(!world.isRemote) {
			ModCriteriaTriggers.TOUCH_MONOLITH.trigger((ServerPlayerEntity) player);
			
			if(player.getHeldItem(hand).getItem() == ModItems.EX_PARROT) {
				
				LightningBoltEntity lightningboltentity = new LightningBoltEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, true);
				((ServerWorld) world).addLightningBolt(lightningboltentity);
				
				ModCriteriaTriggers.EX_PARROT.trigger((ServerPlayerEntity) player);
				if(!player.isCreative()) {
					player.getHeldItem(hand).shrink(1);
				}
				
				ExExParrotEntity parrot = new ExExParrotEntity(ModEntities.EX_EX_PARROT, world);
				parrot.setTamedBy(player);
				parrot.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
				world.addEntity(parrot);
			}
			else {
				player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.MONOLITH_INTERACT));
				world.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_AMBIENT, SoundCategory.BLOCKS, 1F, 0.6f);
			}
		}
		
		return true;
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
}
