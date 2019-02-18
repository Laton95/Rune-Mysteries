package com.laton95.runemysteries;

import com.laton95.runemysteries.capability.CapabilityRejected;
import com.laton95.runemysteries.config.Config;
import com.laton95.runemysteries.init.ModItems;
import com.laton95.runemysteries.item.crafting.AltarRecipe;
import com.laton95.runemysteries.item.crafting.ObeliskRecipe;
import com.laton95.runemysteries.network.RunemysteriesPacketHandler;
import com.laton95.runemysteries.proxy.ClientProxy;
import com.laton95.runemysteries.proxy.ServerProxy;
import com.laton95.runemysteries.world.RuinTracker;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeSerializers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.laton95.runemysteries.RuneMysteries.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RuneMysteries {
	
	public static final String MOD_ID = "runemysteries";
	
	public static ServerProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> ServerProxy::new);
	
	public static final ItemGroup RUNE_GROUP = new ItemGroup(MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.FIRE_RUNE);
		}
	};
	
	public static RuinTracker ruinTracker;
	
	public RuneMysteries() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverConfig);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);
	}
	
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		RunemysteriesPacketHandler.register();
		RecipeSerializers.register(AltarRecipe.RUNE_ALTAR_SERIALIZER);
		RecipeSerializers.register(ObeliskRecipe.OBELISK_SERIALIZER);
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::openGui);
		CapabilityRejected.register();
	}
	
	public void serverConfig(ModConfig.ModConfigEvent event) {
		if(event.getConfig().getSpec() == Config.SERVER_SPEC) {
			Config.load();
		}
	}
}
