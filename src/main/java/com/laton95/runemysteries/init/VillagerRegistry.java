package com.laton95.runemysteries.init;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.VillagerTradeHelper;
import com.laton95.runemysteries.world.VillageWizardsHouse;

import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerRegistry
{

	public static VillagerProfession villagerProfession_Wizard;

	public static void registerVillage()
	{
		net.minecraftforge.fml.common.registry.VillagerRegistry.instance().registerVillageCreationHandler(
				new VillageWizardsHouse.VillageManager());
		MapGenStructureIO.registerStructureComponent(VillageWizardsHouse.class, "WizardHouse");

		villagerProfession_Wizard = new VillagerProfession(ModReference.MOD_ID
				+ ":wizard", "runemysteries:textures/entity/villager/wizard.png", "runemysteries:textures/entity/villager/wizard_zombie.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(villagerProfession_Wizard);

		VillagerCareer career_Runecrafter = new VillagerCareer(villagerProfession_Wizard, ModReference.MOD_ID
				+ ".runecrafter");
		career_Runecrafter.addTrade(1,
				new VillagerTradeHelper.Trade(Items.EMERALD, new PriceInfo(1, 2), ItemRegistry.MINE_SCROLL, new PriceInfo(2, 4)));
		// career_Runecrafter.addTrade(2,
		// new IEVillagerTrades.EmeraldForItemstack(new
		// ItemStack(itemMaterial, 1, 1), new EntityVillager.PriceInfo(2,
		// 6)),
		// new IEVillagerTrades.ItemstackForEmerald(new
		// ItemStack(blockMetalDecoration1, 1, 1), new
		// EntityVillager.PriceInfo(-8, -4)),
		// new IEVillagerTrades.ItemstackForEmerald(new
		// ItemStack(blockMetalDecoration1, 1, 5), new
		// EntityVillager.PriceInfo(-8, -4))
		// );
		// career_Runecrafter.addTrade(3,
		// new IEVillagerTrades.EmeraldForItemstack(new
		// ItemStack(itemMaterial, 1, 2), new EntityVillager.PriceInfo(2,
		// 6)),
		// new IEVillagerTrades.EmeraldForItemstack(new
		// ItemStack(itemMaterial, 1, 7), new EntityVillager.PriceInfo(4,
		// 8)),
		// new IEVillagerTrades.ItemstackForEmerald(new
		// ItemStack(blockStoneDecoration, 1, 5), new
		// EntityVillager.PriceInfo(-6, -2))
		// );
	}
}
