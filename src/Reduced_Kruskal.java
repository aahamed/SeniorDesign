/*
 * Author: Josue Galeas
 * Last Edit: Feb 8, 2016
 * Description: Port of kruskal.m, reduced down to what is actually used by the algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class Reduced_Kruskal
{
	// private int w_st;
	// private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	// private List<List<Integer>> X_st = new ArrayList<List<Integer>>();
	// TODO, might need to make an object to return the three structures above

	public static void MST(List<List<Integer>> a, List<List<Integer>> b)
	{
		boolean isUndirGraph = true;
		int[][] ne = X2ne(a);
	}

	private static int[][] X2ne(List<List<Integer>> a)
	{
		int[][] output;
		int entries = a.size();
		int total = 0, cnt = 1, uppercnt = 1;
		List<Integer> v = new ArrayList<Integer>();
		List<Coordinate<Integer>> edge = new ArrayList<Coordinate<Integer>>();

		for (int x = 0; x < entries; x++)
		{
			for (int y = (x + 1); y < entries; y++)
			{
				total += a.get(x).get(y);
			}
		}

		output = new int[total][2];

		for (int i = 1; i < entries; i++)
		{
			for (int j = 0; j < entries; j++)
			{
				if ((a.get(i - 1).get(j) != 0) && ((j + 1) > i))
					v.add(j+1);
			}

			for (int k = 0; k < v.size(); k++)
			{
				edge.add(new Coordinate<Integer>(i, v.get(k)));
			}

			uppercnt = cnt + edge.size() - 1;

			for (int l = cnt; l <= uppercnt; l++)
			{
				output[l - 1][0] = edge.get(l - cnt).getX();
				output[l - 1][1] = edge.get(l - cnt).getY();
			}

			cnt = cnt + edge.size();
			v.clear();
			edge.clear();
		}

		return output;
	}

	public void w2ne(List<List<Integer>> a, List<List<Integer>> b)
	{
		// Takes in w, ne
		// Returns w
		// Modifies tmp, cnt, w
	}

	public void makeset(int a)
	{
		// Takes in N
		// Returns repr, rnk
		// Modifies repr, rnk
	}

	public void find()
	{
		// Takes in i, repr
		// Returns o
		// Modifies o
	}

	public void union()
	{
		// Takes in i, j, repr, rnk
		// Returns repr, rnk
		// Modifies r_i, r_j, repr, rnk
	}
}
