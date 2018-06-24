package com.laton95.runemysteries.util;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Map;

public class StructureHelper
{
	
	private Template template;
	
	private World world;
	
	private BlockPos pos;
	
	private PlacementSettings settings;
	
	private ResourceLocation loot;
	
	public StructureHelper(World world, String structureName, BlockPos pos)
	{
		this(world, structureName, pos, new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID));
	}
	
	public StructureHelper(World world, String structureName, BlockPos pos, PlacementSettings settings)
	{
		this.world = world;
		this.settings = settings;
		this.pos = pos;
		
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
		ResourceLocation structureResource = new ResourceLocation(ModReference.MOD_ID, structureName);
		template = templatemanager.getTemplate(minecraftserver, structureResource);
	}
	
	public StructureHelper(World world, String structureName, BlockPos pos, ResourceLocation loot)
	{
		this(world, structureName, pos, new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID));
		this.loot = loot;
	}
	
	public void generate()
	{
		template.addBlocksToWorld(world, pos, settings, 2);
		
		Map<BlockPos, String> map = template.getDataBlocks(pos, settings);
		for(Map.Entry<BlockPos, String> entry : map.entrySet())
		{
			if("chest".equals(entry.getValue()))
			{
				BlockPos chestPos = entry.getKey();
				world.setBlockState(chestPos, Blocks.CHEST.getDefaultState());
				TileEntity tileentity = world.getTileEntity(chestPos);
				
				if(tileentity instanceof TileEntityChest)
				{
					((TileEntityChest) tileentity).setLootTable(loot, world.rand.nextLong());
				}
			}
		}
	}
}
