# Day 1: No Time for a Taxicab

You're **airdropped** near **Easter Bunny HQ**, but you need to **navigate a city grid** using a set of **turn-and-move instructions**.  

---

## Part 1

- You start **facing North** at `(0,0)`.  
- Each instruction tells you to:  
  1. **Turn** (`L` for left, `R` for right).  
  2. **Move forward** a certain number of blocks.  
- Your goal is to find the **shortest distance** (Manhattan distance) from your starting point to the final destination.  

#### **Example**  
- `R2, L3` → 2 blocks **East**, 3 blocks **North** → **5 blocks away**  

---

## Part 2
- As you follow the instructions, **track every step** you take.  
- Find the **first location you visit twice** and calculate its distance from the start.  

#### **Example**  
- `R8, R4, R4, R8` → First repeated location is **4 blocks away**  

🎯 **Navigate smart, find HQ!** 🎯