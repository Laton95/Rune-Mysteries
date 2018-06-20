package com.laton95.runemysteries.init;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModAdvancements
{
	public static void RegisterAdvancementTriggers()
	{
		Method method;
		try
		{
			method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);
			method.setAccessible(true);
			for(int i = 0; i < Triggers.TRIGGER_ARRAY.length; i++)
			{
				method.invoke(null, Triggers.TRIGGER_ARRAY[i]);
			}
		}
		catch(SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
		
		}
	}
}
