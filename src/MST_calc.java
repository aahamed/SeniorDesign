/*
 * Author: Josue Galeas
 * Last Edit: Feb 7, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_calc
{
	private List<List<Integer>> w_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> X_matrix = new ArrayList<List<Integer>>();
	private List<List<Integer>> D_matrix;

	public MST_calc(String input, int m)
	{
		Distance_matrix dm = new Distance_matrix(input, m);
		int entries = dm.getSize();
		int distance;
		initXandw(entries);

		for (int x = 0; x < entries; x++)
		{
			for (int y = (x + 1); y < entries; y++)
			{
				distance = dm.getSP0_entry(x, y);
				w_matrix.get(x).set(y, distance);
				w_matrix.get(y).set(x, distance);
			}
		}

		D_matrix = dm.getD_matrix();
	}

	private void initXandw(int size)
	{
		for (int x = 0 ; x < size; x++)
		{
			X_matrix.add(new ArrayList<Integer>());
			w_matrix.add(new ArrayList<Integer>());

			for (int y = 0; y < size; y++)
			{
				if (x != y)
					X_matrix.get(x).add(1);
				else
					X_matrix.get(x).add(0);

				w_matrix.get(x).add(0);
			}
		}
	}

	public void printw()
	{
		List_ops.print_matrix(w_matrix);
	}

	public void printX()
	{
		List_ops.print_matrix(X_matrix);
	}

	public void printD()
	{
		List_ops.print_matrix(D_matrix);
	}
}
