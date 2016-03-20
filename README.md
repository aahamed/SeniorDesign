Senior Design Project by Team MANET
===================================
Implementation of a theoretical connectivity algorithm in Java

Software Dependencies
---------------------
* Java Development Kit (version 1.7.0_95 or greater) from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Make (version 3.81 or greater) from [here](https://www.gnu.org/software/make/)
* GNUplot (version 4.6 patchlevel 6 or greater)
	* **Note:** See installation instructions below
* X11 (any version)
	* Xming for Windows from [here](https://sourceforge.net/projects/xming/)
	* Xquartz for OS X from [here](http://www.xquartz.org/)

Usage
-----
1. Navigate to the project's root directory, and compile the codebase with: `make all`
	* **Note:** If you do not wish to use Make, use:
		* Windows: `javac -cp './bin/;./lib/*' -d ./bin/ ./src/*.java`
		* OS X/Linux: `javac -cp './bin/:./lib/*' -d ./bin/ ./src/*.java`
2. Navigate to `./bin`, and use `java MainBody`
	* Use `--help` flag to see usage details
	* Usage details are:
		```
		Usage: java MainBody [--help] [<args>] [-i <path>]

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
		* See `./src/input/in.txt` for an example of how to set up GPS coordinates
		* See `./src/input/min.txt` for an example of how to set up MATLAB generated coordinates
	* **Note:** Prim's algorithm option is currently bugged, use only Kruskal's algorithm for now
3. To plot the results, navigate back to the project root directory, and use: `make graphFinal`
	* **Note:** If you do not wish to use Make, use: `gnuplot -persist ./gnuplot/graph.gnu`
4. To save the plot to a PNG file, use: `make graphFinalPNG`
	* **Note:** If you do not wish to use Make, use: `gnuplot -persist ./gnuplot/graphpng.gnu`

GNUplot Installation
--------------------
### For Windows:
* Download GNUplot for Windows from [here](https://sourceforge.net/projects/gnuplot/files/gnuplot/)
* Add GNUplot to your terminal's PATH environment variable so that you can invoke it from the command line
	* There should be plenty of information online on how to accomplish this

### For OS X:
**Note:** OS X does not come with a package manager, we recommend [Homebrew](http://brew.sh/).
```
brew install gnuplot-x11
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
* Prim's algorithm option is currently bugged, use only Kruskal's algorithm for now
* Kruskal's algorithm option has a few edge cases that sometimes causes inaccurate results
* Currently does not output GPS coordinates when the input has GPS coordinates

Sources
-------
* Java matrix operations library obtained from [here](http://math.nist.gov/javanumerics/jama/)
* Mercator mapping method based off the Elliptical Mercator script from [here](http://wiki.openstreetmap.org/wiki/Mercator#Elliptical_Mercator)
