package com.laton95.runemysteries.client.settings;

import org.lwjgl.input.Keyboard;

import com.laton95.runemysteries.reference.NamesReference;

import net.minecraft.client.settings.KeyBinding;

public enum Keybindings
{

	EXPLODE(NamesReference.Keys.TEST, Keyboard.KEY_G);

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
