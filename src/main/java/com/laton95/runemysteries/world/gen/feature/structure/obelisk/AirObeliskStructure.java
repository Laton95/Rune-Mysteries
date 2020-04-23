package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public class AirObeliskStructure extends ObeliskStructure {
	
	public static final ResourceLocation AIR_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/air_obelisk");
	
	public AirObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, 13794389, EnumRuneType.AIR, AIR_OBELISK);
	}
	
	@Override
	public int getDistance() {
		return Config.airObeliskDistance;
	}
	
	@Override
	public int getSeparation() {
		return Config.airObeliskSeparation;
	}
	
	@Override
	protected int getHeight(Biome biome) {
		return -1;
	}
}
