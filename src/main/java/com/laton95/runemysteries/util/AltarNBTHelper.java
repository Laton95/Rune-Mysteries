package com.laton95.runemysteries.util;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class AltarNBTHelper extends WorldSavedData
{

	private static final String DATA_NAME = ModReference.MOD_ID;

	public boolean overworldAltarsGenerated;
	public boolean netherAltarsGenerated;
	public boolean endAltarsGenerated;

	public Map<String, BlockPos> posMap = new HashMap<>();
	public Map<String, Boolean> placedMap = new HashMap<>();

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

		boolean airAltarPlaced = nbt.getBoolean("air_altar");
		boolean astralAltarPlaced = nbt.getBoolean("astral_altar");
		boolean bloodAltarPlaced = nbt.getBoolean("blood_altar");
		boolean bodyAltarPlaced = nbt.getBoolean("body_altar");
		boolean chaosAltarPlaced = nbt.getBoolean("chaos_altar");
		boolean cosmicAltarPlaced = nbt.getBoolean("cosmic_altar");
		boolean deathAltarPlaced = nbt.getBoolean("death_altar");
		boolean earthAltarPlaced = nbt.getBoolean("earth_altar");
		boolean fireAltarPlaced = nbt.getBoolean("fire_altar");
		boolean lawAltarPlaced = nbt.getBoolean("law_altar");
		boolean mindAltarPlaced = nbt.getBoolean("mind_altar");
		boolean natureAltarPlaced = nbt.getBoolean("nature_altar");
		boolean soulAltarPlaced = nbt.getBoolean("soul_altar");
		boolean waterAltarPlaced = nbt.getBoolean("water_altar");
		boolean ouraniaAltarPlaced = nbt.getBoolean("ourania_altar");

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

		compound.setBoolean("air_altar", placedMap.get("air_altar"));
		compound.setBoolean("astral_altar", placedMap.get("astral_altar"));
		compound.setBoolean("blood_altar", placedMap.get("blood_altar"));
		compound.setBoolean("body_altar", placedMap.get("body_altar"));
		try
		{
			compound.setBoolean("chaos_altar", placedMap.get("chaos_altar"));
			compound.setBoolean("cosmic_altar", placedMap.get("cosmic_altar"));
		}
		catch (NullPointerException e)
		{
			// Nether and End generators have not been initialized yet,
			// ignore
			// these altars.
		}
		compound.setBoolean("death_altar", placedMap.get("death_altar"));
		compound.setBoolean("earth_altar", placedMap.get("earth_altar"));
		compound.setBoolean("fire_altar", placedMap.get("fire_altar"));
		compound.setBoolean("law_altar", placedMap.get("law_altar"));
		compound.setBoolean("mind_altar", placedMap.get("mind_altar"));
		compound.setBoolean("nature_altar", placedMap.get("nature_altar"));
		compound.setBoolean("soul_altar", placedMap.get("soul_altar"));
		compound.setBoolean("water_altar", placedMap.get("water_altar"));
		compound.setBoolean("ourania_altar", placedMap.get("ourania_altar"));

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
