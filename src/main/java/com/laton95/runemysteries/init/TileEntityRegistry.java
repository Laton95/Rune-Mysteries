package com.laton95.runemysteries.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.laton95.runemysteries.block.BlockAltarPortal;
import com.laton95.runemysteries.block.BlockOuraniaAltar;
import com.laton95.runemysteries.block.BlockParticleLight;
import com.laton95.runemysteries.block.RMModRail;
import com.laton95.runemysteries.block.BlockRuneAltar;
import com.laton95.runemysteries.block.BlockRuneAltarEntrance;
import com.laton95.runemysteries.block.BlockRuneEssence;
import com.laton95.runemysteries.block.BlockStationStone;
import com.laton95.runemysteries.block.RMModBlock;
import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMModStairs;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.tileentity.TileEntityAltarPortal;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class TileEntityRegistry {
	private static Map<String, Class<? extends TileEntity>> tileEntityMap = new HashMap<>();
	
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<Block> event) {
		LogHelper.info("Registering tile entities");
		makeTileEntityMap();
		tileEntityMap.forEach((k, v) -> {
			GameRegistry.registerTileEntity(v, k);
		});
	}

	private static void makeTileEntityMap() {
		tileEntityMap.put(ModReference.MOD_ID + ":" + "altar_portal", TileEntityAltarPortal.class);
	}
}
