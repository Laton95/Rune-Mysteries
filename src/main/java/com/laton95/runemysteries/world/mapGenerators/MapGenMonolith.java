package com.laton95.runemysteries.world.mapGenerators;

import com.laton95.runemysteries.block.BlockBlackMonolith;
import com.laton95.runemysteries.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;

public class MapGenMonolith extends MapGenBase
{
	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer)
	{
		if(worldIn.rand.nextInt(30) == 0)
		{
			boolean hasJungle = false;
			
			try
			{
				for(byte b : worldIn.getChunkFromChunkCoords(x, z).getBiomeArray())
				{
					if(!hasJungle)
					{
						hasJungle = BiomeDictionary.hasType(Biome.getBiome(b), BiomeDictionary.Type.JUNGLE);
					}
				}
			}
			catch(NullPointerException e)
			{
			
			}
			
			if(hasJungle)
			{
				ArrayList<BlockPos> places = new ArrayList<>();
				
				for(int xPos = 0; xPos < 16; xPos++)
				{
					for(int zPos = 0; zPos < 16; zPos++)
					{
						int yPos = worldIn.getChunkFromChunkCoords(x, z).getHeightValue(xPos, zPos);
						BlockPos pos = new BlockPos(xPos, yPos, zPos);
						
						if(worldIn.getChunkFromChunkCoords(x, z).getBlockState(pos.add(0, -1, 0)).getBlock() == Blocks.GRASS)
						{
							places.add(new BlockPos(xPos, yPos, zPos));
						}
					}
				}
				
				if(places.size() > 0)
				{
					BlockPos pos = places.get(rand.nextInt(places.size()));
					worldIn.getChunkFromChunkCoords(x, z).setBlockState(pos, ModBlocks.BLACK_MONOLITH.getStateFromMeta(0).withProperty(BlockBlackMonolith.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)));
				}
			}
		}
	}
}
