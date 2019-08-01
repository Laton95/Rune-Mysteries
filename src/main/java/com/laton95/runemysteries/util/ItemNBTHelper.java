/**
 *
 */
package com.laton95.runemysteries.util;

import net.minecraft.item.ItemStack;

public class ItemNBTHelper {
	
	public static boolean hasTag(ItemStack itemStack, String keyName) {
		return itemStack != null && itemStack.hasTag() && itemStack.getOrCreateTag().contains(keyName);
	}
	
	public static void removeTag(ItemStack itemStack, String keyName) {
		if(itemStack.hasTag()) {
			itemStack.getOrCreateTag().remove(keyName);
		}
	}
	
	// String
	public static String getString(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setString(itemStack, keyName, "");
		}
		
		return itemStack.getOrCreateTag().getString(keyName);
	}
	
	public static void setString(ItemStack itemStack, String keyName, String keyValue) {
		itemStack.getOrCreateTag().putString(keyName, keyValue);
	}
	
	public static boolean toggleBoolean(ItemStack itemStack, String keyName, boolean defaultValue) {
		return setBoolean(itemStack, keyName, !getBoolean(itemStack, keyName, defaultValue));
	}
	
	public static boolean setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {
		itemStack.getOrCreateTag().putBoolean(keyName, keyValue);
		return keyValue;
	}
	
	// boolean
	public static boolean getBoolean(ItemStack itemStack, String keyName, boolean defaultValue) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setBoolean(itemStack, keyName, defaultValue);
		}
		
		return itemStack.getOrCreateTag().getBoolean(keyName);
	}
	
	// byte
	public static byte getByte(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setByte(itemStack, keyName, (byte) 0);
		}
		
		return itemStack.getOrCreateTag().getByte(keyName);
	}
	
	public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {
		itemStack.getOrCreateTag().putByte(keyName, keyValue);
	}
	
	// short
	public static short getShort(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setShort(itemStack, keyName, (short) 0);
		}
		
		return itemStack.getOrCreateTag().getShort(keyName);
	}
	
	public static void setShort(ItemStack itemStack, String keyName, short keyValue) {
		itemStack.getOrCreateTag().putShort(keyName, keyValue);
	}
	
	// long
	public static long getLong(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setLong(itemStack, keyName, 0);
		}
		
		return itemStack.getOrCreateTag().getLong(keyName);
	}
	
	public static void setLong(ItemStack itemStack, String keyName, long keyValue) {
		itemStack.getOrCreateTag().putLong(keyName, keyValue);
	}
	
	// float
	public static float getFloat(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setFloat(itemStack, keyName, 0);
		}
		
		return itemStack.getOrCreateTag().getFloat(keyName);
	}
	
	public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {
		itemStack.getOrCreateTag().putFloat(keyName, keyValue);
	}
	
	// double
	public static double getDouble(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setDouble(itemStack, keyName, 0);
		}
		
		return itemStack.getOrCreateTag().getDouble(keyName);
	}
	
	public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {
		itemStack.getOrCreateTag().putDouble(keyName, keyValue);
	}
	
	// Spell
	//	public static SpellBase getSpell(ItemStack itemStack)
	//	{
	//		initNBTTagCompound(itemStack);
	//
	//		if(!itemStack.getOrCreateTag().contains("spell"))
	//		{
	//			setSpell(itemStack, Spells.NONE_SPELL);
	//		}
	//
	//		int spellID = ItemNBTHelper.getInt(itemStack, "spell");
	//		if(spellID >= 0)
	//		{
	//			return Spells.spellList.get(spellID);
	//		}
	//		else
	//		{
	//			return Spells.NONE_SPELL;
	//		}
	//
	//	}
	//
	//	public static void setSpell(ItemStack itemStack, SpellBase spell)
	//	{
	//		initNBTTagCompound(itemStack);
	//
	//		itemStack.getOrCreateTag().putInteger("spell", Spells.spellList.indexOf(spell));
	//	}
	
	// int
	public static int getInt(ItemStack itemStack, String keyName) {
		if(!itemStack.getOrCreateTag().contains(keyName)) {
			setInteger(itemStack, keyName, 0);
		}
		
		return itemStack.getOrCreateTag().getInt(keyName);
	}
	
	public static void setInteger(ItemStack itemStack, String keyName, int keyValue) {
		itemStack.getOrCreateTag().putInt(keyName, keyValue);
	}
}
