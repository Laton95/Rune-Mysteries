package com.laton95.runemysteries.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SRespawnPacket;
import net.minecraft.network.play.server.SSetPassengersPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.LinkedList;

/**
 * Created by brandon3055 on 6/12/2016.
 * <p>
 * This is a universal class for handling teleportation. Simply tell it where to
 * send an entity and it just works! Also has support for teleporting mounts.
 */
public class TeleportHelper {
	
	/**
	 * Convenience method that does not require pitch and yaw.
	 */
	public static Entity teleportEntity(Entity entity, DimensionType dimension, BlockPos pos) {
		return teleportEntity(entity, dimension, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, entity.rotationYaw, entity.rotationPitch);
	}
	
	/**
	 * Universal method for teleporting entities of all shapes and sizes! This
	 * method will teleport an entity and any entity it is riding recursively.
	 * If riding the riding entity will be re mounted on the other side.
	 * <p>
	 * Note: When teleporting riding entities it is the rider that must be
	 * teleported and the mount will follow automatically. As long as you
	 * teleport the rider you should not need to worry about the mount.
	 *
	 * @return the entity. This may be a new instance so be sure to keep that in
	 * mind.
	 */
	
	public static Entity teleportEntity(Entity entity, DimensionType dimension, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
		if(entity == null || entity.world.isRemote) {
			return entity;
		}
		
		MinecraftServer server = entity.getServer();
		DimensionType sourceDim = entity.world.dimension.getType();
		
		if(!entity.isBeingRidden() && !entity.isPassenger()) {
			return handleEntityTeleport(entity, server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
		}
		
		Entity rootEntity = entity.getLowestRidingEntity();
		PassengerHelper passengerHelper = new PassengerHelper(rootEntity);
		PassengerHelper rider = passengerHelper.getPassenger(entity);
		if(rider == null) {
			return entity;
		}
		passengerHelper.teleport(server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
		passengerHelper.remountRiders();
		passengerHelper.updateClients();
		
		return rider.entity;
	}
	
	/**
	 * This is the base teleport method that figures out how to handle the
	 * teleport and makes it happen!
	 */
	private static Entity handleEntityTeleport(Entity entity, MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
		if(entity == null || entity.world.isRemote) {
			return entity;
		}
		
		boolean interDimensional = sourceDim != targetDim;
		
		if(interDimensional && !ForgeHooks.onTravelToDimension(entity, targetDim)) {
			return entity;
		}
		
		if(interDimensional) {
			if(entity instanceof ServerPlayerEntity) {
				return teleportPlayerInternational((ServerPlayerEntity) entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
			}
			else {
				return teleportEntityInternational(entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
			}
		}
		else {
			if(entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
				player.setRotationYawHead(yaw);
			}
			else {
				entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
				entity.setRotationYawHead(yaw);
			}
		}
		
		return entity;
	}
	
	/**
	 * This is the black magic responsible for teleporting players between
	 * dimensions!
	 */
	private static PlayerEntity teleportPlayerInternational(ServerPlayerEntity player, MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
		ServerWorld sourceWorld = server.getWorld(sourceDim);
		ServerWorld targetWorld = server.getWorld(targetDim);
		PlayerList playerList = server.getPlayerList();
		
		player.dimension = targetDim;
		player.connection.sendPacket(new SRespawnPacket(player.dimension, targetWorld.getWorldInfo().getGenerator(), player.interactionManager.getGameType()));
		playerList.updatePermissionLevel(player);
		sourceWorld.removePlayer(player);
		player.removed = false;
		
		// region Transfer to world
		
		player.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
		player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
		targetWorld.addEntity(player);
		//targetWorld.tickEntity(player, false);
		player.setWorld(targetWorld);
		
		// endregion
		
		//playerList.preparePlayer(player, sourceWorld);
		player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
		player.interactionManager.setWorld(targetWorld);
		//player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities)); //TODO find out how to get all player capabilities
		
		playerList.sendWorldInfo(player, targetWorld);
		playerList.sendInventory(player);
		
		for(EffectInstance potioneffect : player.getActivePotionEffects()) {
			player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), potioneffect));
		}
		//net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, sourceDim, targetDim); //TODO find out how to send changed dimension event
		player.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
		
		return player;
	}
	
	private static Entity teleportEntityInternational(Entity entity, MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
		if(entity.removed) {
			return null;
		}
		
		ServerWorld sourceWorld = server.getWorld(sourceDim);
		ServerWorld targetWorld = server.getWorld(targetDim);
		entity.dimension = targetDim;
		
		sourceWorld.removeEntity(entity);
		entity.removed = false;
		entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
		//sourceWorld.tickEntity(entity, false);
		
		Entity newEntity = null; //EntityList.createEntityByIDFromName(new ResourceLocation(EntityList.getEntityString(entity)), targetWorld); //TODO find the new version of this method
		if(newEntity != null) {
			/** copyDataFromOld */
			CompoundNBT nbttagcompound = entity.writeWithoutTypeId(new CompoundNBT());
			nbttagcompound.remove("Dimension");
			newEntity.read(nbttagcompound);
			newEntity.timeUntilPortal = entity.timeUntilPortal;
			newEntity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
			boolean flag = newEntity.forceSpawn;
			newEntity.forceSpawn = true;
			targetWorld.addEntity(newEntity);
			newEntity.forceSpawn = flag;
			//targetWorld.tickEntity(newEntity, false);
		}
		
		entity.removed = true;
		sourceWorld.resetUpdateEntityTick();
		targetWorld.resetUpdateEntityTick();
		
		return newEntity;
	}
	
	/**
	 * Convenience method that does not require pitch and yaw.
	 */
	public static Entity teleportEntity(Entity entity, DimensionType dimension, double xCoord, double yCoord, double zCoord) {
		return teleportEntity(entity, dimension, xCoord, yCoord, zCoord, entity.rotationYaw, entity.rotationPitch);
	}
	
	public static Entity getHighestRidingEntity(Entity mount) {
		Entity entity;
		
		for(entity = mount; entity.getPassengers().size() > 0; entity = entity.getPassengers().get(0)) {
		}
		
		return entity;
	}
	
	private static class PassengerHelper {
		
		public Entity entity;
		
		public LinkedList<PassengerHelper> passengers = new LinkedList<>();
		
		public double offsetX, offsetY, offsetZ;
		
		/**
		 * Creates a new passenger helper for the given entity and recursively
		 * adds all of the entities passengers.
		 *
		 * @param entity The root entity. If you have multiple stacked entities
		 *               this would be the one at the bottom of the stack.
		 */
		public PassengerHelper(Entity entity) {
			this.entity = entity;
			if(entity.isPassenger()) {
				offsetX = entity.posX - entity.getRidingEntity().posX;
				offsetY = entity.posY - entity.getRidingEntity().posY;
				offsetZ = entity.posZ - entity.getRidingEntity().posZ;
			}
			for(Entity passenger : entity.getPassengers()) {
				passengers.add(new PassengerHelper(passenger));
			}
		}
		
		/**
		 * Recursively teleports the entity and all of its passengers after
		 * dismounting them.
		 *
		 * @param server    The minecraft server.
		 * @param sourceDim The source dimension.
		 * @param targetDim The target dimension.
		 * @param xCoord    The target x position.
		 * @param yCoord    The target y position.
		 * @param zCoord    The target z position.
		 * @param yaw       The target yaw.
		 * @param pitch     The target pitch.
		 */
		public void teleport(MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
			entity.removePassengers();
			entity = handleEntityTeleport(entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
			for(PassengerHelper passenger : passengers) {
				passenger.teleport(server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
			}
		}
		
		/**
		 * Recursively remounts all of this entities riders and offsets their
		 * position relative to their position before teleporting.
		 */
		public void remountRiders() {
			if(entity.isPassenger()) {
				entity.setLocationAndAngles(entity.posX + offsetX, entity.posY + offsetY, entity.posZ + offsetZ, entity.rotationYaw, entity.rotationPitch);
			}
			for(PassengerHelper passenger : passengers) {
				passenger.entity.startRiding(entity, true);
				passenger.remountRiders();
			}
		}
		
		/**
		 * This method sends update packets to any players that were teleported
		 * with the entity stack.
		 */
		public void updateClients() {
			if(entity instanceof ServerPlayerEntity) {
				updateClient((ServerPlayerEntity) entity);
			}
			for(PassengerHelper passenger : passengers) {
				passenger.updateClients();
			}
		}
		
		/**
		 * This is the method that is responsible for actually sending the
		 * update to each client.
		 *
		 * @param playerMP The Player.
		 */
		private void updateClient(ServerPlayerEntity playerMP) {
			if(entity.isBeingRidden()) {
				playerMP.connection.sendPacket(new SSetPassengersPacket(entity));
			}
			for(PassengerHelper passenger : passengers) {
				passenger.updateClients();
			}
		}
		
		/**
		 * This method returns the helper for a specific entity in the stack.
		 *
		 * @param passenger The passenger you are looking for.
		 *
		 * @return The passenger helper for the specified passenger.
		 */
		public PassengerHelper getPassenger(Entity passenger) {
			if(entity == passenger) {
				return this;
			}
			
			for(PassengerHelper rider : passengers) {
				PassengerHelper re = rider.getPassenger(passenger);
				if(re != null) {
					return re;
				}
			}
			
			return null;
		}
	}
}
