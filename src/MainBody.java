/*
 * Author: TODO: Everyone
 * Last Edit: Feb 21, 2016
 * Description: Main body of the algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class MainBody
{
	private static String input_filename;
	private static boolean q_matlab;
	private static boolean q_mstalgo;

	public static int[] minE(List<List<Integer>> a)
	{
		int[] output = new int[3];
		int x_entries = a.size();
		int y_entries = a.get(0).size();
		int minv = Integer.MAX_VALUE;
		int row = 0, col = 0;

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

	public static int[] maxMST(List<Coordinate<Integer>> a, List<List<Integer>> b)
	{
		int[] output = new int[3];
		int len = a.size();
		int maxv = 0;
		int row = 0, col = 0;
		int x_st = 0, y_st = 0;

		for (int c = 0; c < len; c++)
		{
			x_st = a.get(c).getX() - 1;
			y_st = a.get(c).getY() - 1;
			if (b.get(x_st).get(y_st) > maxv)
			{
				maxv = b.get(x_st).get(y_st);
				row = x_st;
				col = y_st;
			}
		}

		output[0] = row + 1;
		output[1] = col + 1;
		output[2] = maxv;

		return output;
	}

	private static void options(String[] a)
	{
		boolean check1 = false, check2 = false, check3 = false;

		if (a.length > 0)
		{
			for (int i = 0; i < a.length; i++)
			{
				if (a[i].equals("-m"))
				{
					q_matlab = true;
					check1 = true;
				}
				if (a[i].equals("-g"))
				{
					q_matlab = false;
					check1 = true;
				}
				if (a[i].equals("-p"))
				{
					q_mstalgo = true;
					check2 = true;
				}
				if (a[i].equals("-k"))
				{
					q_mstalgo = false;
					check2 = true;
				}
				if (a[i].equals("-i"))
				{
					try
					{
						input_filename = a[i + 1];
						check3 = true;
					}
					catch (ArrayIndexOutOfBoundsException x)
					{
						System.err.format("ERROR: No input filename was specified. [%s]\n", x);
						System.exit(0);
					}
				}
			}

			if (!check1)
			{
				q_matlab = false;
				System.out.println("Defaulting to GPS coordinates.");
			}
			if (!check2)
			{
				q_mstalgo = false;
				System.out.println("Defaulting to Kruskal's algorithm.");
			}
			if (!check3)
			{
				input_filename = "./src/input/in.txt";
				System.out.println("Defaulting to input file: " + input_filename);
			}
		}
		else if (a.length == 0)
		{
			System.out.println("No flags were specified, using default preferences.");
			input_filename = "./src/input/in.txt";
			q_matlab = false;
			q_mstalgo = false;
		}

		if (q_matlab)
			System.out.println("Using MATLAB generated values from \"" + input_filename + "\".");
		else
			System.out.println("Using GPS coordinates from \"" + input_filename + "\".");

		if (q_mstalgo)
			System.out.println("Using Prim's algorithm for calculating the MST.");
		else
			System.out.println("Using Kruskal's algorithm for calculating the MST.");
	}

	public static void main (String[] args)
	{
		options(args);
		MSTCalc mc = new MSTCalc(input_filename, q_matlab, q_mstalgo);

		MSTOut Tree = mc.getMST();
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

		List<List<Integer>> D2 = mc.getDM();
		rcv = maxMST(mc.getMST().getST(), D2);
		System.out.println("Row is: " + rcv[0]);
		System.out.println("Col is: " + rcv[1]);
		System.out.println("Value is: " + rcv[2]);

	}
}
