package com.laton95.runemysteries.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SRespawnPacket;
import net.minecraft.network.play.server.SServerDifficultyPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;

public class TeleportHelper {
	
	public static void changePlayerDimension(ServerPlayerEntity player, DimensionType destination) {
		if(!net.minecraftforge.common.ForgeHooks.onTravelToDimension(player, destination)) { return; }
		if(destination == null) { return; }
		//player.invulnerableDimensionChange = true;
		DimensionType dimensiontype = player.dimension;
		ServerWorld serverworld = player.server.getWorld(dimensiontype);
		player.dimension = destination;
		ServerWorld serverworld1 = player.server.getWorld(destination);
		WorldInfo worldinfo = player.world.getWorldInfo();
		player.connection.sendPacket(new SRespawnPacket(destination, WorldInfo.byHashing(worldinfo.getSeed()), worldinfo.getGenerator(), player.interactionManager.getGameType()));
		player.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
		PlayerList playerlist = player.server.getPlayerList();
		playerlist.updatePermissionLevel(player);
		serverworld.removeEntity(player, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
		player.revive();
		double d0 = player.getPosX();
		double d1 = player.getPosY();
		double d2 = player.getPosZ();
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;
		serverworld.getProfiler().startSection("moving");
		
		player.setLocationAndAngles(d0, d1, d2, f1, f);
		serverworld.getProfiler().endSection();
		serverworld.getProfiler().startSection("placing");
		double d7 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
		double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
		double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
		double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
		d0 = MathHelper.clamp(d0, d7, d5);
		d2 = MathHelper.clamp(d2, d4, d6);
		player.setLocationAndAngles(d0, d1, d2, f1, f);
		
		serverworld.getProfiler().endSection();
		player.setWorld(serverworld1);
		serverworld1.func_217447_b(player);
		//player.func_213846_b(serverworld);
		player.connection.setPlayerLocation(player.getPosX(), player.getPosY(), player.getPosZ(), f1, f);
		player.interactionManager.setWorld(serverworld1);
		player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
		playerlist.sendWorldInfo(player, serverworld1);
		playerlist.sendInventory(player);
		
		for(EffectInstance effectinstance : player.getActivePotionEffects()) {
			player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
		}
		
		//		player.lastExperience = -1;
		//		player.lastHealth = -1.0F;
		//		player.lastFoodLevel = -1;
		net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, destination);
	}
	
	public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationDimension, BlockPos pos) {
		teleportPlayer(player, destinationDimension, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationDimension, double x, double y, double z) {
		if(player != null && !player.world.isRemote && !player.isPassenger() && !player.isBeingRidden()) {
			player.setLocationAndAngles(x, y, z, player.rotationPitch, player.rotationYaw);
			player.connection.setPlayerLocation(x, y, z, player.rotationPitch, player.rotationYaw);
			
			if(player.dimension != destinationDimension) {
				changePlayerDimension(player, destinationDimension);
			}
		}
	}
}
