/*
 * Author: Josue Galeas
 * Last Edit; Jan 31, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class Distance_matrix
{
	// Double for now
	private List<List<Integer>> dist_matrix = new ArrayList<List<Integer>>();

	public Distance_matrix(String input)
	{
		Initial_setup is = new Initial_setup(input);

		int entries = is.getSize();
		int result = 0;
		initDM(entries);

		for (int x = 0; x < entries; x++)
		{
			for (int y = x; y < entries; y++)
			{
				if (x == y)
				{
					dist_matrix.get(x).set(y, 0);
					continue;
				}

				result = HCS_distance(is.getLocHCS(x), is.getLocHCS(y)); 
				dist_matrix.get(x).set(y, result);
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

	private void initDM(int size)
	{
		for (int x = 0; x < size; x++)
		{
			dist_matrix.add(new ArrayList<Integer>());
			for (int y = 0; y < size; y++)
			{
				dist_matrix.get(x).add(GlobalConstants.TRANS_RANGE);
			}
		}
	}

	public void printDM()
	{
		int entries = dist_matrix.size();

		for (int x = 0; x < entries; x++)
		{
			for (int y = 0; y < entries; y++)
			{
				System.out.print(dist_matrix.get(x).get(y) + ", ");
			}
			System.out.println();
		}
	}
}
