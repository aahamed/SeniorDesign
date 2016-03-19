Senior Design Project by Team MANET
===================================
Implementation of a theoretical connectivity algorithm in Java

Software Dependencies:
----------------------
* Java Development Kit (versions 1.7.0_65 or above) from [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Make (version 3.81 or above) from [here](https://www.gnu.org/software/make/)
* GNUplot (versions 4.6 patchlevel 6 or above) from [here](https://sourceforge.net/projects/gnuplot/files/gnuplot/)
	* Note: See installation instructions below
* X11 (any version)
	* Xming for Windows from [here](https://sourceforge.net/projects/xming/)
	* Xquartz for OS X from [here](http://www.xquartz.org/)

GNUplot Installation:
---------------------
###For Windows:
Coming soon...

###For OS X:
Note: OS X does not come with a package manager, we recommend [Homebrew](http://brew.sh/).
```
brew install gnuplot-x11
```

###For Linux:
For Ubuntu:
```
sudo apt-get install gnuplot-x11
```

For Debian (Subject To Change):
```
su root
apt-get install gnuplot-x11
```

For Fedora 22 or newer:
```
su root
dnf install gnuplot
```

For Fedora 21 or older:
```
su root
yum install gnuplot
```

###For Unix:
Coming soon...

Usage:
------
* Navigate to the main directory, and compile the codebase with: `make all`
	* Note: If you do not wish to install Make, use:
		* Windows: `javac -cp './bin/;./lib/*' -d ./bin/ ./src/*.java`
		* OS X/Linux/Unix: `javac -cp './bin/:./lib/*' -d ./bin/ ./src/*.java`
* Navigate to `bin`, use `java MainBody`
	* Use `--help` option to see usage details
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
	* Note: Prim's algorithm process is currently bugged, use Kruskal for now 

Sources:
--------
* Java matrix operations library obtained from [here](http://math.nist.gov/javanumerics/jama/)
* Mercator mapping method based off the Elliptical Mercator script from [here](http://wiki.openstreetmap.org/wiki/Mercator#Elliptical_Mercator)
