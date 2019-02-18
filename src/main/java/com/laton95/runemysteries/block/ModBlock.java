package com.laton95.runemysteries.block;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlock extends Block {
	
	public final boolean hasItem;
	
	public final boolean hasItemGroup;
	
	public ModBlock(String name) {
		this(name, Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f), true, true);
	}
	
	public ModBlock(String name, Properties properties, boolean hasItem, boolean hasItemGroup) {
		super(properties);
		setRegistryName(RuneMysteries.MOD_ID, name.toLowerCase());
		this.hasItem = hasItem;
		this.hasItemGroup = hasItemGroup;
	}
	
	public ModBlock(String name, Properties properties) {
		this(name, properties, true, true);
	}
}
