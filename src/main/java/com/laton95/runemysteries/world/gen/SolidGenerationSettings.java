package com.laton95.runemysteries.world.gen;

import net.minecraft.world.gen.GenerationSettings;

public class SolidGenerationSettings extends GenerationSettings {
	
	private int height = 255;
	
	public int getHeight() {
		return height;
	}
	
	public SolidGenerationSettings setHeight(int height) {
		this.height = height;
		return this;
	}
}
