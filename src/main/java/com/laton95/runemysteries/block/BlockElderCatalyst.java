package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancements.ModCriteriaTriggers;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModPotions;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.state.properties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockElderCatalyst extends ModBlock {
	
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
	
	public BlockElderCatalyst() {
		super("elder_catalyst", Properties.create(Material.ROCK).needsRandomTick().hardnessAndResistance(50.0F, 1200.0F));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(CORNER, TOP);
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		int cornerIndex = ((EnumCorner) state.get(CORNER)).ordinal();
		return state.get(TOP) ? topShapes[cornerIndex] : bottomShapes[cornerIndex];
	}
	
	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IWorld world = context.getWorld();
		BlockPos pos = context.getPos();
		return canPlace(context) ? this.getDefaultState().with(CORNER, getCorner(world, pos)).with(TOP, isTop(world, pos)) : null;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, @Nullable EntityLivingBase placer, ItemStack stack) {
		if(!worldIn.isRemote) {
			EnumFacing facing = placer != null ? placer.getHorizontalFacing() : EnumFacing.NORTH;
			
			for(int dx = 0; dx < 2; dx++) {
				for(int dy = 0; dy < 2; dy++) {
					for(int dz = 0; dz < 2; dz++) {
						BlockPos setPos = pos.up(dy).offset(facing, dx).offset(facing.rotateY(), dz);
						worldIn.setBlockState(setPos, getDefaultState(), 2);
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
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.with(CORNER, getCorner(worldIn, currentPos)).with(TOP, isTop(worldIn, currentPos));
	}
	
	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving) {
		super.onReplaced(state, worldIn, pos, newState, isMoving);
		
		if(state.getBlock() != newState.getBlock()) {
			for(EnumFacing direction : EnumFacing.values()) {
				if(worldIn.getBlockState(pos.offset(direction)).getBlock() == this) {
					worldIn.destroyBlock(pos.offset(direction), false);
				}
			}
		}
	}
	
	@Override
	public EnumPushReaction getPushReaction(IBlockState state) {
		return EnumPushReaction.BLOCK;
	}
	
	private EnumCorner getCorner(IWorld worldIn, BlockPos pos) {
		boolean hasNorth = worldIn.getBlockState(pos.north()).getBlock() == this;
		boolean hasSouth = worldIn.getBlockState(pos.south()).getBlock() == this;
		boolean hasWest = worldIn.getBlockState(pos.west()).getBlock() == this;
		boolean hasEast = worldIn.getBlockState(pos.east()).getBlock() == this;
		
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
	
	private boolean isTop(IWorld worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.up()).getBlock() != this;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		List<BlockPos> stonePositions = new ArrayList<>();
		
		int radius = 10;
		for(int y = -radius; y < radius; y++) {
			for(int x = -radius; x < radius; x++) {
				for(int z = -radius; z < radius; z++) {
					BlockPos curPos = pos.add(x, y, z);
					if(worldIn.getBlockState(curPos) == Blocks.STONE.getDefaultState()) {
						stonePositions.add(curPos);
					}
				}
			}
		}
		
		if(stonePositions.size() > 0) {
			BlockPos essPos = stonePositions.get(random.nextInt(stonePositions.size()));
			
			worldIn.setBlockState(essPos, ModBlocks.RUNE_ESSENCE.getDefaultState().with(BlockRuneEssence.INFINITE, false));
		}
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ModCriteriaTriggers.ELDER_CATALYST.trigger((EntityPlayerMP) player);
			
			player.sendMessage(new TextComponentTranslation(StringReference.BlockInteraction.ELDER_CATALYST_INTERACT, player.getDisplayName()));
			
			player.addPotionEffect(new PotionEffect(ModPotions.STONETOUCHER, 10 * 1200, 0, false, true));
		}
		
		return true;
	}
}
