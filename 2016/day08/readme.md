# Day 8: Two-Factor Authentication

## Part 1 

You're dealing with a **50x6 pixel screen**, which starts with all pixels **turned off**. The screen supports the following commands:  

1. `rect AxB`:  
   - Turns on all pixels in a **rectangle** of width **A** and height **B** in the **top-left corner**.  
2. `rotate row y=A by B`:  
   - Shifts **row A** to the **right** by **B pixels** (pixels wrap around).  
3. `rotate column x=A by B`:  
   - Shifts **column A** **down** by **B pixels** (pixels wrap around).  

Your **goal** is to execute these operations and count how many pixels are **lit** at the end.  

## Part 2

Instead of just counting pixels, now you need to **read** the characters displayed on the **screen**. The letters are formed using a **5x6 pixel font**.  

- After executing all operations, the screen will display **a readable message** 
- The message consists of **capital letters**, which you can determine by visually inspecting the final grid.  

Your **goal** is to **decode the message** displayed on the screen.