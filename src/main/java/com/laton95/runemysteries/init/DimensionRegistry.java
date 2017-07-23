package com.laton95.runemysteries.init;

import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.world.dimensionProviders.AirDimension;
import com.laton95.runemysteries.world.dimensionProviders.BloodDimension;
import com.laton95.runemysteries.world.dimensionProviders.BodyDimension;
import com.laton95.runemysteries.world.dimensionProviders.ChaosDimension;
import com.laton95.runemysteries.world.dimensionProviders.CosmicDimension;
import com.laton95.runemysteries.world.dimensionProviders.DeathDimension;
import com.laton95.runemysteries.world.dimensionProviders.EarthDimension;
import com.laton95.runemysteries.world.dimensionProviders.FireDimension;
import com.laton95.runemysteries.world.dimensionProviders.LawDimension;
import com.laton95.runemysteries.world.dimensionProviders.MindDimension;
import com.laton95.runemysteries.world.dimensionProviders.NatureDimension;
import com.laton95.runemysteries.world.dimensionProviders.SoulDimension;
import com.laton95.runemysteries.world.dimensionProviders.WaterDimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
	public static int airDimID;
	public static DimensionType AIR;

	public static int bloodDimID;
	public static DimensionType BLOOD;

	public static int bodyDimID;
	public static DimensionType BODY;
	
	public static int chaosDimID;
	public static DimensionType CHAOS;
	
	public static int cosmicDimID;
	public static DimensionType COSMIC;

	public static int deathDimID;
	public static DimensionType DEATH;

	public static int earthDimID;
	public static DimensionType EARTH;
	
	public static int fireDimID;
	public static DimensionType FIRE;
	
	public static int lawDimID;
	public static DimensionType LAW;
	
	public static int mindDimID;
	public static DimensionType MIND;
	
	public static int natureDimID;
	public static DimensionType NATURE;
	
	public static int soulDimID;
	public static DimensionType SOUL;
	
	public static int waterDimID;
	public static DimensionType WATER;

	public static void registerDimensions() {
		airDimID = ConfigReference.airAltarDimensionID;
		AIR = DimensionType.register("air_altar", "_altar", airDimID, AirDimension.class, false);

		bloodDimID = ConfigReference.bloodAltarDimensionID;
		BLOOD = DimensionType.register("blood_altar", "_altar", bloodDimID, BloodDimension.class, false);

		bodyDimID = ConfigReference.bodyAltarDimensionID;
		BODY = DimensionType.register("body_altar", "_altar", bodyDimID, BodyDimension.class, false);
		
		chaosDimID = ConfigReference.chaosAltarDimensionID;
		CHAOS = DimensionType.register("chaos_altar", "_altar", chaosDimID, ChaosDimension.class, false);
		
		cosmicDimID = ConfigReference.cosmicAltarDimensionID;
		COSMIC = DimensionType.register("cosmic_altar", "_altar", cosmicDimID, CosmicDimension.class, false);

		deathDimID = ConfigReference.deathAltarDimensionID;
		DEATH = DimensionType.register("death_altar", "_altar", deathDimID, DeathDimension.class, false);

		earthDimID = ConfigReference.earthAltarDimensionID;
		EARTH = DimensionType.register("earth_altar", "_altar", earthDimID, EarthDimension.class, false);
		
		fireDimID = ConfigReference.fireAltarDimensionID;
		FIRE = DimensionType.register("fire_altar", "_altar", fireDimID, FireDimension.class, false);
		
		lawDimID = ConfigReference.lawAltarDimensionID;
		LAW = DimensionType.register("law_altar", "_altar", lawDimID, LawDimension.class, false);
		
		mindDimID = ConfigReference.mindAltarDimensionID;
		MIND = DimensionType.register("mind_altar", "_altar", mindDimID, MindDimension.class, false);
		
		natureDimID = ConfigReference.natureAltarDimensionID;
		NATURE = DimensionType.register("nature_altar", "_altar", natureDimID, NatureDimension.class, false);
		
		soulDimID = ConfigReference.soulAltarDimensionID;
		SOUL = DimensionType.register("soul_altar", "_altar", soulDimID, SoulDimension.class, false);
		
		waterDimID = ConfigReference.waterAltarDimensionID;
		WATER = DimensionType.register("water_altar", "_altar", waterDimID, WaterDimension.class, false);

		DimensionManager.registerDimension(airDimID, AIR);
		DimensionManager.registerDimension(bloodDimID, BLOOD);
		DimensionManager.registerDimension(bodyDimID, BODY);
		DimensionManager.registerDimension(chaosDimID, CHAOS);
		DimensionManager.registerDimension(cosmicDimID, COSMIC);
		DimensionManager.registerDimension(deathDimID, DEATH);
		DimensionManager.registerDimension(earthDimID, EARTH);
		DimensionManager.registerDimension(fireDimID, FIRE);
		DimensionManager.registerDimension(lawDimID, LAW);
		DimensionManager.registerDimension(mindDimID, MIND);
		DimensionManager.registerDimension(natureDimID, NATURE);
		DimensionManager.registerDimension(soulDimID, SOUL);
		DimensionManager.registerDimension(waterDimID, WATER);
	}
}
