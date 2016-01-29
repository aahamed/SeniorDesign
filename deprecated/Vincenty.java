/*
 * Author: Josue Galeas
 * Last Edit: Jan 27, 2016
 * Description: Method for finding the distance between two GPS coordinates. Assumes the earth is an oblate spheroid for a more accurate result.
 */

public class Vincenty
{
	public static double inverse(double lat_1, double lon_1, double lat_2, double lon_2)
	{
		double phi1 = Math.toRadians(lat_1);
		double phi2 = Math.toRadians(lat_2);
		double lambda1 = Math.toRadians(lon_1);
		double lambda2 = Math.toRadians(lon_2);

		double L = lambda2 - lambda1;
		double tan_U1 = (1.0 - WGS84.F) * Math.tan(phi1);
		double cos_U1 = 1.0 / Math.sqrt((1.0 + tan_U1 * tan_U1));
		double sin_U1 = tan_U1 * cos_U1;
		double tan_U2 = (1.0 - WGS84.F) * Math.tan(phi2);
		double cos_U2 = 1.0 / Math.sqrt((1.0 + tan_U2 * tan_U2));
		double sin_U2 = tan_U2 * cos_U2;

		double lambda = L, lambda_prime = 0.0;
		int iterationLimit = 100;
		double sin_lambda = 0.0, cos_lambda = 0.0;
		double sin_sq_sigma = 0.0, sin_sigma = 0.0;
		double cos_sigma = 0.0, sigma = 0.0;
		double sin_alpha = 0.0, cos_sq_alpha = 0.0;
		double cos_2sigma_m = 0.0, C = 0.0;

		do
		{
			sin_lambda = Math.sin(lambda);
			cos_lambda = Math.cos(lambda);
			sin_sq_sigma = Math.pow((cos_U2 * sin_lambda), 2.0) + Math.pow((cos_U1 * sin_U2 - sin_U1 * cos_U2 * cos_lambda), 2.0);
			sin_sigma = Math.sqrt(sin_sq_sigma);
			if (sin_sigma == 0)
				return 0.0; // Co-incident points
			cos_sigma = sin_U1 * sin_U2 + cos_U1 * cos_U2 * cos_lambda;
			sigma = Math.atan2(sin_sigma, cos_sigma);
			sin_alpha = cos_U1 * cos_U2 * sin_lambda / sin_sigma;
			cos_sq_alpha = 1.0 - sin_alpha * sin_alpha;
			cos_2sigma_m = cos_sigma - 2.0 * sin_U1 * sin_U2 / cos_sq_alpha;
			// if (Double.isNaN(cos_2sigma_m)) cos_2sigma_m = 0.0; // Equatorial line
			C = WGS84.F / 16.0 * cos_sq_alpha * (4.0 + WGS84.F * (4.0 - 3.0 * cos_sq_alpha));
			lambda_prime = lambda;
			lambda = L + (1.0 - C) * WGS84.F * sin_alpha * (sigma + C * sin_sigma * (cos_2sigma_m + C * cos_sigma * (-1.0 + 2.0 * cos_2sigma_m * cos_2sigma_m)));
		}
		while (Math.abs(lambda - lambda_prime) > 1e-12 && --iterationLimit > 0);

		if (iterationLimit == 0)
			System.out.println("ERROR: Formula failed to converge.");

		double u_sq = cos_sq_alpha * (WGS84.A * WGS84.A - WGS84.B * WGS84.B) / (WGS84.B * WGS84.B);
		double A = 1.0 + u_sq / 16384.0 * (4096.0 + u_sq * (-768.0 + u_sq * (320 - 175 * u_sq)));
		double B = u_sq / 1024.0 * (256.0 + u_sq * (-128.0 + u_sq * (74.0 - 47.0 * u_sq)));

		double p1 = -1.0 + 2.0 * cos_2sigma_m * cos_2sigma_m;
		double p2 = B / 6.0 * cos_2sigma_m * (-3.0 + 4.0 * sin_sigma * sin_sigma) * (-3.0 + 4.0 * cos_2sigma_m * cos_2sigma_m);
		double delta_sigma = B * sin_sigma * (cos_2sigma_m + B / 4.0 * (cos_sigma * p1 - p2));

		double s = WGS84.B * A * (sigma - delta_sigma);
		double fwdAz = Math.atan2(cos_U2 * sin_lambda,  cos_U1 * sin_U2 - sin_U1 * cos_U2 * cos_lambda);
		double revAz = Math.atan2(cos_U1 * sin_lambda, -sin_U1 * cos_U2 + cos_U1 * sin_U2 * cos_lambda);

		return s;
	}

	public static double direct()
	{
		System.out.println("ERROR: Not yet implemented.");
		return 0.0;
	}
}
