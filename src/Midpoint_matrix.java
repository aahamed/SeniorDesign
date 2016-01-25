/*
 * Author: Josue Galeas
 * Last Edit: Jan 18, 2016
 * Description: TODO
 */

public class Midpoint_matrix
{
	static double[] midpoint(double x1, double y1, double x2, double y2)
	{
		double[] point = new double[2];
		double phi1 = Math.toRadians(x1);
		double phi2 = Math.toRadians(x2);
		double delta_lambda = Math.toRadians(y2 - y1);
		double lambda1 = Math.toRadians(y1);

		double Bx = Math.cos(phi2) * Math.cos(delta_lambda);
		double By = Math.cos(phi2) * Math.sin(delta_lambda);
		double p1 = Math.sin(phi1) + Math.sin(phi2);
		double p2 = Math.sqrt((Math.cos(phi1) + Bx) * (Math.cos(phi1) + Bx) + By * By);
		double phi3 = Math.atan2(p1, p2);
		double lambda3 = lambda1 + Math.atan2(By, Math.cos(phi1) + Bx);
		lambda3 = (lambda3 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

		point[0] = Math.toDegrees(phi3);
		point[1] = Math.toDegrees(lambda3);

		return point;
	}

	public static void main (String[] args)
	{
		// TODO
	}
}
