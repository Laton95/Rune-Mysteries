package com.laton95.runemysteries.world.gen.feature;

import com.laton95.runemysteries.block.BlockBlackMonolith;
import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class MonolithFeature extends Feature<NoFeatureConfig> {
	
	@Override
	public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
		//		pos = WorldHelper.getTopSolidBlock(world, pos).up();
		IFluidState ifluidstate = world.getFluidState(pos);
		EnumFacing facing = EnumFacing.values()[rand.nextInt(4) + 2];
		world.setBlockState(pos, ModBlocks.BLACK_MONOLITH.getDefaultState().with(BlockBlackMonolith.FACING, facing).with(BlockBlackMonolith.WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER), 16);
		
		return true;
	}
}
