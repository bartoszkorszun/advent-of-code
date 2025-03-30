# Day 6: Signals and Noise

## Part 1

Your communication with Santa is being **jammed**, but you can still recover the message using a **simple repetition code**.  

Each **column** of the received messages contains multiple characters, and the correct message is reconstructed by taking the **most frequent character** in each column.  

Find the **error-corrected message** from the given input.  

## Part 2

The **error-correction method** changes: instead of choosing the most common letter in each column, you now choose the **least common letter**.  

Example (same input as above):  
- **1st column**: Least common letter → **a**  
- **2nd column**: Least common letter → **d**  
- **3rd column**: Least common letter → **v**  
- ...  
The reconstructed message is **"advent"**.  

Find the **original message** using this new decoding method.