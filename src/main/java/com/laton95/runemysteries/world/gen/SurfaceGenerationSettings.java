package com.laton95.runemysteries.world.gen;

import net.minecraft.world.gen.GenerationSettings;

public class SurfaceGenerationSettings extends GenerationSettings {
	
	private int seaLevel = 63;
	
	public int getSeaLevel() {
		return seaLevel;
	}
	
	public void setSeaLevel(int seaLevel) {
		this.seaLevel = seaLevel;
	}
}
