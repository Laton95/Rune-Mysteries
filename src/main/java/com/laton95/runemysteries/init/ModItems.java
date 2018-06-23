package com.laton95.runemysteries.init;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.block.BlockElementalObelisk;
import com.laton95.runemysteries.block.IMetaBlock;
import com.laton95.runemysteries.block.RMModSlab;
import com.laton95.runemysteries.block.RMRandomRotationBlock;
import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.item.*;
import com.laton95.runemysteries.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ModItems
{
	
	public static final ItemRune RUNE = new ItemRune();
	
	public static final ItemTalisman RUNE_TALISMAN = new ItemTalisman();
	
	public static final ItemSpellbook SPELLBOOK = new ItemSpellbook();
	
	public static final ItemRuneBag RUNE_BAG = new ItemRuneBag();
	
	public static final ItemScroll MINE_SCROLL = new ItemScroll();
	
	public static final RMModFood BANANA = new RMModFood("banana", true, 4, 4);
	
	public static final RMModItem EX_PARROT = new RMModItem("ex_parrot", true);
	
	public static final RMModItem OBELISK_SHARD = new RMModItem("obelisk_shard", true, BlockElementalObelisk.EnumObeliskElement.class);
	
	private static Item[] items = new Item[]
			{
					RUNE,
					RUNE_TALISMAN,
					SPELLBOOK,
					RUNE_BAG,
					MINE_SCROLL,
					BANANA,
					EX_PARROT,
					OBELISK_SHARD
			};
	
	private static ArrayList<Block> itemBlocks = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		LogHelper.info("Registering items");
		for(Item item : items)
		{
			event.getRegistry().register(item);
			
			if(!item.getHasSubtypes())
			{
				registerItemRender(item);
			}
			else if(item instanceof RMModItem)
			{
				registerItemRenderWithVariants((RMModItem) item);
			}
		}
		
		for(Block block : itemBlocks)
		{
			if(block instanceof IMetaBlock)
			{
				ItemBlock itemBlock = new MetaItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				event.getRegistry().register(itemBlock);
				registerBlockRenderWithVariants(block);
				
			}
			else if(block instanceof RMModSlab)
			{
				RMModSlab.Half slab = (RMModSlab.Half) block;
				RMModSlab.Double doubleSlab = slab.doubleSlab;
				ItemBlock itemBlock = new ItemSlab(slab, slab, doubleSlab);
				itemBlock.setRegistryName(slab.getRegistryName());
				slab.setDroppedItem(itemBlock);
				doubleSlab.setDroppedItem(itemBlock);
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "normal");
			}
			else if(block instanceof RMRandomRotationBlock)
			{
				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "inventory");
			}
			else
			{
				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				event.getRegistry().register(itemBlock);
				registerBlockRender(itemBlock, "normal");
			}
		}
	}
	
	private static void registerItemRender(Item item)
	{
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelBakery.registerItemVariants(item, fullModelLocation);
		ModelLoader.setCustomMeshDefinition(item, stack -> fullModelLocation);
	}
	
	private static void registerItemRenderWithVariants(RMModItem item)
	{
		String variantName = item.getValues().getEnumConstants()[0].getID();
		NonNullList<ItemStack> subItems = NonNullList.create();
		item.getSubItems(RMModCreativeTab.RM_TAB, subItems);
		for(ItemStack stack : subItems)
		{
			ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName(), variantName + "=" + item.getValues().getEnumConstants()[stack.getMetadata()].getName());
			ModelLoader.setCustomModelResourceLocation(item, stack.getMetadata(), res);
		}
	}
	
	private static void registerBlockRenderWithVariants(Block block)
	{
		StateMapperBase b = new DefaultStateMapper();
		BlockStateContainer bsc = block.getBlockState();
		ImmutableList<IBlockState> values = bsc.getValidStates();
		for(IBlockState state : values)
		{
			
			String variant = b.getPropertyString(state.getProperties());
			int metadata = block.getMetaFromState(state);
			final Item item = Item.getItemFromBlock(block);
			ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName(), variant);
			ModelLoader.setCustomModelResourceLocation(item, metadata, res);
		}
	}
	
	private static void registerBlockRender(ItemBlock block, String variant)
	{
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation(block.getRegistryName(), variant));
	}
	
	public static void addBlock(Block block)
	{
		itemBlocks.add(block);
	}
}
