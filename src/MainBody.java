/*
 * Author: Josue Galeas, TODO: and everyone else who edits this
 * Last Edit: Feb 21, 2016
 * Description: Main body of the algorithm.
 */

import java.util.ArrayList;
import java.util.List;
import Jama.Matrix;

public class MainBody
{
	private static String input_filename;
	private static boolean q_matlab;
	private static boolean q_mstalgo;

	private static int[] minE(List<List<Integer>> a)
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

	private static int[] maxMST(List<Coordinate<Integer>> a, List<List<Integer>> b)
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

	private static double[][] hcoord2array(Coordinate<Integer> a)
	{
		double[][] output = new double[2][2];

		output[0][0] = a.getX();
		output[0][1] = a.getY();
		output[1][0] = 0.0;
		output[1][1] = 0.0;

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
				// TODO: Might be an issue when specifying -m -i, but with no filename
				// Since there is no filename, it will default to the GPS coordinates which is bad
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

		// Step 1: Find out the distance matrix
		// Calculating Minimal Spanning Tree
		MSTCalc mc = new MSTCalc(input_filename, q_matlab, q_mstalgo);

		// Pre-connective Graph
		List<List<Integer>> D1 = mc.getDM();
		List<Coordinate<Integer>> UV = mc.getHCS();
		int[] rcv;
		Coordinate<Integer> p, q;
		Coordinate<Double> cen1, cen2;
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
				// Is a statement of checking connectivity
				// Draw a cluster
				px = UV.get(rcv[0] - 1).getX();
				py = UV.get(rcv[0] - 1).getY();
				qx = UV.get(rcv[1] - 1).getX();
				qy = UV.get(rcv[1] - 1).getY();
				p = new Coordinate<Integer>(px, qx);
				q = new Coordinate<Integer>(py, qy);
				cen1 = HCS.hexToCart(p);
				cen2 = HCS.hexToCart(q);
				// Mark whomever has been connected !!!
				D1.get(rcv[0] - 1).set((rcv[1] - 1), GlobalConstants.TRANS_RANGE);
			}
		}

		// Place ANs to achieve connection
		MSTOut Tree = mc.getMST();
		List<List<Integer>> D2 = mc.getDM();
		boolean exit = false;
		int Nsign;
		ConnectOut temp;
		Matrix p1;
		Matrix q1;
		Locate LL;

		while (!exit)
		{
			Nsign = 1;
			// Seach through the Spanning Tree to pick up the shortest edge
			rcv = maxMST(Tree.getST(), D2);
			if (rcv[2] <= GlobalConstants.H)
				break;
			// Prepare for Placement
			px = UV.get(rcv[0] - 1).getX();
			py = UV.get(rcv[0] - 1).getY();
			qx = UV.get(rcv[1] - 1).getX();
			qy = UV.get(rcv[1] - 1).getY();
			p = new Coordinate<Integer>(px, py);
			q = new Coordinate<Integer>(qx, qy);
			temp = Connect.connect(p, q, 'a');

			// Check Necessity
			if (temp.getPointer() != 0)
			{
				if (temp.getTheta() < (Math.PI/6 - temp.getTheta()))
					break;
				else
				{
					Nsign = 0; // Already connected
					D2.get(rcv[0] - 1).set((rcv[1] - 1), GlobalConstants.H);
				}
				px = UV.get(rcv[0] - 1).getX();
				py = UV.get(rcv[0] - 1).getY();
				qx = UV.get(rcv[1] - 1).getX();
				qy = UV.get(rcv[1] - 1).getY();
				p = new Coordinate<Integer>(px, qx);
				q = new Coordinate<Integer>(py, qy);
				cen1 = HCS.hexToCart(p);
				cen2 = HCS.hexToCart(q);
			}

			if (Nsign != 0)
			{
				if (temp.getNum() <= 2.01)
				{
					// Connected Those that is feasible with only 1 ANs
					// TODO: Return when Locate1 is finished.
					p1 = new Matrix(hcoord2array(p));
					q1 = new Matrix(hcoord2array(q));
					LL = new Locate(p1, q1, GlobalConstants.H);
					// LC = new LocateC(p1, q1, GlobalConstants.H); // TODO: When LocateC is done
				}
				else if (temp.getNum() > 2.01 && temp.getNum() <= 3.01)
				{
					// TODO
				}
				else
				{
					// TODO
				}
			}
		}
	}
}
