package com.laton95.runemysteries.item;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.reference.StringReference.Talisman;
import com.laton95.runemysteries.util.ModLog;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ItemTalisman extends ModItem {
	
	private final EnumRuneType runeType;
	
	public ItemTalisman(EnumRuneType runeType) {
		super(runeType.toString().toLowerCase() + "_talisman", new Properties().maxStackSize(1));
		this.runeType = runeType;
	}
	
	public EnumRuneType getRuneType() {
		return runeType;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack talisman = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote) {
			DimensionType dimType = runeType.getRuinDimType();
			
			BlockPos pos = null;
			
			//Get altar position
			try {
				pos = RuneMysteries.ruinTracker.getRuinByRune(runeType).getRuinPos();
			}
			catch(Exception e) {
				pos = new BlockPos(worldIn.rand.nextInt(20000) - 10000, 100, worldIn.rand.nextInt(20000) - 10000);
				ModLog.error(e.toString());
				ModLog.warn(String.format("An error occurred while getting %s altar location.", runeType.toString().toLowerCase()));
			}
			
			//TODO add proper config here
			if(pos == null) //|| !ModConfig.WORLD_GENERATION.rune_altars.generateRuneAltars)
			{
				playerIn.sendMessage(new TextComponentTranslation(Talisman.FAIL));
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
			
			if(playerIn.isCreative() && playerIn.isSneaking()) {
				if(pos.getY() != 0) {
					TeleportHelper.teleportEntity(playerIn, dimType, pos.getX() + 2, pos.getY(), pos.getZ() + 2);
				}
				else {
					TeleportHelper.teleportEntity(playerIn, dimType, pos.getX(), 100, pos.getZ());
				}
				
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
			
			playerIn.getCooldownTracker().setCooldown(this, 30);
			
			//Handle other dimension altars
			switch(dimType.getId()) {
				case 0:
					if(worldIn.dimension.getType() != DimensionType.OVERWORLD) {
						playerIn.sendMessage(new TextComponentTranslation(StringReference.Talisman.OVERWORLD));
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
				case -1:
					if(worldIn.dimension.getType() != DimensionType.NETHER) {
						worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation(StringReference.Talisman.NETHER));
						playerIn.attackEntityFrom(new DamageSource(StringReference.DeathMessage.TALISMAN_NETHER), 2f);
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
				case 1:
					if(worldIn.dimension.getType() != DimensionType.THE_END) {
						worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation(StringReference.Talisman.END));
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
			}
			
			//Message player with appropriate directions
			if(WorldHelper.isNearby(playerIn.getPosition(), pos, 5)) {
				playerIn.sendMessage(new TextComponentTranslation(StringReference.Talisman.NEARBY));
			}
			else {
				Tuple<WorldHelper.HorizontalDirection, WorldHelper.VerticalDirection> direction = WorldHelper.getDirection(playerIn.getPosition(), pos);
				
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
				
				playerIn.sendMessage(new TextComponentTranslation(localisationString));
			}
		}
		
		return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
		
	}
}
