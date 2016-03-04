/*
 * Author: Josue Galeas, TODO: and everyone else who edits this
 * Last Edit: Mar 1, 2016
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

	private static List<Coordinate<Double>> CenList1 = new ArrayList<Coordinate<Double>>();
	private static List<Coordinate<Double>> CenList2 = new ArrayList<Coordinate<Double>>();
	private static List<Coordinate<Double>> C1C2List = new ArrayList<Coordinate<Double>>();

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

	private static int[] getUVs(List<Coordinate<Integer>> a, int[] b)
	{
		int[] output = new int[4];

		output[0] = a.get(b[0] - 1).getX(); // pu
		output[1] = a.get(b[0] - 1).getY(); // pv
		output[2] = a.get(b[1] - 1).getX(); // qu
		output[3] = a.get(b[1] - 1).getY(); // qv

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

	private static void reshapeDmatrix(List<List<Integer>> a, int[] b)
	{
		int entries = a.size();
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < entries; i++)
		{
			if ((i == (b[0] - 1)) || (i == (b[1] - 1)))
				a.get(i).add(GlobalConstants.H);
			else
				a.get(i).add(0);
		}
		for (int j = 0; j < (entries + 1); j++)
		{
			temp.add(GlobalConstants.TRANS_RANGE);
		}
		a.add(temp);
	}

	private static void options(String[] a)
	{
		boolean check1 = false, check2 = false, check3 = false;

		if (a.length > 0)
		{
			for (int i = 0; i < a.length; i++)
			{
				if (a[i].equals("--help"))
					helpMenu();
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
						System.err.format("ERROR: No input filename was specified. Use --help for usage details. [%s]\n", x);
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
				System.err.format("ERROR: No input filename was specified. Use --help for usage details.\n");
				System.exit(0);
			}
		}
		else
			helpMenu();

		if (q_matlab)
			System.out.println("Using MATLAB generated values from \"" + input_filename + "\".");
		else
			System.out.println("Using GPS coordinates from \"" + input_filename + "\".");

		if (q_mstalgo)
			System.out.println("Using Prim's algorithm for calculating the MST.");
		else
			System.out.println("Using Kruskal's algorithm for calculating the MST.");
	}

	private static void helpMenu()
	{
		System.out.println("Usage: java MainBody [--help] [<args>] [-i <path>]");
		System.out.println();
		System.out.println("Input file:");
		System.out.println("    -i followed by the path to input text file");
		System.out.println();
		System.out.println("Type of input data:");
		System.out.println("    -g for GPS coordinates (Default)");
		System.out.println("    -m for MATLAB generated coordinates");
		System.out.println();
		System.out.println("MST algorithm to be used:");
		System.out.println("    -k for Kruskal's algorithm (Default)");
		System.out.println("    -p for Prim's algorithm");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		options(args);

		// Step 1: Find out the distance matrix
		// Calculating Minimal Spanning Tree
		MSTCalc mc = new MSTCalc(input_filename, q_matlab, q_mstalgo);

		// Pre-connective Graph
		List<List<Integer>> D1 = mc.getDM();
		List<Coordinate<Integer>> UV = mc.getHCS();
		int[] rcv, pq;
		Coordinate<Integer> p, q;
		Coordinate<Double> cen1, cen2;
		int pointer;

		for (int i = 1; i <= (GlobalConstants.n * (GlobalConstants.n - 1) / 2); i++)
		{
			rcv = minE(D1);
			pq = getUVs(UV, rcv);
			p = new Coordinate<Integer>(pq[0], pq[1]);
			q = new Coordinate<Integer>(pq[2], pq[3]);

			pointer = Connect.connect(p, q, 'c').getPointer();

			if (pointer != 0)
			{
				// Is a statement of checking connectivity
				// Draw a cluster
				pq = getUVs(UV, rcv);
				p = new Coordinate<Integer>(pq[0], pq[2]);
				q = new Coordinate<Integer>(pq[1], pq[3]);
				cen1 = HCS.hexToCart(p);
				cen2 = HCS.hexToCart(q);

				// TODO: Temporary List to hold all these 'cen' coordinates
				CenList1.add(cen1);
				CenList2.add(cen2);

				// Mark whomever has been connected !!!
				D1.get(rcv[0] - 1).set((rcv[1] - 1), GlobalConstants.TRANS_RANGE);
			}
		}

		// Place ANs to achieve connection
		MSTOut Tree = mc.getMST();
		Tree.printAll();
		List<List<Integer>> D2 = mc.getDM();
		Coordinate<Double> XcYc = mc.getMMcom();
		List<Coordinate<Double>> XrYr = mc.getMMlist();
		boolean exit = false;
		int Nsign;
		ConnectOut temp;
		Matrix p1, q1;
		Locate1 L1;
		LocateC1 LC1;
		Coordinate<Integer> O = new Coordinate<Integer>(0, 0);
		Coordinate<Integer> AN;
		Coordinate<Double> C12;
		double xAN, yAN;
		List<Integer> new_row;

		while (!exit)
		{
			Nsign = 1;
			// Seach through the Spanning Tree to pick up the shortest edge
			rcv = maxMST(Tree.getST(), D2);
			if (rcv[2] <= GlobalConstants.H)
				break;
			// Prepare for Placement
			pq = getUVs(UV, rcv);
			p = new Coordinate<Integer>(pq[0], pq[1]);
			q = new Coordinate<Integer>(pq[2], pq[3]);
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
				pq = getUVs(UV, rcv);
				p = new Coordinate<Integer>(pq[0], pq[2]);
				q = new Coordinate<Integer>(pq[1], pq[3]);
				cen1 = HCS.hexToCart(p);
				cen2 = HCS.hexToCart(q);

				// TODO: Temporary List to hold all these 'cen' coordinates
				CenList1.add(cen1);
				CenList2.add(cen2);
				// TODO: Graph functions go here
			}

			if (Nsign != 0)
			{
				if (temp.getNum() <= 2.01)
				{
					// Connected Those that is feasible with only 1 ANs
					p1 = new Matrix(hcoord2array(p));
					q1 = new Matrix(hcoord2array(q));
					L1 = new Locate1(p1, q1, GlobalConstants.H);
					LC1 = new LocateC1(p1, q1, GlobalConstants.H);

					if ((L1.out.getexFlag() != 0) && (LC1.out.getexFlag() != 0))
					{
						if ((HCS.distance(L1.out.getAN(), O)) > (HCS.distance(LC1.out.getAN(), O)))
							AN = LC1.out.getAN();
						else
							AN = L1.out.getAN();

						// If AN is found, draw it out
						C12 = HCS.hexToCart(AN);
						// TODO: Temporary List to hold all these 'C1, C2' coordinates
						C1C2List.add(C12);

						// As AN is found, two clusters that are connected are drew out
						// Draw a cluster
						pq = getUVs(UV, rcv);
						p = new Coordinate<Integer>(pq[0], pq[2]);
						q = new Coordinate<Integer>(pq[1], pq[3]);
						cen1 = HCS.hexToCart(p);
						cen2 = HCS.hexToCart(q);

						// TODO: Temporary List to hold all these 'cen' coordinates
						CenList1.add(cen1);
						CenList2.add(cen2);
						// TODO: Graph functions go here

						// Add AN to sets
						xAN = C12.getX() + XcYc.getX();
						yAN = C12.getY() + XcYc.getY();
						XrYr.add(new Coordinate<Double>(xAN, yAN));
						GlobalConstants.n++;

						// After an AN is placed, Recalculate the Spanning Tree
						// First reset certain element in D2 and then calculate
						// the spanning Tree. Meanwhile, the dimension of D2
						// needs to be RESHAPED
						reshapeDmatrix(D2, rcv);
						// TODO: InitiateMax, then set D to D2 and iterate
					}
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

			exit = true;
		}
	}
}
