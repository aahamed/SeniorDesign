import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Data_ops
{
	List<List<Double>> latlon = new ArrayList<List<Double>>();

	void parse_data(String input)
	{
		Textfile_ops data = new Textfile_ops();
		Conversion_ops convert = new Conversion_ops();

		data.read_file(input);
		int entries = data.data_file.size();

		for (int c = 0; c < entries; c++)
		{
			Scanner line = new Scanner(data.data_file.get(c)).useDelimiter("[°\'\" ]");
			convert.deg1 = Integer.parseInt(line.next());
			convert.min1 = Integer.parseInt(line.next());
			convert.sec1 = Double.parseDouble(line.next());
			convert.dir1 = line.next().charAt(0);
			convert.deg2 = Integer.parseInt(line.next());
			convert.min2 = Integer.parseInt(line.next());
			convert.sec2 = Double.parseDouble(line.next());
			convert.dir2 = line.next().charAt(0);
			line.close();
			convert.gps_to_ll();
			latlon.add(new ArrayList<Double>());
			latlon.get(c).add(convert.lat);
			latlon.get(c).add(convert.lon);
		}
	}

	void combine_data(String output)
	{
		// TODO: Will eventually get back to this.
	}
}
