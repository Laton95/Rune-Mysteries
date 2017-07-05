package com.laton95.runemysteries.reference;

import java.awt.List;
import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class WorldGen {
	public static LinkedList<Block> surfaceBlocks = new LinkedList<Block>();
	public static float structureFlatnessTolerance = 0.8f;
	
	public static void setupSurfaceBlocks() {
		surfaceBlocks.add(Blocks.SAND);
		surfaceBlocks.add(Blocks.GRASS);
		surfaceBlocks.add(Blocks.STONE);
		surfaceBlocks.add(Blocks.GRAVEL);
		surfaceBlocks.add(Blocks.DIRT);
	}
}
