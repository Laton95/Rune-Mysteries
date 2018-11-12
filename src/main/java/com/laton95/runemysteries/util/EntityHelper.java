package com.laton95.runemysteries.util;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import com.laton95.runemysteries.init.ModItems;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityHelper
{
	
	@SubscribeEvent
	public static void livingDeathHandler(LivingDeathEvent event)
	{
		if(!event.getEntity().world.isRemote && event.getEntity() instanceof EntityWither)
		{
			event.getEntity().entityDropItem(new ItemStack(ModItems.DEATH_TALISMAN, 1), 0);
		}
		
		if(!event.getEntity().world.isRemote && event.getEntity() instanceof EntityPlayer && event.getSource() == DamageSource.LAVA)
		{
			Triggers.FIRE_DEATH.trigger((EntityPlayerMP) event.getEntity());
		}
	}
}
