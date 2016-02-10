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
	
	public static Coordinate<Integer> add(Coordinate<Integer> p, Coordinate<Integer> q)
	{
		return new Coordinate<Integer>(p.getX() + q.getX(), p.getY() + q.getY());
	}
	
	public static Coordinate<Double> minus(Coordinate<Double> p, Coordinate<Double> q)
	{
		return new Coordinate<Double>(p.getX() - q.getX(), p.getY() - q.getY());
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
	
	private static void testIntAdd()
	{
		System.out.println("ADD TEST");
		int x1 = 4;
		int y1 = 5;
		int x2 = 3;
		int y2 = 3;
		Coordinate<Integer> p = new Coordinate<Integer>(x1, y1);
		Coordinate<Integer> q = new Coordinate<Integer>(x2, y2);
		Coordinate<Integer> result = Coordinate.add(p, q);
		System.out.println("P: " + p + " Q: " + q + " res = " + result);
	}
	
	private static void testDoubMinus()
	{
		System.out.println("MINUS TEST");
		double x1 = 4;
		double y1 = 5;
		double x2 = 3;
		double y2 = 3;
		Coordinate<Double> p = new Coordinate<Double>(x1, y1);
		Coordinate<Double> q = new Coordinate<Double>(x2, y2);
		Coordinate<Double> result = Coordinate.minus(p, q);
		System.out.println("P: " + p + " Q: " + q + " res = " + result);
	}
	
	public static void main(String[] args)
	{
		Coordinate.test1();
		Coordinate.test2();
		Coordinate.testIntAdd();
		Coordinate.testDoubMinus();
	}
}