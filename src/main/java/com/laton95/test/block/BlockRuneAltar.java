package com.laton95.test.block;

import java.util.List;

import javax.annotation.Nullable;

import com.laton95.test.init.ItemRegistry;
import com.laton95.test.item.ItemRune;
import com.laton95.test.utility.LogHelper;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRuneAltar extends BlockTest{
	private ItemRune rune;
	
	public BlockRuneAltar(String name, ItemRune rune) {
		super(name, Material.ROCK,  0, null, 0, true);
		this.rune = rune;
		setBlockUnbreakable();
	}
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		
		if(entityIn instanceof EntityItem && ((EntityItem) entityIn).getItem().getItem().equals(ItemRegistry.runeEssence)){
			while(((EntityItem) entityIn).getItem().getCount() > 0) {
				ItemStack itemstack = new ItemStack(rune);
				spawnAsEntity(worldIn, entityIn.getPosition(), itemstack);
				((EntityItem) entityIn).getItem().setCount(((EntityItem) entityIn).getItem().getCount() - 1);
			}
			entityIn.setDead();
		}
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}
	
	public static final AxisAlignedBB BoundingBox = new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BoundingBox;
	}
}
