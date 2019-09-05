package com.laton95.runemysteries.block;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference.BlockInteraction;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.world.dimension.RuneTempleDimension;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AltarPortalBlock extends ModBlock {
	
	public AltarPortalBlock() {
		super(Properties.create(Material.BARRIER).lightValue(14).hardnessAndResistance(-1.0F, 3600000.0F), false);
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		double blockCenterX = pos.getX() + 0.5D;
		double blockCenterY = pos.getY() + 2D;
		double blockCenterZ = pos.getZ() + 0.5D;
		
		float xSpeed = 0;
		float ySpeed = -1.5f;
		float zSpeed = 0;
		
		float radius = 0.5f;
		
		int i = rand.nextInt(10) + 20;
		
		for(int theta = 0; theta < 360; theta += i) {
			double theta2 = (Math.PI / 2) - (theta * (Math.PI / 180));
			double xOffset = radius * Math.sin(theta2);
			double zOffset = radius * Math.cos(theta2);
			
			world.addParticle(ParticleTypes.PORTAL, blockCenterX + xOffset, blockCenterY, blockCenterZ + zOffset, xSpeed, ySpeed, zSpeed);
		}
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {

		if(!world.isRemote() && entity instanceof ServerPlayerEntity && world.getDimension() instanceof RuneTempleDimension) {
			entity.sendMessage(new TranslationTextComponent(BlockInteraction.ALTAR_TELEPORT));
			
			EnumRuneType rune = ((RuneTempleDimension) world.getDimension()).runeType;
			
			TeleportHelper.teleportPlayer((ServerPlayerEntity) entity, rune.getRuinDimension(), RuneMysteries.ruinManager.getRuinPosition(rune, (ServerWorld) world).add(2, 0, 2));
		}
	}
}
