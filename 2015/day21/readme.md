# Day 21: RPG Simulator 20XX

## Goal

Determine the **optimal equipment purchases** to either **win with the least gold spent** or **lose with the most gold spent** in a turn-based RPG fight.  

## **Game Mechanics**  
- **Player always attacks first**  
- **Damage dealt** = Attacker’s **damage** - Defender’s **armor** (minimum of 1)  
- **Battle continues** until one character reaches **0 HP**  

## **Shop Rules**  
- **Must buy one weapon**  
- **Can buy one armor (optional)**  
- **Can buy up to 2 rings (optional)**  
- **Total damage/armor is sum of items**  

## Part 1

Find the **cheapest** valid item combination that allows the player to **defeat the boss** before dying.  

## Part 2

Find the **most expensive** valid item combination where the **player still loses** to the boss.  