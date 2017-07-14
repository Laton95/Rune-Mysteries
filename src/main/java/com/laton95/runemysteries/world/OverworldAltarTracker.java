package com.laton95.runemysteries.world;

import com.laton95.runemysteries.reference.WorldGenReference;

import net.minecraft.world.World;

public class OverworldAltarTracker extends AltarTracker {
	public OverworldAltarTracker(World world) {
		super(world);
		runeAltars.add(new RuneAltar("air_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.airAltarBiomes, WorldGenReference.airAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("astral_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.astralAltarBiomes, WorldGenReference.astralAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("death_altar", defaultAltarRadius, 0f, WorldGenReference.deathAltarBiomes,
				WorldGenReference.deathAltarBiomesN, Type.UNDERGROUND, "death_altar_room"));
		runeAltars.add(new RuneAltar("body_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.bodyAltarBiomes, WorldGenReference.bodyAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("blood_altar", defaultAltarRadius, 0f, WorldGenReference.bloodAltarBiomes,
				WorldGenReference.bloodAltarBiomesN, Type.UNDERGROUND, "blood_altar_room"));
		runeAltars.add(new RuneAltar("mind_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.mindAltarBiomes, WorldGenReference.mindAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("earth_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.earthAltarBiomes, WorldGenReference.earthAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("water_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.waterAltarBiomes, WorldGenReference.waterAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("soul_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.soulAltarBiomes, WorldGenReference.soulAltarBiomesN, Type.SOUL, "soul_altar_room"));
		runeAltars.add(new RuneAltar("law_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.lawAltarBiomes, WorldGenReference.lawAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("fire_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.fireAltarBiomes, WorldGenReference.fireAltarBiomesN, Type.SURFACE));
		runeAltars.add(new RuneAltar("nature_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.natureAltarBiomes, WorldGenReference.natureAltarBiomesN, Type.SURFACE));
	}
}
