package com.laton95.runemysteries.spells;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class SnowballSpell extends SpellBase
{
	
	private final static List<SpellCost> costs = ImmutableList.of(new SpellCost(ModItems.RUNE, 2, EnumRuneType.WATER.ordinal()), new SpellCost(ModItems.RUNE, 1, EnumRuneType.AIR.ordinal()));
	
	public SnowballSpell()
	{
		super(costs, 5, NamesReference.Spells.SNOWBALL_SPELL_NAME, NamesReference.Spells.SNOWBALL_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}
	
	@Override
	public boolean fireSpell(World world, EntityPlayer player)
	{
		world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
		
		if (!world.isRemote)
		{
			EntitySnowball entitysnowball = new EntitySnowball(world, player);
			entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entitysnowball);
		}
		
		return true;
	}
	
}
