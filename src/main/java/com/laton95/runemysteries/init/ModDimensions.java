package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.world.dimension.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModDimensions {
	
	public static final ModDimension AIR_TEMPLE = null;
	
	public static final ModDimension BLOOD_TEMPLE = null;
	
	public static final ModDimension BODY_TEMPLE = null;
	
	public static final ModDimension CHAOS_TEMPLE = null;
	
	public static final ModDimension COSMIC_TEMPLE = null;
	
	public static final ModDimension DEATH_TEMPLE = null;
	
	public static final ModDimension EARTH_TEMPLE = null;
	
	public static final ModDimension FIRE_TEMPLE = null;
	
	public static final ModDimension LAW_TEMPLE = null;
	
	public static final ModDimension MIND_TEMPLE = null;
	
	public static final ModDimension NATURE_TEMPLE = null;
	
	public static final ModDimension SOUL_TEMPLE = null;
	
	public static final ModDimension WATER_TEMPLE = null;
	
	@SubscribeEvent
	public static void registerDimensions(RegistryEvent.Register<ModDimension> event) {
		ModLog.info("Registering dimensions");
		ModDimension airDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return AirTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "air_temple");
		event.getRegistry().register(airDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "air_temple"), airDimension, null, true);
		
		ModDimension bloodDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return BloodTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "blood_temple");
		event.getRegistry().register(bloodDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "blood_temple"), bloodDimension, null, false);
		
		ModDimension bodyDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return BodyTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "body_temple");
		event.getRegistry().register(bodyDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "body_temple"), bodyDimension, null, false);
		
		ModDimension chaosDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return ChaosTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "chaos_temple");
		event.getRegistry().register(chaosDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "chaos_temple"), chaosDimension, null, false);
		
		ModDimension cosmicDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return CosmicTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "cosmic_temple");
		event.getRegistry().register(cosmicDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "cosmic_temple"), cosmicDimension, null, true);
		
		ModDimension deathDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return DeathTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "death_temple");
		event.getRegistry().register(deathDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "death_temple"), deathDimension, null, false);
		
		ModDimension earthDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return EarthTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "earth_temple");
		event.getRegistry().register(earthDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "earth_temple"), earthDimension, null, false);
		
		ModDimension fireDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return FireTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "fire_temple");
		event.getRegistry().register(fireDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "fire_temple"), fireDimension, null, true);
		
		ModDimension lawDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return LawTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "law_temple");
		event.getRegistry().register(lawDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "law_temple"), lawDimension, null, false);
		
		ModDimension mindDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return MindTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "mind_temple");
		event.getRegistry().register(mindDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "mind_temple"), mindDimension, null, true);
		
		ModDimension natureDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return NatureTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "nature_temple");
		event.getRegistry().register(natureDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "nature_temple"), natureDimension, null, true);
		
		ModDimension soulDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return SoulTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "soul_temple");
		event.getRegistry().register(soulDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "soul_temple"), soulDimension, null, false);
		
		ModDimension waterDimension = new ModDimension() {
			@Override
			public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
				return WaterTempleDimension::new;
			}
		}.setRegistryName(RuneMysteries.MOD_ID, "water_temple");
		event.getRegistry().register(waterDimension);
		DimensionManager.registerDimension(new ResourceLocation(RuneMysteries.MOD_ID, "water_temple"), waterDimension, null, true);
	}
}
