/*
 * Author: Josue Galeas
 * Last Edit: Feb 7, 2016
 * Description: TODO
 */

import java.util.List;

public class List_ops
{
	public static Coordinate<Double> getCOM(List<Coordinate<Double>> a)
	{
		int entries = a.size();
		double total_x = 0.0, total_y = 0.0;

		for (int c = 0; c < entries; c++)
		{
			total_x += a.get(c).getX();
			total_y += a.get(c).getY();
		}

		return new Coordinate<Double>(total_x/entries, total_y/entries);
	}

	// Currently only used for normalized_HCS
	public static void print_coordlist_int(List<Coordinate<Integer>> a)
	{
		int entries = a.size();

		for (int c = 0; c < entries; c++)
		{
			System.out.println(a.get(c));
		}
	}

	public static void print_coordlist_double(List<Coordinate<Double>> a)
	{
		int entries = a.size();

		for (int c = 0; c < entries; c++)
		{
			System.out.println(a.get(c));
		}
	}

	public static void print_matrix(List<List<Integer>> a)
	{
		int entries = a.size();

		for (int x = 0; x < entries; x++)
		{
			for (int y = 0; y < entries; y++)
			{
				System.out.print(a.get(x).get(y));
				if (y < (entries - 1))
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}
