package com.laton95.runemysteries.init;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public class InitDataStructures
{
	public class TileEntityEntry
	{
		public final String key;
		public final Class<? extends TileEntity> entityClass;
		
		public TileEntityEntry(String key, Class<? extends TileEntity> entityClass)
		{
			this.key = ModReference.MOD_ID + ":" + key;
			this.entityClass = entityClass;
		}
	}
	
	public class StructureEntry
	{
		public final String name;
		public final Class<? extends StructureStart> structureClass;
		
		public StructureEntry(String name, Class<? extends StructureStart> structureClass)
		{
			this.name = name;
			this.structureClass = structureClass;
		}
	}
	
	public class ComponentEntry
	{
		public final String name;
		public final Class<? extends StructureComponent> componentClass;
		
		public ComponentEntry(String name, Class<? extends StructureComponent> componentClass)
		{
			this.name = name;
			this.componentClass = componentClass;
		}
	}
}
