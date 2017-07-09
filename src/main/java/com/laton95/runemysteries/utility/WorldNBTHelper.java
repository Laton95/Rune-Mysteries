package com.laton95.runemysteries.utility;

import com.laton95.runemysteries.reference.Reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class WorldNBTHelper extends WorldSavedData {
	private static final String DATA_NAME = Reference.MOD_ID + "_ExampleData";

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

	// WorldSavedData methods
}