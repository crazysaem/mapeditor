package de.samuel.mapeditor;

public class Vector2d 
{
	private double x;
	private double y;
	
	public Vector2d (double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2d (int x1, int y1, int x2, int y2)
	{
		this(x2 - x1, y2- y1);
	}
	
	public int getLength ()
	{
		return (int) Math.sqrt(x*x + y*y);
	}
	
	public void setLength (double newLength)
	{
		double length = getLength();
		
		x /= length;
		y /= length;
		
		x *= newLength;
		y *= newLength;
	}
	
	public Vector2d copy()
	{
		return new Vector2d(x, y);
	}

	public double getxDir() {
		return x;
	}

	public double getyDir() {
		return y;
	}
}
