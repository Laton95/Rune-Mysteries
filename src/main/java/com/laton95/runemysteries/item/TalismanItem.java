package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.reference.StringReference.Talisman;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class TalismanItem extends ModItem {
	
	private final EnumRuneType runeType;
	
	public TalismanItem(EnumRuneType runeType) {
		super(new Properties().maxStackSize(1));
		this.runeType = runeType;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack talisman = player.getHeldItem(hand);
		
		if(!world.isRemote) {
			DimensionType dimType = runeType.getRuinDimType();
			
			BlockPos pos = null;
			
			//Get altar position
			try {
				pos = RuneMysteries.ruinTracker.getRuinByRune(runeType).getRuinPos();
			}
			catch(Exception e) {
				pos = new BlockPos(world.rand.nextInt(20000) - 10000, 100, world.rand.nextInt(20000) - 10000);
				ModLog.error(e.toString());
				ModLog.warn(String.format("An error occurred while getting %s altar location.", runeType.toString().toLowerCase()));
			}
			
			//TODO add proper config here
			if(pos == null) //|| !ModConfig.WORLD_GENERATION.rune_altars.generateRuneAltars)
			{
				player.sendMessage(new TranslationTextComponent(Talisman.FAIL));
				return new ActionResult<>(ActionResultType.SUCCESS, talisman);
			}
			
			if(player.isCreative() && player.isSneaking()) {
				if(pos.getY() != 0) {
					TeleportHelper.teleportEntity(player, dimType, pos.getX() + 2, pos.getY(), pos.getZ() + 2);
				}
				else {
					TeleportHelper.teleportEntity(player, dimType, pos.getX(), 100, pos.getZ());
				}
				
				return new ActionResult<>(ActionResultType.SUCCESS, talisman);
			}
			
			player.getCooldownTracker().setCooldown(this, 30);
			
			//Handle other dimension altars
			switch(dimType.getId()) {
				case 0:
					if(world.dimension.getType() != DimensionType.OVERWORLD) {
						player.sendMessage(new TranslationTextComponent(StringReference.Talisman.OVERWORLD));
						return new ActionResult<>(ActionResultType.SUCCESS, talisman);
					}
					break;
				case -1:
					if(world.dimension.getType() != DimensionType.THE_NETHER) {
						world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
						player.sendMessage(new TranslationTextComponent(StringReference.Talisman.NETHER));
						player.attackEntityFrom(new DamageSource(StringReference.DeathMessage.TALISMAN_NETHER), 2f);
						return new ActionResult<>(ActionResultType.SUCCESS, talisman);
					}
					break;
				case 1:
					if(world.dimension.getType() != DimensionType.THE_END) {
						world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
						player.sendMessage(new TranslationTextComponent(StringReference.Talisman.END));
						return new ActionResult<>(ActionResultType.SUCCESS, talisman);
					}
					break;
			}
			
			//Message player with appropriate directions
			if(WorldHelper.isNearby(player.getPosition(), pos, 5)) {
				player.sendMessage(new TranslationTextComponent(StringReference.Talisman.NEARBY));
			}
			else {
				Tuple<WorldHelper.HorizontalDirection, WorldHelper.VerticalDirection> direction = WorldHelper.getDirection(player.getPosition(), pos);
				
				String localisationString = "item.runemysteries.talisman.pull.";
				
				if(direction.getA() != WorldHelper.HorizontalDirection.NONE) {
					localisationString += direction.getA().toString().toLowerCase();
				}
				
				if(direction.getB() == WorldHelper.VerticalDirection.UP || direction.getB() == WorldHelper.VerticalDirection.DOWN) {
					if(direction.getA() != WorldHelper.HorizontalDirection.NONE) {
						localisationString += "_";
					}
					localisationString += direction.getB().toString().toLowerCase();
				}
				
				player.sendMessage(new TranslationTextComponent(localisationString));
			}
		}
		
		return new ActionResult<>(ActionResultType.SUCCESS, talisman);
		
	}
}
