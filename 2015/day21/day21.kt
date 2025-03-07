import java.io.File

fun part2(boss: Boss, shop: Shop): Int {
    return 0
}   

fun part1(boss: Boss, shop: Shop): Int {
    var minCost = Int.MAX_VALUE
    val playerHP = 100
    for (weapon in shop.weapons) {
        tryNoArmor(weapon, boss, playerHP, minCost)?.let { minCost = it }

        for (armor in shop.armors) {
            tryNoRings(weapon, armor, boss, playerHP, minCost)?.let { minCost = it }

            for (ring1 in shop.rings) {
                tryOneRing(weapon, armor, ring1, boss, playerHP, minCost)?.let { minCost = it }

                for (ring2 in shop.rings) {
                    if (ring1 == ring2) continue
                    tryTwoRings(weapon, armor, ring1, ring2, boss, playerHP, minCost)?.let { minCost = it }
                }
            }
        }

        for (ring1 in shop.rings) {
                tryOneRingNoArmor(weapon, ring1, boss, playerHP, minCost)?.let { minCost = it }

                for (ring2 in shop.rings) {
                    if (ring1 == ring2) continue
                    tryTwoRingsNoArmor(weapon, ring1, ring2, boss, playerHP, minCost)?.let { minCost = it }
                }
            }   
    }
    return minCost
}

fun main() {
    val bossInput = File("input.txt").readText().trim()
    val shopInput = File("shop.txt").readText().trim()

    val boss = parseBoss(bossInput)
    val shop = parseShop(shopInput)

    val p2 = part2(boss, shop)
    val p1 = part1(boss, shop)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}