/*
 * Author: Josue Galeas
 * Last Edit: May 8, 2016
 * Description: Class for writing lines from a list of coordinates, to a data file.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WriteFile
{
	public static void WF(List<Coordinate<Double>> input, int originalNodes)
	{
		try
		{
			Path original = Paths.get("./output/original.dat");
			Path additional = Paths.get("./output/additional.dat");
			BufferedWriter writer1 = Files.newBufferedWriter(original);
			BufferedWriter writer2 = Files.newBufferedWriter(additional);

			try
			{
				String X = null, Y = null, datastring = null;

				for (int i = 0; i < originalNodes; i++)
				{
					X = Double.toString(input.get(i).getX());
					Y = Double.toString(input.get(i).getY());
					datastring = X + "\t" + Y + "\n";
					writer1.write(datastring);
				}

				for (int j = originalNodes; j < input.size(); j++)
				{
					X = Double.toString(input.get(j).getX());
					Y = Double.toString(input.get(j).getY());
					datastring = X + "\t" + Y + "\n";
					writer2.write(datastring);
				}
			}
			catch (IOException x)
			{
				System.err.format("ERROR: Could not open the output file.\n[%s]\n", x);
				System.exit(103);
			}
			finally
			{
				try
				{
					if (writer1 != null)
						writer1.close();
					if (writer2 != null)
						writer2.close();
				}
				catch (IOException x)
				{
					System.err.format("ERROR: Could not close the output file.\n[%s]\n", x);
					System.exit(104);
				}
			}
		}
		catch (InvalidPathException x)
		{
			System.err.format("FATAL ERROR: Could not convert path string to a Path.\n[%s]\n", x);
			System.exit(101);
		}
		catch (IOException x)
		{
			System.err.format("FATAL ERROR: Could not find the output file.\n[%s]\n", x);
			System.exit(102);
		}
	}
}
