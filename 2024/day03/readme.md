# Day 3

## Part 1

The program is designed to process valid multiplication instructions in a corrupted memory string. A valid instruction has the format `mul(X,Y)`, where `X` and `Y` are numbers between 1 and 3 digits, with no extra spaces or invalid characters. All invalid sequences are ignored. The task is to:

1. Identify all valid `mul(X,Y)` instructions.
2. Multiply the numbers `X` and `Y` for each valid instruction.
3. Sum the results of all valid multiplications.

For example, in the corrupted string `xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))`, only properly formatted instructions like `mul(2,4)`, `mul(11,8)`, and `mul(8,5)` are processed. The sum of their results (2*4 + 11*8 + 8*5) is calculated as the output.

## Part 2

The program now includes conditional instructions that control whether `mul(X,Y)` operations are processed:

1. **`do()` Instruction:** Enables all subsequent valid `mul(X,Y)` instructions.
2. **`don't()` Instruction:** Disables all subsequent `mul(X,Y)` instructions.
3. At the start of the program, `mul` instructions are enabled by default.
4. Only the most recent `do()` or `don't()` affects whether a `mul` is processed.

For example, in the corrupted memory string:

`xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))`

- `mul(2,4)` is processed because `mul` is enabled by default.
- `don't()` disables subsequent `mul` instructions, so `mul(5,5)` and `mul(11,8)` are ignored.
- A `do()` re-enables processing, so `mul(8,5)` is processed.

The task is to parse the memory, respect the state of `do()` and `don't()`, and calculate the sum of all valid, enabled `mul` operations.