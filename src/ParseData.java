/*
 * Author: Josue Galeas
 * Last Edit: May 8, 2016
 * Description: Class for parsing and converting GPS coordinates or MATLAB coordinates into latitude and longitude format.
 */

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ParseData
{
	public static List<Coordinate<Double>> PD(String inputFile, boolean matlab)
	{
		List<Coordinate<Double>> output = new ArrayList<Coordinate<Double>>();
		List<String> fileData = ReadFile.RF(inputFile);

		int entries = fileData.size();

		if (matlab)
		{
			double mat1 = 0.0, mat2 = 0.0;

			for (int c = 0; c < entries; c++)
			{
				Scanner line = new Scanner(fileData.get(c)).useDelimiter(",");

				try
				{
					mat1 = Double.parseDouble(line.next());
					mat2 = Double.parseDouble(line.next());
				}
				catch (NoSuchElementException x)
				{
					System.err.format("ERROR: Insufficient or missing input data.\n[%s]\n", x);
					System.exit(201);
				}
				catch (NumberFormatException x)
				{
					System.err.format("ERROR: Incorrect type of input data. Use --help flag for usage details.\n[%s]\n", x);
					System.exit(202);
				}

				line.close();
				output.add(new Coordinate<Double>(mat1, mat2));
			}
		}
		else
		{
			int deg1 = 0, min1 = 0, deg2 = 0, min2 = 0;
			double sec1 = 0.0, sec2 = 0.0, lat = 0.0, lon = 0.0;
			char dir1 = '\0', dir2 = '\0';

			for (int c = 0; c < entries; c++)
			{
				Scanner line = new Scanner(fileData.get(c)).useDelimiter("[°\'\" ]");

				try
				{
					deg1 = Integer.parseInt(line.next());
					min1 = Integer.parseInt(line.next());
					sec1 = Double.parseDouble(line.next());
					dir1 = line.next().charAt(0);

					deg2 = Integer.parseInt(line.next());
					min2 = Integer.parseInt(line.next());
					sec2 = Double.parseDouble(line.next());
					dir2 = line.next().charAt(0);
				}
				catch (NoSuchElementException x)
				{
					System.err.format("ERROR: Insufficient or missing input data.\n[%s]\n", x);
					System.exit(203);
				}
				catch (NumberFormatException x)
				{
					System.err.format("ERROR: Incorrect type of input data. Use --help flag for usage details.\n[%s]\n", x);
					System.exit(204);
				}

				lat = deg1/1.0 + min1/60.0 + sec1/3600.0;
				if (Character.toUpperCase(dir1) == 'S')
					lat *= -1;

				lon = deg2/1.0 + min2/60.0 + sec2/3600.0;
				if (Character.toUpperCase(dir2) == 'W')
					lon *= -1;

				line.close();
				output.add(new Coordinate<Double>(lat, lon));
			}
		}

		return output;
	}

	// TODO: TEMPORARY METHOD UNTIL GNUPLOT IS REPLACED
	public static List<Coordinate<Double>> NodeList(String inputFile)
	{
		List<Coordinate<Double>> output = new ArrayList<Coordinate<Double>>();
		List<String> fileData = ReadFile.RF(inputFile);

		double mat1, mat2;

		for (int c = 0; c < fileData.size(); c++)
		{
			Scanner line = new Scanner(fileData.get(c));

			mat1 = Double.parseDouble(line.next());
			mat2 = Double.parseDouble(line.next());

			line.close();
			output.add(new Coordinate<Double>(mat1, mat2));
		}

		return output;
	}

	public static void main(String[] args)
	{
		List<Coordinate<Double>> test = PD(args[0], false);
		// True is Matlab, false is GPS

		System.out.println(">> The parsed data has " + test.size() + " entries.");
		System.out.println(">> The parsed data contains:");
		ListOps.printDoubleCoords(test);
	}
}
