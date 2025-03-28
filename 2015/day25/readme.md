# Day 25: Let It Snow

Santa's **weather machine** needs a **code** to start, but the manual is missing! We must **calculate the code** using a pattern.  

## Part 1
- Codes are arranged in a **diagonal grid**:  

```

   |    1         2         3         4         5         6
---+---------+---------+---------+---------+---------+---------+
 1 | 20151125  18749137  17289845  30943339  10071777  33511524
 2 | 31916031  21629792  16929656   7726640  15514188   4041754
 3 | 16080970   8057251   1601130   7981243  11661866  16474243
 4 | 24592653  32451966  21345942   9380097  10600672  31527494
 5 |    77061  17552253  28094349   6899651   9250759  31663883
 6 | 33071741   6796745  25397450  24659492   1534922  27995004

```

- The **next code** is calculated from the previous one:  
  \[
  \text{new code} = (\text{previous code} \times 252533) \mod 33554393
  \]
- The first code is `20151125`.  
- Find the **target position**, follow the pattern, and compute the final code.  

---

## Part 2
The machine needs **50 stars** to start. **Complete all puzzles** to power it up!  

🎄 **Merry Christmas!** 🎄