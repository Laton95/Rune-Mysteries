package com.laton95.runemysteries.block;

import com.laton95.runemysteries.advancement.triggers.Triggers;
import com.laton95.runemysteries.block.properties.PropertyCorner;
import com.laton95.runemysteries.enums.EnumCorner;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.init.ModLoot;
import com.laton95.runemysteries.init.ModPotions;
import com.laton95.runemysteries.item.ItemRune;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public class BlockRuneAltar extends RMModBlock
{
	
	private static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	
	public static final PropertyCorner CORNER = PropertyCorner.create("corner");
	
	private final EnumRuneType runeType;
	
	public BlockRuneAltar(EnumRuneType runeType)
	{
		super(runeType.toString().toLowerCase() + "_altar", Material.ROCK, 0, 2000f, null, 0);
		setBlockUnbreakable();
		this.runeType = runeType;
	}
	
	public BlockRuneAltar()
	{
		super("ourania_altar", Material.ROCK, 0, 2000f, null, 0, true, true);
		setBlockUnbreakable();
		this.runeType = null;
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
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		boolean hasNorth = worldIn.getBlockState(pos.north()).getBlock() == this;
		boolean hasSouth = worldIn.getBlockState(pos.south()).getBlock() == this;
		boolean hasWest = worldIn.getBlockState(pos.west()).getBlock() == this;
		boolean hasEast = worldIn.getBlockState(pos.east()).getBlock() == this;
		
		EnumCorner corner = EnumCorner.NORTH_EAST;
		
		if(hasNorth && hasWest)
		{
			corner = EnumCorner.SOUTH_EAST;
		}
		
		if(hasNorth && hasEast)
		{
			corner = EnumCorner.SOUTH_WEST;
		}
		
		if(hasSouth && hasWest)
		{
			corner = EnumCorner.NORTH_EAST;
		}
		
		if(hasSouth && hasEast)
		{
			corner = EnumCorner.NORTH_WEST;
		}
		
		return state.withProperty(CORNER, corner);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, CORNER);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		if(entityIn instanceof EntityItem && !worldIn.isRemote)
		{
			ItemStack stack = ((EntityItem) entityIn).getItem();
			boolean isOurania = runeType == null;
			
			if(stack.getItem() == ModItems.RUNE_ESSENCE)
			{
				if(isOurania)
				{
					changeItem(worldIn, (EntityItem) entityIn, getRandomRune(worldIn), true);
				}
				else
				{
					changeItem(worldIn, (EntityItem) entityIn, runeType.getRuneOfType(), false);
				}
			}
			else if(stack.getItem() == ModItems.BLANK_TALISMAN && !isOurania)
			{
				changeItem(worldIn, (EntityItem) entityIn, runeType.getTalismanOfType(), false);
			}
			else if(stack.getItem() == Items.BOOK)
			{
				changeItem(worldIn, (EntityItem) entityIn, ModItems.SPELLBOOK, isOurania);
			}
		}
	}
	
	private void changeItem(World worldIn, EntityItem entityItem, Item item, boolean isOurania)
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
			
			entityItem.setItem(new ItemStack(item, 1));
			entityItem.setDead();
			
			while(count > 0)
			{
				if(isOurania && (item == ModItems.RUNE_ESSENCE || item instanceof ItemRune))
				{
					item = getRandomRune(worldIn);
				}
				
				ItemStack newStack = new ItemStack(item, 1);
				EntityItem newItem = new EntityItem(worldIn, entityItem.posX, entityItem.posY, entityItem.posZ);
				newItem.setItem(newStack);
				worldIn.spawnEntity(newItem);
				count--;
			}
		}
	}
	
	private Item getRandomRune(World world)
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
		return items.get(0).getItem();
	}
}
