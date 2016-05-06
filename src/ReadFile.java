/*
 * Author: Josue Galeas
 * Last Edit: May 5, 2016
 * Description: Class for reading lines from a text file and storing each line into an ArrayList of strings.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadFile
{
	public static List<String> RF(String inputFile)
	{
		List<String> output = new ArrayList<String>();

		try
		{
			Path filePath = Paths.get(inputFile);
			BufferedReader reader = Files.newBufferedReader(filePath);

			try
			{
				String line = null;

				while ((line = reader.readLine()) != null)
				{
					output.add(line);
				}
			}
			catch (IOException x)
			{
				System.err.format("ERROR: Could not open the input file. [%s]\n", x);
				System.exit(3);
			}
			finally
			{
				try
				{
					if (reader != null)
						reader.close();
				}
				catch (IOException x)
				{
					System.err.format("ERROR: Could not close the input file. [%s]\n", x);
					System.exit(4);
				}
			}
		}
		catch (InvalidPathException x)
		{
			System.err.format("FATAL ERROR: Could not convert path string to a Path. [%s]\n", x);
			System.exit(1);
		}
		catch (IOException x)
		{
			System.err.format("ERROR: Could not find the input file. Use --help flag for usage details. [%s]\n", x);
			System.exit(2);
		}

		return output;
	}

	private static void printFD(List<String> fileData)
	{
		for (int c = 0; c < fileData.size(); c++)
		{
			System.out.println(fileData.get(c));
		}
	}

	public static void main(String[] args)
	{
		List<String> test = RF(args[0]);

		System.out.println(">> The input file has " + test.size() + " entries.");
		System.out.println(">> The input file contains:");
		printFD(test);
	}
}
