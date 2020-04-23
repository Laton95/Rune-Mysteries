package com.laton95.runemysteries.client.renderer.dimension.cosmic;

import com.laton95.runemysteries.util.ModLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.client.IRenderHandler;

public class NoCloudsRenderer implements IRenderHandler {
	
	@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
		ModLog.info("render");
	}
}
