public class Conversion_ops
{
	int deg1, min1, sec1;
	int deg2, min2, sec2;
	char dir1, dir2;
	double lat, lon;

	void gps_to_ll()
	{
		lat = deg1 + min1/60.0 + sec1/3600.0;
		if (dir1 == 'S')
			lat *= -1;
		lon = deg2 + min2/60.0 + sec2/3600.0;
		if (dir2 == 'W')
			lon *= -1;
	}

	void ll_to_gps()
	{
		// TODO: Will eventually get back to this.
	}
}
