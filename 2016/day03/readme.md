# Day 3: Squares With Three Sides

## Part 1

You're given a list of triangles, each defined by three side lengths. Your task is to determine how many of them are **valid triangles** based on the triangle inequality rule:  

> The sum of any two sides must be greater than the third side.  

For each set of three numbers, check if they form a valid triangle and count how many are possible.  

## Part 2

The triangles are now **specified by columns instead of rows**.  

- Instead of reading the side lengths **row by row**, you now read them **column by column**, grouping every three numbers in each column as a separate triangle.  
- Apply the same triangle validity rule as before.  
- Count how many valid triangles exist when reading the input this way.