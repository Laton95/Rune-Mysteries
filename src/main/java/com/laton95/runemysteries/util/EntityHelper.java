package com.laton95.runemysteries.util;

import com.laton95.runemysteries.init.ItemRegistry;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityHelper
{
	
	@SubscribeEvent
	public static void livingDeathHandler(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof EntityWither && !event.getEntity().world.isRemote)
		{
			event.getEntity().entityDropItem(new ItemStack(ItemRegistry.RUNE_TALISMAN, 1, 6), 0);
		}
	}
}
