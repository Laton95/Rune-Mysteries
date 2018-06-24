package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.init.ModLoot;
import com.laton95.runemysteries.init.ModPotions;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import net.minecraft.block.material.Material;
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

import java.util.ArrayList;
import java.util.List;

public class BlockRuneAltar extends RMModBlock implements IMetaBlock
{
	
	private static final PropertyEnum<EnumRuneType> TYPE = PropertyEnum.create("type", EnumRuneType.class);
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	
	public BlockRuneAltar()
	{
		super("rune_Altar", Material.ROCK, 0, 2000f, null, 0, true, true);
		setBlockUnbreakable();
		setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumRuneType.AIR));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(TYPE, EnumRuneType.values()[meta]);
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumRuneType type = state.getValue(TYPE);
		return type.ordinal();
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
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		if(entityIn instanceof EntityItem && !worldIn.isRemote)
		{
			ItemStack stack = ((EntityItem) entityIn).getItem();
			boolean isOurania = getMetaFromState(state) == EnumRuneType.ESSENCE.ordinal();
			int altarData = getMetaFromState(state);
			
			if(stack.getItem() == ModItems.RUNE && stack.getItemDamage() == EnumRuneType.ESSENCE.ordinal())
			{
				if(isOurania)
				{
					changeItem(worldIn, (EntityItem) entityIn, ModItems.RUNE, getRandomRune(worldIn).getMetadata(), isOurania);
				}
				else
				{
					changeItem(worldIn, (EntityItem) entityIn, ModItems.RUNE, altarData, isOurania);
				}
			}
			else if(stack.getItem() == ModItems.RUNE_TALISMAN && stack.getItemDamage() == EnumRuneType.ESSENCE.ordinal())
			{
				changeItem(worldIn, (EntityItem) entityIn, ModItems.RUNE_TALISMAN, altarData, isOurania);
			}
			else if(stack.getItem() == Items.BOOK)
			{
				changeItem(worldIn, (EntityItem) entityIn, ModItems.SPELLBOOK, 0, isOurania);
			}
		}
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(int i = 0; i < EnumRuneType.values().length; i++)
		{
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
	}
	
	private void changeItem(World worldIn, EntityItem entityItem, Item item, int metadata, boolean isOurania)
	{
		if(!worldIn.isRemote)
		{
			int bonusRunes = 0;
			
			String playerName = entityItem.getThrower();
			if(playerName != null)
			{
				EntityPlayer player = worldIn.getPlayerEntityByName(playerName);
				
				Triggers.CRAFT_RUNE.trigger((EntityPlayerMP) player);
				
				if(isOurania)
				{
					Triggers.OURANIA.trigger((EntityPlayerMP) player);
				}
				
				if(ModPotions.STONETOUCHER.hasEffect(player))
				{
					bonusRunes = entityItem.getItem().getCount();
				}
			}
			
			int count = entityItem.getItem().getCount() + bonusRunes;
			
			entityItem.setItem(new ItemStack(item, 1, metadata));
			entityItem.setDead();
			
			while(count > 0)
			{
				if(isOurania && item.getHasSubtypes())
				{
					metadata = getRandomRune(worldIn).getMetadata();
				}
				ItemStack newStack = new ItemStack(item, 1, metadata);
				EntityItem newItem = new EntityItem(worldIn, entityItem.posX, entityItem.posY, entityItem.posZ);
				newItem.setItem(newStack);
				worldIn.spawnEntity(newItem);
				count--;
			}
		}
	}
	
	private ItemStack getRandomRune(World world)
	{
		List<ItemStack> items = new ArrayList<>();
		try
		{
			ResourceLocation resourcelocation = ModLoot.OURANIA_ALTAR;
			LootTable loottable = world.getLootTableManager().getLootTableFromLocation(resourcelocation);
			LootContext.Builder lootBuilder = new LootContext.Builder((WorldServer) world);
			items = loottable.generateLootForPools(world.rand, lootBuilder.build());
		}
		catch(NullPointerException e)
		{
			items.add(new ItemStack(Blocks.DIRT));
		}
		return items.get(0);
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return EnumRuneType.values()[stack.getItemDamage()].toString();
	}
}
