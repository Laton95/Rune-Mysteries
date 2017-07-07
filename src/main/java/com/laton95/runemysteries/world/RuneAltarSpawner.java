package com.laton95.runemysteries.world;

import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RuneAltarSpawner {
	
	@SubscribeEvent
	public void getAltarPositions(InitMapGenEvent event) {
		
	}
}
