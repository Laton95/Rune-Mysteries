package com.laton95.runemysteries.init;

import com.laton95.runemysteries.init.InitDataStructures.ComponentEntry;
import com.laton95.runemysteries.init.InitDataStructures.StructureEntry;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.mapGenerators.*;
import com.laton95.runemysteries.world.structureComponents.*;
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
				new InitDataStructures().new StructureEntry("RuneTemple", MapGenRuneTemple.Start.class),
				new InitDataStructures().new StructureEntry("CosmicTemple", MapGenCosmicTemple.Start.class),
				new InitDataStructures().new StructureEntry("EssenceMine", MapGenMine.Start.class)
				};
		
		ComponentEntry[] components = {
				new InitDataStructures().new ComponentEntry("SurfaceAltar", ComponentSurfaceAltar.class),
				new InitDataStructures().new ComponentEntry("UndergroundAltar", ComponentUndergroundAltar.class),
				new InitDataStructures().new ComponentEntry("SoulAltar", ComponentSoulAltar.class),
				new InitDataStructures().new ComponentEntry("EndAltar", ComponentEndAltar.class),
				new InitDataStructures().new ComponentEntry("NetherAltar", ComponentNetherAltar.class),
				new InitDataStructures().new ComponentEntry("Temple", ComponentTemple.class),
				new InitDataStructures().new ComponentEntry("CosmicTemple", ComponentCosmicTemple.class),
				new InitDataStructures().new ComponentEntry("EssenceMine", ComponentMine.class)
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
