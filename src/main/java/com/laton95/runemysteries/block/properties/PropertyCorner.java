package com.laton95.runemysteries.block.properties;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.laton95.runemysteries.util.EnumCorner;
import net.minecraft.block.properties.PropertyEnum;

import java.util.Collection;

public class PropertyCorner extends PropertyEnum<EnumCorner>
{
	
	protected PropertyCorner(String name, Collection<EnumCorner> allowedValues)
	{
		super(name, EnumCorner.class, allowedValues);
	}
	
	public static PropertyCorner create(String name)
	{
		return create(name, Predicates.alwaysTrue());
	}
	
	/**
	 * Create a new PropertyDirection with all directions that match the given Predicate
	 */
	public static PropertyCorner create(String name, Predicate<EnumCorner> filter)
	{
		return create(name, Collections2.filter(Lists.newArrayList(EnumCorner.values()), filter));
	}
	
	/**
	 * Create a new PropertyDirection for the given direction values
	 */
	public static PropertyCorner create(String name, Collection<EnumCorner> values)
	{
		return new PropertyCorner(name, values);
	}
}
