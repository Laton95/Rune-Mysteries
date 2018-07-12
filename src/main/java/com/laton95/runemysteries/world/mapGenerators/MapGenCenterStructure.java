package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.world.structureComponents.ComponentCenterStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public abstract class MapGenCenterStructure extends MapGenStructure
{
	protected final String structure;
	
	private final String name;
	
	private final int yPos;
	
	private final List<BlockPos> portals;
	
	public MapGenCenterStructure(String name, String structure, int yPos, List<BlockPos> portals)
	{
		this.name = name;
		this.structure = structure;
		this.yPos = yPos;
		this.portals = portals;
	}
	
	@Override
	public String getStructureName()
	{
		return name;
	}
	
	@Nullable
	@Override
	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
	{
		return new BlockPos(0, 0, 0);
	}
	
	@Override
	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
	{
		return chunkX >= -2 && chunkX <= 1 && chunkZ >= -2 && chunkZ <= 1;
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new Start(world, rand, chunkX, chunkZ, structure + chunkX + '-' + chunkZ, yPos, portals);
	}
	
	public static class Start extends StructureStart
	{
		public Start()
		{
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ, String structure, int yPos, List<BlockPos> portals)
		{
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)), structure, yPos, portals);
			
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn, String structure, int yPos, List<BlockPos> portals)
		{
			super(chunkX, chunkZ);
			ComponentCenterStructure component = new ComponentCenterStructure(structure, chunkX, chunkZ, yPos, portals);
			components.add(component);
			updateBoundingBox();
		}
	}
}
