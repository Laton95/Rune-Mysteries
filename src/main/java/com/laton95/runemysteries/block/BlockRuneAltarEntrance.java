package com.laton95.runemysteries.block;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.util.ModConfig;
import com.laton95.runemysteries.util.TeleportHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneAltarEntrance extends RMModBlock implements IMetaBlock
{

	public static final PropertyEnum<EnumRuneType> TYPE = PropertyEnum.create("type", EnumRuneType.class);

	public BlockRuneAltarEntrance()
	{
		super("rune_Altar_Entrance", Material.ROCK, 0, 2000f, null, 0, false);
		setBlockUnbreakable();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { TYPE });
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumRuneType type = state.getValue(TYPE);
		return type.ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, EnumRuneType.values()[meta]);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (int i = 0; i < EnumRuneType.values().length; i++)
		{
			if (i != EnumRuneType.ASTRAL.ordinal() && i != EnumRuneType.ESSENCE.ordinal())
			{
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			new ItemStack(ItemRegistry.RUNE_TALISMAN, 1, getMetaFromState(state));
			if (playerIn.getHeldItemMainhand().getItem().equals(ItemRegistry.RUNE_TALISMAN)
					&& playerIn.getHeldItemMainhand().getItemDamage() == getMetaFromState(state)
					|| playerIn.getHeldItemOffhand().getItem().equals(ItemRegistry.RUNE_TALISMAN)
					&& playerIn.getHeldItemOffhand().getItemDamage() == getMetaFromState(state))
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.AltarInteraction.ENTER));
				TeleportHelper.teleportEntity(playerIn, getDimIDFromState(state), 2, 87, 2);
			}
			else
			{
				playerIn.sendMessage(new TextComponentTranslation(NamesReference.AltarInteraction.INTERACT));
			}
		}
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return BoundingBox;
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumRuneType.values()[stack.getItemDamage()].toString();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}

	private int getDimIDFromState(IBlockState state)
	{
		switch (EnumRuneType.values()[getMetaFromState(state)])
		{
		case AIR:
			return ModConfig.DIMENSIONS.airTempleDimID;
		case BLOOD:
			return ModConfig.DIMENSIONS.bloodTempleDimID;
		case BODY:
			return ModConfig.DIMENSIONS.bodyTempleDimID;
		case CHAOS:
			return ModConfig.DIMENSIONS.chaosTempleDimID;
		case COSMIC:
			return ModConfig.DIMENSIONS.cosmicTempleDimID;
		case DEATH:
			return ModConfig.DIMENSIONS.deathTempleDimID;
		case EARTH:
			return ModConfig.DIMENSIONS.earthTempleDimID;
		case FIRE:
			return ModConfig.DIMENSIONS.fireTempleDimID;
		case LAW:
			return ModConfig.DIMENSIONS.lawTempleDimID;
		case MIND:
			return ModConfig.DIMENSIONS.mindTempleDimID;
		case NATURE:
			return ModConfig.DIMENSIONS.natureTempleDimID;
		case SOUL:
			return ModConfig.DIMENSIONS.soulTempleDimID;
		case WATER:
			return ModConfig.DIMENSIONS.waterTempleDimID;
		default:
			return 0;
		}
	}
}
