/*
 * Author: Josue Galeas
 * Last Edit: Feb 12, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_calc
{
	private int w_st = 0;
	private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	private List<List<Integer>> X_st = new ArrayList<List<Integer>>();

	public MST_calc(String input, boolean matlab, boolean mst)
	{
		Distance_matrix dm = new Distance_matrix(input, matlab);
		int entries = dm.getSize();
		initMatrices(entries);

		// KruskalMST option
		Reduced_Kruskal.MST(dm.getMatrix("X"), dm.getMatrix("w"));

		// PrimMST option
		int[][] graph = List_ops.ll2array(dm.getMatrix("w"));
		int[] parent = PrimMST.primMST(graph);

		for (int c = 1; c < entries; c++)
		{
			X_st.get(parent[c]).set(c, 1);
			X_st.get(c).set(parent[c], 1);
			w_st += graph[parent[c]][c];
		}
		for (int d = 1; d < parent.length; d++)
		{
			ST.add(new Coordinate<Integer>((parent[d] + 1), (d + 1)));
		}

		printall();
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

	public void printall()
	{
		System.out.println();
		System.out.println(">> w_st is:");
		System.out.println(w_st);
		System.out.println(">> ST is:");
		List_ops.print_coordlist_int(ST);
		System.out.println(">> X_st is:");
		List_ops.print_matrix(X_st);
	}
}
