package com.laton95.runemysteries.client.gui;

import java.util.Set;

import com.laton95.runemysteries.handler.ConfigurationHandler;
import com.laton95.runemysteries.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;

public class GuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public boolean hasConfigGui() {
		return false;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ModGuiConfig(parentScreen,
				new ConfigElement(ConfigurationHandler.configuration.getCategory("World Generation"))
						.getChildElements(),
				Reference.MOD_ID, false, true, Reference.MOD_NAME + " Configuration");
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

}
