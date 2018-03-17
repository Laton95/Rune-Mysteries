package com.laton95.runemysteries.client.settings;

import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public enum Keybindings
{
	
	TEST(NamesReference.Keys.TEST, Keyboard.KEY_G);
	
	private final KeyBinding keyBinding;
	
	private Keybindings(String keyName, int defaultKeyValue)
	{
		keyBinding = new KeyBinding(keyName, defaultKeyValue, NamesReference.Keys.CATEGORY);
	}
	
	public KeyBinding getKeybind()
	{
		return keyBinding;
	}
	
	public boolean isPressed()
	{
		return keyBinding.isPressed();
	}
}
