package com.laton95.runemysteries.utility;

import com.laton95.runemysteries.reference.Reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class WorldNBTHelper extends WorldSavedData {
	private static final String DATA_NAME = Reference.MOD_ID;

	// Required constructors
	public WorldNBTHelper() {
		super(DATA_NAME);
	}

	public WorldNBTHelper(String s) {
		super(s);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static WorldNBTHelper get(World world) {
		  // The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
		  MapStorage storage = world.getMapStorage();
		  WorldNBTHelper instance = (WorldNBTHelper) storage.getOrLoadData(WorldNBTHelper.class, DATA_NAME);

		  if (instance == null) {
		    instance = new WorldNBTHelper();
		    storage.setData(DATA_NAME, instance);
		  }
		  return instance;
		}

	// WorldSavedData methods
}