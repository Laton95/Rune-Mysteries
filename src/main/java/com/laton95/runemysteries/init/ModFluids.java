package com.laton95.runemysteries.init;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModFluids
{
	
	public static final RMModFluid fluid_blood = new RMModFluid("fluid_Blood");
	
	private static List<RMModFluid> fluidList = ImmutableList.of(fluid_blood);
	
	private static List<RMModFluidBlock> fluidBlocks = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerFluids(RegistryEvent.Register<Block> event)
	{
		for(RMModFluid fluid : fluidList)
		{
			FluidRegistry.registerFluid(fluid);
			RMModFluidBlock fluidBlock = new RMModFluidBlock(fluid.getFluidName(), fluid);
			fluid.setFluidBlock(fluidBlock);
			event.getRegistry().register(fluidBlock);
			FluidRegistry.addBucketForFluid(fluid);
			fluidBlocks.add(fluidBlock);
		}
		
		RuneMysteries.proxy.registerRenders();
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		for(RMModFluidBlock fluidBlock : fluidBlocks)
		{
			ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase()
			{

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state)
				{
					return new ModelResourceLocation(new ResourceLocation(ModReference.MOD_ID, fluidBlock.getFluidName()), "fluid");
				}
			});
		}
	}
	
	public static final class RMModFluid extends Fluid
	{
		
		private final String name;
		
		private RMModFluidBlock fluidBlock;
		
		public RMModFluid(String name)
		{
			super(ModReference.MOD_ID + ":" + name, new ResourceLocation(ModReference.MOD_ID, "fluids/" + name + "_still"), new ResourceLocation(ModReference.MOD_ID, "fluids/" + name + "_flowing"));
			this.name = name;
		}
		
		public String getFluidName()
		{
			return name;
		}
		
		public RMModFluidBlock getFluidBlock()
		{
			return fluidBlock;
		}
		
		public void setFluidBlock(RMModFluidBlock fluidBlock)
		{
			this.fluidBlock = fluidBlock;
		}
	}
	
	public static final class RMModFluidBlock extends BlockFluidClassic
	{
		private String fluidName;
		
		public RMModFluidBlock(String name, Fluid fluid)
		{
			super(fluid, Material.WATER);
			setUnlocalizedName(ModReference.MOD_ID + ":" + name);
			this.setRegistryName(ModReference.MOD_ID, name.toLowerCase());
			this.fluidName = name;
		}
		
		public String getFluidName()
		{
			return fluidName;
		}
	}
}
