package com.laton95.runemysteries.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen parentScreen, List<IConfigElement> configElements, String modID,
			boolean allRequireWorldRestart, boolean allRequireMcRestart, String title) {
		super(parentScreen, configElements, modID, allRequireWorldRestart, allRequireMcRestart, title);
	}
}
