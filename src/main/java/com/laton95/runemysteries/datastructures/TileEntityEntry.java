package com.laton95.runemysteries.datastructures;

import com.laton95.runemysteries.reference.ModReference;
import net.minecraft.tileentity.TileEntity;

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
