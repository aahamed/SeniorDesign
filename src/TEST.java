/*
 * Author: Josue Galeas
 * Last Edit: Jan 28, 2016
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
			input_filename = "in.txt";

		Parse_data ll = new Parse_data(input_filename);
		System.out.println("Testing printing from Parse_data:");
		ll.printll();
		Coordinate<Double> ll_point = ll.getCOM();
		System.out.println("COM: " + ll_point);
		System.out.println();

		Mercator_mapping mm = new Mercator_mapping(input_filename);
		System.out.println("Testing printing from Mercator_mapping:");
		mm.printmm();
		Coordinate<Double> mm_point = mm.getCOM();
		System.out.println("COM: " + mm_point);
		System.out.println();
	}
}
