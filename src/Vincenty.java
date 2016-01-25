/*
 * Author: Josue Galeas
 * Last Edit: Jan 25, 2016
 * Description: TODO
 */

public class Vincenty
{
	private static final double A_WGS84 = 6378137.0;
	private static final double B_WGS84 = 6356752.314245;
	private static final double F_WGS84 = 1.0/298.257223563;

	public static double inverse(double x1, double y1, double x2, double y2)
	{
		double phi1 = Math.toRadians(x1);
		double phi2 = Math.toRadians(x2);
		double lambda1 = Math.toRadians(y1);
		double lambda2 = Math.toRadians(y2);

		double L = lambda2 - lambda1;
		double tan_U1 = (1.0 - F_WGS84) * Math.tan(phi1);
		double cos_U1 = 1.0 / Math.sqrt((1.0 + tan_U1 * tan_U1));
		double sin_U1 = tan_U1 * cos_U1;
		double tan_U2 = (1.0 - F_WGS84) * Math.tan(phi2);
		double cos_U2 = 1.0 / Math.sqrt((1.0 + tan_U2 * tan_U2));
		double sin_U2 = tan_U2 * cos_U2;

		double lambda = L, lambda_prime;
		int iterationLimit = 100;
		double sin_lambda, cos_lambda;
		double sin_sq_sigma, sin_sigma, cos_sigma, sigma;
		double sin_alpha, cos_sq_alpha;
		double cos_2sigma_m, C;

		do
		{
			sin_lambda = Math.sin(lambda);
			cos_lambda = Math.cos(lambda);
			sin_sq_sigma = Math.pow((cos_U2 * sin_lambda), 2.0) + Math.pow((cos_U1 * sin_U2 - sin_U1 * cos_U2 * cos_lambda), 2.0);
			sin_sigma = Math.sqrt(sin_sq_sigma);
			// if (sin_sigma == 0) return 0;  // co-incident points
			cos_sigma = sin_U1 * sin_U2 + cos_U1 * cos_U2 * cos_lambda;
			sigma = Math.atan2(sin_sigma, cos_sigma);
			sin_alpha = cos_U1 * cos_U2 * sin_lambda / sin_sigma;
			cos_sq_alpha = 1.0 - sin_alpha * sin_alpha;
			cos_2sigma_m = cos_sigma - 2.0 * sin_U1 * sin_U2 / cos_sq_alpha;
			// if (isNaN(cos_2sigma_m)) cos_2sigma_m = 0;  // equatorial line: cos_sq_alpha=0 (§6)
			C = F_WGS84 / 16.0 * cos_sq_alpha * (4.0 + F_WGS84 * (4.0 - 3.0 * cos_sq_alpha));
			lambda_prime = lambda;
			lambda = L + (1.0 - C) * F_WGS84 * sin_alpha * (sigma + C * sin_sigma * (cos_2sigma_m + C * cos_sigma * (-1.0 + 2.0 * cos_2sigma_m * cos_2sigma_m)));
		}
		while (Math.abs(lambda - lambda_prime) > 1e-12 && --iterationLimit > 0);
		// if (iterationLimit == 0) throw new Error('Formula failed to converge');
		if (iterationLimit == 0)
			System.out.println("ERROR: Formula failed to converge.");

		double u_sq = cos_sq_alpha * (A_WGS84 * A_WGS84 - B_WGS84 * B_WGS84) / (B_WGS84 * B_WGS84);
		double A = 1.0 + u_sq / 16384.0 * (4096.0 + u_sq * (-768.0 + u_sq * (320 - 175 * u_sq)));
		double B = u_sq / 1024.0 * (256.0 + u_sq * (-128.0 + u_sq * (74.0 - 47.0 * u_sq)));

		double p1 = -1.0 + 2.0 * cos_2sigma_m * cos_2sigma_m;
		double p2 = B / 6.0 * cos_2sigma_m * (-3.0 + 4.0 * sin_sigma * sin_sigma) * (-3.0 + 4.0 * cos_2sigma_m * cos_2sigma_m);
		double delta_sigma = B * sin_sigma * (cos_2sigma_m + B / 4.0 * (cos_sigma * p1 - p2));

		double s = B_WGS84 * A * (sigma - delta_sigma);
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
