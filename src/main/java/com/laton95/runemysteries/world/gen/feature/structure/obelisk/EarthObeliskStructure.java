package com.laton95.runemysteries.world.gen.feature.structure.obelisk;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public class EarthObeliskStructure extends ObeliskStructure {
	
	public static final ResourceLocation EARTH_OBELISK = new ResourceLocation(RuneMysteries.MOD_ID, "obelisk/earth_obelisk");
	
	public EarthObeliskStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, 32, 1, 90161713, EnumRuneType.EARTH, EARTH_OBELISK);
	}
	
	@Override
	protected int getHeight(Biome biome) {
		return 40;
	}
}
