package com.laton95.runemysteries.world.gen.feature.structure;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;

import java.util.Random;
import java.util.function.Function;

public abstract class ModifiableRarityStructure extends ScatteredStructure<NoFeatureConfig> {
	
	public ModifiableRarityStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function);
	}
	
	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
		random.setSeed(getSeedModifier());
		int distance = getDistance();
		int separation = getSeparation();
		int x1 = x + distance * spacingOffsetsX;
		int z1 = z + distance * spacingOffsetsZ;
		int x2 = x1 < 0 ? x1 - distance + 1 : x1;
		int z2 = z1 < 0 ? z1 - distance + 1 : z1;
		int x3 = x2 / distance;
		int z3 = z2 / distance;
		((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), x3, z3, this.getSeedModifier());
		x3 = x3 * distance;
		z3 = z3 * distance;
		x3 = x3 + random.nextInt(distance - separation);
		z3 = z3 + random.nextInt(distance - separation);
		return new ChunkPos(x3, z3);
	}
	
	protected abstract int getDistance();
	
	protected abstract int getSeparation();
}
