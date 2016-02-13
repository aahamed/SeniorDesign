/*
 * Author: Josue Galeas
 * Last Edit: Feb 12, 2016
 * Description: Adjusts Mercator data set in relation to the center of mass.
 */

import java.util.ArrayList;
import java.util.List;

public class Initial_setup
{
	private List<Coordinate<Double>> normalized = new ArrayList<Coordinate<Double>>();
	private List<Coordinate<Integer>> normalized_HCS = new ArrayList<Coordinate<Integer>>();

	public Initial_setup(String input, boolean m)
	{
		Mercator_mapping mm = new Mercator_mapping(input);
		if (m)
			mm.MATLAB_TEST();

		int entries = mm.getSize();
		Coordinate<Double> mm_com = mm.getMMCOM();

		for (int c = 0; c < entries; c++)
		{
			double normX = mm.getmX(c) - mm_com.getX();
			double normY = mm.getmY(c) - mm_com.getY();

			normalized.add(new Coordinate<Double>(normX, normY));
			normalized_HCS.add(HCS.cartToHex(normalized.get(c)));
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

	public Coordinate<Integer> getLoc_HCS(int a)
	{
		return normalized_HCS.get(a);
	}

	public void printNorm()
	{
		List_ops.print_coordlist_double(normalized);
	}

	public void printNorm_HCS()
	{
		List_ops.print_coordlist_int(normalized_HCS);
	}
}
