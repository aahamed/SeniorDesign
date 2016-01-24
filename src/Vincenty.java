/*
 * Author: Josue Galeas
 * Last Edit: Jan 23, 2016
 * Description: TODO
 */

public class Vincenty
{
	private static final double A_E = 6378137.0;
	private static final double B_E = 6356752.314245;
	private static final double F_E = 1.0/298.257223563;

	public static double inverse(double x1, double y1, double x2, double y2)
	{
		double phi1 = Math.toRadians(x1);
		double phi2 = Math.toRadians(x2);
		double lambda1 = Math.toRadians(y1);
		double lambda2 = Math.toRadians(y2);
		// TODO
		double L = lambda2 - lambda1;
		double tanU1 = (1.0 - F_E) * Math.tan(phi1);
		double cosU1 = 1.0 / Math.sqrt((1.0 + tanU1 * tanU1));
		double sinU1 = tanU1 * cosU1;
		double tanU2 = (1.0 - F_E) * Math.tan(phi2);
		double cosU2 = 1.0 / Math.sqrt((1.0 + tanU2 * tanU2));
		double sinU2 = tanU2 * cosU2;

		double lambda = L, lambda_p;
		int iterationLimit = 100;
		double sinlambda = 0.0, coslambda = 0.0;
		double sinSqsigma = 0.0, sinsigma = 0.0, cossigma = 0.0, sigma = 0.0;
		double sinalpha = 0.0, cosSqalpha = 0.0;
		double cos2sigmaM = 0.0, C = 0.0;

		do
		{
			sinlambda = Math.sin(lambda);
			coslambda = Math.cos(lambda);
			sinSqsigma = (cosU2 * sinlambda) * (cosU2 * sinlambda) + (cosU1 * sinU2 - sinU1 * cosU2 * coslambda) * (cosU1 * sinU2 - sinU1 * cosU2 * coslambda);
			sinsigma = Math.sqrt(sinSqsigma);
			// if (sinsigma == 0) return 0;  // co-incident points
			cossigma = sinU1 * sinU2 + cosU1 * cosU2 * coslambda;
			sigma = Math.atan2(sinsigma, cossigma);
			sinalpha = cosU1 * cosU2 * sinlambda / sinsigma;
			cosSqalpha = 1.0 - sinalpha * sinalpha;
			cos2sigmaM = cossigma - 2.0 * sinU1 * sinU2 / cosSqalpha;
			// if (isNaN(cos2sigmaM)) cos2sigmaM = 0;  // equatorial line: cosSqalpha=0 (§6)
			C = F_E / 16.0 * cosSqalpha * (4.0 + F_E * (4.0 - 3.0 * cosSqalpha));
			lambda_p = lambda;
			lambda = L + (1.0 - C) * F_E * sinalpha * (sigma + C * sinsigma * (cos2sigmaM + C * cossigma * (-1.0 + 2.0 * cos2sigmaM * cos2sigmaM)));
		}
		while (Math.abs(lambda - lambda_p) > 1e-12 && --iterationLimit > 0);
		// if (iterationLimit == 0) throw new Error('Formula failed to converge');

		double uSq = cosSqalpha * (A_E * A_E - B_E * B_E) / (B_E * B_E);
		double A = 1.0 + uSq / 16384.0 * (4096.0 + uSq * (-768.0 + uSq * (320 - 175 * uSq)));
		double B = uSq / 1024.0 * (256.0 + uSq * (-128.0 + uSq * (74.0 - 47.0 * uSq)));
		double delta_sigma = B * sinsigma * (cos2sigmaM + B / 4.0 * (cossigma * (-1.0 + 2.0 * cos2sigmaM * cos2sigmaM) - B / 6.0 * cos2sigmaM * (-3.0 + 4.0 * sinsigma * sinsigma) * (-3.0 + 4.0 * cos2sigmaM * cos2sigmaM)));

		double s = B_E * A * (sigma - delta_sigma);

		double fwdAz = Math.atan2(cosU2 * sinlambda,  cosU1 * sinU2 - sinU1 * cosU2 * coslambda);
		double revAz = Math.atan2(cosU1 * sinlambda, -sinU1 * cosU2 + cosU1 * sinU2 * coslambda);

		return s;
	}
}
