/*
 * Author: Josue Galeas
 * Last Edit; Feb 13, 2016
 * Description: Gets normalized data set and calculates four distance matrices to be interpreted by the MST algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class Distance_matrix
{
	private List<List<Integer>> D_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> SP0_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> X_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> w_matrix = new ArrayList<List<Integer>>();

	public Distance_matrix(String input, boolean m)
	{
		Initial_setup is = new Initial_setup(input, m);

		int entries = is.getSize();
		int distance;
		initMatrices(entries);

		for (int x = 0; x < entries; x++)
		{
			for (int y = (x + 1); y < entries; y++)
			{
				distance = HCS_distance(is.getLoc_HCS(x), is.getLoc_HCS(y));
				D_matrix.get(x).set(y, distance);
				SP0_matrix.get(x).set(y, distance);
				w_matrix.get(x).set(y, distance);
				w_matrix.get(y).set(x, distance);
			}
		}
	}

	private int HCS_distance(Coordinate<Integer> a, Coordinate<Integer> b)
	{
		int u = Math.abs(a.getX() - b.getX());
		int v = Math.abs(a.getY() - b.getY());
		int uv = Math.abs(a.getX() - b.getX() + a.getY() - b.getY());

		return Math.max(Math.max(u, v), uv);
	}

	private void initMatrices(int size)
	{
		for (int x = 0; x < size; x++)
		{
			D_matrix.add(new ArrayList<Integer>());
			SP0_matrix.add(new ArrayList<Integer>());
			X_matrix.add(new ArrayList<Integer>());
			w_matrix.add(new ArrayList<Integer>());

			for (int y = 0; y < size; y++)
			{
				D_matrix.get(x).add(GlobalConstants.TRANS_RANGE);
				SP0_matrix.get(x).add(0);
				if (x != y)
					X_matrix.get(x).add(1);
				else
					X_matrix.get(x).add(0);
				w_matrix.get(x).add(0);
			}
		}
	}

	public int getSize()
	{
		return D_matrix.size();
	}

	public List<List<Integer>> getMatrix(String a)
	{
		switch (a)
		{
			case "D":
				return D_matrix;
			case "SP0":
				return SP0_matrix;
			case "X":
				return X_matrix;
			case "w":
				return w_matrix;
			default:
				System.out.println("ERROR: Matrix not found.");
				return new ArrayList<List<Integer>>();
		}
	}

	public void printD()
	{
		System.out.println(">> D is:");
		List_ops.print_matrix(D_matrix);
	}

	public void printSP0()
	{
		System.out.println(">> SP0 is:");
		List_ops.print_matrix(SP0_matrix);
	}

	public void printX()
	{
		System.out.println(">> X is:");
		List_ops.print_matrix(X_matrix);
	}

	public void printw()
	{
		System.out.println(">> w is:");
		List_ops.print_matrix(w_matrix);
	}

	public void printAll()
	{
		printD();
		printSP0();
		printX();
		printw();
	}
}
