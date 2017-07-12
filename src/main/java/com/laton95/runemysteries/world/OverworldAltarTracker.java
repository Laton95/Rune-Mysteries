package com.laton95.runemysteries.world;

import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.world.AltarTracker.RuneAltar;
import com.laton95.runemysteries.world.AltarTracker.Type;

import net.minecraft.world.World;

public class OverworldAltarTracker extends AltarTracker{
	public OverworldAltarTracker(World world) {
		super(world);
		runeAltars.add(new RuneAltar("air_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.airAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("astral_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.astralAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("death_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.deathAltarBiomes, Type.UNDERGROUND));
		runeAltars.add(new RuneAltar("body_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.bodyAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("blood_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.bloodAltarBiomes, Type.UNDERGROUND));
		runeAltars.add(new RuneAltar("mind_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.mindAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("earth_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.earthAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("water_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.waterAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("soul_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.soulAltarBiomes, Type.SOUL));
		runeAltars.add(new RuneAltar("law_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.lawAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("fire_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.fireAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("nature_altar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.natureAltarBiomes, Type.SURFACE));
	}
}
