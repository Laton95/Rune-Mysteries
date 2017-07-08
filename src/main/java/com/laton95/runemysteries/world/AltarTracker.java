package com.laton95.runemysteries.world;

import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class AltarTracker {
	private Map<String, BlockPos> altarMap = new HashMap<>();
	public Map<String, Boolean> altarPlacedMap = new HashMap<>();
	public int altarRadius = 10;
	
	private boolean flag;
	private String altar;
	
	
	
	
	
	public AltarTracker() {
		altarPlacedMap.put("astralaltar", false);
		altarPlacedMap.put("deathaltar", false);
		altarPlacedMap.put("bodyaltar", false);
		altarPlacedMap.put("bloodaltar", false);
		altarPlacedMap.put("airaltar", false);
		altarPlacedMap.put("mindaltar", false);
		altarPlacedMap.put("earthaltar", false);
		altarPlacedMap.put("wateraltar", false);
		altarPlacedMap.put("soulaltar", false);
		altarPlacedMap.put("lawaltar", false);
		altarPlacedMap.put("firealtar", false);
		altarPlacedMap.put("naturealtar", false);
	}
	
	public void printAltarChunks() {
		altarMap.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			LogHelper.info(k + " at " + vPos.getXStart() + "," + vPos.getZStart());
		});
	}
	
	public void printAltar(String altar) {
		BlockPos pos = altarMap.get(altar);
		LogHelper.info(altar + " at " + pos.getX() + "," + pos.getY() + "," + pos.getZ());
	}
	
	public boolean isCloseToChunks(ChunkPos pos){
		flag = false;
		altarMap.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			if (WorldHelper.isNearby(vPos, pos, altarRadius)) {
				flag = true;
			}
		});
		return flag;
	}
	
	public boolean isInChunks(ChunkPos pos) {
		flag = false;
		altarMap.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			if (vPos.x == pos.x && vPos.z == pos.z) {
				flag = true;
			}
		});
		return flag;
	}
	
	public String getAltar(ChunkPos pos) {
		altar = "";
		altarMap.forEach((k, v) -> {
			ChunkPos vPos = new ChunkPos(v);
			if (WorldHelper.isNearby(pos, vPos, altarRadius)) {
				altar = k;
			}
		});
		return altar;
	}
	
	public void addAltar(String altar, ChunkPos chunkPos){
		BlockPos pos = new BlockPos(chunkPos.getXStart(), 64, chunkPos.getXEnd());
		altarMap.put(altar, pos);
	}
	
	public void updateAltar(String altar, BlockPos pos) {
		altarMap.put(altar, pos);
	}
	
	public void wipeAltarChunks() {
		altarMap.clear();
	}
}
