fun parseBoss(input: String): Boss {
    val lines = input.lines().filter { it.isNotEmpty() }
    val hp = lines[0].substringAfter("Hit Points: ").toInt()
    val damage = lines[1].substringAfter("Damage: ").toInt()
    return Boss(hp, damage)
}

fun defineSpells(): List<Spell> {
    return listOf(
        Spell(SpellType.MAGIC_MISSILE, 53, 4, 0, 0),
        Spell(SpellType.DRAIN, 73, 2, 2, 0),
        Spell(SpellType.SHIELD, 113, 0, 0, 6),
        Spell(SpellType.POISON, 173, 0, 0, 6),
        Spell(SpellType.RECHARGE, 229, 0, 0, 5)
    )
}

fun findMinMana(initialState: GameState, spells: List<Spell>): Int {
    var minManaToWin = Int.MAX_VALUE
    val stateQueue = ArrayDeque<GameState>()
    stateQueue.add(initialState)

    while (stateQueue.isNotEmpty()) {
        val state = stateQueue.removeFirst()
        if (state.manaSpent >= minManaToWin) {
            continue
        }

        if (state.bossHp <= 0) {
            minManaToWin = state.manaSpent
            continue
        }

        if (state.playerHp <= 0) {
            continue
        }

        if (state.isPlayerTurn) {
            var playerHp = state.playerHp
            if (state.hardMode) {
                playerHp -= 1
                if (playerHp <= 0) continue 
            }
            
            val (newState, newEffects) = applyEffects(state.copy(playerHp = playerHp))
            
            for (spell in spells) {
                if (spell.manaCost > newState.playerMana || 
                    (spell.effectDuration > 0 && newEffects.containsKey(spell.type))) {
                    continue
                }
                
                val afterSpellState = castSpell(newState, spell, newEffects)
                
                stateQueue.add(afterSpellState.copy(isPlayerTurn = false))
            }
        } else {
            val (newState, newEffects) = applyEffects(state)
            
            if (newState.bossHp <= 0) {
                if (newState.manaSpent < minManaToWin) {
                    minManaToWin = newState.manaSpent
                }
                continue
            }
            
            val damage = maxOf(1, newState.bossDamage - newState.playerArmor)
            val afterBossAttackHp = newState.playerHp - damage
            
            stateQueue.add(newState.copy(
                playerHp = afterBossAttackHp,
                activeEffects = newEffects,
                isPlayerTurn = true
            ))
        }
    }

    return minManaToWin
}

fun applyEffects(state: GameState): Pair<GameState, Map<SpellType, Int>> {
    var newState = state
    val newEffects = state.activeEffects.toMutableMap()
    var playerArmor = 0
    
    for ((effectType, turnsLeft) in state.activeEffects) {
        when (effectType) {
            SpellType.SHIELD -> {
                playerArmor = 7
            }
            SpellType.POISON -> {
                newState = newState.copy(bossHp = newState.bossHp - 3)
            }
            SpellType.RECHARGE -> {
                newState = newState.copy(playerMana = newState.playerMana + 101)
            }
            else -> {} 
        }
        
        if (turnsLeft > 1) {
            newEffects[effectType] = turnsLeft - 1
        } else {
            newEffects.remove(effectType)
        }
    }
    
    return Pair(newState.copy(playerArmor = playerArmor), newEffects)
}

fun castSpell(state: GameState, spell: Spell, activeEffects: Map<SpellType, Int>): GameState {
    var newState = state.copy(
        playerMana = state.playerMana - spell.manaCost,
        manaSpent = state.manaSpent + spell.manaCost,
        spellHistory = state.spellHistory + spell.type
    )
    
    val newEffects = activeEffects.toMutableMap()
    
    if (spell.damage > 0) {
        newState = newState.copy(bossHp = newState.bossHp - spell.damage)
    }
    
    if (spell.healing > 0) {
        newState = newState.copy(playerHp = newState.playerHp + spell.healing)
    }
    
    if (spell.effectDuration > 0) {
        newEffects[spell.type] = spell.effectDuration
    }
    
    return newState.copy(activeEffects = newEffects)
}