/*
 * Author: Josue Galeas
 * Last Edit; Feb 9, 2016
 * Description: Gets normalized data set and calculates two distance matrices to be interpreted by the MST algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class Distance_matrix
{
	private List<List<Integer>> D_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> SP0_matrix = new ArrayList<List<Integer>>();

	public Distance_matrix(String input, int m)
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

			for (int y = 0; y < size; y++)
			{
				D_matrix.get(x).add(GlobalConstants.TRANS_RANGE);
				SP0_matrix.get(x).add(0);
			}
		}
	}

	public int getSize()
	{
		return D_matrix.size();
	}

	public int getD_entry(int a, int b)
	{
		return D_matrix.get(a).get(b);
	}

	public int getSP0_entry(int a, int b)
	{
		return SP0_matrix.get(a).get(b);
	}

	public List<List<Integer>> getD_matrix()
	{
		return D_matrix;
	}

	public void printD()
	{
		List_ops.print_matrix(D_matrix);
	}

	public void printSP0()
	{
		List_ops.print_matrix(SP0_matrix);
	}
}
