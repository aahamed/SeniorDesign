/*
 * Author: Josue Galeas
 * Last Edit: Feb 14, 2016
 * Description: Class for testing.
 */

import java.util.ArrayList;
import java.util.List;

public class TEST
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
		mc.printAll();
		List<List<Integer>> D1 = mc.getDM();
		System.out.println(">> D1 is:");
		List_ops.print_matrix(D1);
	}
}
