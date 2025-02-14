# Day 12: JSAbacusFramework.io

Santa’s accounting software stores data in a JSON-like format with numbers, strings, arrays, and objects. The task is to find all numbers in the document and sum them.

## Part 1

Extract all numbers and compute their total sum.

Examples:
	•	[1,2,3] → 6
	•	{"a":2,"b":4} → 6
	•	[[[3]]] → 3
	•	{"a":{"b":4},"c":-1} → 3
	•	{"a":[-1,1]} and [-1,{"a":1}] → 0
	•	[] and {} → 0

## Part 2

Ignore entire objects ({...}) if any of their properties have the value "red", but keep numbers in arrays.

Examples:
	•	[1,2,3] → 6
	•	[1,{"c":"red","b":2},3] → 4
	•	{"d":"red","e":[1,2,3,4],"f":5} → 0
	•	[1,"red",5] → 6

Compute the new sum following this rule.
