package com.laton95.runemysteries.advancements;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCriteriaTriggers {
	
	public static final ModCriterion TOUCH_MONOLITH = new ModCriterion("black_monolith");
	
	public static final ModCriterion EX_PARROT = new ModCriterion("ex_parrot");
	
	public static final ModCriterion CRAFT_RUNE = new ModCriterion("craft_rune");
	
	public static final ModCriterion OURANIA = new ModCriterion("ourania");
	
	public static final ModCriterion CHARGE_ORB = new ModCriterion("charge_orb");
	
	public static final ModCriterion FIRE_DEATH = new ModCriterion("fire_altar_death");
	
	public static final ModCriterion ELDER_CATALYST = new ModCriterion("elder_catalyst");
	
	public static final ModCriterion DOLPHIN_CRAFT = new ModCriterion("dolphin_craft");
	
	private static final ModCriterion[] triggers = new ModCriterion[] {
			TOUCH_MONOLITH,
			EX_PARROT,
			CRAFT_RUNE,
			OURANIA,
			CHARGE_ORB,
			FIRE_DEATH,
			ELDER_CATALYST,
			DOLPHIN_CRAFT
	};
	
	@SubscribeEvent
	public static void registerAdvancementTriggers(FMLCommonSetupEvent event) {
		ModLog.info("Registering advancement triggers");
		
		for(ModCriterion trigger : triggers) {
			CriteriaTriggers.register(trigger);
		}
	}
}
