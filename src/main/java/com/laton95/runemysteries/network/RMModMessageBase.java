package com.laton95.runemysteries.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class RMModMessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{

	@Override
	public REQ onMessage(REQ message, MessageContext ctx)
	{
		if (ctx.side == Side.SERVER)
		{
			handleServerSide(message, ctx.getServerHandler().player);
		}
		else
		{
			handleClientSide(message, null);
		}
		return null;
	}

	/**
	 * Handle a packet on the client side. Note this occurs after decoding has
	 * completed.
	 *
	 * @param message
	 * @param player
	 *            the player reference
	 */
	public abstract void handleClientSide(REQ message, EntityPlayer player);

	/**
	 * Handle a packet on the server side. Note this occurs after decoding has
	 * completed.
	 *
	 * @param message
	 * @param player
	 *            the player reference
	 */
	public abstract void handleServerSide(REQ message, EntityPlayer player);
}
