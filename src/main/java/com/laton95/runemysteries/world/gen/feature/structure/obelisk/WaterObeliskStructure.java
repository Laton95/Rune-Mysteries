package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public class WaterObeliskStructure extends ObeliskStructure {
	
	public static final ResourceLocation WATER_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/water_obelisk");
	
	public WaterObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, 15393495, EnumRuneType.WATER, WATER_OBELISK);
	}
	
	@Override
	public int getDistance() {
		return Config.waterObeliskDistance;
	}
	
	@Override
	public int getSeparation() {
		return Config.waterObeliskSeparation;
	}
	
	@Override
	protected int getHeight(Biome biome) {
		return -1;
	}
}
