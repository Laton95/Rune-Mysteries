package com.laton95.runemysteries.util;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class StructureHelper
{
	
	private Template template;
	
	private World world;
	
	private BlockPos pos;
	
	private PlacementSettings settings;
	
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
	
	public void generate()
	{
		template.addBlocksToWorld(world, pos, settings, 2);
	}
}
