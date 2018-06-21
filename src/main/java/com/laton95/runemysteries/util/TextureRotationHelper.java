package com.laton95.runemysteries.util;

public class TextureRotationHelper
{
	public Coordinate[] coordinates;
	
	public TextureRotationHelper(int size)
	{
		coordinates = new Coordinate[]
				{
						new Coordinate(0.0D, 0.0D),
						new Coordinate(0.0D, 1.0D * size),
						new Coordinate(1.0D * size, 1.0D * size),
						new Coordinate(1.0D * size, 0.0D)
				};
	}
	
	public void rotate(int count)
	{
		for(int i = 0; i < count; i++)
		{
			Coordinate temp = coordinates[3];
			coordinates[3] = coordinates[0];
			coordinates[0] = coordinates[1];
			coordinates[1] = coordinates[2];
			coordinates[2] = temp;
		}
	}
	
	public void flip(Alignment axis)
	{
		switch(axis)
		{
			case HORIZONTAL:
				Coordinate temp = coordinates[0];
				coordinates[0] = coordinates[1];
				coordinates[1] = temp;
				
				temp = coordinates[2];
				coordinates[2] = coordinates[3];
				coordinates[3] = temp;
				break;
			case VERTICAL:
				temp = coordinates[0];
				coordinates[0] = coordinates[3];
				coordinates[3] = temp;
				
				temp = coordinates[1];
				coordinates[1] = coordinates[2];
				coordinates[2] = temp;
				break;
		}
	}
	
	public enum Alignment
	{
		HORIZONTAL,
		VERTICAL
	}
	
	public class Coordinate
	{
		public double u;
		
		public double v;
		
		Coordinate(double u, double v)
		{
			this.u = u;
			this.v = v;
		}
	}
}
