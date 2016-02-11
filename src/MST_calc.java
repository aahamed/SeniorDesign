/*
 * Author: Josue Galeas
 * Last Edit: Feb 10, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_calc
{
	private int w_st = 0;
	private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	private List<List<Integer>> X_st = new ArrayList<List<Integer>>();

	public MST_calc(String input, int m)
	{
		Distance_matrix dm = new Distance_matrix(input, m);
		int entries = dm.getSize();
		initMatrices(entries);

		// PrimMST option
		int[][] graph = List_ops.ll2array(dm.getMatrix("w"));
		int[] parent = PrimMST.primMST(graph);
		PrimMST.printMST(parent, parent.length, graph);

		// KruskalMST option
		Reduced_Kruskal.MST(dm.getMatrix("X"), dm.getMatrix("w"));

		for (int c = 1; c < entries; c++)
		{
			X_st.get(parent[c]).set(c, 1);
			X_st.get(c).set(parent[c], 1);
			w_st += graph[parent[c]][c];
		}

		System.out.println();
		System.out.println(">> Printing W_st: " + w_st);
		System.out.println(">> Printing X_st: ");
		List_ops.print_matrix(X_st);
	}

	private void initMatrices(int size)
	{
		for (int x = 0 ; x < size; x++)
		{
			X_st.add(new ArrayList<Integer>());

			for (int y = 0; y < size; y++)
			{
				X_st.get(x).add(0);
			}
		}
	}

	/*
	public void printw()
	{
		List_ops.print_matrix(w_matrix);
	}

	public void printX()
	{
		List_ops.print_matrix(X_matrix);
	}
	*/
}
