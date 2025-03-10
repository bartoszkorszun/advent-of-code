# Day 22: Wizard Simulator 20XX

## Goal

Find the **minimum mana cost** to defeat the boss in a turn-based wizard battle.  

## Game Mechanics
- **Player goes first**  
- **No normal attacks or armor**, only **spells**  
- **Boss armor is ignored**  
- If **no mana** is left to cast a spell, **you lose**  
- **Effects (like Poison, Shield, Recharge) last multiple turns**  
- **Cannot cast an already active effect**  

### **Spells Available**  
| Spell           | Cost  | Effect |
|----------------|------:|------------------------------------|
| **Magic Missile** | 53   | Instantly deals **4 damage** |
| **Drain**         | 73   | Deals **2 damage** & heals **2 HP** |
| **Shield**        | 113  | +7 Armor for **6 turns** |
| **Poison**        | 173  | Deals **3 damage per turn for 6 turns** |
| **Recharge**      | 229  | +101 Mana per turn for **5 turns** |

## Part 1

Find the **cheapest sequence of spells** that lets you defeat the boss before dying.  

## Part 2
- **Lose 1 HP at the start of every turn**  
- **Survive and still defeat the boss using the least mana**  