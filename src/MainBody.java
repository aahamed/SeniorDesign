/*
 * Author: Josue Galeas
 * Last Edit: Mar 6, 2016
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

		output[0] = a.get(b[0] - 1).getX(); // u(row)
		output[1] = a.get(b[0] - 1).getY(); // v(row)
		output[2] = a.get(b[1] - 1).getX(); // u(col)
		output[3] = a.get(b[1] - 1).getY(); // v(col)

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
		int row = b[0] - 1;
		int col = b[1] - 1;
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < entries; i++)
		{
			if ((i == row) || (i == col))
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

	private static void reshapeDmatrix2(List<List<Integer>> a, int[] b)
	{
		int entries = a.size();
		int row = b[0] - 1;
		int col = b[1] - 1;
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < entries; i++)
		{
			if ((i == row) || (i == col))
			{
				a.get(i).add(GlobalConstants.H);
				a.get(i).add(GlobalConstants.H);
			}
			else
			{
				a.get(i).add(0);
				a.get(i).add(0);
			}
		}
		for (int j = 0; j < (entries + 2); j++)
		{
			temp.add(GlobalConstants.TRANS_RANGE);
		}
		a.add(temp);
		a.add(temp);
	}

	private static void reshapeDmatrix3(List<List<Integer>> a)
	{
		int entries = a.size();
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < entries; i++)
		{
			a.get(i).add(1);
			a.get(i).add(1);
		}
		for (int j = 0; j < (entries + 2); j++)
		{
			temp.add(GlobalConstants.TRANS_RANGE);
		}
		a.add(temp);
		a.add(temp);
	}

	private static void reshapeDmatrix4(List<List<Integer>> a)
	{
		int entries = a.size();
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < entries; i++)
		{
			a.get(i).add(0);
		}
		for (int j = 0; j < (entries + 1); j++)
		{
			temp.add(GlobalConstants.TRANS_RANGE);
		}
		a.get(entries - 1).set(entries, GlobalConstants.H);
		a.get(entries - 2).set(entries, GlobalConstants.H);
		a.add(temp);
	}

	private static List<Coordinate<Integer>> PrimPrep(int[] a)
	{
		List<Coordinate<Integer>> output = new ArrayList<Coordinate<Integer>>();

		for (int i = 1; i < a.length; i++)
		{
			output.add(new Coordinate<Integer>((a[i] + 1), (i + 1)));
		}

		return output;
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
		mc.printAll(); // TODO: For debugging
		System.out.println();

		// Pre-connective Graph
		List<List<Integer>> D1 = mc.getDM(); // TODO: This is a reference, not a copy, might cause issues
		GlobalConstants.n = D1.size();
		List<Coordinate<Integer>> UV = mc.getHCS();
		int[] rcv, pq;
		Coordinate<Integer> p = null, q = null;
		Coordinate<Double> cen1, cen2;
		int pointer;

		System.out.println(">> Entering PCG loop."); // TODO: DEBUG
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
				CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
				CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
				// TODO: Graph functions go here

				// Mark whomever has been connected !!!
				D1.get(rcv[0] - 1).set((rcv[1] - 1), GlobalConstants.TRANS_RANGE);
			}
		}
		System.out.println(">> Done with PCG loop."); // TODO: DEBUG
		System.out.println();

		// Place ANs to achieve connection
		MSTOut Tree = mc.getMST();
		List<Coordinate<Integer>> ST = Tree.getST();
		List<List<Integer>> D2 = mc.getDM();
		Coordinate<Double> XYc = mc.getMMcom();
		List<Coordinate<Double>> XYr = mc.getMMlist();
		boolean exit = false;
		// Pointer if-statement
		int Nsign;
		ConnectOut temp;
		// Nsign if-statement
		Matrix p1, q1;
		Locate1 L1;
		LocateC1 LC1;
		LocateM2 LM2;
		LocateMM2 LMM2;
		InitMaxOut IMOUT;
		boolean cond1, cond2;
		int dist1, dist2;
		int base, base_t;
		Coordinate<Integer> AN, AN2, O = new Coordinate<Integer>(0, 0);
		Coordinate<Double> C1, C2;
		double num;
		int[][] HOPE;

		System.out.println(">> Entering AN placement loop."); // TODO: DEBUG
		while (!exit)
		{
			Nsign = 1;
			// Search through the Spanning Tree to pick up the shortest edge
			rcv = maxMST(ST, D2);
			System.out.println();
			System.out.println("Current longest edge is at: (" + rcv[0] + ", " + rcv[1] + "), and that length is: " + rcv[2]);
			if (rcv[2] <= GlobalConstants.H)
				break;
			// Prepare for Placement
			pq = getUVs(UV, rcv);
			p = new Coordinate<Integer>(pq[0], pq[1]);
			q = new Coordinate<Integer>(pq[2], pq[3]);
			temp = Connect.connect(p, q, 'a'); // TODO: Maybe rename 'temp' to something better?

			System.out.println("Checking necessity for AN!"); // TODO: DEBUG
			// Check Necessity
			if (temp.getPointer() != 0)
			{
				if (temp.getTheta() < (Math.PI/6 - temp.getTheta()))
					break;
				else
				{
					Nsign = 0; // Already connected
					// Set ST(i) = [row, col] to be a large number so the next iteration won't pick it up again
					D2.get(rcv[0] - 1).set((rcv[1] - 1), GlobalConstants.H);
				}
				cen1 = HCS.hexToCart(new Coordinate<Integer>(pq[0], pq[1]));
				cen2 = HCS.hexToCart(new Coordinate<Integer>(pq[2], pq[3]));

				// TODO: Temporary List to hold all these 'cen' coordinates
				CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
				CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
				// TODO: Graph functions go here
			}

			System.out.println("Entering Nsign loop."); // TODO: DEBUG
			if (Nsign != 0)
			{
				if (temp.getNum() <= 2.01)
				{
					System.out.println("Exactly 1 node needed case!"); // TODO: DEBUG
					// Connected Those that is feasible with only 1 ANs
					p1 = new Matrix(hcoord2array(p));
					q1 = new Matrix(hcoord2array(q));
					L1 = new Locate1(p1, q1, GlobalConstants.H);
					LC1 = new LocateC1(p1, q1, GlobalConstants.H);
					cond1 = L1.out.getexFlag() != 0;
					cond2 = LC1.out.getexFlag() != 0;

					if (cond1 && cond2)
					{
						dist1 = HCS.distance(L1.out.getAN(), O);
						dist2 = HCS.distance(LC1.out.getAN(), O);
						if (dist1 > dist2)
							AN = LC1.out.getAN();
						else
							AN = L1.out.getAN();

						// If AN is found, draw it out
						C1 = HCS.hexToCart(AN);
						// TODO: Temporary List to hold all these 'C1, C2' coordinates
						C1C2List.add(C1);
						// TODO: Graph functions go here

						// As AN is found, two clusters that are connected are drew out
						// Draw a cluster
						cen1 = HCS.hexToCart(new Coordinate<Integer>(pq[0], pq[1]));
						cen2 = HCS.hexToCart(new Coordinate<Integer>(pq[2], pq[3]));
						// TODO: Temporary List to hold all these 'cen' coordinates
						CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
						CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
						// TODO: Graph functions go here

						// Add AN to sets
						XYr.add(HCS.add2coord(C1, XYc));
						GlobalConstants.n++;
						System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

						// After an AN is placed, Recalculate the Spanning Tree
						// First reset certain element in D2 and then calculate
						// the spanning Tree. Meanwhile, the dimension of D2
						// needs to be RESHAPED
						reshapeDmatrix(D2, rcv);
						IMOUT = InitMax.initMax(XYr, XYc, D2);
						UV = IMOUT.getHCSList();
						D2 = IMOUT.getDMatrix();
						// ST = PrimPrep(IMOUT.getParent());
						ST = IMOUT.getST();
					}
				}
				else if (temp.getNum() > 2.01 && temp.getNum() <= 3.01)
				{
					System.out.println("Exactly 2 nodes needed case!"); // TODO: DEBUG
					// & abs(theta - pi/6) > 0.013
					// the last one comes when it seems to be ok but not
					p1 = new Matrix(hcoord2array(p));
					q1 = new Matrix(hcoord2array(q));
					LMM2 = new LocateMM2(p1, q1, GlobalConstants.H, temp.getTheta());

					AN = LMM2.out.getAN();
					AN2 = LMM2.out.getAN2();

					// Draw newly placed ANs
					C1 = HCS.hexToCart(AN);
					C2 = HCS.hexToCart(AN2);
					// TODO: Temporary List to hold all these 'C1, C2' coordinates
					C1C2List.add(C1);
					C1C2List.add(C2);
					// TODO: Graph functions go here

					// Draw head and tail nodes
					cen1 = HCS.hexToCart(new Coordinate<Integer>(pq[0], pq[1]));
					cen2 = HCS.hexToCart(new Coordinate<Integer>(pq[2], pq[3]));
					// TODO: Temporary List to hold all these 'cen' coordinates
					CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
					CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
					// TODO: Graph functions go here

					XYr.add(HCS.add2coord(C1, XYc));
					GlobalConstants.n++;
					System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

					XYr.add(HCS.add2coord(C2, XYc));
					GlobalConstants.n++;
					System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

					// Update information in distance matrix
					reshapeDmatrix2(D2, rcv);
					D2.get(GlobalConstants.n - 2).set((GlobalConstants.n - 1), GlobalConstants.H);
					IMOUT = InitMax.initMax(XYr, XYc, D2);
					UV = IMOUT.getHCSList();
					D2 = IMOUT.getDMatrix();
					// ST = PrimPrep(IMOUT.getParent());
					ST = IMOUT.getST();
				}
				else
				{
					System.out.println("More than 2 nodes needed case!"); // TODO: DEBUG
					base = GlobalConstants.n;
					temp.setNum(3.1); // This comes when 2 is actually not able to cover !!

					while (temp.getNum() > 3.01 && temp.getPointer() == 0)
					{
						p1 = new Matrix(hcoord2array(p));
						q1 = new Matrix(hcoord2array(q));
						LM2 = new LocateM2(p1, q1, GlobalConstants.H, temp.getTheta());

						AN = LM2.out.getAN();
						AN2 = LM2.out.getAN2();

						C1 = HCS.hexToCart(AN);
						C2 = HCS.hexToCart(AN2);
						// TODO: Temporary List to hold all these 'C1, C2' coordinates
						C1C2List.add(C1);
						C1C2List.add(C2);
						// TODO: Graph functions go here

						// Draw red hex on p & q
						cen1 = HCS.hexToCart(new Coordinate<Integer>(pq[0], pq[1]));
						cen2 = HCS.hexToCart(new Coordinate<Integer>(pq[2], pq[3]));
						// TODO: Temporary List to hold all these 'cen' coordinates
						CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
						CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
						// TODO: Graph functions go here

						// Add AN1 and AN2 to the set
						XYr.add(HCS.add2coord(C1, XYc));
						GlobalConstants.n++;

						XYr.add(HCS.add2coord(C2, XYc));
						GlobalConstants.n++;

						reshapeDmatrix3(D2);

						p = AN;
						q = AN2;
						// If pointer == 1, while loop is broken
						temp = Connect.connect(p, q, 'a');

						// Considering the case if we need 1 or 2
						if (temp.getNum() <= 2.01 && temp.getPointer() == 0)
						{
							System.out.println("\tInternal: Exactly 1 node needed case!"); // TODO: DEBUG
							p1 = new Matrix(hcoord2array(p));
							q1 = new Matrix(hcoord2array(q));
							L1 = new Locate1(p1, q1, GlobalConstants.H);
							LC1 = new LocateC1(p1, q1, GlobalConstants.H);
							cond1 = L1.out.getexFlag() != 0;
							cond2 = LC1.out.getexFlag() != 0;

							if (cond1 && cond2)
							{
								dist1 = HCS.distance(L1.out.getAN(), O);
								dist2 = HCS.distance(LC1.out.getAN(), O);
								if (dist1 > dist2)
									AN = LC1.out.getAN();
								else
									AN = L1.out.getAN();

								// If AN is found, draw it out
								C1 = HCS.hexToCart(AN);
								// TODO: Temporary List to hold all these 'C1, C2' coordinates
								C1C2List.add(C1);
								// TODO: Graph functions go here

								// Add AN to sets
								XYr.add(HCS.add2coord(C1, XYc));
								GlobalConstants.n++;
								System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

								// Update Distance Matrix
								reshapeDmatrix4(D2);
								temp.setPointer(1);
							}
						}
						else if (temp.getNum() > 2.01 && temp.getNum() <= 3.01 && temp.getPointer() == 0)
						{
							System.out.println("\tInternal: Exactly 2 nodes needed case!"); // TODO: DEBUG
							// & abs(theta - pi/6) > 0.013
							// the last one comes when it seems to be ok but not
							p1 = new Matrix(hcoord2array(p));
							q1 = new Matrix(hcoord2array(q));
							LMM2 = new LocateMM2(p1, q1, GlobalConstants.H, temp.getTheta());

							AN = LMM2.out.getAN();
							AN2 = LMM2.out.getAN2();

							// Draw newly placed ANs
							C1 = HCS.hexToCart(AN);
							C2 = HCS.hexToCart(AN2);
							// TODO: Temporary List to hold all these 'C1, C2' coordinates
							C1C2List.add(C1);
							C1C2List.add(C2);
							// TODO: Graph functions go here

							// Draw head and tail nodes
							cen1 = HCS.hexToCart(new Coordinate<Integer>(pq[0], pq[1]));
							cen2 = HCS.hexToCart(new Coordinate<Integer>(pq[2], pq[3]));
							// TODO: Temporary List to hold all these 'cen' coordinates
							CenList1.add(new Coordinate<Double>(cen1.getX(), cen2.getX()));
							CenList2.add(new Coordinate<Double>(cen1.getY(), cen2.getY()));
							// TODO: Graph functions go here

							XYr.add(HCS.add2coord(C1, XYc));
							GlobalConstants.n++;
							System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

							XYr.add(HCS.add2coord(C2, XYc));
							GlobalConstants.n++;
							System.out.println("XYr now has: " + XYr.get(XYr.size() - 1));

							// Update information in distance matrix
							reshapeDmatrix3(D2);
							D2.get(GlobalConstants.n - 2).set((GlobalConstants.n - 1), GlobalConstants.H);
							temp.setPointer(1);
						}

						if ((GlobalConstants.n - base) > 3)
						{
							System.out.println("\tInternal: More than two nodes needed case!"); // TODO: DEBUG
							base_t = GlobalConstants.n - 3;

							// D2.get(base_t - 1).set((base_t + 1), GlobalConstants.H);
							// D2.get(base_t).set((base_t + 2), GlobalConstants.H);
							HOPE = List_ops.ll2array(D2); // TODO: WORK-AROUND
							HOPE[base_t - 1][base_t + 1] = GlobalConstants.H;
							HOPE[base_t][base_t + 2] = GlobalConstants.H;
							D2 = List_ops.array2ll(HOPE);
						}
					}

					// After placement, change the Max Value to be 0 --> R/r
					D2.get(rcv[0] - 1).set(base, GlobalConstants.H);
					D2.get(rcv[1] - 1).set((base + 1), GlobalConstants.H);
					IMOUT = InitMax.initMax(XYr, XYc, D2);
					UV = IMOUT.getHCSList();
					D2 = IMOUT.getDMatrix();
					// ST = PrimPrep(IMOUT.getParent());
					ST = IMOUT.getST();
				}
			}
		}
		System.out.println(">> Done with AN placement loop. Algorithm complete."); // TODO: DEBUG
		System.out.println(">> Final list of node locations:");
		List_ops.print_coordlist_double(XYr);
		WriteFile.WF(XYr);
		System.out.println(">> Original node locations saved in \"./src/output/original.dat\"");
		System.out.println(">> Additional node locations saved in \"./src/output/additional.dat\"");
	}
}
