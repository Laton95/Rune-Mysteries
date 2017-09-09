package com.laton95.runemysteries.tileentity;

import java.util.ArrayList;

import com.laton95.runemysteries.util.ModConfig;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.world.WorldGenerator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityAltarPortal extends RMModTileEntity implements ITickable
{

	private int timer = 20;
	private String altar;
	private int returnID;

	@Override
	public void update()
	{
		timer--;
		if (timer < 0)
		{
			timer = 20;
		}
		if (timer == 0 && !world.isRemote)
		{
			ArrayList<EntityPlayer> teleportedPlayers = new ArrayList<>();
			for (EntityPlayer player : world.playerEntities)
			{
				if (Math.max(Math.abs(player.posX), Math.abs(player.posZ)) > 100)
				{
					teleportedPlayers.add(player);
				}
			}
			for (EntityPlayer player : teleportedPlayers)
			{
				TeleportEntity(player, world);
			}
		}
	}

	public void TeleportEntity(Entity entityIn, World worldIn)
	{
		if (!worldIn.isRemote && worldIn.provider.getDimension() != returnID)
		{
			switch (returnID)
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

			if (entityIn.world.provider.getDimension() != returnID && altar != null)
			{
				BlockPos altarPos = WorldGenerator.altarTracker.getAltar(altar).getPosition();

				if (altarPos == null)
				{
					altarPos = new BlockPos(0, 100, 0);
				}
				TeleportHelper.teleportEntity(entityIn, returnID, altarPos.getX() - 1, altarPos.getY(),
						altarPos.getZ()
								- 1);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		altar = compound.getString("altar");
		returnID = compound.getInteger("returnID");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setString("altar", altar);
		compound.setInteger("returnID", returnID);
		return super.writeToNBT(compound);
	}

	@Override
	public void onLoad()
	{
		int dimID = world.provider.getDimension();
		if (dimID == ModConfig.DIMENSIONS.airTempleDimID)
		{
			returnID = 0;
			altar = "air_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.bloodTempleDimID)
		{
			returnID = 0;
			altar = "blood_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.bodyTempleDimID)
		{
			returnID = 0;
			altar = "body_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.chaosTempleDimID)
		{
			returnID = -1;
			altar = "chaos_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.cosmicTempleDimID)
		{
			returnID = 1;
			altar = "cosmic_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.deathTempleDimID)
		{
			returnID = 0;
			altar = "death_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.earthTempleDimID)
		{
			returnID = 0;
			altar = "earth_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.fireTempleDimID)
		{
			returnID = 0;
			altar = "fire_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.lawTempleDimID)
		{
			returnID = 0;
			altar = "law_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.mindTempleDimID)
		{
			returnID = 0;
			altar = "mind_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.natureTempleDimID)
		{
			returnID = 0;
			altar = "nature_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.soulTempleDimID)
		{
			returnID = 0;
			altar = "soul_altar";
		}
		else if (dimID == ModConfig.DIMENSIONS.waterTempleDimID)
		{
			returnID = 0;
			altar = "water_altar";
		}
	}
}
