/*
 * Author: Josue Galeas
 * Last Edit: Jan 29, 2016
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
			input_filename = "./input/in.txt";

		Parse_data ll = new Parse_data(input_filename);
		System.out.println(">> Testing printing from Parse_data:");
		ll.printll();
		Coordinate<Double> ll_point = ll.getCOM();
		System.out.println(">> COM: " + ll_point);
		System.out.println();

		Mercator_mapping mm = new Mercator_mapping(input_filename);
		System.out.println(">> Testing printing from Mercator_mapping:");
		mm.printmm();
		Coordinate<Double> mm_point = mm.getCOM();
		System.out.println(">> COM: " + mm_point);
		Coordinate<Double> ll_merc_point = mm.getConverted(ll_point);
		System.out.println(">> Calculated COM: " + ll_merc_point);
		System.out.println();

		Initial_setup is = new Initial_setup(input_filename);
		System.out.println(">> Testing printing from Initial_setup:");
		is.printnorm();
		System.out.println(">> Testing HCS print:");
		is.printnormHCS();
		System.out.println(">> Converting back test:");
		is.printnormBack();
	}
}
