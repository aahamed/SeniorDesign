public class Distance_matrix
{
	static double harvesine_distance(double x1, double y1, double x2, double y2)
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
		Data_ops latlon = new Data_ops();
		String input_filename = "in.txt";
		String output_filename = "out.txt";
		int entries;
		double lat1, lon1, lat2, lon2;

		if (args.length > 0)
		{
			input_filename = args[0];
		}

		latlon.parse_data(input_filename);
		entries = latlon.ll.size();
		double[][] distance_matrix = new double[entries][entries];

		for (int x = 0; x < entries; x++)
		{
			for (int y = 0; y < entries; y++)
			{
				lat1 = latlon.ll.get(x).get(0);
				lon1 = latlon.ll.get(x).get(1);
				lat2 = latlon.ll.get(y).get(0);
				lon2 = latlon.ll.get(y).get(1);

				distance_matrix[x][y] = harvesine_distance(lat1, lon1, lat2, lon2);
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
