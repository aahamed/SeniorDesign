/*
 * Author: Josue Galeas
 * Last Edit: Feb 13, 2016
 * Description: Common operations for the data structures found in... TODO
 */

import java.util.ArrayList;
import java.util.List;

public class List_ops
{
	// Currently only used for making w_matrix into graph for Prim, and two uses in Kruskal for initialization
	public static int[][] ll2array(List<List<Integer>> a)
	{
		int x_entries = a.size();
		int y_entries = a.get(0).size();
		int[][] output = new int[x_entries][y_entries];

		for (int x = 0; x < x_entries; x++)
		{
			for (int y = 0; y < y_entries; y++)
			{
				output[x][y] = a.get(x).get(y);
			}
		}

		return output;
	}

	// Currently not used
	public static List<List<Integer>> array2ll(int[][] a)
	{
		int x_entries = a.length;
		int y_entries = a[0].length;
		List<List<Integer>> output = new ArrayList<List<Integer>>();

		for (int x = 0; x < x_entries; x++)
		{
			output.add(new ArrayList<Integer>());

			for (int y = 0; y < y_entries; y++)
			{
				output.get(x).add(a[x][y]);
			}
		}

		return output;
	}

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
