/*
 * Author: Josue Galeas
 * Last Edit: Feb 21, 2016
 * Description: Maps latitude and longitude values to a Mercator projection, a 2D Cartesian plane.
 */

import java.util.ArrayList;
import java.util.List;

public class Mercator_mapping
{
	private List<Coordinate<Double>> mercmap = new ArrayList<Coordinate<Double>>();
	private final double WGS84_A = 6378137.0;
	private final double WGS84_B = 6356752.314245;
	private final double WGS84_F = 1.0/298.257223563;

	public Mercator_mapping(String input, boolean m)
	{
		Parse_data ll = new Parse_data(input, m);
		int entries = ll.getSize();
		double mX, mY;

		if (m)
		{
			for (int c = 0; c < entries; c++)
			{
				mX = ll.getLat(c);
				mY = ll.getLon(c);

				mercmap.add(new Coordinate<Double>(mX, mY));
			}

		}
		else
		{
			for (int c = 0; c < entries; c++)
			{
				mX = lon2mercX(ll.getLon(c));
				mY = lat2mercY(ll.getLat(c));

				mercmap.add(new Coordinate<Double>(mX, mY));
			}
		}
	}

	private double lon2mercX(double lon)
	{
		return WGS84_A * Math.toRadians(lon);
	}

	private double lat2mercY(double lat)
	{
		if (lat > 89.5)
			lat = 89.5;
		if (lat < -89.5)
			lat = -89.5;

		double temp = WGS84_B / WGS84_A;
		double es = 1.0 - (temp * temp);
		double eccent = Math.sqrt(es);
		double phi = Math.toRadians(lat);
		double sin_phi = Math.sin(phi);
		double con = eccent * sin_phi;
		double com = 0.5 * eccent;
		con = Math.pow(((1.0 - con) / (1.0 + con)), com);
		double ts = Math.tan(0.5 * ((Math.PI * 0.5) - phi)) / con;
		double y = 0 - WGS84_A * Math.log(ts);

		return y;
	}

	public int getSize()
	{
		return mercmap.size();
	}

	public double getmX(int a)
	{
		return mercmap.get(a).getX();
	}

	public double getmY(int a)
	{
		return mercmap.get(a).getY();
	}

	public Coordinate<Double> getMMCOM()
	{
		return List_ops.getCOM(mercmap);
	}

	public void printMM()
	{
		List_ops.print_coordlist_double(mercmap);
	}
}
