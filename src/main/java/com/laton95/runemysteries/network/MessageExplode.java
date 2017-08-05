package com.laton95.runemysteries.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageExplode extends RMModMessageBase<MessageExplode> {

	private float explosionSize = 0;
	private boolean destroysBlocks = false;
	
	public MessageExplode(){}
	
	public MessageExplode(float explosionSize, boolean destroysBlocks) {
		this.explosionSize = explosionSize;
		this.destroysBlocks = destroysBlocks;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		explosionSize = buf.readFloat();
		destroysBlocks = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(explosionSize);
		buf.writeBoolean(destroysBlocks);
	}

	@Override
	public void handleClientSide(MessageExplode message, EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(MessageExplode message, EntityPlayer player) {
		player.world.createExplosion(player, player.posX, player.posY, player.posZ, message.explosionSize, message.destroysBlocks);
	}

}
