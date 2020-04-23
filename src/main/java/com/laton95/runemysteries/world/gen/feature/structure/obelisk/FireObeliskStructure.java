package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public class FireObeliskStructure extends ObeliskStructure {
	
	public static final ResourceLocation FIRE_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/fire_obelisk");
	
	public FireObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, 73596167, EnumRuneType.FIRE, FIRE_OBELISK);
	}
	
	@Override
	public int getDistance() {
		return Config.fireObeliskDistance;
	}
	
	@Override
	public int getSeparation() {
		return Config.fireObeliskSeparation;
	}
	
	@Override
	protected int getHeight(Biome biome) {
		return biome.getCategory() == Biome.Category.NETHER ? 31 : 10;
	}
}
