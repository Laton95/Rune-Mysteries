package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import com.laton95.runemysteries.util.WorldTeleporter;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
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
	private EnumRuneType type;

	public BlockRuneAltarEntrance(String name, EnumRuneType type, String altar) {
		super(name, Material.ROCK, 0, 2000f, null, 0, false);
		this.type = type;
		setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack stack = new ItemStack(ItemRegistry.RUNE_TALISMAN, 1, type.ordinal());
			if (playerIn.getHeldItemMainhand().getItem().equals(ItemRegistry.RUNE_TALISMAN) && playerIn.getHeldItemMainhand().getItemDamage() == type.ordinal()
			 || playerIn.getHeldItemOffhand().getItem().equals(ItemRegistry.RUNE_TALISMAN) && playerIn.getHeldItemOffhand().getItemDamage() == type.ordinal()) {
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.AltarInteraction.ENTER));
				TeleportHelper.teleportEntity(playerIn, dimID, 2, 87, 2);
			} else {
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.AltarInteraction.INTERACT));
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
