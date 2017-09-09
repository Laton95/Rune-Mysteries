package com.laton95.runemysteries.block;

import java.util.ArrayList;
import java.util.List;

import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.init.LootRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class BlockRuneAltar extends RMModBlock implements IMetaBlock
{
	
	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumRuneType.class);

	public BlockRuneAltar()
	{
		super("rune_Altar", Material.ROCK, 0, 2000f, null, 0, true);
		setBlockUnbreakable();
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumRuneType.AIR));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumRuneType type = (EnumRuneType) state.getValue(TYPE);
		return type.ordinal();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumRuneType.values()[meta]);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (int i = 0; i < EnumRuneType.values().length; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		if (entityIn instanceof EntityItem)
		{
			ItemStack stack = ((EntityItem) entityIn).getItem();
			boolean isOurania = getMetaFromState(state) == EnumRuneType.ESSENCE.ordinal();
			if (stack.getItem() == ItemRegistry.RUNE && stack.getItemDamage() == EnumRuneType.ESSENCE.ordinal())
			{
				giveAdvancements((EntityItem) entityIn, worldIn, isOurania);
				spawnItem(worldIn, (EntityItem) entityIn, ItemRegistry.RUNE, getMetaFromState(state), isOurania);
			}
			
			if (stack.getItem() == ItemRegistry.RUNE_TALISMAN && stack.getItemDamage() == EnumRuneType.ESSENCE.ordinal() && isOurania == false)
			{
				giveAdvancements((EntityItem) entityIn, worldIn, isOurania);
				spawnItem(worldIn, (EntityItem) entityIn, ItemRegistry.RUNE_TALISMAN, getMetaFromState(state), isOurania);
			}

			if (stack.getItem() == Items.BOOK)
			{
				giveAdvancements((EntityItem) entityIn, worldIn, isOurania);
				spawnItem(worldIn, (EntityItem) entityIn, ItemRegistry.SPELLBOOK, 0, isOurania);
			}
		}
	}

	protected void giveAdvancements(EntityItem entityIn, World worldIn, boolean isOurania)
	{
		String thrower = entityIn.getThrower();
		if (thrower != null)
		{
			EntityPlayer player = worldIn.getPlayerEntityByName(thrower);
			if (player instanceof EntityPlayerMP)
			{
				if (entityIn.getItem().getItem() == ItemRegistry.RUNE && entityIn.getItem().getItemDamage() == EnumRuneType.ESSENCE.ordinal())
				{
					CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player,
							new ItemStack(ItemRegistry.RUNE, 1, EnumRuneType.ESSENCE.ordinal()));
				}
				
				if (isOurania)
				{
					CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) player,
							new ItemStack(Item.getItemFromBlock(this), 1, EnumRuneType.ESSENCE.ordinal()));
				}
			}
		}
	}

	protected void spawnItem(World worldIn, EntityItem entityIn, Item item, int metadata, boolean isOurania)
	{
		while (entityIn.getItem().getCount() > 0)
		{
			ItemStack itemstack ;
			if (isOurania)
			{
				itemstack = getRandomRune(worldIn);
			} else {
				itemstack = new ItemStack(item, 1, metadata);
			}
			
			spawnAsEntity(worldIn, entityIn.getPosition(), itemstack);
			((EntityItem) entityIn).getItem().setCount(((EntityItem) entityIn).getItem().getCount() - 1);
		}
		entityIn.setDead();
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
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
	
	private ItemStack getRandomRune(World world)
	{
		List<ItemStack> items = new ArrayList<>();
		try
		{
			ResourceLocation resourcelocation = LootRegistry.OURANIA_ALTAR;
			LootTable loottable = world.getLootTableManager().getLootTableFromLocation(resourcelocation);
			LootContext.Builder lootBuilder = new LootContext.Builder((WorldServer) world);
			items = loottable.generateLootForPools(world.rand, lootBuilder.build());
		}
		catch (NullPointerException e)
		{
			items.add(new ItemStack(Blocks.DIRT));
		}
		return items.get(0);
	}
}
