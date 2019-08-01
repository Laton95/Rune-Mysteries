package com.laton95.runemysteries.util;

import com.laton95.runemysteries.RuneMysteries;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Map;

public class StructureHelper {
	
	private Template template;
	
	private World world;
	
	private BlockPos pos;
	
	private PlacementSettings settings;
	
	private ResourceLocation loot;
	
	public StructureHelper(World world, String structureName, BlockPos pos) {
		this(world, structureName, pos, new PlacementSettings());
	}
	
	public StructureHelper(World world, String structureName, BlockPos pos, PlacementSettings settings) {
		if(structureName != null) {
			this.world = world;
			this.settings = settings;
			this.pos = pos;
			
//			TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
//			ResourceLocation structureResource = new ResourceLocation(RuneMysteries.MOD_ID, structureName);
//			template = templatemanager.getTemplate(structureResource);
		}
	}
	
	public StructureHelper(World world, String structureName, BlockPos pos, ResourceLocation loot) {
		this(world, structureName, pos, new PlacementSettings());
		this.loot = loot;
	}
	
	public void generate() {
		if(template != null) {
			template.addBlocksToWorld(world, pos, settings, 2);
			
//			Map<BlockPos, String> map = template.func_215381_a(pos, settings);
//			for(Map.Entry<BlockPos, String> entry : map.entrySet()) {
//				if("chest".equals(entry.getValue())) {
//					BlockPos chestPos = entry.getKey();
//					world.setBlockState(chestPos, Blocks.CHEST.getDefaultState());
//					TileEntity tileentity = world.getTileEntity(chestPos);
//
//					if(tileentity instanceof ChestTileEntity) {
//						((ChestTileEntity) tileentity).setLootTable(loot, world.rand.nextLong());
//					}
//				}
//			}
		}
	}
}
