package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class CloudBlock extends ModBlock {
	
	public CloudBlock() {
		super(Block.Properties.create(Material.WEB).hardnessAndResistance(0.5F).sound(SoundType.CLOTH).variableOpacity(), true);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean canEntitySpawn(BlockState p_220067_1_, IBlockReader p_220067_2_, BlockPos p_220067_3_, EntityType<?> p_220067_4_) {
		return false;
	}
	
	@Override
	public boolean isSideInvisible(BlockState p_200122_1_, BlockState p_200122_2_, Direction p_200122_3_) {
		return p_200122_2_.getBlock() == this || super.isSideInvisible(p_200122_1_, p_200122_2_, p_200122_3_);
	}
}
