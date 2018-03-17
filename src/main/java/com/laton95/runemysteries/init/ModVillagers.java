package com.laton95.runemysteries.init;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.util.VillagerTradeHelper;
import com.laton95.runemysteries.world.VillageWizardsHouse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class ModVillagers
{
	public static VillagerProfession villagerProfession_Wizard;
	
	public static void registerVillage()
	{
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageWizardsHouse.VillageManager());
		MapGenStructureIO.registerStructureComponent(VillageWizardsHouse.class, "WizardHouse");
		
		villagerProfession_Wizard = new VillagerProfession(ModReference.MOD_ID + ":wizard", "runemysteries:textures/entity/villager/wizard.png", "runemysteries:textures/entity/villager/wizard_zombie.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(villagerProfession_Wizard);
		
		VillagerCareer career_Runecrafter = new VillagerCareer(villagerProfession_Wizard, ModReference.MOD_ID + ".runecrafter");
		career_Runecrafter.addTrade(1, new VillagerTradeHelper.Trade(Items.EMERALD, new EntityVillager.PriceInfo(1, 2), 0, ModItems.MINE_SCROLL, new EntityVillager.PriceInfo(2, 4), 0));
		career_Runecrafter.addTrade(2, new VillagerTradeHelper.Trade(ModItems.RUNE_TALISMAN, new EntityVillager.PriceInfo(1, 1), 13, Items.EMERALD, new EntityVillager.PriceInfo(3, 6), 0));
		career_Runecrafter.addTrade(3, new VillagerTradeHelper.Trade(Items.EMERALD, new EntityVillager.PriceInfo(8, 12), 0, ModItems.RUNE_TALISMAN, new EntityVillager.PriceInfo(1, 1), 13));
	}
}
