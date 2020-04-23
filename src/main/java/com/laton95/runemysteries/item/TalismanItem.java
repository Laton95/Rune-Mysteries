package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.block.AltarEntranceBlock;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.reference.StringReference.Talisman;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

public class TalismanItem extends Item {
	
	private final EnumRuneType runeType;
	
	public TalismanItem(EnumRuneType runeType) {
		super(new Properties().maxStackSize(1).group(RuneMysteries.RUNE_GROUP));
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
					world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
					player.sendMessage(new TranslationTextComponent(StringReference.Talisman.NETHER));
					player.attackEntityFrom(new DamageSource(StringReference.DeathMessage.TALISMAN_NETHER), 2f);
				}
				else if(ruinDimension == DimensionType.THE_END) {
					world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
					player.sendMessage(new TranslationTextComponent(StringReference.Talisman.END));
				}
				
				if(player.isCreative() && player.isCrouching()) {
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
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, ((ServerChunkProvider) (world.getChunkProvider())).getChunkGenerator());
				}
				else {
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, (ServerWorld) world);
				}
				
				if(ruinPos == null) {
					player.sendMessage(new TranslationTextComponent(Talisman.FAIL));
				}
				else {
					if(WorldHelper.isNearby(player.getPosition(), ruinPos, 5)) {
						if(!(world.getBlockState(ruinPos).getBlock() instanceof AltarEntranceBlock)) {
							world.setBlockState(ruinPos, runeType.getTempleEntranceBlock().getDefaultState());
						}
						player.sendMessage(new TranslationTextComponent(Talisman.NEARBY));
					}
					else if(player.isCreative() && player.isCrouching()) {
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
