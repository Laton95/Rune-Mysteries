package com.laton95.runemysteries.world;

import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.world.AltarTracker.RuneAltar;
import com.laton95.runemysteries.world.AltarTracker.Type;

import net.minecraft.world.World;

public class OverworldAltarTracker extends AltarTracker{
	public OverworldAltarTracker(World world) {
		super(world);
		runeAltars.add(new RuneAltar("airaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.airAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("astralaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.astralAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("deathaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.deathAltarBiomes, Type.UNDERGROUND));
		runeAltars.add(new RuneAltar("bodyaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.bodyAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("bloodaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.bloodAltarBiomes, Type.UNDERGROUND));
		runeAltars.add(new RuneAltar("mindaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.mindAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("earthaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.earthAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("wateraltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.waterAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("soulaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.soulAltarBiomes, Type.SOUL));
		runeAltars.add(new RuneAltar("lawaltar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.lawAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("firealtar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.fireAltarBiomes, Type.SURFACE));
		runeAltars.add(new RuneAltar("naturealtar", defaultAltarRadius, defaultAltarFlatnessTolerance, WorldGenReference.natureAltarBiomes, Type.SURFACE));
	}
}
