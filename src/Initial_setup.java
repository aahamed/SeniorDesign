/*
 * Author: Josue Galeas
 * Last Edit: Jan 18, 2016
 * Description: TODO
 */

import java.util.ArrayList;
import java.util.List;

class Initial_setup
{
	private List<List<Double>> normalized = new ArrayList<List<Double>>();
	private double lat, lon;
	private double[] center;

	public Initial_setup(String input)
	{
		Parse_data ll = new Parse_data(input);

		int entries = ll.getSize();
		center = ll.getCOM();

		for (int x = 0; x < entries; x++)
		{
			lat = ll.getLocation(x, 0) - center[0];
			lon = ll.getLocation(x, 1) - center[1];

			normalized.add(new ArrayList<Double>());
			normalized.get(x).add(lat);
			normalized.get(x).add(lon);
		}
	}

	public int getSize()
	{
		return normalized.size();
	}

	public double getLocation(int a, int b)
	{
		return normalized.get(a).get(b);
	}

	public double[] getCOM()
	{
		// Might not be neccesary since the COM will be at the origin
		return center;
	}
}
