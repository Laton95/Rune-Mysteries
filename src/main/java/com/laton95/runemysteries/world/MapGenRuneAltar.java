package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.css.ElementCSSInlineStyle;

import java.util.Random;

import com.google.common.collect.Lists;
import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.reference.ConfigReference;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import jline.internal.Log;
import net.minecraft.entity.passive.EntityRabbit.RabbitJumpHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenRuneAltar extends MapGenStructure {
	private final List<Biome.SpawnListEntry> runeAltarSpawnList;
    private boolean foundLocations = false;
    private AltarTracker altarTracker;
    
    public MapGenRuneAltar(World world) {
    	this.world = world;
		this.runeAltarSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
		altarTracker = new AltarTracker();
	}

	public String getStructureName() {
		return "RuneAltar";
	}
	
	public AltarTracker getAltarTracker() {
		return altarTracker;
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		if (!this.foundLocations)
        {
            altarTracker.findLocations(world);
            this.foundLocations = true;
        }
		
        return altarTracker.isCloseToChunks(new ChunkPos(chunkX, chunkZ));
	}

	public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
		return null;
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenRuneAltar.Start(this.world, this.rand, chunkX, chunkZ, altarTracker);
	}

	/**
	 * returns possible spawns for rune altars
	 */
	public List<Biome.SpawnListEntry> getSpawnList() {
		return this.runeAltarSpawnList;
	}

	public static class Start extends StructureStart {
		public Start() {
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, AltarTracker altarTracker) {
			this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)), altarTracker);
		}

		public Start(World worldIn, Random random, int chunkX, int chunkZ, Biome biomeIn, AltarTracker altarTracker) {
			super(chunkX, chunkZ);
			
			String altar = altarTracker.getAltar(new ChunkPos(chunkX, chunkZ));
			
			boolean biomeFlag;
			if (altarTracker.getBiomeDependancy(altar)) {
				biomeFlag =  WorldGenReference.getBiomeList(altar).contains(biomeIn);
			} else biomeFlag = true;
			
			if (!altarTracker.isAltarPlaced(altar) && biomeFlag) {
				ComponentRuneAltar componentRuneAltar = new ComponentRuneAltar(random, chunkX * 16 + random.nextInt(4) + 1, chunkZ * 16 + random.nextInt(4) + 1, altar);
				StructureBoundingBox bBox = componentRuneAltar.getBoundingBox();
				componentRuneAltar.offsetToAverageGroundLevel(worldIn, bBox, -1);
				
				BlockPos blockpos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
				if (WorldHelper.isFlat(worldIn, blockpos, bBox.getXSize(), bBox.getYSize(), bBox.getZSize(), 3, 1, altarTracker.getAltarFlatnessTolerance(altar))) {
					//Altar generated
					altarTracker.setAltarPlaced(altar, true);
					altarTracker.updateAltarPosition(altar, blockpos);
					altarTracker.printAltar(altar);
					altarTracker.setAltarPlacementRadius(altar, 0);
					this.components.add(componentRuneAltar);
				} else {
					//Altar failed to generate because ground was not flat
					emergency(altar, altarTracker);
					componentRuneAltar = null;
				}
			} else {
				//Altar failed to generate because incorrect biome in selected chunk
				if (!altarTracker.isAltarPlaced(altar)) {
					emergency(altar, altarTracker);
				}
			}
			this.updateBoundingBox();
		}
		
		private void emergency(String altar, AltarTracker altarTracker) {
			altarTracker.incrementFailureCount(altar);
			if (!altarTracker.isAltarPlaced(altar) && altarTracker.getFailureCount(altar) > (altarTracker.getAltarPlacementRadius(altar)^2)*0.75) {
				altarTracker.setAltarPlacementRadius(altar, altarTracker.getAltarPlacementRadius(altar) + 5);
				altarTracker.setAltarFlatnessTolerance(altar, 0.2f);
				if (altarTracker.getFailureCount(altar) > (altarTracker.getAltarPlacementRadius(altar)^2)) {
					altarTracker.setAltarPlacementRadius(altar, altarTracker.getAltarPlacementRadius(altar) + 20);
					altarTracker.setBiomeDependancy(altar, false);
					altarTracker.setAltarFlatnessTolerance(altar, 0f);
				}
				
			}
			
		}
	}
}