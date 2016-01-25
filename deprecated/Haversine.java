/*
 * Author: Josue Galeas
 * Last Edit: Jan 25, 2016
 * Description: Method for finding the great circle distance between two GPS coordinates. Assumes the earth is a perfect sphere.
 */

public class Haversine
{
	public static double distance(double x1, double y1, double x2, double y2)
	{
		double R = 6371000.0;
		double phi1 = Math.toRadians(x1);
		double phi2 = Math.toRadians(x2);
		double delta_phi = Math.toRadians(x2 - x1);
		double delta_lambda = Math.toRadians(y2 - y1);

		double p1 = Math.sin(delta_phi / 2.0) * Math.sin(delta_phi / 2.0);
		double p2 = Math.cos(phi1) * Math.cos(phi2) * Math.sin(delta_lambda / 2.0) * Math.sin(delta_lambda / 2.0);
		double a = p1 + p2;
		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

		return R * c;
	}
}
