# Day 1 - Not Quite Lisp

Santa is navigating a tall apartment building to deliver presents but needs help determining his destination floor. Using a series of instructions (`(` for up and `)` for down), calculate the final floor he reaches and identify when he first enters the basement (floor `-1`).

## Part 1
Given the instructions, compute the final floor Santa reaches after processing all characters.

**Examples:**
- `(())` and `()()` result in floor `0`.
- `(((` and `(()(()(` result in floor `3`.
- `())` and `))(` result in floor `-1`.

## Part 2
Find the position of the first character that causes Santa to enter the basement (floor `-1`).

**Examples:**
- `)` causes entry to the basement at position `1`.
- `()())` causes entry at position `5`.

### Key Insights
- The building has no top or bottom limits.
- Instructions must be processed character by character.

This script calculates both the final floor and the first position where Santa enters the basement.