# GNUplot script file for plotting data in file "original.dat" and "additional.dat"
# This file is called graph.p
set autoscale # scale axes automatically
unset log # remove any log-scaling
unset label # remove any previous labels
set xtic auto # set xtics automatically
set ytic auto # set ytics automatically
set title "Output of the Connectivity Algorithm"
set xlabel " "
set ylabel " "
set xr [0:30000]
set yr [0:30000]
plot "./src/output/original.dat" using 1:2 title ' ' with points pointtype 6 lw 2 , \
    "./src/output/additional.dat" using 1:2 title ' ' with points lw 2
set term png
set output 'graph.png'
replot
set term x11
