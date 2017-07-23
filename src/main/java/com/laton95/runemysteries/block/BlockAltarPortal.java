package com.laton95.runemysteries.block;

import com.laton95.runemysteries.utility.WorldTeleporter;
import com.laton95.runemysteries.world.ChunkGenerator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import scala.tools.nsc.transform.Delambdafy.ThisReferringMethodsTraverser;

public class BlockAltarPortal extends RMModBlock {
	private int returnID;
	private String altar;

	public BlockAltarPortal(String name, String altar, int returnID) {
		super(name, Material.ROCK, 0, 2000f, null, 0, true);
		this.altar = altar;
		this.returnID = returnID;
		setBlockUnbreakable();
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityPlayer && !worldIn.isRemote && worldIn.provider.getDimension() != returnID) {
			switch (returnID) {
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

			BlockPos altarPos = ChunkGenerator.altarTracker.getAltar(altar + "_altar").getPosition();
			
			if (altarPos == null) {
				altarPos = new BlockPos(0, 100, 0);
			}
			BlockPos landing = new BlockPos(altarPos.getX() - 1, altarPos.getY(), altarPos.getZ() - 1);
			FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension(
					(EntityPlayerMP) entityIn, returnID,
					new WorldTeleporter(entityIn.getServer().getWorld(returnID), pos, landing));
		}
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BoundingBox;
	}
}
