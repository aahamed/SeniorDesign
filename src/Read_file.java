/*
 * Author: Josue Galeas
 * Last Edit: Jan 27, 2016
 * Description: Class for reading GPS coordinates from a text file and storing each line into an ArrayList of strings.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Read_file
{
	// Makes an ArrayList called 'file_data' which will contain the strings from the text file.
	private List<String> file_data = new ArrayList<String>();

	public Read_file(String input)
	{
		// Finds path to the input file.
		Path input_file = Paths.get(input);

		// Will try and read the input file and place it into a buffer called 'reader'.
		// Assuming text file is in encoded in UTF_8, which is usually the case.
		try (BufferedReader reader = Files.newBufferedReader(input_file, StandardCharsets.UTF_8))
		{
			// Will write each line from the input file into 'file_data'.
			String line = null;

			while ((line = reader.readLine()) != null)
			{
				file_data.add(line);
			}
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
	}

	public int getSize()
	{
		return file_data.size();
	}

	public String getString(int x)
	{
		return file_data.get(x);
	}

	public void printfd()
	{
		int entries = file_data.size();

		for (int c = 0; c < entries; c++)
		{
			System.out.println(file_data.get(c));
		}
	}
}
