# Day 7: Some Assembly Required 

## Part 1  

You need to simulate a circuit made of **wires and bitwise logic gates**. Each wire carries a **16-bit signal** (0–65535) and gets its value from:  
- A direct assignment (e.g., `123 -> x`).  
- A bitwise operation (`AND`, `OR`, `LSHIFT`, `RSHIFT`, `NOT`) involving one or more wires.  

Determine the **final signal** carried by wire `a` after evaluating all instructions.

## Part 2  

Take the signal from wire `a`, override **wire `b`** with that value, and **reset the circuit**.  
Determine the **new signal** on wire `a`.