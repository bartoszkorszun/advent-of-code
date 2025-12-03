# Day 3: Lobby

Your input consists of several **banks of batteries**, each represented as a single line of digits.
Each digit is a battery with a **joltage rating** from 1 to 9.

Example bank:

```
987654321111111
```

Within each bank, you must turn on a fixed number of batteries (depending on the part).
The output joltage for that bank is the **number formed by the digits of the selected batteries, in their original order**.

You **cannot** reorder digits.

The final answer is the **sum of the output joltages from all banks**.

## Part 1

For each bank:

* Select **exactly two** digits.
* Maintain the original order.
* Form a 2-digit number from those two digits.
* That number is the bank’s output joltage.
* The goal is to choose the two digits that form the **maximum possible number**.

Finally:

* Sum all banks' maximum two-digit joltages.

## Part 2

Rules change:

* Select **exactly twelve** digits from each bank.
* Maintain original order.
* Form a 12-digit number.
* Again, choose digits so that this 12-digit number is **as large as possible**.

Finally:

* Sum all banks' maximum 12-digit joltages.