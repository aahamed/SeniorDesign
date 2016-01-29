/*
 * Author: Josue Galeas
 * Last Edit: Jan 27, 2015
 * Description: Maps latitude and longitude values to a 3D Cartesian space.
 */

public class ThreeD_mapping
{
	public static void main (String[] args)
	{
		String input_filename;

		if (args.length > 0)
			input_filename = args[0];
		else
			input_filename = "in.txt";

		Parse_data ll = new Parse_data(input_filename);
		int entries = ll.getSize();
		double lat, lon, result;
		double[][] points = new double[entries][3];

		for (int n = 0; n < entries; n++)
		{
			lat = Math.toRadians(ll.getLat(n));
			lon = Math.toRadians(ll.getLon(n));
			points[n][0] = WGS84.A * Math.cos(lat) * Math.cos(lon);
			points[n][1] = WGS84.A * Math.cos(lat) * Math.sin(lon);
			points[n][2] = WGS84.B * Math.sin(lat);
		}

		System.out.print("[");
		for (int a = 0; a < entries; a++)
		{
			for (int b = 0; b < 3; b++)
			{
				System.out.print(points[a][b]);
				if (b < 2)
					System.out.print(" ");
			}
			if (a < (entries - 1))
				System.out.print("; ");
		}
		System.out.println("]");

		System.out.println();
		for (int a = 0; a < entries; a++)
		{
			for (int b = 0; b < 3; b++)
			{
				System.out.print(points[a][b]);
				if (b < 2)
					System.out.print("\t");
			}
			System.out.println();
		}
	}
}
