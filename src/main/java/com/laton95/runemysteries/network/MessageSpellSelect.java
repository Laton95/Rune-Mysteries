package com.laton95.runemysteries.network;

import com.laton95.runemysteries.item.ItemSpellbook;
import com.laton95.runemysteries.spells.Spell;
import com.laton95.runemysteries.spells.Spells;
import com.laton95.runemysteries.util.ItemNBTHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageSpellSelect extends RMModMessageBase<MessageSpellSelect> {
	private int spellID;
	
	public MessageSpellSelect(){}
	
	public MessageSpellSelect(Spell spell) {
		spellID = Spells.spellList.indexOf(spell);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		spellID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(spellID);
	}

	@Override
	public void handleClientSide(MessageSpellSelect message, EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(MessageSpellSelect message, EntityPlayer player) {
		ItemStack spellbook = player.getHeldItemMainhand();
		ItemStack spellbook2 = player.getHeldItemOffhand();
		if (spellbook.getItem() instanceof ItemSpellbook) {
			ItemNBTHelper.setSpell(spellbook, Spells.spellList.get(message.spellID));
		} else if (spellbook2.getItem() instanceof ItemSpellbook) {
			ItemNBTHelper.setSpell(spellbook2, Spells.spellList.get(message.spellID));
		}
	}

}
