/*
 * Author: Josue Galeas
 * Last Edit: Jan 18, 2016
 * Description: Class for calculating distance matrix from latitude and longitude.
 */

public class Distance_matrix
{
	static double haversine_distance(double x1, double y1, double x2, double y2)
	{
		double R = 6371000;
		double phi1 = Math.toRadians(x1);
		double phi2 = Math.toRadians(x2);
		double delta_phi = Math.toRadians(x2 - x1);
		double delta_lambda = Math.toRadians(y2 - y1);

		double p1 = Math.sin(delta_phi / 2) * Math.sin(delta_phi / 2);
		double p2 = Math.cos(phi1) * Math.cos(phi2) * Math.sin(delta_lambda / 2) * Math.sin(delta_lambda / 2);
		double a = p1 + p2;
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (R * c)/1000;
	}

	public static void main (String[] args)
	{
		String input_filename;

		if (args.length > 0)
			input_filename = args[0];
		else
			input_filename = "in.txt";

		Parse_data ll = new Parse_data(input_filename);
		int entries = ll.getSize();
		double lat1, lon1, lat2, lon2, result;
		double[][] distance_matrix = new double[entries][entries];

		for (int x = 0; x < entries; x++)
		{
			for (int y = x; y < entries; y++)
			{
				if (x == y)
				{
					distance_matrix[x][y] = 0.0;
					continue;
				}

				lat1 = ll.getLocation(x, 0);
				lon1 = ll.getLocation(x, 1);
				lat2 = ll.getLocation(y, 0);
				lon2 = ll.getLocation(y, 1);

				result = haversine_distance(lat1, lon1, lat2, lon2);
				distance_matrix[x][y] = result;
				distance_matrix[y][x] = result;
			}
		}

		for (int a = 0; a < entries; a++)
		{
			for (int b = 0; b < entries; b++)
			{
				System.out.printf("%.3f km, ", distance_matrix[a][b]);
			}
			System.out.println();
		}
	}
}
