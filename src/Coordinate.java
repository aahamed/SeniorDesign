/*
Author: Aadil Ahamed
Date: 1/12/16
Description: Class representing a 2-D Coordinate. You may specify the type (int/float/etc.)
*/

public class Coordinate <T>
{
	private T x;
	private T y;
	public Coordinate(T x, T y)
	{
		this.x = x;
		this.y = y;
	}
	
	public T getX()
	{
		return x;
	}
	
	public T getY()
	{
		return y;
	}
	
	public void setX(T value)
	{
		this.x = value;
	}
	
	public void setY(T value)
	{
		this.y = value;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y  + ")";
	}
	
	public static void test1()
	{
		float x = 1.5f;
		float y = 3.3f;
		Coordinate<Float> c = new Coordinate<Float>(x, y);
		System.out.println(c);
	}
	
	public static void test2()
	{
		int x = 1;
		int y = 3;
		Coordinate<Integer> c = new Coordinate<Integer>(x, y);
		System.out.println(c);
	}
	
	public static void main(String[] args)
	{
		Coordinate.test1();
		Coordinate.test2();
	}
}