Senior Design Project by Team MANET
===================================
Implementation of a theoretical connectivity algorithm in Java

Software Dependencies
---------------------
* Java Development Kit (version 1.7.0_95 or greater) from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Make (version 3.81 or greater) from [here](https://www.gnu.org/software/make/)
* GNUplot (version 4.6 patchlevel 6 or greater)
	* **Note:** See installation instructions below

GUI Usage
---------
1. Navigate to the project's root directory, and compile the codebase with: `make all`
	* **Note:** If you do not wish to use Make, use:
		* Windows: `javac -cp './bin/;./lib/*' -d ./bin/ ./src/*.java`
		* OS X/Linux: `javac -cp './bin/:./lib/*' -d ./bin/ ./src/*.java`
2. Run the GUI with: `make run`
	* **Note:** If you do not wish to use Make, use:
		* Windows: `java -cp './bin/;./lib/*' MainGUI`
		* OS X/Linux: `java -cp './bin/:./lib/*' MainGUI`

Command-Line Usage (with Make)
------------------------------
1. Navigate to the project's root directory, and compile the codebase with: `make all`
2. Run the code with:
	* Windows: `java -cp './bin/;./lib/*' MainBody`
	* OS X/Linux: `java -cp './bin/:./lib/*' MainBody`
3. Set options with flags:
	* Use `--help` flag to see usage details:
		```
		Usage: MainBody [--help] [<args>] [-i <path>]

		Input file:
		-i followed by the path to input text file

		Type of input data:
		-g for GPS coordinates (Default)
		-m for MATLAB generated coordinates

		MST algorithm to be used:
		-k for Kruskal's algorithm (Default)
		-p for Prim's algorithm
		```
	* Input text file should be formatted as follows:
		* See `./input/in.txt` for an example of how to set up GPS coordinates
		* See `./input/min.txt` for an example of how to set up MATLAB generated coordinates
4. To plot the results using GNUplot, use: `make graphGNU`
5. To plot the results using Java instead, use: `make graphJava`

Command-Line Usage (without Make)
------------------------------
1. Navigate to the project's root directory, and compile the codebase with:
	* Windows: `javac -cp './bin/;./lib/*' -d ./bin/ ./src/*.java`
	* OS X/Linux: `javac -cp './bin/:./lib/*' -d ./bin/ ./src/*.java`
2. Run the code with:
	* Windows: `java -cp './bin/;./lib/*' MainBody`
	* OS X/Linux: `java -cp './bin/:./lib/*' MainBody`
3. Set options with flags:
	* Use `--help` flag to see usage details:
		```
		Usage: MainBody [--help] [<args>] [-i <path>]

		Input file:
		-i followed by the path to input text file

		Type of input data:
		-g for GPS coordinates (Default)
		-m for MATLAB generated coordinates

		MST algorithm to be used:
		-k for Kruskal's algorithm (Default)
		-p for Prim's algorithm
		```
	* Input text file should be formatted as follows:
		* See `./input/in.txt` for an example of how to set up GPS coordinates
		* See `./input/min.txt` for an example of how to set up MATLAB generated coordinates
4. To plot the results using GNUplot, use: `gnuplot -persist ./gnuplot/graph.gnu`
5. To plot the results using Java instead, use:
	* Windows: `java -cp './bin/;./lib/*' TransformScale`
	* OS X/Linux: `java -cp './bin/:./lib/*' TransformScale`

GNUplot Installation
--------------------
### For Windows:
* Download and install GNUplot for Windows from [here](https://sourceforge.net/projects/gnuplot/files/gnuplot/)
* Add GNUplot to your terminal's PATH environment variable so that you can invoke it from the command line
	* An example of how to do this could be: `export PATH="$PATH:/c/Program Files/gnuplot/bin"`

### For OS X:
**Note:** OS X does not come with a package manager, we recommend [Homebrew](http://brew.sh/).
```
brew install qt
brew install gnuplot --with-qt
```

### For Linux:
#### For Ubuntu:
```
sudo apt-get install gnuplot
```

#### For Debian:
```
su root
apt-get install gnuplot
```

#### For Fedora:
**Note:** Use `yum` instead of `dnf` if on Fedora 21 or older.
```
su root
dnf install gnuplot
```

Known Issues
------------
* Kruskal's algorithm option has a few edge cases that sometimes causes inaccurate results
* MainBody has a bug that causes extra intermediate nodes to be placed
* Currently does not output GPS coordinates when the input has GPS coordinates

Sources
-------
* Based off the work from Kai Ding and Professor Homayoun Yousefi'zadeh from University of California, Irvine
	* Can be contacted at [kaid1,hyousefi]@uci.edu
* Java matrix operations library obtained from [here](http://math.nist.gov/javanumerics/jama/)
* Mercator mapping class based off the elliptical Mercator Java implementation from [here](http://wiki.openstreetmap.org/wiki/Mercator#Java_Implementation)
