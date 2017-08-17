package com.laton95.runemysteries.item;

import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.ModConfig;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.world.WorldGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemTalisman extends RMModItem
{

	public ItemTalisman(String name, boolean showInCreative)
	{
		super(name, showInCreative, EnumRuneType.class);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack talisman = playerIn.getHeldItem(handIn);
		int dimID = getDimID(talisman);
		String altar = EnumRuneType.values()[talisman.getItemDamage()].getName() + "_altar";

		switch (dimID)
		{
			case 0:
				if (!WorldGenerator.altarTracker.overworldAltarsFound && !worldIn.isRemote)
				{
					WorldGenerator.altarTracker.findOverworldLocations(worldIn);
				}
				break;
			case -1:
				if (!WorldGenerator.altarTracker.netherAltarsFound && !worldIn.isRemote)
				{
					WorldGenerator.altarTracker.findNetherLocations(worldIn);
				}
				break;
			case 1:
				if (!WorldGenerator.altarTracker.endAltarsFound && !worldIn.isRemote)
				{
					WorldGenerator.altarTracker.findEndLocations(worldIn);
				}
				break;
		}

		BlockPos pos = WorldGenerator.altarTracker.getAltar(altar).getPosition();

		if (playerIn.isCreative() && playerIn.isSneaking())
		{
			try
			{
				if (pos.getY() != 0)
				{
					TeleportHelper.teleportEntity(playerIn, dimID, pos.getX(), pos.getY(), pos.getZ());
				}
				else
				{
					TeleportHelper.teleportEntity(playerIn, dimID, pos.getX(), 100, pos.getZ());
				}
			}
			catch (NullPointerException e)
			{
				TeleportHelper.teleportEntity(playerIn, dimID, playerIn.posX, playerIn.posY, playerIn.posZ);
			}
		}

		playerIn.getCooldownTracker().setCooldown(this, 30);

		if (!worldIn.isRemote)
		{
			if (!ModConfig.WORLD_GENERATION.rune_altars.generateRuneAltars)
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}

			switch (worldIn.provider.getDimension())
			{
				case 0:
					switch (dimID)
					{
						case 0:
							printDirection(playerIn, worldIn, pos);
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case -1:
							worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
									SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NETHER));
							playerIn.attackEntityFrom(new DamageSource("chaostalisman"), 2f);
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case 1:
							worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
									SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.END));
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
				case -1:
					switch (dimID)
					{
						case -1:
							printDirection(playerIn, worldIn, pos);
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case 0:
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.OVERWORLD));
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case 1:
							worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
									SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.END));
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
				case 1:
					switch (dimID)
					{
						case 1:
							printDirection(playerIn, worldIn, pos);
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case 0:
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.OVERWORLD));
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
						case -1:
							worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
									SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
							playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NETHER));
							playerIn.attackEntityFrom(new DamageSource(NamesReference.Talisman.NETHER_DAMAGE), 2f);
							return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
					}
				default:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, talisman);
	}

	private void printDirection(EntityPlayer playerIn, World worldIn, BlockPos pos)
	{
		if (WorldHelper.isNearby(playerIn.getPosition(), pos, 5))
		{
			playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NEARBY));
		}
		else
		{
			switch (WorldHelper.getDirection(playerIn.getPosition(), pos))
			{
				case NORTH:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NORTH));
					break;
				case NORTH_EAST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NORTH_EAST));
					break;
				case EAST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.EAST));
					break;
				case SOUTH_EAST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.SOUTH_EAST));
					break;
				case SOUTH:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.SOUTH));
					break;
				case SOUTH_WEST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.SOUTH_WEST));
					break;
				case WEST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.WEST));
					break;
				case NORTH_WEST:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.NORTH_WEST));
					break;
				case UP:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.UP));
					break;
				case DOWN:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.DOWN));
					break;
				case UNKNOWN:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
					break;
				default:
					playerIn.sendMessage(new TextComponentTranslation(NamesReference.Talisman.FAIL));
					LogHelper.info(
							"Something went wrong with altar locating, please submit a bug report to the Rune Mysteries github.");
					break;
			}
		}
	}

	private int getDimID(ItemStack talisman)
	{
		switch (talisman.getItemDamage())
		{
			case 4:
				return -1;
			case 5:
				return 1;
			default:
				return 0;
		}
	}
}
