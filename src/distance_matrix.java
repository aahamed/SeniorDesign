public class distance_matrix
{
	static double distance_formula(double x1, double y1, double x2, double y2)
	{
		double dist;
		double first_part, second_part;
		first_part = (x2 - x1) * (x2 - x1);
		second_part = (y2 - y1) * (y2 - y1);
		dist = Math.sqrt(first_part + second_part);

		return dist;
	}

	public static void main (String[] args)
	{
		data_ops TEST = new data_ops();
		TEST.parse_data();

		int entries = TEST.ll.size();
		double lat1, lon1, lat2, lon2;
		double result;
		double[][] distance_matrix = new double[entries][entries];
		for (int x = 0; x < entries; x++)
		{
			for (int y = 0; y < entries; y++)
			{
				lat1 = TEST.ll.get(x).get(0);
				lon1 = TEST.ll.get(x).get(1);
				lat2 = TEST.ll.get(y).get(0);
				lon2 = TEST.ll.get(y).get(1);

				result = distance_formula(lat1, lon1, lat2, lon2);
				distance_matrix[x][y] = result;
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
