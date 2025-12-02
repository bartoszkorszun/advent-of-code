# Day 2: Gift Shop  

This puzzle works with a list of **numeric ranges**, each given in the format:

```
start-end,start-end,start-end,...
```

Your task is to check each number in these ranges and identify which ones are **invalid product IDs** based on repeating-digit patterns.

## Part 1

A product ID is **invalid** if:

* It consists of a sequence of digits repeated **exactly twice**.
* Examples of invalid IDs:

  * `55` → `"5"` repeated twice
  * `6464` → `"64"` repeated twice
  * `123123` → `"123"` repeated twice

IDs never include leading zeros.

**Goal:**
For every range in the input, find all invalid IDs and return the **sum of those invalid IDs**.

## Part 2

The rules expand:

A product ID is **invalid** if:

* It consists of some digit sequence repeated **two or more times**.
* Examples:

  * `12341234` → `"1234"` × 2
  * `1212121212` → `"12"` × 5
  * `1111111` → `"1"` × 7
  * `999` → `"9"` × 3
  * `824824824` → `"824"` × 3

**Goal:**
Using the new rule, find all invalid IDs in all ranges and return their **sum**.
