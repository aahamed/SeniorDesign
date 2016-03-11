# GNUplot script file for plotting data in file "original.dat"
# This file is called graph.p
set autoscale # scale axes automatically
unset log # remove any log-scaling
unset label # remove any previous labels
set xtic auto # set xtics automatically
set ytic auto # set ytics automatically
set title "Initial Locations of Clusters"
set xlabel " "
set ylabel " "
set xr [0:30000]
set yr [0:30000]
plot "./src/output/original.dat" using 1:2 title ' ' with points lw 2
set term png
set output 'initial.png'
replot
set term x11
