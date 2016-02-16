/*
 * Author: Josue Galeas
 * Last Edit: Feb 14, 2016
 * Description: Class for testing.
 */

import java.util.ArrayList;
import java.util.List;

public class PCG
{
	public static void main (String[] args)
	{
		String input_filename = "./src/input/in.txt";
		boolean q_matlab = false;
		boolean q_mstalgo = false;

		if (args.length > 0)
		{
			for (int i = 0; i < args.length; i++)
			{
				if (args[i].equals("-m"))
					q_matlab = true;
				if (args[i].equals("-g"))
					q_matlab = false;
				if (args[i].equals("-p"))
					q_mstalgo = true;
				if (args[i].equals("-k"))
					q_mstalgo = false;
				if (args[i].equals("-i"))
					input_filename = args[i + 1];
			}
		}

		if (q_matlab)
			System.out.println("Using MATLAB generated values from \"" + input_filename + "\".");
		else
			System.out.println("Using GPS coordinates from \"" + input_filename + "\".");

		if (q_mstalgo)
			System.out.println("Using Prim's algorithm for calculating the MST.");
		else
			System.out.println("Using Kruskal's algorithm for calculating the MST.");

		MST_calc mc = new MST_calc(input_filename, q_matlab, q_mstalgo);

		MST Tree = mc.getMST();
		List<List<Integer>> D1 = mc.getDM();
		List<Coordinate<Integer>> UV = mc.getHCS();

		int[] rcv;
		Coordinate<Integer> p, q;
		int px, py, qx, qy;
		int pointer;

		for (int i = 1; i <= (GlobalConstants.n * (GlobalConstants.n - 1) / 2); i++)
		{
			rcv = minE(D1);
			px = UV.get(rcv[0] - 1).getX(); 
			py = UV.get(rcv[0] - 1).getY(); 
			qx = UV.get(rcv[1] - 1).getX(); 
			qy = UV.get(rcv[1] - 1).getY();
			p = new Coordinate<Integer>(px, py);
			q = new Coordinate<Integer>(qx, qy);

			pointer = Connect.connect(p, q, 'c').getPointer(); 
			if (pointer != 0)
			{
				// TODO: This will never happen in our situation but I'll do it anyway.
			}
		}
	}

	public static int[] minE(List<List<Integer>> a)
	{
		int[] output = new int[3];
		int x_entries = a.size();
		int y_entries = a.get(0).size();
		int minv = Integer.MAX_VALUE;
		int row = 0;
		int col = 0;

		for (int x = 0; x < x_entries; x++)
		{
			for (int y = 0; y < y_entries; y++)
			{
				if (a.get(x).get(y) < minv)
				{
					minv = a.get(x).get(y);
					row = x;
					col = y;
				}
			}
		}

		output[0] = row + 1;
		output[1] = col + 1;
		output[2] = minv;

		return output;
	}
}
