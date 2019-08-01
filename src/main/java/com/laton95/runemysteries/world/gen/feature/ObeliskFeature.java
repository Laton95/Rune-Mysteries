package com.laton95.runemysteries.world.gen.feature;

import com.laton95.runemysteries.block.ElementalObeliskBlock;
import com.laton95.runemysteries.init.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ObeliskFeature extends Feature<NoFeatureConfig> {
	
	private final ElementalObeliskBlock[] obelisks = {
			ModBlocks.AIR_OBELISK,
			ModBlocks.EARTH_OBELISK,
			ModBlocks.FIRE_OBELISK,
			ModBlocks.WATER_OBELISK
	};
	
	public ObeliskFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
		if(world.isAirBlock(pos)) {
			makeObelisk(world, pos, obelisks[rand.nextInt(obelisks.length)]);
		}
		else if(world.getBlockState(pos).getMaterial() == Material.WATER) {
			makeObelisk(world, pos, ModBlocks.WATER_OBELISK);
		}
		
		return true;
	}
	
	public void makeObelisk(IWorld world, BlockPos pos, ElementalObeliskBlock obelisk) {
		for(int y = 0; y < 2; ++y) {
			world.setBlockState(pos.up(y), obelisk.getDefaultState().with(ElementalObeliskBlock.TOP, false), 8);
		}
		world.setBlockState(pos.up(2), obelisk.getDefaultState().with(ElementalObeliskBlock.TOP, true), 8);
	}
}
