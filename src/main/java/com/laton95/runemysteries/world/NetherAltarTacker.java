package com.laton95.runemysteries.world;

import com.laton95.runemysteries.reference.WorldGenReference;

import net.minecraft.world.World;

public class NetherAltarTacker extends AltarTracker {
	public NetherAltarTacker(World world) {
		super(world);
		runeAltars.add(new RuneAltar("chaos_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.chaosAltarBiomes, WorldGenReference.chaosAltarBiomesN, Type.NETHER));
	}
}
