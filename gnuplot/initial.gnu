#!/usr/bin/gnuplot

#Author: Josue Galeas
#Last Edit: Mar 12, 2016
#Description: GNUplot script for plotting data in file "original.dat"

reset
unset key
set xtic auto
set ytic auto
set title "Initial Locations of Clusters"
set xrange [0:30000]
set yrange [0:30000]
plot "./src/output/original.dat" using 1:2 with points pt 6 lw 2

#set term png
#set output 'initial.png'
#replot
#set term x11
