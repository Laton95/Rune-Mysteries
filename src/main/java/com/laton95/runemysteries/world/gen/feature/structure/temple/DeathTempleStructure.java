package com.laton95.runemysteries.world.gen.feature.structure.temple;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.function.Function;

public class DeathTempleStructure extends RuneTempleStructure {
	
	public DeathTempleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config, EnumRuneType.DEATH, 54);
	}
}
