package com.laton95.runemysteries.util;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;

public class AltarNBTHelper extends WorldSavedData
{
	
	private static final String DATA_NAME = ModReference.MOD_ID;
	
	public boolean overworldAltarsGenerated;
	public boolean netherAltarsGenerated;
	public boolean endAltarsGenerated;
	
	public Map<String, BlockPos> posMap = new HashMap<>();
	public Map<String, Boolean> placedMap = new HashMap<>();
	public Map<String, Boolean> generatedMap = new HashMap<>();
	
	// Required constructors
	public AltarNBTHelper()
	{
		super(DATA_NAME);
	}
	
	public AltarNBTHelper(String s)
	{
		super(s);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		overworldAltarsGenerated = nbt.getBoolean("overworldAltarsGenerated");
		netherAltarsGenerated = nbt.getBoolean("netherAltarsGenerated");
		endAltarsGenerated = nbt.getBoolean("endAltarsGenerated");
		
		BlockPos airAltarPos = intArrayToBlockPos(nbt.getIntArray("airAltarPos"));
		BlockPos astralAltarPos = intArrayToBlockPos(nbt.getIntArray("astralAltarPos"));
		BlockPos bloodAltarPos = intArrayToBlockPos(nbt.getIntArray("bloodAltarPos"));
		BlockPos bodyAltarPos = intArrayToBlockPos(nbt.getIntArray("bodyAltarPos"));
		BlockPos chaosAltarPos = intArrayToBlockPos(nbt.getIntArray("chaosAltarPos"));
		BlockPos cosmicAltarPos = intArrayToBlockPos(nbt.getIntArray("cosmicAltarPos"));
		BlockPos deathAltarPos = intArrayToBlockPos(nbt.getIntArray("deathAltarPos"));
		BlockPos earthAltarPos = intArrayToBlockPos(nbt.getIntArray("earthAltarPos"));
		BlockPos fireAltarPos = intArrayToBlockPos(nbt.getIntArray("fireAltarPos"));
		BlockPos lawAltarPos = intArrayToBlockPos(nbt.getIntArray("lawAltarPos"));
		BlockPos mindAltarPos = intArrayToBlockPos(nbt.getIntArray("mindAltarPos"));
		BlockPos natureAltarPos = intArrayToBlockPos(nbt.getIntArray("natureAltarPos"));
		BlockPos soulAltarPos = intArrayToBlockPos(nbt.getIntArray("soulAltarPos"));
		BlockPos waterAltarPos = intArrayToBlockPos(nbt.getIntArray("waterAltarPos"));
		BlockPos ouraniaAltarPos = intArrayToBlockPos(nbt.getIntArray("ouraniaAltarPos"));
		
		boolean airAltarPlaced = nbt.getBoolean("air_altar_placed");
		boolean astralAltarPlaced = nbt.getBoolean("astral_altar_placed");
		boolean bloodAltarPlaced = nbt.getBoolean("blood_altar_placed");
		boolean bodyAltarPlaced = nbt.getBoolean("body_altar_placed");
		boolean chaosAltarPlaced = nbt.getBoolean("chaos_altar_placed");
		boolean cosmicAltarPlaced = nbt.getBoolean("cosmic_altar_placed");
		boolean deathAltarPlaced = nbt.getBoolean("death_altar_placed");
		boolean earthAltarPlaced = nbt.getBoolean("earth_altar_placed");
		boolean fireAltarPlaced = nbt.getBoolean("fire_altar_placed");
		boolean lawAltarPlaced = nbt.getBoolean("law_altar_placed");
		boolean mindAltarPlaced = nbt.getBoolean("mind_altar_placed");
		boolean natureAltarPlaced = nbt.getBoolean("nature_altar_placed");
		boolean soulAltarPlaced = nbt.getBoolean("soul_altar_placed");
		boolean waterAltarPlaced = nbt.getBoolean("water_altar_placed");
		boolean ouraniaAltarPlaced = nbt.getBoolean("ourania_altar_placed");
		
		boolean airAltarGenerated = nbt.getBoolean("air_altar_generated");
		boolean astralAltarGenerated = nbt.getBoolean("astral_altar_generated");
		boolean bloodAltarGenerated = nbt.getBoolean("blood_altar_generated");
		boolean bodyAltarGenerated = nbt.getBoolean("body_altar_generated");
		boolean chaosAltarGenerated = nbt.getBoolean("chaos_altar_generated");
		boolean cosmicAltarGenerated = nbt.getBoolean("cosmic_altar_generated");
		boolean deathAltarGenerated = nbt.getBoolean("death_altar_generated");
		boolean earthAltarGenerated = nbt.getBoolean("earth_altar_generated");
		boolean fireAltarGenerated = nbt.getBoolean("fire_altar_generated");
		boolean lawAltarGenerated = nbt.getBoolean("law_altar_generated");
		boolean mindAltarGenerated = nbt.getBoolean("mind_altar_generated");
		boolean natureAltarGenerated = nbt.getBoolean("nature_altar_generated");
		boolean soulAltarGenerated = nbt.getBoolean("soul_altar_generated");
		boolean waterAltarGenerated = nbt.getBoolean("water_altar_generated");
		boolean ouraniaAltarGenerated = nbt.getBoolean("ourania_altar_generated");
		
		posMap.put("air_altar", airAltarPos);
		posMap.put("astral_altar", astralAltarPos);
		posMap.put("blood_altar", bloodAltarPos);
		posMap.put("body_altar", bodyAltarPos);
		posMap.put("chaos_altar", chaosAltarPos);
		posMap.put("cosmic_altar", cosmicAltarPos);
		posMap.put("death_altar", deathAltarPos);
		posMap.put("earth_altar", earthAltarPos);
		posMap.put("fire_altar", fireAltarPos);
		posMap.put("law_altar", lawAltarPos);
		posMap.put("mind_altar", mindAltarPos);
		posMap.put("nature_altar", natureAltarPos);
		posMap.put("soul_altar", soulAltarPos);
		posMap.put("water_altar", waterAltarPos);
		posMap.put("ourania_altar", ouraniaAltarPos);
		
		placedMap.put("air_altar", airAltarPlaced);
		placedMap.put("astral_altar", astralAltarPlaced);
		placedMap.put("blood_altar", bloodAltarPlaced);
		placedMap.put("body_altar", bodyAltarPlaced);
		placedMap.put("chaos_altar", chaosAltarPlaced);
		placedMap.put("cosmic_altar", cosmicAltarPlaced);
		placedMap.put("death_altar", deathAltarPlaced);
		placedMap.put("earth_altar", earthAltarPlaced);
		placedMap.put("fire_altar", fireAltarPlaced);
		placedMap.put("law_altar", lawAltarPlaced);
		placedMap.put("mind_altar", mindAltarPlaced);
		placedMap.put("nature_altar", natureAltarPlaced);
		placedMap.put("soul_altar", soulAltarPlaced);
		placedMap.put("water_altar", waterAltarPlaced);
		placedMap.put("ourania_altar", ouraniaAltarPlaced);
		
		generatedMap.put("air_altar", airAltarGenerated);
		generatedMap.put("astral_altar", astralAltarGenerated);
		generatedMap.put("blood_altar", bloodAltarGenerated);
		generatedMap.put("body_altar", bodyAltarGenerated);
		generatedMap.put("chaos_altar", chaosAltarGenerated);
		generatedMap.put("cosmic_altar", cosmicAltarGenerated);
		generatedMap.put("death_altar", deathAltarGenerated);
		generatedMap.put("earth_altar", earthAltarGenerated);
		generatedMap.put("fire_altar", fireAltarGenerated);
		generatedMap.put("law_altar", lawAltarGenerated);
		generatedMap.put("mind_altar", mindAltarGenerated);
		generatedMap.put("nature_altar", natureAltarGenerated);
		generatedMap.put("soul_altar", soulAltarGenerated);
		generatedMap.put("water_altar", waterAltarGenerated);
		generatedMap.put("ourania_altar", ouraniaAltarGenerated);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("overworldAltarsGenerated", overworldAltarsGenerated);
		compound.setBoolean("netherAltarsGenerated", netherAltarsGenerated);
		compound.setBoolean("endAltarsGenerated", endAltarsGenerated);
		
		compound.setIntArray("airAltarPos", blockPosToIntArray(posMap.get("air_altar")));
		compound.setIntArray("astralAltarPos", blockPosToIntArray(posMap.get("astral_altar")));
		compound.setIntArray("bloodAltarPos", blockPosToIntArray(posMap.get("blood_altar")));
		compound.setIntArray("bodyAltarPos", blockPosToIntArray(posMap.get("body_altar")));
		compound.setIntArray("chaosAltarPos", blockPosToIntArray(posMap.get("chaos_altar")));
		compound.setIntArray("cosmicAltarPos", blockPosToIntArray(posMap.get("cosmic_altar")));
		compound.setIntArray("deathAltarPos", blockPosToIntArray(posMap.get("death_altar")));
		compound.setIntArray("earthAltarPos", blockPosToIntArray(posMap.get("earth_altar")));
		compound.setIntArray("fireAltarPos", blockPosToIntArray(posMap.get("fire_altar")));
		compound.setIntArray("lawAltarPos", blockPosToIntArray(posMap.get("law_altar")));
		compound.setIntArray("mindAltarPos", blockPosToIntArray(posMap.get("mind_altar")));
		compound.setIntArray("natureAltarPos", blockPosToIntArray(posMap.get("nature_altar")));
		compound.setIntArray("soulAltarPos", blockPosToIntArray(posMap.get("soul_altar")));
		compound.setIntArray("waterAltarPos", blockPosToIntArray(posMap.get("water_altar")));
		compound.setIntArray("ouraniaAltarPos", blockPosToIntArray(posMap.get("ourania_altar")));
		
		compound.setBoolean("air_altar_placed", placedMap.get("air_altar"));
		compound.setBoolean("astral_altar_placed", placedMap.get("astral_altar"));
		compound.setBoolean("blood_altar_placed", placedMap.get("blood_altar"));
		compound.setBoolean("body_altar_placed", placedMap.get("body_altar"));
		try
		{
			compound.setBoolean("chaos_altar_placed", placedMap.get("chaos_altar"));
			compound.setBoolean("cosmic_altar_placed", placedMap.get("cosmic_altar"));
		} catch (NullPointerException e)
		{
			// Nether and End generators have not been initialized yet,
			// ignore
			// these altars.
		}
		compound.setBoolean("death_altar_placed", placedMap.get("death_altar"));
		compound.setBoolean("earth_altar_placed", placedMap.get("earth_altar"));
		compound.setBoolean("fire_altar_placed", placedMap.get("fire_altar"));
		compound.setBoolean("law_altar_placed", placedMap.get("law_altar"));
		compound.setBoolean("mind_altar_placed", placedMap.get("mind_altar"));
		compound.setBoolean("nature_altar_placed", placedMap.get("nature_altar"));
		compound.setBoolean("soul_altar_placed", placedMap.get("soul_altar"));
		compound.setBoolean("water_altar_placed", placedMap.get("water_altar"));
		compound.setBoolean("ourania_altar_placed", placedMap.get("ourania_altar"));
		
		compound.setBoolean("air_altar_generated", generatedMap.get("air_altar"));
		compound.setBoolean("astral_altar_generated", generatedMap.get("astral_altar"));
		compound.setBoolean("blood_altar_generated", generatedMap.get("blood_altar"));
		compound.setBoolean("body_altar_generated", generatedMap.get("body_altar"));
		try
		{
			compound.setBoolean("chaos_altar_generated", generatedMap.get("chaos_altar"));
			compound.setBoolean("cosmic_altar_generated", generatedMap.get("cosmic_altar"));
		} catch (NullPointerException e)
		{
			// Nether and End generators have not been initialized yet,
			// ignore
			// these altars.
		}
		compound.setBoolean("death_altar_generated", generatedMap.get("death_altar"));
		compound.setBoolean("earth_altar_generated", generatedMap.get("earth_altar"));
		compound.setBoolean("fire_altar_generated", generatedMap.get("fire_altar"));
		compound.setBoolean("law_altar_generated", generatedMap.get("law_altar"));
		compound.setBoolean("mind_altar_generated", generatedMap.get("mind_altar"));
		compound.setBoolean("nature_altar_generated", generatedMap.get("nature_altar"));
		compound.setBoolean("soul_altar_generated", generatedMap.get("soul_altar"));
		compound.setBoolean("water_altar_generated", generatedMap.get("water_altar"));
		compound.setBoolean("ourania_altar_generated", generatedMap.get("ourania_altar"));
		
		return compound;
	}
	
	public static AltarNBTHelper get(World world)
	{
		MapStorage storage = world.getMapStorage();
		AltarNBTHelper instance = (AltarNBTHelper) storage.getOrLoadData(AltarNBTHelper.class, DATA_NAME);
		
		if (instance == null)
		{
			instance = new AltarNBTHelper();
			storage.setData(DATA_NAME, instance);
			instance.markDirty();
		}
		return instance;
	}
	
	private BlockPos intArrayToBlockPos(int[] array)
	{
		return new BlockPos(array[0], array[1], array[2]);
	}
	
	private int[] blockPosToIntArray(BlockPos pos)
	{
		int[] array = new int[3];
		if (pos != null & array != null)
		{
			array[0] = pos.getX();
			array[1] = pos.getY();
			array[2] = pos.getZ();
		}
		return array;
	}
}
