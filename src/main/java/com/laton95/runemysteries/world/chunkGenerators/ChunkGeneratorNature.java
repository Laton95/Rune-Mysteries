package com.laton95.runemysteries.world.chunkGenerators;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.world.mapGenerators.MapGenNatureTemple;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;

import java.util.List;

public class ChunkGeneratorNature extends ChunkGeneratorSurfaceWorld
{
	
	private final static List<Biome.SpawnListEntry> MOB_SPAWNS = ImmutableList.of(new SpawnListEntry(EntityParrot.class, 20, 1, 4));
	
	public ChunkGeneratorNature(World worldIn, long seed)
	{
		super(worldIn, seed, Blocks.STONE.getDefaultState(), NamesReference.worldGenStrings.NATURE, 14, 8, MOB_SPAWNS);
		centerpiece = new MapGenNatureTemple();
	}
}
