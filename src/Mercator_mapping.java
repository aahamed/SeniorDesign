/*
 * Author: Josue Galeas
 * Last Edit: Feb 13, 2016
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

	public void MATLAB_TEST()
	{
		mercmap.clear();
		double[] x_r = {24441.71059, 27173.75811, 3809.604489, 27401.27568, 18970.77739, 2926.21215, 8354.946566, 16406.44558, 28725.20506, 28946.65606};
		double[] y_r = {4728.39245, 29117.78345, 28715.00845, 14561.26946, 24008.41407, 4256.590159, 12652.83848, 27472.06576, 23766.21989, 28784.77279};

		for (int c = 0; c < 10; c++)
		{
			mercmap.add(new Coordinate<Double>(x_r[c], y_r[c]));
		}
	}
}
