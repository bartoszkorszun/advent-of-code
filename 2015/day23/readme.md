# Day 23: Opening the Turing Lock

## Goal

Execute a simple assembly-like program with **two registers (`a` and `b`)** and determine the final value of `b`.  

### **Instructions Supported**
| Instruction  | Effect |
|-------------|-----------------------------------------------|
| **hlf r**   | Halve the value of register `r` |
| **tpl r**   | Triple the value of register `r` |
| **inc r**   | Increment register `r` by 1 |
| **jmp offset** | Jump `offset` instructions relative to current |
| **jie r, offset** | Jump `offset` if `r` is even ("Jump If Even") |
| **jio r, offset** | Jump `offset` if `r` is exactly 1 ("Jump If One") |

## Part 1

- Start with `a = 0`, `b = 0`.  
- Execute instructions until the program exits.  

## Part 2

- Same execution but `a` starts as **1** instead of **0**.  