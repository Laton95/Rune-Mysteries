package com.laton95.runemysteries.world.gen.feature;

import com.laton95.runemysteries.block.BlockElementalObelisk;
import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class ObeliskFeature extends Feature<NoFeatureConfig> {
	
	private final BlockElementalObelisk[] obelisks = {
			ModBlocks.AIR_OBELISK,
			ModBlocks.EARTH_OBELISK,
			ModBlocks.FIRE_OBELISK,
			ModBlocks.WATER_OBELISK
	};
	
	@Override
	public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
		if(world.isAirBlock(pos)) {
			makeObelisk(world, pos, obelisks[rand.nextInt(obelisks.length)]);
		}
		else if(world.getBlockState(pos).getMaterial() == Material.WATER) {
			makeObelisk(world, pos, ModBlocks.WATER_OBELISK);
		}
		
		return true;
	}
	
	public void makeObelisk(IWorld world, BlockPos pos, BlockElementalObelisk obelisk) {
		for(int y = 0; y < 2; ++y) {
			world.setBlockState(pos.up(y), obelisk.getDefaultState().with(BlockElementalObelisk.TOP, false), 8);
		}
		world.setBlockState(pos.up(2), obelisk.getDefaultState().with(BlockElementalObelisk.TOP, true), 8);
	}
}
