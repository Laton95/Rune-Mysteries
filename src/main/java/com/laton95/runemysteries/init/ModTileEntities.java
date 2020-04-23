package com.laton95.runemysteries.init;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.tileentity.GravestoneTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = RuneMysteries.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RuneMysteries.MOD_ID)
public class ModTileEntities {
	
	public static final TileEntityType<GravestoneTileEntity> GRAVESTONE = null;
	
	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		register(event, GravestoneTileEntity::new, ModBlocks.GRAVESTONE, "gravestone");
	}
	
	private static void register(RegistryEvent.Register<TileEntityType<?>> event, Supplier<? extends TileEntity> supplier, Block block, String name) {
		TileEntityType tileEntity = TileEntityType.Builder.create(supplier, block).build(null);
		tileEntity.setRegistryName(RuneMysteries.MOD_ID, name);
		event.getRegistry().register(tileEntity);
	}
}
