package com.laton95.test.client.gui;

import java.util.List;

import com.laton95.test.handler.ConfigurationHandler;
import com.laton95.test.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig{

	public ModGuiConfig(GuiScreen parentScreen, List<IConfigElement> configElements, String modID,
			boolean allRequireWorldRestart, boolean allRequireMcRestart, String title) {
		super(parentScreen, configElements, modID, allRequireWorldRestart, allRequireMcRestart, title);
		// TODO Auto-generated constructor stub
	}
}
