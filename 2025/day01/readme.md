# Day 1: Secret Entrance

## Part 1

You are given a list of dial rotations.
Each rotation consists of a direction (L for left, R for right) and a number of clicks to rotate on a circular dial numbered 0–99.
The dial starts at 50, and after each rotation you check where it ends.

Goal: Count how many times the dial ends on 0 after completing a rotation.

## Part 2

Security protocol 0x434C49434B changes the rules: now you must also count every time the dial passes through 0 during the rotation itself, not just where it ends.

Large rotations may pass 0 multiple times due to looping around the dial.

Goal: Count how many total clicks—during or at the end of any rotation—cause the dial to point at 0.