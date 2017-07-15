package com.laton95.runemysteries.item;

import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.AltarTracker.Type;

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
	WorldHelper.dimType type;

	public ItemTalisman(String name, String altar, WorldHelper.dimType type) {
		super(name, true);
		this.altar = altar;
		this.type = type;
		setMaxStackSize(1);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack talisman = playerIn.getHeldItem(handIn);
		BlockPos pos = null;
		AltarTracker.RuneAltar runeAltar = null;
		try {
			AltarTracker tracker = WorldGenRegistry.chunkGenerator.getAltarGenerator(type).getAltarTracker();
			runeAltar = tracker.getAltar(altar);
			pos = runeAltar.getPosition();
		} catch (NullPointerException e) {
			try {
				WorldGenRegistry.chunkGenerator.getAltarGenerator(type).init();
				AltarTracker tracker = WorldGenRegistry.chunkGenerator.getAltarGenerator(type).getAltarTracker();
				runeAltar = tracker.getAltar(altar);
				pos = runeAltar.getPosition();
			} catch (NullPointerException e2) {
				LogHelper.info("Something went wrong, altar tracker is not loaded.");
			}

		}

		playerIn.getCooldownTracker().setCooldown(this, 10);

		if (!worldIn.isRemote && pos != null) {
			switch (worldIn.provider.getDimensionType()) {
			case OVERWORLD:
				if (runeAltar.getType() == Type.SURFACE || runeAltar.getType() == Type.UNDERGROUND
						|| runeAltar.getType() == Type.SOUL) {
					break;
				} else {
					switch (runeAltar.getType()) {
					case NETHER:
						worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
								SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nether"));
						playerIn.attackEntityFrom(new DamageSource("chaostalisman"), 2f);
						break;
					case END:
						worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
								SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.end"));
						break;
					default:
						break;
					}
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, talisman);
				}
			case NETHER:
				if (runeAltar.getType() == Type.NETHER) {
					break;
				} else {
					switch (runeAltar.getType()) {
					case SURFACE:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case UNDERGROUND:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case SOUL:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case END:
						worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
								SoundEvents.ENTITY_ENDERMEN_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.end"));
						break;
					default:
						break;
					}
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, talisman);
				}
			case THE_END:
				if (runeAltar.getType() == Type.END) {
					break;
				} else {
					switch (runeAltar.getType()) {
					case SURFACE:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case UNDERGROUND:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case SOUL:
						playerIn.sendMessage(
								new TextComponentTranslation("item.runemysteries.talisman.pull.overworld"));
						break;
					case NETHER:
						worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
								SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1f, 1f);
						playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nether"));
						playerIn.attackEntityFrom(new DamageSource("chaostalisman"), 2f);
						break;
					default:
						break;
					}
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, talisman);
				}
			}

			if (WorldHelper.isNearby(playerIn.getPosition(), pos, 5)) {
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.nearby"));
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, talisman);
			}
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
				playerIn.sendMessage(new TextComponentTranslation("item.runemysteries.talisman.pull.unknown"));
				break;
			default:
				LogHelper.info("Failed to find direction");
				break;
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, talisman);
	}
}
