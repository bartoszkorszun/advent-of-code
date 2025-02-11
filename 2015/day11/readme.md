# Day 11: Corporate Policy

## Part 1  

Santa needs a **new password** that follows corporate security rules:  
1. It must be exactly **eight lowercase letters**.  
2. It must include **one increasing straight** of at least **three consecutive letters** (e.g., `abc`, `bcd`).  
3. It **cannot contain** the letters `i`, `o`, or `l`.  
4. It must contain **at least two different, non-overlapping pairs** of letters (e.g., `aa`, `bb`).  

Santa's new password is found by **incrementing** the current one (like counting letters, where `z` wraps to `a`) until a valid password is found.  

## Part 2  

Santa's password expires again! This time, find the **next valid password** after the one found in Part 1.