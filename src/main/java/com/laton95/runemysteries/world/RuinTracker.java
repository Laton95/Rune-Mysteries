package com.laton95.runemysteries.world;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.util.RuinNBTHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;

public class RuinTracker {
	
	public final Ruin airRuin = new Ruin("air_ruin", "dirt_island");
	
	public final Ruin astralRuin = new Ruin("astral_ruin", "dirt_island");
	
	public final Ruin bloodRuin = new Ruin("blood_ruin");
	
	public final Ruin bodyRuin = new Ruin("body_ruin", "dirt_island");
	
	public final Ruin chaosRuin = new Ruin("chaos_ruin", "nether_island");
	
	public final Ruin cosmicRuin = new Ruin("cosmic_ruin", "end_island");
	
	public final Ruin deathRuin = new Ruin("death_ruin");
	
	public final Ruin earthRuin = new Ruin("earth_ruin", "dirt_island");
	
	public final Ruin fireRuin = new Ruin("fire_ruin", "sand_island");
	
	public final Ruin lawRuin = new Ruin("law_ruin", "dirt_island");
	
	public final Ruin mindRuin = new Ruin("mind_ruin", "dirt_island");
	
	public final Ruin natureRuin = new Ruin("nature_ruin", "dirt_island");
	
	public final Ruin soulRuin = new Ruin("soul_ruin");
	
	public final Ruin waterRuin = new Ruin("water_ruin", "dirt_island");
	
	public final Ruin ouraniaRuin = new Ruin("ourania_ruin");
	
	public void updateOverworldLocations(World world) {
		if(world.dimension.getType() == DimensionType.OVERWORLD) {
			RuinNBTHelper nbt = RuinNBTHelper.get(world);
			
			if(nbt.overworldRuinsGenerated) {
				airRuin.ruinPos = nbt.posMap.get("air");
				astralRuin.ruinPos = nbt.posMap.get("astral");
				bloodRuin.ruinPos = nbt.posMap.get("blood");
				bodyRuin.ruinPos = nbt.posMap.get("body");
				deathRuin.ruinPos = nbt.posMap.get("death");
				earthRuin.ruinPos = nbt.posMap.get("earth");
				fireRuin.ruinPos = nbt.posMap.get("fire");
				lawRuin.ruinPos = nbt.posMap.get("law");
				mindRuin.ruinPos = nbt.posMap.get("mind");
				natureRuin.ruinPos = nbt.posMap.get("nature");
				soulRuin.ruinPos = nbt.posMap.get("soul");
				waterRuin.ruinPos = nbt.posMap.get("water");
				ouraniaRuin.ruinPos = nbt.posMap.get("ourania");
			}
			else {
				airRuin.setRuinPos(findPosition(world, WorldGenReference.AIR_ALTAR_BIOMES, WorldGenReference.AIR_ALTAR_BIOMES_AVOID), world);
				
				astralRuin.setRuinPos(findPosition(world, WorldGenReference.ASTRAL_ALTAR_BIOMES, WorldGenReference.ASTRAL_ALTAR_BIOMES_AVOID), world);
				
				bloodRuin.setRuinPos(findPosition(world, WorldGenReference.BLOOD_ALTAR_BIOMES, WorldGenReference.BLOOD_ALTAR_BIOMES_AVOID), world);
				
				bodyRuin.setRuinPos(findPosition(world, WorldGenReference.BODY_ALTAR_BIOMES, WorldGenReference.BODY_ALTAR_BIOMES_AVOID), world);
				
				deathRuin.setRuinPos(findPosition(world, WorldGenReference.DEATH_ALTAR_BIOMES, WorldGenReference.DEATH_ALTAR_BIOMES_AVOID), world);
				
				earthRuin.setRuinPos(findPosition(world, WorldGenReference.EARTH_ALTAR_BIOMES, WorldGenReference.EARTH_ALTAR_BIOMES_AVOID), world);
				
				fireRuin.setRuinPos(findPosition(world, WorldGenReference.FIRE_ALTAR_BIOMES, WorldGenReference.FIRE_ALTAR_BIOMES_AVOID), world);
				
				lawRuin.setRuinPos(findPosition(world, WorldGenReference.LAW_ALTAR_BIOMES, WorldGenReference.LAW_ALTAR_BIOMES_AVOID), world);
				
				mindRuin.setRuinPos(findPosition(world, WorldGenReference.MIND_ALTAR_BIOMES, WorldGenReference.MIND_ALTAR_BIOMES_AVOID), world);
				
				natureRuin.setRuinPos(findPosition(world, WorldGenReference.NATURE_ALTAR_BIOMES, WorldGenReference.NATURE_ALTAR_BIOMES_AVOID), world);
				
				soulRuin.setRuinPos(findPosition(world, WorldGenReference.SOUL_ALTAR_BIOMES, WorldGenReference.SOUL_ALTAR_BIOMES_AVOID), world);
				
				waterRuin.setRuinPos(findPosition(world, WorldGenReference.WATER_ALTAR_BIOMES, WorldGenReference.WATER_ALTAR_BIOMES_AVOID), world);
				
				ouraniaRuin.setRuinPos(findPosition(world, WorldGenReference.OURANIA_ALTAR_BIOMES, WorldGenReference.OURANIA_ALTAR_BIOMES_AVOID), world);
				
				nbt.overworldRuinsGenerated = true;
				nbt.markDirty();
			}
		}
	}
	
