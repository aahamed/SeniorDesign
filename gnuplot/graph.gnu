#!/usr/bin/gnuplot

#Author: Josue Galeas
#Last Edit: Mar 12, 2016
#Description: GNUplot script for plotting data in files "original.dat" and "additional.dat"

reset
unset key
set xtic auto
set ytic auto
set title "Output of the Connectivity Algorithm"
set xrange [0:30000]
set yrange [0:30000]
set size ratio -1
plot "./src/output/original.dat" using 1:2 with points pt 6 lw 2 , \
    "./src/output/additional.dat" using 1:2 with points lw 2 , \

#set term png
#set output 'graph.png'
#replot
#set term x11
