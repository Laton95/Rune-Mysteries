package com.laton95.runemysteries.init;

import com.laton95.runemysteries.init.InitDataStructures.ComponentEntry;
import com.laton95.runemysteries.init.InitDataStructures.StructureEntry;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.mapGenerators.*;
import com.laton95.runemysteries.world.structureComponents.ComponentCenterStructure;
import com.laton95.runemysteries.world.structureComponents.ComponentElementalObelisks;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorldGen
{
	
	public static void registerWorldGen()
	{
		LogHelper.info("Registering world-gen");
		
		WorldGenerator chunkGenerator;
		chunkGenerator = new WorldGenerator();
		MinecraftForge.EVENT_BUS.register(chunkGenerator);
		MinecraftForge.TERRAIN_GEN_BUS.register(chunkGenerator);
		GameRegistry.registerWorldGenerator(chunkGenerator, 0);
		
		StructureEntry[] structures = {
				new InitDataStructures().new StructureEntry("RuneAltar_SURFACE", MapGenRuneAltar_SURFACE.Start.class),
				new InitDataStructures().new StructureEntry("RuneAltar_UNDERGROUND", MapGenRuneAltar_UNDERGROUND.Start.class),
				new InitDataStructures().new StructureEntry("RuneAltar_SOUL", MapGenRuneAltar_SOUL.Start.class),
				new InitDataStructures().new StructureEntry("RuneAltar_NETHER", MapGenRuneAltar_NETHER.Start.class),
				new InitDataStructures().new StructureEntry("RuneAltar_END", MapGenRuneAltar_END.Start.class),
				new InitDataStructures().new StructureEntry("elemental_obelisk", MapGenElementalObelisk.Start.class),
				new InitDataStructures().new StructureEntry("air_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("blood_temple", MapGenBloodTemple.Start.class),
				new InitDataStructures().new StructureEntry("body_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("chaos_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("cosmic_temple", MapGenCosmicTemple.Start.class),
				new InitDataStructures().new StructureEntry("death_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("earth_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("fire_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("law_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("mind_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("nature_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("soul_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("water_temple", MapGenAirTemple.Start.class),
				new InitDataStructures().new StructureEntry("essence_mine", MapGenAirTemple.Start.class),
				};
		
		ComponentEntry[] components = {
				new InitDataStructures().new ComponentEntry("air_obelisk", ComponentElementalObelisks.ComponentAirObelisk.class),
				new InitDataStructures().new ComponentEntry("earth_obelisk", ComponentElementalObelisks.ComponentEarthObelisk.class),
				new InitDataStructures().new ComponentEntry("fire_obelisk", ComponentElementalObelisks.ComponentFireObelisk.class),
				new InitDataStructures().new ComponentEntry("water_obelisk", ComponentElementalObelisks.ComponentWaterObelisk.class),
				new InitDataStructures().new ComponentEntry("center_structure", ComponentCenterStructure.class)
				};
		
		for(StructureEntry structure : structures)
		{
			MapGenStructureIO.registerStructure(structure.structureClass, structure.name);
		}
		
		for(ComponentEntry component : components)
		{
			MapGenStructureIO.registerStructureComponent(component.componentClass, component.name);
		}
	}
}
