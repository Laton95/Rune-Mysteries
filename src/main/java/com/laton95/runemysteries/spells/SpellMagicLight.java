package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ModBlocks;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class SpellMagicLight extends SpellBase
{
	private final static List<SpellCost> costs = ImmutableList.of
			(
					new SpellCost(ModItems.NATURE_RUNE, 1)
			);
	
	private Block light = ModBlocks.WHITE_LIGHT;
	
	private BlockPos pos;
	
	private EnumFacing facing;
	
	private float hitX;
	
	private float hitY;
	
	private float hitZ;
	
	public SpellMagicLight()
	{
		super(costs, 5, NamesReference.Spells.MAGIC_LIGHT_SPELL_NAME, NamesReference.Spells.MAGIC_LIGHT_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public void fireSpell(World world, EntityPlayer player)
	{
		IBlockState iblockstate1 = light.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 0, player, EnumHand.MAIN_HAND);
		
		if(placeBlockAt(player, world, pos, iblockstate1, light))
		{
			iblockstate1 = world.getBlockState(pos);
			SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, world, pos, player);
			world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
		}
	}
	
	@Override
	public boolean canCast(World world, EntityPlayer player)
	{
		RayTraceResult traceResult = player.rayTrace(20, 0f);
		
		if(traceResult.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			pos = traceResult.getBlockPos();
			facing = traceResult.sideHit;
			hitX = (float) traceResult.hitVec.x;
			hitY = (float) traceResult.hitVec.y;
			hitZ = (float) traceResult.hitVec.z;
			
			IBlockState iblockstate = world.getBlockState(pos);
			Block block = iblockstate.getBlock();
			
			if(!block.isReplaceable(world, pos))
			{
				pos = pos.offset(facing);
			}
			
			return player.canPlayerEdit(pos, facing, ItemStack.EMPTY) && world.mayPlace(light, pos, false, facing, null);
		}
		
		return false;
	}
	
	private boolean placeBlockAt(EntityPlayer player, World world, BlockPos pos, IBlockState newState, Block block)
	{
		return world.setBlockState(pos, newState, 11);
	}
}
