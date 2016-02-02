/*
 * Author: Josue Galeas
 * Last Edit: Feb 2, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_calc
{
	private List<List<Integer>> w_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> X_matrix = new ArrayList<List<Integer>>();


	public MST_calc(String input, int m)
	{
		Distance_matrix dm = new Distance_matrix(input, m);
		int entries = dm.getSize();
		initX(entries);
	}

	private void initX(int size)
	{
		for (int x = 0 ; x < size; x++)
		{
			X_matrix.add(new ArrayList<Integer>());

			for (int y = 0; y < size; y++)
			{
				if (x != y)
					X_matrix.get(x).add(1);
				else
					X_matrix.get(x).add(0);
			}
		}
	}

	public void printX()
	{
		int entries = X_matrix.size();

		for (int x = 0; x < entries; x++)
		{
			for (int y = 0; y < entries; y++)
			{
				System.out.print(X_matrix.get(x).get(y));
				if (y < (entries - 1))
					System.out.print(", ");
			}
			System.out.println();
		}
	}

}
