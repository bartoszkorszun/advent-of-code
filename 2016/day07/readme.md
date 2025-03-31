# Day 7: Internet Protocol Version 7

## Part 1

You are analyzing a list of **IPv7 addresses** to determine which ones support **TLS (Transport-Layer Snooping)**.  

An **IP supports TLS** if:  
1. It contains an **ABBA sequence** outside square brackets.  
   - An **ABBA** is a four-character sequence where the first and last characters match, and the two middle characters match but are different from the outer ones (e.g., **xyyx**, **abba**).  
2. It **does NOT** contain an ABBA sequence **within square brackets**.  

**Task:** Count how many IPs in the input support TLS.  

## Part 2

Now, you need to determine which IPs support **SSL (Super-Secret Listening)**.  

An **IP supports SSL** if:  
1. It contains an **ABA sequence** outside square brackets.  
   - An **ABA** is a three-character sequence where the first and last characters match, and the middle character is different (e.g., **aba**, **xyx**).  
2. There exists a corresponding **BAB sequence** inside square brackets.  
   - A **BAB** is the reverse of an ABA (e.g., if `aba` is outside, there must be `bab` inside).  

**Task:** Count how many IPs in the input support SSL.