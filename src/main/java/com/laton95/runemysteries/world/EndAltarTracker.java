package com.laton95.runemysteries.world;

import com.laton95.runemysteries.reference.WorldGenReference;

import net.minecraft.world.World;

public class EndAltarTracker extends AltarTracker {
	public EndAltarTracker(World world) {
		super(world);
		runeAltars.add(new RuneAltar("cosmic_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.cosmicAltarBiomes, WorldGenReference.cosmicAltarBiomesN, Type.END));
	}
}
