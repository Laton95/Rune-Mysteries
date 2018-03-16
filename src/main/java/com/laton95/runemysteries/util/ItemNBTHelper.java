/**
 *
 */
package com.laton95.runemysteries.util;

import com.laton95.runemysteries.spells.SpellBase;
import com.laton95.runemysteries.spells.Spells;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemNBTHelper
{
	
	public static boolean hasTag(ItemStack itemStack, String keyName)
	{
		return itemStack != null && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(keyName);
	}
	
	public static void removeTag(ItemStack itemStack, String keyName)
	{
		if (itemStack.hasTagCompound())
		{
			itemStack.getTagCompound().removeTag(keyName);
		}
	}
	
	/**
	 * Initializes the NBT Tag Compound for the given ItemStack if it is null
	 *
	 * @param itemStack The ItemStack for which its NBT Tag Compound is being checked
	 *                  for initialization
	 */
	private static void initNBTTagCompound(ItemStack itemStack)
	{
		if (!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static void setLong(ItemStack itemStack, String keyName, long keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setLong(keyName, keyValue);
	}
	
	// String
	public static String getString(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setString(itemStack, keyName, "");
		}
		
		return itemStack.getTagCompound().getString(keyName);
	}
	
	public static void setString(ItemStack itemStack, String keyName, String keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setString(keyName, keyValue);
	}
	
	// boolean
	public static boolean getBoolean(ItemStack itemStack, String keyName, boolean defaultValue)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setBoolean(itemStack, keyName, defaultValue);
		}
		
		return itemStack.getTagCompound().getBoolean(keyName);
	}
	
	public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setBoolean(keyName, keyValue);
	}
	
	public static void toggleBoolean(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setBoolean(keyName, !itemStack.getTagCompound().getBoolean(keyName));
	}
	
	// byte
	public static byte getByte(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setByte(itemStack, keyName, (byte) 0);
		}
		
		return itemStack.getTagCompound().getByte(keyName);
	}
	
	public static void setByte(ItemStack itemStack, String keyName, byte keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setByte(keyName, keyValue);
	}
	
	// short
	public static short getShort(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setShort(itemStack, keyName, (short) 0);
		}
		
		return itemStack.getTagCompound().getShort(keyName);
	}
	
	public static void setShort(ItemStack itemStack, String keyName, short keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setShort(keyName, keyValue);
	}
	
	// int
	public static int getInt(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setInteger(itemStack, keyName, 0);
		}
		
		return itemStack.getTagCompound().getInteger(keyName);
	}
	
	public static void setInteger(ItemStack itemStack, String keyName, int keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setInteger(keyName, keyValue);
	}
	
	// long
	public static long getLong(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setLong(itemStack, keyName, 0);
		}
		
		return itemStack.getTagCompound().getLong(keyName);
	}
	
	// float
	public static float getFloat(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setFloat(itemStack, keyName, 0);
		}
		
		return itemStack.getTagCompound().getFloat(keyName);
	}
	
	public static void setFloat(ItemStack itemStack, String keyName, float keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setFloat(keyName, keyValue);
	}
	
	// double
	public static double getDouble(ItemStack itemStack, String keyName)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey(keyName))
		{
			setDouble(itemStack, keyName, 0);
		}
		
		return itemStack.getTagCompound().getDouble(keyName);
	}
	
	public static void setDouble(ItemStack itemStack, String keyName, double keyValue)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setDouble(keyName, keyValue);
	}
	
	// Spell
	public static SpellBase getSpell(ItemStack itemStack)
	{
		initNBTTagCompound(itemStack);
		
		if (!itemStack.getTagCompound().hasKey("spell"))
		{
			setSpell(itemStack, Spells.NONE_SPELL);
		}
		
		int spellID = ItemNBTHelper.getInt(itemStack, "spell");
		if (spellID >= 0)
		{
			return Spells.spellList.get(spellID);
		} else
		{
			return Spells.NONE_SPELL;
		}
		
	}
	
	public static void setSpell(ItemStack itemStack, SpellBase spell)
	{
		initNBTTagCompound(itemStack);
		
		itemStack.getTagCompound().setInteger("spell", Spells.spellList.indexOf(spell));
	}
}
