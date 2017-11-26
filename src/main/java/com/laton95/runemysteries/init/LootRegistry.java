package com.laton95.runemysteries.init;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public final class LootRegistry
{

	public static final ResourceLocation OURANIA_ALTAR = LootTableList
			.register(new ResourceLocation(ModReference.MOD_ID, "altars/ourania_altar"));

	private static final List<String> TABLES = ImmutableList.of("inject/chests/abandoned_mineshaft",
			"inject/chests/desert_pyramid", "inject/chests/jungle_temple", "inject/chests/simple_dungeon",
			"inject/chests/spawn_bonus_chest", "inject/chests/village_blacksmith", "inject/chests/stronghold_corridor",
			"inject/chests/nether_bridge", "inject/chests/igloo_chest", "inject/chests/stronghold_crossing",
			"inject/chests/stronghold_library", "inject/chests/woodland_mansion", "inject/chests/end_city_treasure",
			"inject/gameplay/fishing/treasure", "inject/entities/blaze", "inject/entities/elder_guardian",
			"inject/entities/enderman", "inject/entities/endermite", "inject/entities/evocation_illager",
			"inject/entities/ghast", "inject/entities/guardian", "inject/entities/husk", "inject/entities/magma_cube",
			"inject/entities/silverfish", "inject/entities/skeleton", "inject/entities/slime", "inject/entities/squid",
			"inject/entities/stray", "inject/entities/vex", "inject/entities/vindication_illager",
			"inject/entities/wither_skeleton", "inject/entities/zombie_pigman", "inject/entities/zombie_villager",
			"inject/entities/zombie"

			);

	public LootRegistry()
	{
		LogHelper.info("Registering loot tables");
		for (String s : TABLES)
		{
			LootTableList.register(new ResourceLocation(ModReference.MOD_ID, s));
		}
	}

	@SubscribeEvent
	public void lootLoad(LootTableLoadEvent evt)
	{
		String prefix = "minecraft:";
		String name = evt.getName().toString();

		if (name.startsWith(prefix))
		{
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			switch (file)
			{
			case "chests/abandoned_mineshaft":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/desert_pyramid":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/jungle_temple":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/simple_dungeon":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/spawn_bonus_chest":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/stronghold_corridor":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/nether_bridge":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/igloo_chest":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/stronghold_crossing":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/stronghold_library":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/woodland_mansion":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/end_city_treasure":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "chests/village_blacksmith":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "gameplay/fishing/treasure":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/blaze":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/elder_guardian":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/enderman":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/endermite":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/evocation_illager":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/ghast":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/guardian":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/husk":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/magma_cube":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/silverfish":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/skeleton":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/slime":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/squid":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/stray":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/vex":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/vindication_illager":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/wither_skeleton":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/zombie_pigman":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/zombie_villager":
				evt.getTable().addPool(getInjectPool(file));
				break;
			case "entities/zombie":
				evt.getTable().addPool(getInjectPool(file));
				break;
			default:
				break;
			}
		}
	}

	private LootPool getInjectPool(String entryName)
	{
		return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0],
				new RandomValueRange(1), new RandomValueRange(0, 1), "runemysteries_inject_pool");
	}

	private LootEntryTable getInjectEntry(String name, int weight)
	{
		return new LootEntryTable(new ResourceLocation(ModReference.MOD_ID, "inject/" + name), weight, 0,
				new LootCondition[0], "runemysteries_inject_entry");
	}
}
