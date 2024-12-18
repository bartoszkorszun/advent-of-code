# Day 2: I Was Told There Would Be No Math

The elves need to calculate how much wrapping paper and ribbon are required to prepare their presents. Each present is a rectangular prism, and its dimensions are given as length (`l`), width (`w`), and height (`h`). 

## Part 1

To determine the amount of wrapping paper required:
1. Calculate the surface area of the box:  
   `2*l*w + 2*w*h + 2*h*l`.
2. Add the area of the smallest side for slack.

## Part 2

To determine the amount of ribbon required:
1. Calculate the shortest perimeter of any one face:  
   `2 * (smallest + second smallest dimension)`.
2. Add the volume of the box for the bow:  
   `l * w * h`.