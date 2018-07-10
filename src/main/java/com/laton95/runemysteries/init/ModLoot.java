package com.laton95.runemysteries.init;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class ModLoot
{
	
	public static final ResourceLocation OURANIA_ALTAR = LootTableList.register(new ResourceLocation(ModReference.MOD_ID, "altars/ourania_altar"));
	
	public static final ResourceLocation ESSENCE_MINE = LootTableList.register(new ResourceLocation(ModReference.MOD_ID, "chests/essence_mine"));
	
	private static final String[] lootTables = {
			"inject/chests/abandoned_mineshaft",
			"inject/chests/desert_pyramid",
			"inject/chests/jungle_temple",
			"inject/chests/simple_dungeon",
			"inject/chests/spawn_bonus_chest",
			"inject/chests/village_blacksmith",
			"inject/chests/stronghold_corridor",
			"inject/chests/nether_bridge",
			"inject/chests/igloo_chest",
			"inject/chests/stronghold_crossing",
			"inject/chests/stronghold_library",
			"inject/chests/woodland_mansion",
			"inject/chests/end_city_treasure",
			"inject/gameplay/fishing/treasure",
			"inject/entities/blaze",
			"inject/entities/cave_spider",
			"inject/entities/creeper",
			"inject/entities/elder_guardian",
			"inject/entities/enderman",
			"inject/entities/endermite",
			"inject/entities/evocation_illager",
			"inject/entities/ghast",
			"inject/entities/guardian",
			"inject/entities/husk",
			"inject/entities/illusion_illager",
			"inject/entities/magma_cube",
			"inject/entities/parrot",
			"inject/entities/shulker",
			"inject/entities/silverfish",
			"inject/entities/skeleton",
			"inject/entities/slime",
			"inject/entities/spider",
			"inject/entities/squid",
			"inject/entities/stray",
			"inject/entities/vex",
			"inject/entities/vindication_illager",
			"inject/entities/witch",
			"inject/entities/wither_skeleton",
			"inject/entities/zombie_pigman",
			"inject/entities/zombie_villager",
			"inject/entities/zombie"
	};
	
	public ModLoot()
	{
		LogHelper.info("Registering loot tables");
		
		for(String path : lootTables)
		{
			LootTableList.register(new ResourceLocation(ModReference.MOD_ID, path));
		}
	}
	
	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent event)
	{
		String prefix = "minecraft:";
		String name = event.getName().toString();
		String suffix = name.substring(10);
		
		if(name.startsWith(prefix) && isValidLoottable(suffix))
		{
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			event.getTable().addPool(getInjectPool(file));
		}
	}
	
	private static boolean isValidLoottable(String suffix)
	{
		for(String s : lootTables)
		{
			if(s.endsWith(suffix))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private LootPool getInjectPool(String entryName)
	{
		return new LootPool(new LootEntry[] {getInjectEntry(entryName, 1)}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "runemysteries_inject_pool");
	}
	
	private LootEntryTable getInjectEntry(String name, int weight)
	{
		return new LootEntryTable(new ResourceLocation(ModReference.MOD_ID, "inject/" + name), weight, 0, new LootCondition[0], "runemysteries_inject_entry");
	}
}