	public void updateNetherLocations(World world) {
		if(world.dimension.getType() == DimensionType.THE_NETHER) {
			RuinNBTHelper nbt = RuinNBTHelper.get(world);
			
			if(nbt.netherRuinsGenerated) {
				chaosRuin.ruinPos = nbt.posMap.get("chaos");
			}
			else {
				chaosRuin.setRuinPos(findPosition(world, WorldGenReference.CHAOS_ALTAR_BIOMES, WorldGenReference.CHAOS_ALTAR_BIOMES_AVOID), world);
				
				nbt.netherRuinsGenerated = true;
				nbt.markDirty();
			}
		}
	}
	
	public void updateEndLocations(World world) {
		if(world.dimension.getType() == DimensionType.THE_END) {
			RuinNBTHelper nbt = RuinNBTHelper.get(world);
			
			if(nbt.endRuinsGenerated) {
				cosmicRuin.ruinPos = nbt.posMap.get("cosmic");
			}
			else {
				cosmicRuin.setRuinPos(findPosition(world, WorldGenReference.COSMIC_ALTAR_BIOMES, WorldGenReference.COSMIC_ALTAR_BIOMES_AVOID), world);
				
				nbt.endRuinsGenerated = true;
				nbt.markDirty();
			}
		}
	}
	
	private BlockPos findPosition(World world, List<BiomeDictionary.Type> allowedBiomes, List<BiomeDictionary.Type> avoidBiomes) {
		//TODO add proper config here
		BlockPos pos = null;
		int maxRange = 10000; //ModConfig.WORLD_GENERATION.rune_altars.maxRuneAltarRange;
		int minRange = 1000; //ModConfig.WORLD_GENERATION.rune_altars.minRuneAltarRange;
		int tries = 10; //ModConfig.WORLD_GENERATION.rune_altars.runeAltarTries;
		
		while(pos == null && tries > 0) {
			int x = world.rand.nextInt(maxRange - minRange) + minRange;
			int z = world.rand.nextInt(maxRange - minRange) + minRange;
			Biome biome = world.getBiome(new BlockPos(x, 0, z));
			
			if((allowedBiomes.size() == 0 || WorldHelper.biomeIsOfType(allowedBiomes, biome)) && !WorldHelper.biomeIsOfType(avoidBiomes, biome)) {
				pos = new BlockPos(x, 0, z);
			}
			else {
				tries--;
			}
		}
		
		if(tries <= 0) {
			ModLog.warn("Could not find location for ruin, putting randomly");
			pos = findPosition(world, ImmutableList.of(), avoidBiomes);
		}
		
		return pos;
	}
	
	public Ruin getRuinByRune(EnumRuneType rune) {
		switch(rune) {
			case AIR:
				return airRuin;
			case ASTRAL:
				return astralRuin;
			case BLOOD:
				return bloodRuin;
			case BODY:
				return bodyRuin;
			case CHAOS:
				return chaosRuin;
			case COSMIC:
				return cosmicRuin;
			case DEATH:
				return deathRuin;
			case EARTH:
				return earthRuin;
			case FIRE:
				return fireRuin;
			case LAW:
				return lawRuin;
			case MIND:
				return mindRuin;
			case NATURE:
				return natureRuin;
			case SOUL:
				return soulRuin;
			case WATER:
				return waterRuin;
			default:
				return ouraniaRuin;
		}
	}
	
	public class Ruin {
		
		private final String name;
		
		private final String island;
		
		private BlockPos ruinPos;
		
		private boolean isGenerated;
		
		public Ruin(String name) {
			this(name, null);
		}
		
		public Ruin(String name, String island) {
			this.name = name;
			this.island = island;
		}
		
		public BlockPos getRuinPos() {
			return ruinPos;
		}
		
		public void setRuinPos(BlockPos ruinPos, World world) {
			this.ruinPos = ruinPos;
			ModLog.info(name + ": " + ruinPos);
			RuinNBTHelper nbt = RuinNBTHelper.get(world);
			String runeName = name.substring(0, name.indexOf('_'));
			nbt.posMap.put(runeName, ruinPos);
			nbt.markDirty();
		}
		
		public String getName() {
			return name;
		}
		
		public boolean isGenerated() {
			return isGenerated;
		}
		
		public void setGenerated(boolean generated) {
			isGenerated = generated;
		}
		
		public String getIsland() {
			return island;
		}
	}
}
