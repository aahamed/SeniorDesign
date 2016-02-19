/*
 * Author: Josue Galeas
 * Last Edit: Feb 15, 2016
 * Description: Adjusts Mercator data set in relation to the center of mass of the data points, and also converts the data points to HCS coordinates.
 */

import java.util.ArrayList;
import java.util.List;

public class Initial_setup
{
	private List<Coordinate<Double>> normalized = new ArrayList<Coordinate<Double>>();
	private List<Coordinate<Integer>> normalized_HCS = new ArrayList<Coordinate<Integer>>();
	public Initial_setup(String input, boolean m)
	{
		Mercator_mapping mm = new Mercator_mapping(input, m);

		int entries = mm.getSize();
		double normX, normY;
		Coordinate<Double> mm_com = mm.getMMCOM();

		for (int c = 0; c < entries; c++)
		{
			normX = mm.getmX(c) - mm_com.getX();
			normY = mm.getmY(c) - mm_com.getY();

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

	public List<Coordinate<Integer>> getHCS()
	{
		return normalized_HCS;
	}

	public void printNorm()
	{
		System.out.println(">> Normalized points (x, y) are:");
		List_ops.print_coordlist_double(normalized);
	}

	public void printNorm_HCS()
	{
		System.out.println(">> HCS normalized points (u, v) are:");
		List_ops.print_coordlist_int(normalized_HCS);
	}

	public void printAll()
	{
		printNorm();
		printNorm_HCS();
	}
}
