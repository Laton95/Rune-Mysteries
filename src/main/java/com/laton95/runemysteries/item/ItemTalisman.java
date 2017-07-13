package com.laton95.runemysteries.item;

import com.laton95.runemysteries.init.WorldGenRegistry;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.WorldHelper;
import com.laton95.runemysteries.world.AltarTracker;
import com.laton95.runemysteries.world.ChunkGenerator;
import com.laton95.runemysteries.world.MapGenRuneAltar;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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
		try {
			AltarTracker tracker = WorldGenRegistry.chunkGenerator.getAltarGenerator(type).getAltarTracker();
			AltarTracker.RuneAltar runeAltar = tracker.getAltar(altar);
			pos = runeAltar.getPosition();
		} catch (NullPointerException e) {
			WorldGenRegistry.chunkGenerator.getAltarGenerator(type).init();
			AltarTracker tracker = WorldGenRegistry.chunkGenerator.getAltarGenerator(type).getAltarTracker();
			AltarTracker.RuneAltar runeAltar = tracker.getAltar(altar);
			pos = runeAltar.getPosition();
		}
		
		playerIn.getCooldownTracker().setCooldown(this, 10);

        if (!worldIn.isRemote && pos != null)
        {
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
