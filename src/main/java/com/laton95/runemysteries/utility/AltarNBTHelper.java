package com.laton95.runemysteries.utility;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.BackoffManager;

import com.laton95.runemysteries.reference.Reference;

import jline.internal.Log;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants.NBT;
import scala.collection.generic.BitOperations.Int;

public class AltarNBTHelper extends WorldSavedData {
	private static final String DATA_NAME = Reference.MOD_ID;
	
	public boolean altarsGenerated;
	
	public Map<String, BlockPos> posMap = new HashMap<>();

	// Required constructors
	public AltarNBTHelper() {
		super(DATA_NAME);
	}

	public AltarNBTHelper(String s) {
		super(s);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		altarsGenerated = nbt.getBoolean("altarsGenerated");
		
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
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("altarsGenerated", altarsGenerated);
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
		
		return compound;
	}
	
	public static AltarNBTHelper get(World world) {
		  MapStorage storage = world.getMapStorage();
		  AltarNBTHelper instance = (AltarNBTHelper) storage.getOrLoadData(AltarNBTHelper.class, DATA_NAME);

		  if (instance == null) {
		    instance = new AltarNBTHelper();
		    storage.setData(DATA_NAME, instance);
		    instance.markDirty();
		  }
		  return instance;
		}
	
	private BlockPos intArrayToBlockPos(int[] array) {
		return new BlockPos(array[0], array[1], array[2]);
	}
	
	private int[] blockPosToIntArray(BlockPos pos) {
		int[] array = new int[3];
		if (pos != null & array != null) {
			array[0] = pos.getX();
			array[1] = pos.getY();
			array[2] = pos.getZ();
		}
		return array;
	}
}