/*
 * Author: Josue Galeas
 * Last Edit: Feb 10, 2016
 * Description: Port of kruskal.m, reduced down to what is actually used by the algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class Reduced_Kruskal
{
	// private int w_st;
	// private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	// private List<List<Integer>> X_st = new ArrayList<List<Integer>>();
	// TODO, might need to make an object to return the three structures above together

	public static void MST(List<List<Integer>> a, List<List<Integer>> b)
	{
		boolean isUndirGraph = true;
		int[][] X_ne = List_ops.ll2array(a);
		int[][] w_ne = List_ops.ll2array(b);

		// TODO, do conditional statements before X2ne method is called.
		X_ne = X2ne(a, isUndirGraph);

		if ((b.size() * b.get(0).size()) != b.size())
		{
			// TODO, must make any(any(A - A')) method
			if (isUndirGraph)
			{
				System.out.println("Testing just single condition. This should ultimately be false.");
				// System.out.println("ERROR: Weight matrix must be symmetric.");
			}
			w_ne = w2ne(b, X_ne);
		}
	}

	private static int[][] X2ne(List<List<Integer>> a, boolean b)
	{
		int[][] output;
		int entries = a.size(), v_entries;
		int total = 0, cnt = 1, uppercnt = 1;
		List<Integer> v = new ArrayList<Integer>();
		List<Coordinate<Integer>> edge = new ArrayList<Coordinate<Integer>>();

		if (b)
		{
			for (int x = 0; x < entries; x++)
			{
				for (int y = (x + 1); y < entries; y++)
				{
					total += a.get(x).get(y);
				}
			}
			output = new int[total][2];
		}
		else
		{
			// TODO if undirected graph
			output = new int[1][2]; // Temporary to avoid errors
		}

		for (int i = 1; i < entries; i++)
		{
			for (int j = 0; j < entries; j++)
			{
				if (a.get(i - 1).get(j) != 0)
					v.add(j + 1);
			}

			if (b)
			{
				v_entries = v.size();
				for (int n = 0; n < v_entries; n++)
				{
					if (v.get(n) <= i)
					{
						v.remove(n);
						v_entries--;
						n--;
					}
				}
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

	private static int[][] w2ne(List<List<Integer>> a, int[][] b)
	{
		int entries = b.length;
		int[][] output = new int[entries][1];
		int cnt = 1;

		for (int x = 0; x < entries; x++)
		{
			output[cnt - 1][0] = a.get(b[x][0] - 1).get(b[x][1] - 1);
			cnt++;
		}

		return output;
	}

	private static void printmatrix(int[][] a)
	{
		for (int x = 0; x < a.length; x++)
		{
			System.out.print("Row " + (x + 1) + ": ");

			for (int y = 0; y < a[0].length; y++)
			{
				System.out.print(a[x][y] + ", ");
			}

			System.out.println();
		}
		System.out.println();
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
