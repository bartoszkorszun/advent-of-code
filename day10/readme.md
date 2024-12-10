# Day 10

## Part 1

Identify all **trailheads** (positions with height `0`) on a topographic map and calculate their scores. A **trailhead's score** is the number of `9`-height positions reachable from the trailhead via a valid hiking trail. Hiking trails must:
- Start at height `0` and end at height `9`.
- Increase by exactly `1` at each step.
- Move only up, down, left, or right (no diagonals).

Sum the scores of all trailheads to find the total.

## Part 2

For each trailhead, calculate its **rating**. A **trailhead's rating** is the number of distinct hiking trails that start at that position. Each distinct trail must:
- Begin at the trailhead.
- Follow the same rules as in Part 1 to traverse from `0` to `9`.

Sum the ratings of all trailheads to determine the total.