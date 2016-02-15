/*
 * Author: Josue Galeas
 * Last Edit: Feb 15, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class MST_calc
{
	private MST mstinfo;
	private int w_st;
	private List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
	private List<List<Integer>> X_st = new ArrayList<List<Integer>>();

	public MST_calc(String input, boolean matlab, boolean mstalgo)
	{
		Distance_matrix dm = new Distance_matrix(input, matlab);

		if (mstalgo)
		{
			int entries = dm.getSize();
			initXst(entries);

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

			mstinfo = new MST(w_st, ST, X_st);
		}
		else
		{
			mstinfo = Reduced_Kruskal.MST(dm.getMatrix("X"), dm.getMatrix("w"));
		}
	}

	private void initXst(int size)
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

	private MST getMST()
	{
		return mstinfo;
	}

	public void printAll()
	{
		mstinfo.printAll();
	}
}
