/*
 * Author: Josue Galeas
 * Last Edit: Mar 1, 2016
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
	private List<Coordinate<Integer>> HCSlist;
	private Coordinate<Double> MMcom;
	private List<Coordinate<Double>> MMlist;

	public Distance_matrix(String input, boolean m)
	{
		Initial_setup is = new Initial_setup(input, m);
		HCSlist = is.getHCSlist();
		MMcom = is.getMMcom();
		MMlist = is.getMMlist();

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

	private static int HCS_distance(Coordinate<Integer> a, Coordinate<Integer> b)
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

	public static List<List<Integer>> DMCalc1(List<Coordinate<Integer>> a)
	{
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		int entries = a.size();
		int distance;

		for (int i = 0; i < entries; i++)
		{
			output.add(new ArrayList<Integer>());
			for (int j = 0; j < entries; j++)
			{
				output.get(i).add(0);
			}
		}
		for (int x = 0; x < entries; x++)
		{
			for (int y = (x + 1); y < entries; y++)
			{
				distance = HCS_distance(a.get(x), a.get(y));
				output.get(x).set(y, distance);
				output.get(y).set(x, distance);
			}
		}

		return output;
	}

	public static List<List<Integer>> DMCalc2(List<Coordinate<Integer>> a)
	{
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		int entries = a.size();
		int distance;

		for (int i = 0; i < entries; i++)
		{
			output.add(new ArrayList<Integer>());
			for (int j = 0; j < entries; j++)
			{
				output.get(i).add(GlobalConstants.TRANS_RANGE);
			}
		}
		for (int x = 0; x < entries; x++)
		{
			for (int y = (x + 1); y < entries; y++)
			{
				distance = HCS_distance(a.get(x), a.get(y));
				output.get(x).set(y, distance);
			}
		}

		return output;
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
				System.err.format("ERROR: Matrix \"" + a + "\" does not exist.");
				System.exit(0);
				return new ArrayList<List<Integer>>();
		}
	}

	public List<Coordinate<Integer>> getHCSlist()
	{
		return HCSlist;
	}

	public Coordinate<Double> getMMcom()
	{
		return MMcom;
	}

	public List<Coordinate<Double>> getMMlist()
	{
		return MMlist;
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
