/*
 * Author: Josue Galeas
 * Last Edit: Feb 2, 2016
 * Description: Class for testing.
 */

public class TEST
{
	public static void main (String[] args)
	{
		String input_filename;
		int override = 0;

		if (args.length > 0)
			input_filename = args[0];
		else
			input_filename = "./src/input/in.txt";

		if (input_filename.equals("MATLAB"))
		{
			override = 1;
			input_filename = "./src/input/in.txt"; // To avoid IO issues
			System.out.println("Using MATLAB testbench.");
		}
		else
			System.out.println("Using \"" + input_filename + "\" as the input file.");

		Distance_matrix dm = new Distance_matrix(input_filename, override);
		System.out.println(">> Testing: Printing Distance matrix");
		dm.printD();
		System.out.println(">> Testing: Printing SP0 matrix");
		dm.printSP0();

		MST_calc mc = new MST_calc(input_filename, override);
		System.out.println(">> Testing: Printing X matrix");
		mc.printX();
		System.out.println(">> Testing: Printing w matrix");
		mc.printw();
	}
}
