# Day 7

## Part 1

The engineers are working to repair a rope bridge, but they need help to calibrate their equations. Each equation consists of a test value followed by a sequence of numbers. The goal is to determine if any combination of addition and multiplication operators can be inserted between the numbers to produce the test value. The operators are evaluated left-to-right, and the numbers cannot be rearranged. If the equation can be made true, its test value is added to the total calibration result. The answer to Part 1 is the sum of the test values that can be made true.

## Part 2

In Part 2, a third operator, concatenation (`||`), is introduced. This operator combines the digits of two numbers into a single number (e.g., `12 || 345` becomes `12345`). With this new operator, additional equations can now be made true, along with those that were solvable using addition and multiplication. The goal is to compute the new total calibration result, which is the sum of all test values that can be made true using any combination of addition, multiplication, and concatenation operators.