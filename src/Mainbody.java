/*
 * Author: TODO: Everyone
 * Last Edit: Feb 19, 2016
 * Description: Main body of the algorithm TODO
 */

public class Mainbody
{
	private static String input_filename;
	private static boolean q_matlab;
	private static boolean q_mstalgo;

	public static void main (String[] args)
	{
		Options(args);
		MST_calc mc = new MST_calc(input_filename, q_matlab, q_mstalgo);
		MST Tree = mc.getMST();
		Tree.printAll();
	}

	private static void Options(String[] a)
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

			// TODO: Make it clear that only some of the flags were specified and what individual default values are used
			if (!check1)
				q_matlab = false;
			if (!check2)
				q_mstalgo = false;
			if (!check3)
				input_filename = "./src/input/in.txt";
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
}
