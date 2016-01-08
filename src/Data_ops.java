import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Data_ops
{
	List<List<Double>> ll = new ArrayList<List<Double>>();
	// textfile_ops datafile = new textfile_ops(); // TODO: Maybe move this out?

	void parse_data()
	{
		Textfile_ops datafile = new Textfile_ops(); // TODO: Maybe move this out?
		Conversion_ops convert = new Conversion_ops();
		int entries;

		datafile.read_file();
		entries = datafile.data.size();

		for (int c = 0; c < entries; c++)
		{
			Scanner line = new Scanner(datafile.data.get(c)).useDelimiter("[°\'\" ]");
			convert.deg1 = Integer.parseInt(line.next());
			convert.min1 = Integer.parseInt(line.next());
			convert.sec1 = Integer.parseInt(line.next());
			convert.dir1 = line.next().charAt(0);
			convert.deg2 = Integer.parseInt(line.next());
			convert.min2 = Integer.parseInt(line.next());
			convert.sec2 = Integer.parseInt(line.next());
			convert.dir2 = line.next().charAt(0);
			line.close();
			convert.gps_to_ll();
			ll.add(new ArrayList<Double>());
			ll.get(c).add(convert.lat);
			ll.get(c).add(convert.lon);
		}
	}

	void combine_data()
	{
		// TODO: Will eventually get back to this.
	}
}
