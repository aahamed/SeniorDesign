/*
 * Author: Josue Galeas
 * Last Edit: Mar 1, 2016
 * Description: Class which uses an MST algorithm and generates the corresponding data structures.
 */

import java.util.ArrayList;
import java.util.List;

public class MSTCalc
{
	private MSTOut mstinfo;
	private List<List<Integer>> D_matrix;
	private List<Coordinate<Integer>> HCSlist;
	private Coordinate<Double> MMcom;
	private List<Coordinate<Double>> MMlist;

	public MSTCalc(String input, boolean matlab, boolean mstalgo)
	{
		Distance_matrix dm = new Distance_matrix(input, matlab);
		D_matrix = dm.getMatrix("D");
		HCSlist = dm.getHCSlist();
		MMcom = dm.getMMcom();
		MMlist = dm.getMMlist();

		int w_st = 0;
		List<Coordinate<Integer>> ST;
		List<List<Integer>> X_st;

		if (mstalgo)
		{
			int entries = dm.getSize();
			ST = new ArrayList<Coordinate<Integer>>();
			X_st = new ArrayList<List<Integer>>();

			for (int x = 0 ; x < entries; x++)
			{
				X_st.add(new ArrayList<Integer>());

				for (int y = 0; y < entries; y++)
				{
					X_st.get(x).add(0);
				}
			}

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

			mstinfo = new MSTOut(w_st, ST, X_st);
		}
		else
			mstinfo = Kruskal.MST(dm.getMatrix("X"), dm.getMatrix("w"));
	}

	public MSTOut getMST()
	{
		return mstinfo;
	}

	public List<List<Integer>> getDM()
	{
		return D_matrix;
	}

	public int[][] getDMArray()
	{
		return List_ops.ll2array(D_matrix);
	}

	public List<Coordinate<Integer>> getHCS()
	{
		return HCSlist;
	}

	public int[][] getHCSArray()
	{
		return List_ops.lc2array(HCSlist);
	}

	public Coordinate<Double> getMMcom()
	{
		return MMcom;
	}

	public List<Coordinate<Double>> getMMlist()
	{
		return MMlist;
	}

	public void printMST()
	{
		mstinfo.printAll();
	}

	public void printDM()
	{
		System.out.println(">> D is:");
		List_ops.print_matrix(D_matrix);
	}

	public void printHCS()
	{
		System.out.println(">> HCS normalized points (u, v) are:");
		List_ops.print_coordlist_int(HCSlist);
	}

	public void printAll()
	{
		printMST();
		printDM();
		printHCS();
	}
}
