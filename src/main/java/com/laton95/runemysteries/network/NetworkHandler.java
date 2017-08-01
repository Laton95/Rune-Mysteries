package com.laton95.runemysteries.network;

import java.lang.ref.Reference;

import com.laton95.runemysteries.reference.ModReference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

	private static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		 INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModReference.MOD_ID);
		 
		 INSTANCE.registerMessage(MessageExplode.class, MessageExplode.class, 0, Side.SERVER);
	}
	
	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
}
