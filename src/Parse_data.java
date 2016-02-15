/*
 * Author: Josue Galeas
 * Last Edit: Feb 15, 2016
 * Description: Class for parsing and converting GPS information from a list of strings into latitude and longitude format. Also calculates center of mass.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Parse_data
{
	private List<Coordinate<Double>> lat_lon = new ArrayList<Coordinate<Double>>();
	private int deg1, min1, deg2, min2;
	private double sec1, sec2, lat, lon;
	private char dir1, dir2;

	public Parse_data(String input, boolean m)
	{
		Read_file data = new Read_file(input);

		int entries = data.getSize();

		if (m)
		{
			double mat1, mat2;

			for (int c = 0; c < entries; c++)
			{
				Scanner line = new Scanner(data.getString(c)).useDelimiter("[,]");

				mat1 = Double.parseDouble(line.next());
				mat2 = Double.parseDouble(line.next());

				line.close();
				lat_lon.add(new Coordinate<Double>(mat1, mat2));
			}
		}
		else
		{
			for (int c = 0; c < entries; c++)
			{
				Scanner line = new Scanner(data.getString(c)).useDelimiter("[°\'\" ]");

				deg1 = Integer.parseInt(line.next());
				min1 = Integer.parseInt(line.next());
				sec1 = Double.parseDouble(line.next());
				dir1 = line.next().charAt(0);

				deg2 = Integer.parseInt(line.next());
				min2 = Integer.parseInt(line.next());
				sec2 = Double.parseDouble(line.next());
				dir2 = line.next().charAt(0);

				line.close();
				gps_to_ll();
				lat_lon.add(new Coordinate<Double>(lat, lon));
			}
		}
	}

	private void gps_to_ll()
	{
		lat = deg1/1.0 + min1/60.0 + sec1/3600.0;
		if (dir1 == 'S')
			lat *= -1;

		lon = deg2/1.0 + min2/60.0 + sec2/3600.0;
		if (dir2 == 'W')
			lon *= -1;
	}

	public int getSize()
	{
		return lat_lon.size();
	}

	public double getLat(int a)
	{
		return lat_lon.get(a).getX();
	}

	public double getLon(int a)
	{
		return lat_lon.get(a).getY();
	}

	public Coordinate<Double> getLLCOM()
	{
		return List_ops.getCOM(lat_lon);
	}

	public void printLL()
	{
		List_ops.print_coordlist_double(lat_lon);
	}
}
