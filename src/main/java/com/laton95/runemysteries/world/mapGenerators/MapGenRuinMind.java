package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceRuin;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Random;

public class MapGenRuinMind extends MapGenStructure
{
	@Override
	public String getStructureName()
	{
		return "mind_ruin";
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
		try
		{
			BlockPos pos = WorldGenerator.ruinTracker.mindRuin.getRuinPos();
			
			return chunkX == pos.getX() >> 4 && chunkZ == pos.getZ() >> 4 && !WorldGenerator.ruinTracker.mindRuin.isGenerated();
		}
		catch(NullPointerException e)
		{
			return false;
		}
	}
	
	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ)
	{
		return new Start(world, rand, chunkX, chunkZ);
	}
	
	public static class Start extends StructureStart
	{
		public Start()
		{
		}
		
		public Start(World worldIn, Random random, int chunkX, int chunkZ)
		{
			super(chunkX, chunkZ);
			LogHelper.warn("Starting");
			int ypos = random.nextInt(10) + 20;
			ComponentSurfaceRuin ruin = new ComponentSurfaceRuin(WorldGenerator.ruinTracker.mindRuin, chunkX, chunkZ, ypos);
			components.add(ruin);
			updateBoundingBox();
		}
	}
}
