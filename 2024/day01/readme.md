# Day 1

## Part 1

The task involves **reconciling two lists of location IDs** by minimizing the overall "distance" between paired numbers from the two lists. The steps are as follows:

1. **Sort both lists** of location IDs.
2. Pair numbers by position in the sorted lists (smallest with smallest, second smallest with second smallest, etc.).
3. For each pair, calculate the **absolute difference** between the numbers.
4. Sum up all the absolute differences to get the total "distance" between the lists.

The goal is to determine the minimum possible total distance by optimally pairing the numbers based on their sorted order.

## Part 2

Calculate the total similarity score by determining how many times each number from the left list appears in the right list. For each number in the left list, multiply it by its frequency in the right list and sum up these products to get the score.