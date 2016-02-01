/*
 * Author: Josue Galeas
 * Last Edit: Jan 31, 2016
 * Description: Class for testing.
 */

public class TEST
{
	public static void main (String[] args)
	{
		String input_filename;

		if (args.length > 0)
			input_filename = args[0];
		else
			input_filename = "./src/input/in.txt";

		Initial_setup is = new Initial_setup(input_filename);
		System.out.println(">> Testing: Printing from Initial_setup:");
		is.printNorm();
		System.out.println(">> Testing: Converting data to HCS:");
		is.printNormHCS();

		Distance_matrix dm = new Distance_matrix(input_filename);
		System.out.println(">> Testing: Printing from Distance_matrix");
		dm.printDM();
	}
}
