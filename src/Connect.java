/*
Author: Aadil Ahamed
Date: 1/12/16
Description: Port of connect.m from Kai's repository
*/

public class Connect
{
	public Connect(){}
	
	
	private static double calculateNum(double theta, double cart_distance)
	{
		final double PI = Math.PI;
		double num;
		if(theta > PI / 6)
		{
			num = (cart_distance * Math.cos(PI/3 - theta))/(2 * GlobalConstants.R * Math.cos(PI/6));
		}
		else
		{
			num = (cart_distance * Math.cos(theta))/(2 * GlobalConstants.R * Math.cos(PI/6));
		}
		return num;
	}
	
	private static double incrementNum(double num, double theta)
	{
		if((Math.abs(theta - Math.PI/6) < 0.03) && (Math.abs(num - Math.ceil(num)) < 0.1) || (num - Math.floor(num) < 0.02))
		{
			num += 0.15;
		}
		return num;
	}
	
	private static int calculatePtr(double num)
	{
		if(num <= 1.02)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static ConnectOut connect(Coordinate<Integer> p, Coordinate<Integer> q)
	{
		final double PI = Math.PI;
		Coordinate<Double> a = HCS.hexToCart(p);
		Coordinate<Double> b = HCS.hexToCart(q);
		//double[] cart_vector = new double[2];
		//cart_vector[0] =  a.getX() - b.getX();
		//cart_vector[1] = a.getY() - b.getY();
		double cart_distance = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)); // calculate cartesian distance
		int[] pq_vector = new int[2];
		pq_vector[0] = q.getX() - p.getX();		//represents x component of vector PQ
		pq_vector[1] = q.getY() - p.getY();		//represents y component of vector PQ
		double theta, num;
		int pointer;
		int[] direction = new int[2];
		//CASE 1: Theta w.r.t x+ axis is in 1st or 3rd quadrant
		if(pq_vector[0] * pq_vector [1] > 0)
		{
			theta = Math.atan(Math.sqrt(3)/((2 * pq_vector[0] / pq_vector[1]) + 1));
			direction[0] = 1;
			direction[1] = 0;
			num = Connect.calculateNum(theta, cart_distance);
			num = Connect.incrementNum(num, theta);
			pointer = Connect.calculatePtr(num);
		}
		//CASE 2: Theta w.r.t x+ axis is in 2nd or 4th quadrant
		else if(pq_vector[0] * pq_vector [1] > 0)
		{
			// Case 1: w.r.t y+ axis
			if(Math.abs(pq_vector[0]) <= Math.abs(pq_vector[1]))
			{
				theta = Math.atan((-1 * Math.sqrt(3))/((2 * pq_vector[1] / pq_vector[0]) + 1));
				direction[0] = 0;
				direction[1] = 1;
				num = Connect.calculateNum(theta, cart_distance);
				num = Connect.incrementNum(num, theta);
				pointer = Connect.calculatePtr(num);
			}
			// Case 2: w.r.t x+ axis
			else
			{
				theta = Math.atan((-1 * Math.sqrt(3))/((2 * pq_vector[0] / pq_vector[1]) + 1));
				direction[0] = -1;
				direction[1] = 0;
				num = Connect.calculateNum(theta, cart_distance);
				num = Connect.incrementNum(num, theta);
				pointer = Connect.calculatePtr(num);
			}
		}
		//CASE 3: pq_vector[0] * pq_vector [1] == 0
		else
		{
			theta = 0;
			if(pq_vector[0] == 0){ direction[0] = 0; direction[1] = 1;}
			else{ direction[0] = 1; direction[1] = 0; }
			num = Connect.calculateNum(theta, cart_distance);
			pointer = Connect.calculatePtr(num);
		}
		return new ConnectOut(num, theta, pointer, direction);
	}
	
	private static void test1()
	{
		Coordinate<Integer> p = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> q = new Coordinate<Integer>(10, 0);
		System.out.println(Connect.connect(p, q)); // verify against matlab code
	}
	
	public static void main(String[] args)
	{
		Connect.test1();
	}
}