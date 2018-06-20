package com.laton95.runemysteries.block;

import com.laton95.runemysteries.creativetab.RMModCreativeTab;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.Random;

public abstract class RMModSlab extends BlockSlab
{
	
	public static final PropertyEnum<RMModSlab.Variant> VARIANT = PropertyEnum.create("variant", RMModSlab.Variant.class);
	
	private Item halfSlabItem;
	
	public RMModSlab(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative)
	{
		super(material);
		setUnlocalizedName(ModReference.MOD_ID + ":" + name);
		setRegistryName(ModReference.MOD_ID, name.toLowerCase());
		setCreativeTab(RMModCreativeTab.RM_TAB);
		setHardness(hardness);
		setResistance(resistance);
		setHarvestLevel(toolClass, harvestLevel);
		
		IBlockState state = blockState.getBaseState();
		if(!isDouble())
		{
			state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		
		setDefaultState(state);
		useNeighborBrightness = true;
	}
	
	@Override
	public String getUnlocalizedName(int meta)
	{
		return this.getUnlocalizedName();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = getDefaultState().withProperty(VARIANT, RMModSlab.Variant.DEFAULT);
		
		if(!isDouble())
		{
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}
		
		return iblockstate;
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		
		if(!isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
		{
			i |= 8;
		}
		
		return i;
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF,
																							 VARIANT);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return halfSlabItem;
	}
	
	@Override
	public IProperty<?> getVariantProperty()
	{
		return VARIANT;
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return RMModSlab.Variant.DEFAULT;
	}
	
	public void setDroppedItem(Item item)
	{
		halfSlabItem = item;
	}
	
	public enum Variant implements IStringSerializable
	{
		DEFAULT;
		
		@Override
		public String getName()
		{
			return "default";
		}
	}
	
	public static class Double extends RMModSlab
	{
		
		public Double(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative)
		{
			super(name + "_Double", material, hardness, resistance, toolClass, harvestLevel, showInCreative);
		}
		
		@Override
		public boolean isDouble()
		{
			return true;
		}
	}
	
	public static class Half extends RMModSlab
	{
		public final RMModSlab.Double doubleSlab;
		
		public Half(String name, Material material, float hardness, Float resistance, String toolClass, int harvestLevel, boolean showInCreative)
		{
			super(name + "_Half", material, hardness, resistance, toolClass, harvestLevel, showInCreative);
			doubleSlab = new Double(name, material, hardness, resistance, toolClass, harvestLevel, showInCreative);
		}
		
		@Override
		public boolean isDouble()
		{
			return false;
		}
	}
	
	
}
