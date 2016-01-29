/*
 * Author: Josue Galeas
 * Last Edit: Jan 28, 2016
 * Description: Adjusts Mercator data set in relation to the center of mass.
 */

import java.util.ArrayList;
import java.util.List;

class Initial_setup
{
	private List<Coordinate<Double>> normalized = new ArrayList<Coordinate<Double>>();

	public Initial_setup(String input)
	{
		Mercator_mapping mm = new Mercator_mapping(input);

		int entries = mm.getSize();
		Coordinate<Double> mm_com = mm.getCOM();

		for (int c = 0; c < entries; c++)
		{
			double normX = mm.getmX(c) - mm_com.getX();
			double normY = mm.getmY(c) - mm_com.getY();

			normalized.add(new Coordinate<Double>(normX, normY));
		}
	}

	public int getSize()
	{
		return normalized.size();
	}

	public Coordinate<Double> getLoc(int a)
	{
		return normalized.get(a);
	}

	public void printnorm()
	{
		int entries = normalized.size();

		for (int c = 0; c < entries; c++)
		{
			System.out.println(normalized.get(c));
		}
	}
}
