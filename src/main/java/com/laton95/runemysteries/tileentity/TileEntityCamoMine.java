package com.laton95.runemysteries.tileentity;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.text.html.HTML.Tag;

import com.laton95.runemysteries.util.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityCamoMine extends TileEntity implements ITickable{
	private int timer = 60;

	@Override
	public void update() {
		timer--;
		if (timer < 0) {
			timer = 60;
		}
		if (timer == 0 && !world.isRemote) {
			ArrayList<EntityPlayer> entities = (ArrayList<EntityPlayer>) world.playerEntities;
			for (EntityPlayer player : entities) {
				if (pos.getDistance((int)player.posX, (int)player.posY, (int)player.posZ) > 5) {
					LogHelper.info("Player to far away");
					player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		timer = compound.getInteger("timer");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("timer", timer);
		return super.writeToNBT(compound);
	}
}
