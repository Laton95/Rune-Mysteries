package com.laton95.runemysteries.util;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.WorldSavedDataStorage;

import java.util.HashMap;
import java.util.Map;

public class RuinNBTHelper extends WorldSavedData {
	
	private static final String DATA_NAME = RuneMysteries.MOD_ID;
	
	public boolean overworldRuinsGenerated;
	
	public boolean netherRuinsGenerated;
	
	public boolean endRuinsGenerated;
	
	public Map<String, BlockPos> posMap = new HashMap<>();
	
	public RuinNBTHelper() {
		super(DATA_NAME);
	}
	
	public static RuinNBTHelper get(World world) {
		WorldSavedDataStorage storage = world.getMapStorage();
		RuinNBTHelper instance = storage.func_212426_a(DimensionType.OVERWORLD, RuinNBTHelper::new, DATA_NAME);
		
		if(instance == null) {
			instance = new RuinNBTHelper();
			storage.func_212424_a(DimensionType.OVERWORLD, DATA_NAME, instance);
			instance.markDirty();
		}
		return instance;
	}
	
	public RuinNBTHelper(String s) {
		super(s);
	}
	
	@Override
	public void read(NBTTagCompound nbt) {
		overworldRuinsGenerated = nbt.getBoolean("overworldRuinsGenerated");
		netherRuinsGenerated = nbt.getBoolean("netherRuinsGenerated");
		endRuinsGenerated = nbt.getBoolean("endRuinsGenerated");
		
		BlockPos airRuinPos = intArrayToBlockPos(nbt.getIntArray("airRuinPos"));
		BlockPos astralRuinPos = intArrayToBlockPos(nbt.getIntArray("astralRuinPos"));
		BlockPos bloodRuinPos = intArrayToBlockPos(nbt.getIntArray("bloodRuinPos"));
		BlockPos bodyRuinPos = intArrayToBlockPos(nbt.getIntArray("bodyRuinPos"));
		BlockPos chaosRuinPos = intArrayToBlockPos(nbt.getIntArray("chaosRuinPos"));
		BlockPos cosmicRuinPos = intArrayToBlockPos(nbt.getIntArray("cosmicRuinPos"));
		BlockPos deathRuinPos = intArrayToBlockPos(nbt.getIntArray("deathRuinPos"));
		BlockPos earthRuinPos = intArrayToBlockPos(nbt.getIntArray("earthRuinPos"));
		BlockPos fireRuinPos = intArrayToBlockPos(nbt.getIntArray("fireRuinPos"));
		BlockPos lawRuinPos = intArrayToBlockPos(nbt.getIntArray("lawRuinPos"));
		BlockPos mindRuinPos = intArrayToBlockPos(nbt.getIntArray("mindRuinPos"));
		BlockPos natureRuinPos = intArrayToBlockPos(nbt.getIntArray("natureRuinPos"));
		BlockPos soulRuinPos = intArrayToBlockPos(nbt.getIntArray("soulRuinPos"));
		BlockPos waterRuinPos = intArrayToBlockPos(nbt.getIntArray("waterRuinPos"));
		BlockPos ouraniaRuinPos = intArrayToBlockPos(nbt.getIntArray("ouraniaRuinPos"));
		
		posMap.put("air", airRuinPos);
		posMap.put("astral", astralRuinPos);
		posMap.put("blood", bloodRuinPos);
		posMap.put("body", bodyRuinPos);
		posMap.put("chaos", chaosRuinPos);
		posMap.put("cosmic", cosmicRuinPos);
		posMap.put("death", deathRuinPos);
		posMap.put("earth", earthRuinPos);
		posMap.put("fire", fireRuinPos);
		posMap.put("law", lawRuinPos);
		posMap.put("mind", mindRuinPos);
		posMap.put("nature", natureRuinPos);
		posMap.put("soul", soulRuinPos);
		posMap.put("water", waterRuinPos);
		posMap.put("ourania", ouraniaRuinPos);
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		compound.setBoolean("overworldRuinsGenerated", overworldRuinsGenerated);
		compound.setBoolean("netherRuinsGenerated", netherRuinsGenerated);
		compound.setBoolean("endRuinsGenerated", endRuinsGenerated);
		
		compound.setIntArray("airRuinPos", blockPosToIntArray(posMap.get("air")));
		compound.setIntArray("astralRuinPos", blockPosToIntArray(posMap.get("astral")));
		compound.setIntArray("bloodRuinPos", blockPosToIntArray(posMap.get("blood")));
		compound.setIntArray("bodyRuinPos", blockPosToIntArray(posMap.get("body")));
		compound.setIntArray("chaosRuinPos", blockPosToIntArray(posMap.get("chaos")));
		compound.setIntArray("cosmicRuinPos", blockPosToIntArray(posMap.get("cosmic")));
		compound.setIntArray("deathRuinPos", blockPosToIntArray(posMap.get("death")));
		compound.setIntArray("earthRuinPos", blockPosToIntArray(posMap.get("earth")));
		compound.setIntArray("fireRuinPos", blockPosToIntArray(posMap.get("fire")));
		compound.setIntArray("lawRuinPos", blockPosToIntArray(posMap.get("law")));
		compound.setIntArray("mindRuinPos", blockPosToIntArray(posMap.get("mind")));
		compound.setIntArray("natureRuinPos", blockPosToIntArray(posMap.get("nature")));
		compound.setIntArray("soulRuinPos", blockPosToIntArray(posMap.get("soul")));
		compound.setIntArray("waterRuinPos", blockPosToIntArray(posMap.get("water")));
		compound.setIntArray("ouraniaRuinPos", blockPosToIntArray(posMap.get("ourania")));
		
		return compound;
	}
	
	private int[] blockPosToIntArray(BlockPos pos) {
		int[] array = new int[3];
		if(pos != null & array != null) {
			array[0] = pos.getX();
			array[1] = pos.getY();
			array[2] = pos.getZ();
		}
		return array;
	}
	
	private BlockPos intArrayToBlockPos(int[] array) {
		return new BlockPos(array[0], array[1], array[2]);
	}
}
