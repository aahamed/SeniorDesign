/*
 * Author: Josue Galeas
 * Last Edit: Jan 25, 2016
 * Description: Class for calculating distance matrix from latitude and longitude.
 */

public class Distance_matrix
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

				lat1 = ll.getLat(x);
				lon1 = ll.getLon(x);
				lat2 = ll.getLat(y);
				lon2 = ll.getLon(y);

				result = Vincenty.inverse(lat1, lon1, lat2, lon2);
				distance_matrix[x][y] = result;
				distance_matrix[y][x] = result;
			}
		}

		for (int a = 0; a < entries; a++)
		{
			for (int b = 0; b < entries; b++)
			{
				System.out.printf("%.3f m, ", distance_matrix[a][b]);
			}
			System.out.println();
		}
	}
}
