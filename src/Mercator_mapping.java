/*
 * Author: Josue Galeas
 * Last Edit: Jan 28, 2016
 * Description: Maps latitude and longitude values to a Mercator projection, a 2D Cartesian plane.
 */

import java.util.ArrayList;
import java.util.List;

public class Mercator_mapping
{
	private List<Coordinate<Double>> mercmap = new ArrayList<Coordinate<Double>>();

	public Mercator_mapping(String input)
	{
		Parse_data ll = new Parse_data(input);

		int entries = ll.getSize();
		double mX, mY;

		for (int c = 0; c < entries; c++)
		{
			mX = lon2mercX(ll.getLon(c));
			mY = lat2mercY(ll.getLat(c));

			mercmap.add(new Coordinate<Double>(mX, mY));
		}
	}

	private double lon2mercX(double lon)
	{
		return WGS84.A * Math.toRadians(lon);
	}

	private double lat2mercY(double lat)
	{
		if (lat > 89.5)
			lat = 89.5;
		if (lat < -89.5)
			lat = -89.5;

		double temp = WGS84.B / WGS84.A;
		double es = 1.0 - (temp * temp);
		double eccent = Math.sqrt(es);
		double phi = Math.toRadians(lat);
		double sinphi = Math.sin(phi);
		double con = eccent * sinphi;
		double com = 0.5 * eccent;
		con = Math.pow(((1.0 - con) / (1.0 + con)), com);
		double ts = Math.tan(0.5 * ((Math.PI * 0.5) - phi)) / con;
		double y = 0 - WGS84.A * Math.log(ts);

		return y;
    }

	public double getmX(int a)
	{
		return mercmap.get(a).getX();
	}

	public double getmY(int a)
	{
		return mercmap.get(a).getY();
	}

	public void printmm()
	{
		int entries = mercmap.size();

		for (int c = 0; c < entries; c++)
		{
			System.out.println(mercmap.get(c));
		}
	}

	public Coordinate<Double> getCOM()
	{
		int entries = mercmap.size();
		double total_x = 0.0, total_y = 0.0;

		for (int c = 0; c < entries; c++)
		{
			total_x += mercmap.get(c).getX();
			total_y += mercmap.get(c).getY();
		}

		return new Coordinate<Double>(total_x/entries, total_y/entries);
	}

	public Coordinate<Double> getConverted(Coordinate<Double> a)
	{
		double new_mX = lon2mercX(a.getY());
		double new_mY = lat2mercY(a.getX());

		return new Coordinate<Double>(new_mX, new_mY);
	}
}
