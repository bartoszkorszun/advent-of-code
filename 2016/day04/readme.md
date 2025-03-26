# Day 4: Security Through Obscurity

## Part 1

You're given a list of **encrypted room names**, each followed by a **sector ID** and a **checksum** in square brackets. Your task is to determine which rooms are **real** and then sum their sector IDs.  

A **room is real** if:  
1. You count the occurrences of each letter in the encrypted name (ignoring dashes).  
2. You take the five most common letters, sorting ties alphabetically.  
3. If this matches the provided checksum, the room is real.  

For example:  
- **aaaaa-bbb-z-y-x-123[abxyz]** → Real (checksum matches)  
- **totally-real-room-200[decoy]** → Not real (checksum doesn't match)  

Sum the **sector IDs** of all real rooms.  

## Part 2

Now, decrypt the names of the **real** rooms to find where North Pole objects are stored.  

The encryption is a **Caesar cipher** where each letter shifts forward by an amount equal to the **sector ID**.  
- `a → b → c` (1 shift)  
- `z → a` (wraps around)  
- Dashes (`-`) become spaces.  

For example:  
- **qzmt-zixmtkozy-ivhz-343** → **very encrypted name**  

Find the sector ID of the room where **North Pole objects** are stored.