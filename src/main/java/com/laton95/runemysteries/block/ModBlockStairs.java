package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class ModBlockStairs extends BlockStairs {
	
	public ModBlockStairs(String name, Block block) {
		super(block.getDefaultState(), Properties.from(block));
		setRegistryName(name);
	}
}
