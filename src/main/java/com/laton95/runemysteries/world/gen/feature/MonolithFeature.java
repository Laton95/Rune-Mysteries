package com.laton95.runemysteries.world.gen.feature;

import com.laton95.runemysteries.block.BlackMonolithBlock;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.util.WorldHelper;
import com.mojang.datafixers.Dynamic;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class MonolithFeature extends Feature<NoFeatureConfig> {
	
	public MonolithFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
		pos = WorldHelper.getTopSolidBlock(world, pos).up();
		IFluidState ifluidstate = world.getFluidState(pos);
		Direction facing = Direction.values()[rand.nextInt(4) + 2];
		world.setBlockState(pos, ModBlocks.BLACK_MONOLITH.getDefaultState().with(BlackMonolithBlock.FACING, facing).with(BlackMonolithBlock.WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER), 16);
		return true;
	}
}
