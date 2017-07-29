package com.laton95.runemysteries.item;

import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.ModConfig;
import com.laton95.runemysteries.utility.WorldHelper;
import com.laton95.runemysteries.world.ChunkGenerator;

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

public class ItemTalisman extends RMModItem {
	String altar;
	int dimID;

	public ItemTalisman(String name, String altar, int dimID) {
		super(name, true);
		this.altar = altar;
		this.dimID = dimID;
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack talisman = playerIn.getHeldItem(handIn);

		playerIn.getCooldownTracker().setCooldown(this, 30);

		if (!worldIn.isRemote) {
			if (!ModConfig.world.rune_altars.generateRuneAltars) {
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.fail"));
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}

			switch (worldIn.provider.getDimension()) {
			case 0:
				switch (dimID) {
				case 0:
					printDirection(playerIn, worldIn);
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case -1:
					worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
							SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nether"));
					playerIn.attackEntityFrom(new DamageSource("chaostalisman"), 2f);
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case 1:
					worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
							SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.end"));
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				}
			case -1:
				switch (dimID) {
				case -1:
					printDirection(playerIn, worldIn);
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case 0:
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case 1:
					worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
							SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.end"));
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				}
			case 1:
				switch (dimID) {
				case 1:
					printDirection(playerIn, worldIn);
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case 0:
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				case -1:
					worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
							SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
					playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nether"));
					playerIn.attackEntityFrom(new DamageSource("chaostalisman"), 2f);
					return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
				}
			default:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.fail"));
				return new ActionResult<>(EnumActionResult.SUCCESS, talisman);
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, talisman);
	}

	private void printDirection(EntityPlayer playerIn, World worldIn) {
		switch (dimID) {
		case 0:
			if (!ChunkGenerator.altarTracker.overworldAltarsFound && !worldIn.isRemote) {
				ChunkGenerator.altarTracker.findOverworldLocations(worldIn);
			}
			break;
		case -1:
			if (!ChunkGenerator.altarTracker.netherAltarsFound && !worldIn.isRemote) {
				ChunkGenerator.altarTracker.findNetherLocations(worldIn);
			}
			break;
		case 1:
			if (!ChunkGenerator.altarTracker.endAltarsFound && !worldIn.isRemote) {
				ChunkGenerator.altarTracker.findEndLocations(worldIn);
			}
			break;
		}

		BlockPos pos;
		pos = ChunkGenerator.altarTracker.getAltar(altar).getPosition();

		if (WorldHelper.isNearby(playerIn.getPosition(), pos, 5)) {
			playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nearby"));
		} else {
			switch (WorldHelper.getDirection(playerIn.getPosition(), pos)) {
			case NORTH:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.north"));
				break;
			case NORTH_EAST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.northEast"));
				break;
			case EAST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.east"));
				break;
			case SOUTH_EAST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.southEast"));
				break;
			case SOUTH:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.south"));
				break;
			case SOUTH_WEST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.southWest"));
				break;
			case WEST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.west"));
				break;
			case NORTH_WEST:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.northWest"));
				break;
			case UP:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.up"));
				break;
			case DOWN:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.down"));
				break;
			case UNKNOWN:
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.fail"));
				break;
			default:
				LogHelper.info("Failed to find direction");
				break;
			}
		}
	}
}
