package com.laton95.runemysteries.client.handler;

import com.laton95.runemysteries.client.settings.Keybindings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler
{

	private Keybindings getPressedKey()
	{
		for (Keybindings key : Keybindings.values())
		{
			if (key.isPressed())
			{
				return key;
			}
		}
		return null;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
	{
		Keybindings key = getPressedKey();
		if (key != null)
		{
			switch (key)
			{
				case TEST:
					// NetworkHandler.sendToServer(new MessageExplode(3, false));
					break;
			}
		}
	}
}
