# Day 9: Explosives in Cyberspace

# Part 1 

While exploring a secure area, you discover a file compressed with a custom format. The compression uses markers in the form `(AxB)`, meaning the next A characters should be repeated B times. Whitespace is ignored, and markers only apply to the characters that immediately follow them. Nested markers inside repeated sections are treated as plain text and are not further decompressed.

## Part 2

It turns out the file uses an updated compression format. In this version, markers within decompressed sections must also be processed recursively. This means the length of the fully decompressed file can grow exponentially, requiring a strategy that computes the decompressed length without fully expanding the data.