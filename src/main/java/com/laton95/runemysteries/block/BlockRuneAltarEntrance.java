package com.laton95.runemysteries.block;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.TeleportHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneAltarEntrance extends RMModBlock
{
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	
	private final EnumRuneType runeType;
	
	public BlockRuneAltarEntrance(EnumRuneType runeType)
	{
		super(runeType.toString().toLowerCase() + "_altar_entrance", Material.ROCK, 0, 2000f, null, 0, false, false);
		setBlockUnbreakable();
		this.runeType = runeType;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			if(playerIn.getHeldItemMainhand().getItem() == runeType.getTalismanOfType() || playerIn.getHeldItemOffhand().getItem() == runeType.getTalismanOfType())
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.ALTAR_ENTER));
				TeleportHelper.teleportEntity(playerIn, runeType.getTempleDimId(), runeType.getTempleEntrancePoint());
			}
			else
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.BlockInteraction.ALTAR_INTERACT));
			}
		}
		return true;
	}
}
