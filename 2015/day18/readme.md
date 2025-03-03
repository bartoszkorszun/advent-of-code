# Day 18: Like a GIF For Your Yard

Simulate a **100x100 grid** of lights that follow **Game of Life** rules for **100 steps** and determine how many lights remain **on** at the end.  

## Part 1

You have a **100x100 grid** of lights, initially set according to the input. The grid follows **Conway's Game of Life** rules:  
- An **on** light stays **on** if **2 or 3** neighbors are on; otherwise, it turns **off**.  
- An **off** light turns **on** if **exactly 3** neighbors are on; otherwise, it stays **off**.  
- The process repeats for **100 steps**.  

## Part 2

The **four corner lights** are stuck **on** and never change state. The rest of the grid follows the same rules.  