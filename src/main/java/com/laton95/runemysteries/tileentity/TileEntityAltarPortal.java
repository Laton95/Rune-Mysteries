package com.laton95.runemysteries.tileentity;

import com.laton95.runemysteries.capabilities.ICapabilityPlayerLastLocation;
import com.laton95.runemysteries.capabilities.ProviderPlayerLastLocation;
import com.laton95.runemysteries.config.ModConfig;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TileEntityAltarPortal extends RMModTileEntity implements ITickable
{
	
	private int timer = 20;
	
	private EnumRuneType rune = null;
	
	private int returnID = 0;
	
	@Override
	public void update()
	{
		timer--;
		if(timer < 0)
		{
			timer = 20;
		}
		if(timer == 0 && !world.isRemote)
		{
			ArrayList<EntityPlayer> teleportedPlayers = new ArrayList<>();
			for(EntityPlayer player : world.playerEntities)
			{
				if(Math.max(Math.abs(player.posX), Math.abs(player.posZ)) > 100)
				{
					teleportedPlayers.add(player);
				}
			}
			for(EntityPlayer player : teleportedPlayers)
			{
				TeleportEntity(player, world);
			}
		}
	}
	
	public void TeleportEntity(Entity entityIn, World worldIn)
	{
		if(!worldIn.isRemote)
		{
			if(rune == null)
			{
				updateDimensionValues();
			}
			
			if(worldIn.provider.getDimension() != returnID)
			{
				if(entityIn.world.provider.getDimension() == ModConfig.DIMENSIONS.essenceMineDimID && entityIn instanceof EntityPlayer)
				{
					BlockPos returnPos = new BlockPos(0, 70, 0);
					int dimId = 0;
					
					ICapabilityPlayerLastLocation location = entityIn.getCapability(ProviderPlayerLastLocation.LAST_LOCATION_CAPABILITY, null);
					
					if(location != null)
					{
						returnPos = location.getPosition();
						dimId = location.getDimId();
					}
					
					TeleportHelper.teleportEntity(entityIn, dimId, returnPos.getX(), returnPos.getY() + 0.5, returnPos.getZ());
				}
				else if(entityIn.world.provider.getDimension() != returnID && rune != null)
				{
					BlockPos returnPos = new BlockPos(0, 70, 0);
					
					try
					{
						returnPos = WorldGenerator.ruinTracker.getRuinByRune(rune).getRuinPos();
					}
					catch(Exception e)
					{
						LogHelper.warn(String.format("An error occured while getting %s altar location. Teleporting to default location", rune.toString().toLowerCase()));
					}
					
					TeleportHelper.teleportEntity(entityIn, returnID, returnPos.getX() + 2, returnPos.getY(), returnPos.getZ() + 1);
				}
			}
		}
	}
	
	private void updateDimensionValues()
	{
		int dimID = world.provider.getDimension();
		
		if(dimID == ModConfig.DIMENSIONS.airTempleDimID)
		{
			rune = EnumRuneType.AIR;
		}
		else if(dimID == ModConfig.DIMENSIONS.bloodTempleDimID)
		{
			rune = EnumRuneType.BLOOD;
		}
		else if(dimID == ModConfig.DIMENSIONS.bodyTempleDimID)
		{
			rune = EnumRuneType.BODY;
		}
		else if(dimID == ModConfig.DIMENSIONS.chaosTempleDimID)
		{
			rune = EnumRuneType.CHAOS;
		}
		else if(dimID == ModConfig.DIMENSIONS.cosmicTempleDimID)
		{
			rune = EnumRuneType.COSMIC;
		}
		else if(dimID == ModConfig.DIMENSIONS.deathTempleDimID)
		{
			rune = EnumRuneType.DEATH;
		}
		else if(dimID == ModConfig.DIMENSIONS.earthTempleDimID)
		{
			rune = EnumRuneType.EARTH;
		}
		else if(dimID == ModConfig.DIMENSIONS.fireTempleDimID)
		{
			rune = EnumRuneType.FIRE;
		}
		else if(dimID == ModConfig.DIMENSIONS.lawTempleDimID)
		{
			rune = EnumRuneType.LAW;
		}
		else if(dimID == ModConfig.DIMENSIONS.mindTempleDimID)
		{
			rune = EnumRuneType.MIND;
		}
		else if(dimID == ModConfig.DIMENSIONS.natureTempleDimID)
		{
			rune = EnumRuneType.NATURE;
		}
		else if(dimID == ModConfig.DIMENSIONS.soulTempleDimID)
		{
			rune = EnumRuneType.SOUL;
		}
		else if(dimID == ModConfig.DIMENSIONS.waterTempleDimID)
		{
			rune = EnumRuneType.WATER;
		}
		
		returnID = rune.gerRuinDimType().getId();
	}
}
