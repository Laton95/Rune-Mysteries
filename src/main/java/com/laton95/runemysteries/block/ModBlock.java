package com.laton95.runemysteries.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlock extends Block {
	
	public final boolean showInCreative;
	
	public ModBlock() {
		this(Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f), true);
	}
	
	public ModBlock(Properties properties) {
		this(properties, true);
	}
	
	public ModBlock(Properties properties, boolean showInCreative) {
		super(properties);
		this.showInCreative = showInCreative;
	}
}
