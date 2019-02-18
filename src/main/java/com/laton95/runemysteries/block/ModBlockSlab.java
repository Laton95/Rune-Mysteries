package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;

public class ModBlockSlab extends BlockSlab {
	
	public ModBlockSlab(String name, Block block) {
		super(Properties.from(block));
		setRegistryName(name);
	}
}
