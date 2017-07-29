package com.laton95.runemysteries.init;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.laton95.runemysteries.creativetab.RMCreativeTab;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

//Thanks to Choonster for the example class this class is based on.
public class LiquidRegistry {
	/**
	 * The fluids registered by this mod. Includes fluids that were already
	 * registered by another mod.
	 */
	public static final Set<Fluid> FLUIDS = new HashSet<>();

	/**
	 * The fluid blocks from this mod only. Doesn't include blocks for fluids
	 * that were already registered by another mod.
	 */
	public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

	public static final Fluid blood = createFluid("blood", true,
			fluid -> fluid.setLuminosity(10).setDensity(1600).setViscosity(100),
			fluid -> new BlockFluidClassic(fluid, new MaterialLiquid(MapColor.RED)));

	/**
	 * Create a {@link Fluid} and its {@link IFluidBlock}, or use the existing
	 * ones if a fluid has already been registered with the same name.
	 *
	 * @param name
	 *            The name of the fluid
	 * @param hasFlowIcon
	 *            Does the fluid have a flow icon?
	 * @param fluidPropertyApplier
	 *            A function that sets the properties of the {@link Fluid}
	 * @param blockFactory
	 *            A function that creates the {@link IFluidBlock}
	 * @return The fluid and block
	 */
	private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon,
			Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory) {
		final String texturePrefix = Reference.MOD_ID + ":" + "blocks/fluid_";

		final ResourceLocation still = new ResourceLocation(texturePrefix + name + "_still");
		final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(texturePrefix + name + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

		if (useOwnFluid) {
			fluidPropertyApplier.accept(fluid);
			MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
		} else {
			fluid = FluidRegistry.getFluid(name);
		}

		FLUIDS.add(fluid);

		return fluid;
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		/**
		 * Register this mod's fluid {@link Block}s.
		 *
		 * @param event
		 *            The event
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				block.setRegistryName(Reference.MOD_ID, "fluid." + fluidBlock.getFluid().getName());
				block.setUnlocalizedName(Reference.MOD_ID + ":" + fluidBlock.getFluid().getUnlocalizedName());
				block.setCreativeTab(RMCreativeTab.RM_TAB);
				registry.register(block);
			}
		}

		/**
		 * Register this mod's fluid {@link ItemBlock}s.
		 *
		 * @param event
		 *            The event
		 */
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final IFluidBlock fluidBlock : MOD_FLUID_BLOCKS) {
				final Block block = (Block) fluidBlock;
				final ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				registry.register(itemBlock);
			}
		}
	}

	public static void registerFluidContainers() {
		// registerTank(FluidRegistry.WATER);
		// registerTank(FluidRegistry.LAVA);

		for (final Fluid fluid : FLUIDS) {
			registerBucket(fluid);
			// registerTank(fluid);
		}
	}

	private static void registerBucket(Fluid fluid) {
		LogHelper.info(fluid.getName() + " bucket added: " + FluidRegistry.addBucketForFluid(fluid));
	}

	// private static void registerTank(Fluid fluid) {
	// final FluidStack fluidStack = new FluidStack(fluid,
	// TileEntityFluidTank.CAPACITY);
	//
	// final Item item = Item.getItemFromBlock(ModBlocks.FLUID_TANK);
	// assert item instanceof ItemFluidTank;
	//
	// ((ItemFluidTank) item).addFluid(fluidStack);
	// }

}