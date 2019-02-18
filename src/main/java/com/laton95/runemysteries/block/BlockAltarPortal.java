package com.laton95.runemysteries.block;

import com.laton95.runemysteries.reference.StringReference.BlockInteraction;
import com.laton95.runemysteries.util.TeleportHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Particles;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.Random;

public class BlockAltarPortal extends ModBlock {
	
	public BlockAltarPortal() {
		super("altar_portal", Properties.create(Material.BARRIER).lightValue(14).hardnessAndResistance(-1.0F, 3600000.0F), false, false);
	}
	
	@Override
	public void animateTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
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
			
			worldIn.spawnParticle(Particles.PORTAL, blockCenterX + xOffset, blockCenterY, blockCenterZ + zOffset, xSpeed, ySpeed, zSpeed);
		}
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(!worldIn.isRemote()) {
			entityIn.sendMessage(new TextComponentTranslation(BlockInteraction.ALTAR_TELEPORT));
			
			TeleportHelper.teleportEntity(entityIn, DimensionType.OVERWORLD, new BlockPos(0, 60, 0));
		}
	}
}
