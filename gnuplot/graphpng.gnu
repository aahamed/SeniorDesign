#!/usr/bin/gnuplot

# Author: Josue Galeas
# Last Edit: Mar 28, 2016
# Description: GNUplot script for saving the plotting data in files "original.dat" and "additional.dat" to a PNG file

set term png
set output 'graph.png'
reset
unset key
set xtic auto
set ytic auto
set title "Output of the Connectivity Algorithm"
set autoscale
set size ratio -1
plot "./output/original.dat" using 1:2 with points pt 6 lw 2 , \
    "./output/additional.dat" using 1:2 with points lw 2 , \
