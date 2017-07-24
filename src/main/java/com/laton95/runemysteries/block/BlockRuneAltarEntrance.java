package com.laton95.runemysteries.block;

import com.laton95.runemysteries.item.ItemTalisman;
import com.laton95.runemysteries.utility.WorldTeleporter;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class BlockRuneAltarEntrance extends RMModBlock {
	private int dimID;
	private ItemTalisman talisman;
	private String altar;

	public BlockRuneAltarEntrance(String name, ItemTalisman talisman, String altar) {
		super(name, Material.ROCK, 0, 2000f, null, 0, true);
		this.talisman = talisman;
		this.altar = altar;
		setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (playerIn.getHeldItemMainhand().getItem().equals(talisman)
					|| playerIn.getHeldItemOffhand().getItem().equals(talisman)) {
				playerIn.sendMessage(new TextComponentTranslation("tile.runemysteries:altar_entrance.enter"));
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension(
						(EntityPlayerMP) playerIn, dimID,
						new WorldTeleporter(playerIn.getServer().getWorld(dimID), pos, new BlockPos(2, 87, 2)));
			} else {
				playerIn.sendMessage(new TextComponentTranslation("tile.runemysteries:altar_entrance.rightClick"));
			}
		}
		return true;
	}

	public void setDimID(int dimID) {
		this.dimID = dimID;
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