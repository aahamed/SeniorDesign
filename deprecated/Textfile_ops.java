import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Textfile_ops
{
	// Makes a list called 'data' which will contain the strings from the file.
	List<String> data_file = new ArrayList<String>();

	void read_file(String input)
	{
		// Finds path to the input file.
		// TODO: Check out URI class.
		Path input_file = Paths.get(input);

		// Will try and read the input file and place it into a buffer called 'reader'.
		// Assuming text file is in encoded in UTF_8, which is usually the case.
		try (BufferedReader reader = Files.newBufferedReader(input_file, StandardCharsets.UTF_8))
		{
			// Will write each line from the input file into 'data'.
			String line = null;

			while ((line = reader.readLine()) != null)
			{
				data_file.add(line);
			}
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
	}

	void write_file(String output)
	{
		// TODO: Will eventually get back to this.
	}
}