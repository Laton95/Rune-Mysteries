package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

public class ModBlockStairs extends BlockStairs {
	
	public ModBlockStairs(Block block) {
		super(block.getDefaultState(), Properties.from(block));
	}
}
