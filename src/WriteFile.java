/*
 * Author: Josue Galeas
 * Last Edit: March 10, 2016
 * Description: TODO.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteFile
{
	public static void WF(List<Coordinate<Double>> input)
	{
		BufferedWriter BW1 = null;
		BufferedWriter BW2 = null;
		try
		{
			File datafile1 = new File("./src/output/original.dat");
			if (!datafile1.exists())
				datafile1.createNewFile();
			File datafile2 = new File("./src/output/additional.dat");
			if (!datafile2.exists())
				datafile2.createNewFile();

			FileWriter FW1 = new FileWriter(datafile1);
			FileWriter FW2 = new FileWriter(datafile2);
			BW1 = new BufferedWriter(FW1);
			BW2 = new BufferedWriter(FW2);

			int entries = input.size();
			String X = null, Y = null, datastring = null;

			for (int i = 0; i < 10; i++)
			{
				X = Double.toString(input.get(i).getX());
				Y = Double.toString(input.get(i).getY());
				datastring = X + "         " + Y + "\n"; 
				BW1.write(datastring);
			}

			for (int i = 10; i < entries; i++)
			{
				X = Double.toString(input.get(i).getX());
				Y = Double.toString(input.get(i).getY());
				datastring = X + "         " + Y + "\n"; 
				BW2.write(datastring);
			}
		}
		catch (IOException x)
		{
			x.printStackTrace();
		}
		finally
		{
			try
			{
				if (BW1 != null)
					BW1.close();
				if (BW2 != null)
					BW2.close();
			}
			catch (Exception x)
			{
				System.out.println("Error is closing BW" + x);
			}
		}
	}
}
