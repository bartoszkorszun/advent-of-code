# Day 2

## Part 1

Analyze a list of reports, where each report contains a sequence of levels. Determine which reports are "safe" based on two criteria:

1. The levels must either increase or decrease throughout the report.
2. The difference between any two adjacent levels must be between 1 and 3 (inclusive).

Count the reports that meet both criteria.

## Part 2

Extend the analysis of reactor reports to account for a "Problem Dampener." This module allows a single "bad level" in an otherwise unsafe report to be ignored.

A report is now considered "safe" if it meets the following criteria:

    The levels are either all increasing or all decreasing, with differences between adjacent levels being between 1 and 3 (inclusive).
    If the report does not initially meet these criteria, removing any one level makes it safe according to the above rules.

Determine the total number of reports that are safe under these updated conditions.