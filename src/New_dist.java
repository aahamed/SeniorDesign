/*
 * Author: Josue Galeas
 * Last Edit: Jan 29, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

public class New_dist
{
	private List<List<Double>> distance_matrix = new ArrayList<List<Double>>();

	public New_dist(String input, String type)
	{
		if (type == "vincenty")
		{
			// Function call, argument is 'input'
		}
		else if (type == "cartesian")
		{
			// Function call, argument is 'input'
		}
		else
			System.out.println("ERROR: Could not discern which distance formula to use.");
	}

	private void vincenty_dist(String input_file)
	{
		// TODO
	}

	private void cartesian_dist()
	{
		// TODO
	}
}
