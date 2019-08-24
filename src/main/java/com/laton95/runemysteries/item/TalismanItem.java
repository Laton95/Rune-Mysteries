package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.reference.StringReference.Talisman;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

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
			player.getCooldownTracker().setCooldown(this, 30);
			
			DimensionType ruinDimension = runeType.getRuinDimension();
			DimensionType templeDimension = runeType.getTempleDimension();
			DimensionType playerDimension = player.dimension;
			
			if(playerDimension == templeDimension) {
				player.sendMessage(new TranslationTextComponent(Talisman.TEMPLE));
			}
			else if(playerDimension != ruinDimension) {
				if(ruinDimension == DimensionType.OVERWORLD) {
					player.sendMessage(new TranslationTextComponent(StringReference.Talisman.OVERWORLD));
				}
				else if(ruinDimension == DimensionType.THE_NETHER) {
					world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
					player.sendMessage(new TranslationTextComponent(StringReference.Talisman.NETHER));
					player.attackEntityFrom(new DamageSource(StringReference.DeathMessage.TALISMAN_NETHER), 2f);
				}
				else if(ruinDimension == DimensionType.THE_END) {
					world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
					player.sendMessage(new TranslationTextComponent(StringReference.Talisman.END));
				}
				
				if(player.isCreative() && player.isSneaking()) {
					BlockPos ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, (ServerWorld) world);
					
					if(ruinPos != null) {
						TeleportHelper.teleportPlayer((ServerPlayerEntity) player, ruinDimension, ruinPos.add(2, 0, 2));
					}
					else {
						TeleportHelper.teleportPlayer((ServerPlayerEntity) player, ruinDimension, new BlockPos(0, 100, 0));
					}
					
				}
			}
			else {
				BlockPos ruinPos;
				if(playerDimension.isVanilla()) {
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, world.getChunkProvider().getChunkGenerator());
				}
				else {
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, (ServerWorld) world);
				}
				
				//TODO add proper config here
				if(ruinPos == null) //|| !ModConfig.WORLD_GENERATION.rune_altars.generateRuneAltars)
				{
					player.sendMessage(new TranslationTextComponent(Talisman.FAIL));
				}
				else {
					if(WorldHelper.isNearby(player.getPosition(), ruinPos, 5)) {
						player.sendMessage(new TranslationTextComponent(Talisman.NEARBY));
					}
					else if(player.isCreative() && player.isSneaking()) {
						TeleportHelper.teleportPlayer((ServerPlayerEntity) player, ruinDimension, ruinPos.add(2, 0, 2));
					}
					else {
						WorldHelper.Direction direction = WorldHelper.getDirection(player.getPosition(), ruinPos);
						player.sendMessage(new TranslationTextComponent("item.runemysteries.talisman.pull." + direction.name().toLowerCase()));
					}
				}
			}
		}
		
		return new ActionResult<>(ActionResultType.SUCCESS, talisman);
	}
}
