/*
Author: Aadil Ahamed
Date: 1/12/16
Description: Class containing a library of methods related 	to HCS
*/

public class HCS
{
	private HCS(){}
	
	/*
	 * Converts a HCS coordinate to Cartesian. Port of h2c.m
	 * Assumes HCS coordinates are integers.
	 * @param p HCS Coordinate<Integer>
	 * @param r edge length of hexagon
	 * @return Cartesian Coordinate<double>
	 */
	public static Coordinate<Double> hexToCart(Coordinate<Integer> p)
	{
		double h = 	GlobalConstants.APOTHEM;
		double x = p.getX() * 2 * h + p.getY() * 2 * h * Math.cos(Math.PI/3);
		double y = p.getY() * 2 * h * Math.cos(Math.PI/6);
		return new Coordinate<Double>(x, y);
	}
	
	/*
	 * Converts a Cartesian coordinate to HCS. Port of c2h1.m
	 * Assumes Cartesian coordinates are type Double.
	 * @param p HCS Coordinate<Integer>
	 * @param r edge length of hexagon
	 * @return Hex Coordinate<double>
	 */
	public static Coordinate<Integer> cartToHex(Coordinate<Double> p)
	{
		double U = (p.getX()-p.getY())/Math.sqrt(3);
		double V = (2*p.getY())/Math.sqrt(3);
		int u = (int)(Math.round(U/Math.sqrt(3*Math.pow((GlobalConstants.SN_R),2))));
		int v = (int)(Math.round(U/Math.sqrt(3*Math.pow((GlobalConstants.SN_R),2))));
		return new Coordinate<Integer>(u,v);
	}
	
	/* Unit Testing */
	public static void test1()
	{
		int x = 0;
		int y = 0;
		Coordinate<Integer> c = new Coordinate<Integer>(x, y);
		Coordinate<Double> d = HCS.hexToCart(c);
		System.out.printf("HCS Coord: " + c + " Cart Coord: (%.2f, %.2f)\n", d.getX(), d.getY()); //verify against matlab code

		x = 7000;
		y = 7000;
		c.setX(x);
		c.setY(y);
		d = HCS.hexToCart(c);
		System.out.printf("HCS Coord: " + c + " Cart Coord: (%.2f, %.2f)\n", d.getX(), d.getY());
		
		x = -3;
		y = 2;
		c.setX(x);
		c.setY(y);
		d = HCS.hexToCart(c);
		System.out.printf("HCS Coord: " + c + " Cart Coord: (%.2f, %.2f)\n", d.getX(), d.getY());
		x = 30;
		y = 22;
		c.setX(x);
		c.setY(y);
		d = HCS.hexToCart(c);
		System.out.printf("HCS Coord: " + c + " Cart Coord: (%.2f, %.2f)\n", d.getX(), d.getY());
		
		x = 3;
		y = -2;
		c.setX(x);
		c.setY(y);
		d = HCS.hexToCart(c);
		System.out.printf("HCS Coord: " + c + " Cart Coord: (%.2f, %.2f)\n", d.getX(), d.getY());
		
	}
	
	public static void main(String[] args)
	{
		HCS.test1();
	}
	
}