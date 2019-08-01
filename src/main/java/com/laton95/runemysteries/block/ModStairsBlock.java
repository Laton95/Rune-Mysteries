package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class ModStairsBlock extends StairsBlock {
	
	public ModStairsBlock(Block block) {
		super(block.getDefaultState(), Properties.from(block));
	}
}
