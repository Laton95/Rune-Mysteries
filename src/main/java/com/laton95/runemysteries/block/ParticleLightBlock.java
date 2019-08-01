package com.laton95.runemysteries.block;

import com.laton95.runemysteries.enums.EnumColour;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ParticleLightBlock extends ModBlock implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape SHAPE = Block.makeCuboidShape(5D, 5D, 5D, 11D, 11D, 11D);
	
	private final EnumColour colour;
	
	public ParticleLightBlock(EnumColour colour) {
		super(Properties.create(Material.GLASS).lightValue(15).doesNotBlockMovement(), false);
		this.colour = colour;
		this.setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Override
	public int getOpacity(BlockState state, IBlockReader world, BlockPos pos) {
		return 0;
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		IParticleData particle = new RedstoneParticleData(colour.getRGB().getRed() / 255, colour.getRGB().getGreen() / 255, colour.getRGB().getBlue() / 255, 1);
		
		for(int i = 0; i < 5; i++) {
			world.addParticle(particle, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0, 0, 0);
		}
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(hand == Hand.MAIN_HAND && player.getHeldItem(hand) == ItemStack.EMPTY) {
			if(!world.isRemote) {
				ParticleLightBlock newLight;
				
				Item heldItem = player.getHeldItem(hand).getItem();
				
				if(heldItem instanceof DyeItem) {
					newLight = EnumColour.getColourFromDye(((DyeItem) heldItem).getDyeColor()).getLight();
				}
				else {
					int oldColourOrdinal = colour.ordinal();
					
					if(!player.isSneaking()) {
						if(oldColourOrdinal == 15) { oldColourOrdinal = -1; }
						
						newLight = EnumColour.values()[oldColourOrdinal + 1].getLight();
					}
					else {
						if(oldColourOrdinal == 0) { oldColourOrdinal = 16; }
						
						newLight = EnumColour.values()[oldColourOrdinal + -1].getLight();
					}
				}
				
				world.setBlockState(pos, newLight.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)));
			}
			
			return true;
		}
		
		return false;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if(state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return state;
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
}
