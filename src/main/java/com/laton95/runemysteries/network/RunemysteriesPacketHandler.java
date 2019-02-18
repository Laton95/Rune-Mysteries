package com.laton95.runemysteries.network;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RunemysteriesPacketHandler {
	
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	
	private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(RuneMysteries.MOD_ID, "main_channel"))
																				.clientAcceptedVersions(PROTOCOL_VERSION::equals)
																				.serverAcceptedVersions(PROTOCOL_VERSION::equals)
																				.networkProtocolVersion(() -> PROTOCOL_VERSION)
																				.simpleChannel();
	
	public static void register()
	{
		int disc = 0;
		
		INSTANCE.registerMessage(disc++, PacketSyncRuneBag.class, PacketSyncRuneBag::encode, PacketSyncRuneBag::decode, PacketSyncRuneBag.Handler::handle);
	}
	
	public static void sendTo(Object msg, EntityPlayerMP player)
	{
		if (!(player instanceof FakePlayer))
		{
			INSTANCE.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
