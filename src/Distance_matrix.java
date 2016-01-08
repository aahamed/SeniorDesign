public class Distance_matrix
{
	static double distance_formula(double x1, double y1, double x2, double y2)
	{
		double x_part, y_part;

		x_part = (x2 - x1) * (x2 - x1);
		y_part = (y2 - y1) * (y2 - y1);

		return Math.sqrt(x_part + y_part);
	}

	public static void main (String[] args)
	{
		Data_ops latlon = new Data_ops();
		int entries;
		double lat1, lon1, lat2, lon2;

		latlon.parse_data();
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

				distance_matrix[x][y] = distance_formula(lat1, lon1, lat2, lon2);
			}
		}

		for (int a = 0; a < entries; a++)
		{
			for (int b = 0; b < entries; b++)
			{
				System.out.print(distance_matrix[a][b] + ", ");
			}
			System.out.print("\n");
		}
	}
}
