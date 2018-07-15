package com.laton95.runemysteries.item;

import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class ItemTalisman extends RMModItem
{
	private final EnumRuneType runeType;
	
	public ItemTalisman(EnumRuneType runeType)
	{
		super(runeType.toString().toLowerCase() + "_talisman", true);
		setMaxStackSize(1);
		this.runeType = runeType;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack talisman = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote)
		{
			DimensionType dimType = runeType.gerRuinDimType();
			
			BlockPos pos = null;
			
			try
			{
				pos = WorldGenerator.ruinTracker.getRuinByRune(runeType).getRuinPos();
			}
			catch(Exception e)
			{
				LogHelper.error(e.toString());
				LogHelper.warn(String.format("An error occured while getting %s altar location.", runeType.toString().toLowerCase()));
			}
			
			if(pos == null || !ModConfig.WORLD_GENERATION.rune_altars.generateRuneAltars)
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
			
			if(playerIn.isCreative() && playerIn.isSneaking())
			{
				if(pos.getY() != 0)
				{
					TeleportHelper.teleportEntity(playerIn, dimType.getId(), pos.getX() + 2, pos.getY(), pos.getZ() + 2);
				}
				else
				{
					TeleportHelper.teleportEntity(playerIn, dimType.getId(), pos.getX(), 100, pos.getZ());
				}
				
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
			
			playerIn.getCooldownTracker().setCooldown(this, 30);
			
			switch(dimType)
			{
				case OVERWORLD:
					if(worldIn.provider.getDimensionType() != DimensionType.OVERWORLD)
					{
						playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.OVERWORLD));
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
				case NETHER:
					if(worldIn.provider.getDimensionType() != DimensionType.NETHER)
					{
						worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NETHER));
						playerIn.attackEntityFrom(new DamageSource(NamesReference.Talisman.NETHER_DAMAGE), 2f);
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
				case THE_END:
					if(worldIn.provider.getDimensionType() != DimensionType.THE_END)
					{
						worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.END));
						return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
					break;
			}
			
			if(WorldHelper.isNearby(playerIn.getPosition(), pos, 5))
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NEARBY));
			}
			else
			{
				Tuple<WorldHelper.HorizontalDirection, WorldHelper.VerticalDirection> direction = WorldHelper.getDirection(playerIn.getPosition(), pos);
				
				String localisationString = "item.runemysteries.talisman.pull.";
				
				if(direction.getFirst() != WorldHelper.HorizontalDirection.NONE)
				{
					localisationString += direction.getFirst().toString().toLowerCase();
				}
				
				if(direction.getSecond() == WorldHelper.VerticalDirection.UP || direction.getSecond() == WorldHelper.VerticalDirection.DOWN)
				{
					if(direction.getFirst() != WorldHelper.HorizontalDirection.NONE)
					{
						localisationString += "_";
					}
					localisationString += direction.getSecond().toString().toLowerCase();
				}
				
				playerIn.sendMessage(new TextComponentTranslation(localisationString));
			}
		}
		
		return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
	}
}
