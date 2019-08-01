package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.renderer.FaceDirection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class RuneEssenceBlock extends ModBlock {
	
	public static final BooleanProperty INFINITE = BooleanProperty.create("infinite");
	
	public RuneEssenceBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f));
		setDefaultState(stateContainer.getBaseState().with(INFINITE, false));
	}
	
	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
		onBlockHarvested(world, pos, state, player);
		
		if(state.get(INFINITE) && !player.isCreative()) {
			world.setBlockState(pos, state, world.isRemote ? 11 : 3);
			return true;
		}
		
		return world.setBlockState(pos, fluid.getBlockState(), world.isRemote ? 11 : 3);
	}
	
	@Override
	public float getExplosionResistance(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
		if(state.get(INFINITE)) {
			return 3600000.0F;
		}
		
		return 6.0f;
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if(state.get(INFINITE)) {
			double xPos = 0;
			double yPos = 0;
			double zPos = 0;
			
			double offset = 0.1;
			
			switch(FaceDirection.values()[rand.nextInt(FaceDirection.values().length - 1) + 1]) {
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
			
			world.addParticle(ParticleTypes.INSTANT_EFFECT, xPos, yPos, zPos, 0, -0.9, 0);
		}
	}
	
	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(INFINITE);
	}
}
