package com.laton95.test.item;

import com.laton95.test.reference.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTest extends Item{
	private String name;
	
	public ItemTest(){
		super();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
