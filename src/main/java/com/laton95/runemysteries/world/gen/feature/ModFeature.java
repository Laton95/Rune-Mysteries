package com.laton95.runemysteries.world.gen.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ModFeature {
	
	public static final Feature<NoFeatureConfig> OBELISK = new ObeliskFeature(NoFeatureConfig::deserialize);
	
	public static final Feature<NoFeatureConfig> BLACK_MONOLITH = new MonolithFeature(NoFeatureConfig::deserialize);
}
