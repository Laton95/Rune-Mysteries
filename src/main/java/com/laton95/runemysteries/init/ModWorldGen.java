package com.laton95.runemysteries.init;

import com.laton95.runemysteries.init.InitDataStructures.ComponentEntry;
import com.laton95.runemysteries.init.InitDataStructures.StructureEntry;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.world.WorldGenerator;
import com.laton95.runemysteries.world.mapGenerators.*;
import com.laton95.runemysteries.world.structureComponents.ComponentCenterStructure;
import com.laton95.runemysteries.world.structureComponents.ComponentElementalObelisks;
import com.laton95.runemysteries.world.structureComponents.ComponentSurfaceRuin;
import com.laton95.runemysteries.world.structureComponents.ComponentUndergroundRuin;
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
				new InitDataStructures().new StructureEntry("elemental_obelisk", MapGenElementalObelisk.Start.class),
				new InitDataStructures().new StructureEntry("air_temple", MapGenTempleAir.Start.class),
				new InitDataStructures().new StructureEntry("blood_temple", MapGenTempleBlood.Start.class),
				new InitDataStructures().new StructureEntry("body_temple", MapGenTempleBody.Start.class),
				new InitDataStructures().new StructureEntry("chaos_temple", MapGenTempleChaos.Start.class),
				new InitDataStructures().new StructureEntry("cosmic_temple", MapGenTempleCosmic.Start.class),
				new InitDataStructures().new StructureEntry("death_temple", MapGenTempleDeath.Start.class),
				new InitDataStructures().new StructureEntry("earth_temple", MapGenTempleEarth.Start.class),
				new InitDataStructures().new StructureEntry("fire_temple", MapGenTempleFire.Start.class),
				new InitDataStructures().new StructureEntry("law_temple", MapGenTempleLaw.Start.class),
				new InitDataStructures().new StructureEntry("mind_temple", MapGenTempleMind.Start.class),
				new InitDataStructures().new StructureEntry("nature_temple", MapGenTempleNature.Start.class),
				new InitDataStructures().new StructureEntry("soul_temple", MapGenTempleSoul.Start.class),
				new InitDataStructures().new StructureEntry("water_temple", MapGenTempleWater.Start.class),
				new InitDataStructures().new StructureEntry("essence_mine", MapGenEssenceMine.Start.class),
				
				new InitDataStructures().new StructureEntry("air_ruin", MapGenRuinAir.Start.class),
				new InitDataStructures().new StructureEntry("astral_ruin", MapGenRuinAstral.Start.class),
				new InitDataStructures().new StructureEntry("blood_ruin", MapGenRuinBlood.Start.class),
				new InitDataStructures().new StructureEntry("body_ruin", MapGenRuinBody.Start.class),
				new InitDataStructures().new StructureEntry("cosmic_ruin", MapGenRuinCosmic.Start.class),
				new InitDataStructures().new StructureEntry("chaos_ruin", MapGenRuinChaos.Start.class),
				new InitDataStructures().new StructureEntry("death_ruin", MapGenRuinDeath.Start.class),
				new InitDataStructures().new StructureEntry("earth_ruin", MapGenRuinEarth.Start.class),
				new InitDataStructures().new StructureEntry("fire_ruin", MapGenRuinFire.Start.class),
				new InitDataStructures().new StructureEntry("law_ruin", MapGenRuinLaw.Start.class),
				new InitDataStructures().new StructureEntry("mind_ruin", MapGenRuinMind.Start.class),
				new InitDataStructures().new StructureEntry("nature_ruin", MapGenRuinNature.Start.class),
				new InitDataStructures().new StructureEntry("soul_ruin", MapGenRuinSoul.Start.class),
				new InitDataStructures().new StructureEntry("water_ruin", MapGenRuinWater.Start.class)
				};
		
		ComponentEntry[] components = {
				new InitDataStructures().new ComponentEntry("air_obelisk", ComponentElementalObelisks.ComponentAirObelisk.class),
				new InitDataStructures().new ComponentEntry("earth_obelisk", ComponentElementalObelisks.ComponentEarthObelisk.class),
				new InitDataStructures().new ComponentEntry("fire_obelisk", ComponentElementalObelisks.ComponentFireObelisk.class),
				new InitDataStructures().new ComponentEntry("water_obelisk", ComponentElementalObelisks.ComponentWaterObelisk.class),
				new InitDataStructures().new ComponentEntry("center_structure", ComponentCenterStructure.class),
				new InitDataStructures().new ComponentEntry("surface_ruin", ComponentSurfaceRuin.class),
				new InitDataStructures().new ComponentEntry("underground_ruin", ComponentUndergroundRuin.class)
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
