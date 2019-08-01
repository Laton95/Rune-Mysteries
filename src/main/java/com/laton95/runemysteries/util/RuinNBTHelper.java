package com.laton95.runemysteries.util;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;

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
		return null;
//		WorldSavedDataStorage storage = world.getSavedDataStorage();
//		RuinNBTHelper instance = storage.get(DimensionType.OVERWORLD, RuinNBTHelper::new, DATA_NAME);
//
//		if(instance == null) {
//			instance = new RuinNBTHelper();
//			storage.set(DimensionType.OVERWORLD, DATA_NAME, instance);
//			instance.markDirty();
//		}
//		return instance;
	}
	
	public RuinNBTHelper(String s) {
		super(s);
	}
	
	@Override
	public void read(CompoundNBT nbt) {
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
	public CompoundNBT write(CompoundNBT compound) {
		compound.putBoolean("overworldRuinsGenerated", overworldRuinsGenerated);
		compound.putBoolean("netherRuinsGenerated", netherRuinsGenerated);
		compound.putBoolean("endRuinsGenerated", endRuinsGenerated);
		
		compound.putIntArray("airRuinPos", blockPosToIntArray(posMap.get("air")));
		compound.putIntArray("astralRuinPos", blockPosToIntArray(posMap.get("astral")));
		compound.putIntArray("bloodRuinPos", blockPosToIntArray(posMap.get("blood")));
		compound.putIntArray("bodyRuinPos", blockPosToIntArray(posMap.get("body")));
		compound.putIntArray("chaosRuinPos", blockPosToIntArray(posMap.get("chaos")));
		compound.putIntArray("cosmicRuinPos", blockPosToIntArray(posMap.get("cosmic")));
		compound.putIntArray("deathRuinPos", blockPosToIntArray(posMap.get("death")));
		compound.putIntArray("earthRuinPos", blockPosToIntArray(posMap.get("earth")));
		compound.putIntArray("fireRuinPos", blockPosToIntArray(posMap.get("fire")));
		compound.putIntArray("lawRuinPos", blockPosToIntArray(posMap.get("law")));
		compound.putIntArray("mindRuinPos", blockPosToIntArray(posMap.get("mind")));
		compound.putIntArray("natureRuinPos", blockPosToIntArray(posMap.get("nature")));
		compound.putIntArray("soulRuinPos", blockPosToIntArray(posMap.get("soul")));
		compound.putIntArray("waterRuinPos", blockPosToIntArray(posMap.get("water")));
		compound.putIntArray("ouraniaRuinPos", blockPosToIntArray(posMap.get("ourania")));
		
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
