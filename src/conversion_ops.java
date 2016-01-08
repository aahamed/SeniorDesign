import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class conversion_ops
{
	int deg1, min1, sec1;
	int deg2, min2, sec2;
	char dir1, dir2;
	double lat, lon;
	List<List<Double>> ll = new ArrayList<List<Double>>();

	void parse_data()
	{
		textfile_ops datafile = new textfile_ops(); // TODO: Maybe move this out?
		datafile.read_file();
		int entries = datafile.data.size();

		for (int c = 0; c < entries; c++)
		{
			Scanner string = new Scanner(datafile.data.get(c)).useDelimiter("[°\'\" ]");
			deg1 = Integer.parseInt(string.next());
			min1 = Integer.parseInt(string.next());
			sec1 = Integer.parseInt(string.next());
			dir1 = string.next().charAt(0);
			deg2 = Integer.parseInt(string.next());
			min2 = Integer.parseInt(string.next());
			sec2 = Integer.parseInt(string.next());
			dir2 = string.next().charAt(0);
			string.close();
			gps_to_ll();
			ll.add(new ArrayList<Double>());
			ll.get(c).add(lat);
			ll.get(c).add(lon);
		}
	}

	void combine_data()
	{
		// TODO: Will eventually get back to this.
	}

	void gps_to_ll()
	{
		lat = deg1 + min1/60.0 + sec1/3600.0;
		if (dir1 == 'S')
		{
			lat *= -1;
		}
		lon = deg2 + min2/60.0 + sec2/3600.0;
		if (dir2 == 'W')
		{
			lon *= -1;
		}
	}

	void ll_to_gps()
	{
		// TODO: Will eventually get back to this.
	}
}
