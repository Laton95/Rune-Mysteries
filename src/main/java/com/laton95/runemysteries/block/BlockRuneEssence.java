package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Particles;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockRuneEssence extends ModBlock {
	
	public static final BooleanProperty INFINITE = BooleanProperty.create("infinite");
	
	public BlockRuneEssence() {
		super("rune_essence_block", Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f));
		setDefaultState(stateContainer.getBaseState().with(INFINITE, false));
	}
	
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest, IFluidState fluid) {
		onBlockHarvested(world, pos, state, player);
		
		if(state.get(INFINITE) && !player.isCreative()) {
			world.setBlockState(pos, state, world.isRemote ? 11 : 3);
			return true;
		}
		
		return world.setBlockState(pos, fluid.getBlockState(), world.isRemote ? 11 : 3);
	}
	
	@Override
	public float getExplosionResistance(IBlockState state, IWorldReader world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
		if(state.get(INFINITE)) {
			return 3600000.0F;
		}
		
		return 6.0f;
	}
	
	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(stateIn.get(INFINITE)) {
			double xPos = 0;
			double yPos = 0;
			double zPos = 0;
			
			double offset = 0.1;
			
			switch(EnumFaceDirection.values()[rand.nextInt(EnumFaceDirection.values().length - 1) + 1]) {
				case UP:
					xPos += rand.nextFloat() + offset;
					yPos += 1 + offset;
					zPos += rand.nextFloat() + offset;
					break;
				case NORTH:
					xPos += rand.nextFloat() + offset;
					yPos += rand.nextFloat() + offset;
					zPos -= offset;
					break;
				case SOUTH:
					xPos += rand.nextFloat() + offset;
					yPos += rand.nextFloat() + offset;
					zPos += 1 + offset;
					break;
				case WEST:
					xPos -= offset;
					yPos += rand.nextFloat() + offset;
					zPos += rand.nextFloat() + offset;
					break;
				case EAST:
					xPos += 1 + offset;
					yPos += rand.nextFloat() + offset;
					zPos += rand.nextFloat() + offset;
					break;
			}
			
			xPos += pos.getX();
			yPos += pos.getY();
			zPos += pos.getZ();
			
			worldIn.spawnParticle(Particles.INSTANT_EFFECT, xPos, yPos, zPos, 0, -0.9, 0);
		}
	}
	
	@Override
	@Nonnull
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return ModItems.RUNE_ESSENCE;
	}
	
	@Override
	public int getItemsToDropCount(IBlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
		if(fortune > 4) { fortune = 4; }
		return 1 + random.nextInt(5) + random.nextInt(fortune + 1);
	}
	
	@Override
	@Nonnull
	public EnumPushReaction getPushReaction(IBlockState state) {
		return EnumPushReaction.BLOCK;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(INFINITE);
	}
}
