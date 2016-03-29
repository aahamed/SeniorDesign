/*
 * Author: Josue Galeas
 * Last Edit: Mar 1, 2016
 * Description: Class which uses an MST algorithm and generates the corresponding data structures.
 */

import java.util.ArrayList;
import java.util.List;

public class MSTCalc2
{
	public static MSTOut Calc(DMOut DMatrices, boolean mstalgo)
	{
		MSTOut output;

		if (mstalgo)
		{
			int[][] graph = List_ops.ll2array(DMatrices.getwM());
			int[] parent = PrimMST.primMST(graph);

			output = primMapper(parent, graph, DMatrices.getDM().size());
		}
		else
			output = Kruskal.MST(DMatrices.getXM(), DMatrices.getwM());

		return output;
	}

	private static MSTOut primMapper(int[] a, int[][] b, int c)
	{
		MSTOut output;
		int w_st = 0;
		List<Coordinate<Integer>> ST = new ArrayList<Coordinate<Integer>>();
		List<List<Integer>> X_st = new ArrayList<List<Integer>>();

		for (int x = 0 ; x < c; x++)
		{
			X_st.add(new ArrayList<Integer>());

			for (int y = 0; y < c; y++)
			{
				X_st.get(x).add(0);
			}
		}

		for (int i = 1; i < a.length; i++)
		{
			X_st.get(a[i]).set(i, 1);
			X_st.get(i).set(a[i], 1);
			w_st += b[a[i]][i];
		}

		for (int d = 1; d < a.length; d++)
		{
			ST.add(new Coordinate<Integer>((a[d] + 1), (d + 1)));
		}

		output = new MSTOut(w_st, ST, X_st);

		return output;
	}

	public static void main(String[] args)
	{
		List<Coordinate<Double>> test1 = MercatorMapping.MM(args[0], true);
		// True is Matlab, false is GPS
		Coordinate<Double> test1COM = List_ops.getCOM(test1);
		List<Coordinate<Integer>> test2 = InitialSetup.IS(test1);
		DMOut test3 = DistanceMatrix.DMCalc(test2);
		MSTOut test4 = Calc(test3, false);
		// True is Prim, false is Kruskal

		System.out.println(">> The mapped data has " + test1.size() + " entries.");
		System.out.println(">> The mapped data has COM:");
		System.out.println(List_ops.getCOM(test1));
		System.out.println(">> The mapped data contains:");
		List_ops.print_coordlist_double(test1);

		System.out.println(">> The HCS data has " + test2.size() + " entries.");
		System.out.println(">> The HCS data contains:");
		List_ops.print_coordlist_int(test2);

		System.out.println(">> The DM is:");
		List_ops.print_matrix(test3.getDM());

		System.out.println(">> The MST information is:");
		test4.printAll();
	}
}
