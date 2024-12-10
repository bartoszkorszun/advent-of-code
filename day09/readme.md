# Day 9

## Part 1

Compact the amphipod's hard drive by moving individual file blocks one at a time to the leftmost available free space. Repeat this process until there are no gaps between file blocks. Finally, calculate the **filesystem checksum** by summing the product of each file block's position and its file ID. Skip free space blocks in the calculation.

## Part 2

Compact the amphipod's hard drive by moving whole files (not individual blocks) to the leftmost free space span large enough to fit each file. Files are moved in order of decreasing file ID, starting with the highest. If no suitable free space exists to the left, the file remains in place. After compacting, calculate the **filesystem checksum**.