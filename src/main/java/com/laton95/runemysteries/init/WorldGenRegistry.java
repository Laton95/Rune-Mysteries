package com.laton95.runemysteries.init;

import java.util.ArrayList;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.world.OreGen;
import com.laton95.runemysteries.world.RuneAltarGen;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class WorldGenRegistry {
	private static ArrayList<IWorldGenerator> genList = new ArrayList<IWorldGenerator>();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		LogHelper.info("Registering worldgen...");
		makeGenList();
		for (IWorldGenerator generator : genList) {
			GameRegistry.registerWorldGenerator(generator, 0);
			LogHelper.info("Rune essence registered to worldgen");
		}
		LogHelper.info("All worldgen registered successfully");
	}

	private static void makeGenList() {
		genList.add(new OreGen());
		genList.add(new RuneAltarGen());
	}
}
