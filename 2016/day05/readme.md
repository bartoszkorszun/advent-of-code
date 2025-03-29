# Day 5: How About a Nice Game of Chess?

## Part 1

The security door requires an **8-character password**, which is generated **one character at a time** by finding MD5 hashes of a given **Door ID** and an **increasing integer index** (starting at 0).  

A hash is **valid** if its hexadecimal representation **starts with five zeroes (00000)**. When this happens, the **sixth character** of the hash becomes the next character of the password.  

Example (Door ID: `abc`):  
- `abc3231929` → `000001f...` → First password character: **1**  
- `abc5017308` → `000008f...` → Second password character: **8**  
- `abc5278568` → `00000f...` → Third password character: **f**  
- Continue until the password has **8 characters**  

Find the password for the given **Door ID**.  

## Part 2

The second door has a **more advanced security mechanism**. Instead of filling in the password sequentially, the hash also determines **which position (0-7) to fill**.  

- The **sixth character** of a valid hash (00000X...) represents the **position**.  
- The **seventh character** represents the **password character**.  
- If the position is **out of range (8 or higher)** or **already filled**, ignore it.  
- The password is completed once all **8 positions** are filled.  

Example (Door ID: `abc`):  
- `abc3231929` → `0000015...` → Puts **5** at **position 1** → `_5______`  
- `abc5357525` → `000004e...` → Puts **e** at **position 4** → `_5__e___`  
- Keep going until all **8 positions are filled**.  

Find the **new password** using this method. Bonus points if you visualize it as a cinematic decryption sequence!