import java.io.File

fun part2(boss: Boss): Int {
    val spells = defineSpells()
    val initialState = GameState(
        playerHp = 50,
        playerMana = 500,
        playerArmor = 0,
        bossHp = boss.hp,
        bossDamage = boss.damage,
        activeEffects = emptyMap(),
        manaSpent = 0,
        isPlayerTurn = true,
        hardMode = true
    )
    
    return findMinMana(initialState, spells)
}   

fun part1(boss: Boss): Int {
    val spells = defineSpells()
    val initialState = GameState(
        playerHp = 50,
        playerMana = 500,
        playerArmor = 0,
        bossHp = boss.hp,
        bossDamage = boss.damage,
        activeEffects = emptyMap(),
        manaSpent = 0,
        isPlayerTurn = true
    )
    
    return findMinMana(initialState, spells)
}

fun main() {
    val input = File("input.txt").readText().trim()
    val boss = parseBoss(input)

    val p1 = part1(boss)
    val p2 = part2(boss)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}