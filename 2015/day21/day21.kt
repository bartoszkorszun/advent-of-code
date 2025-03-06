import java.io.File

data class Boss(
    val hp: Int, 
    val damage: Int, 
    val armor: Int
)

data class Weapon(
    val cost: Int, 
    val damage: Int,
    val armor: Int
)

data class Armor(
    val cost: Int, 
    val damage: Int,
    val armor: Int
)

data class Ring(
    val cost: Int, 
    val damage: Int,
    val armor: Int
)

data class Shop(
    val weapons: List<Weapon>,
    val armors: List<Armor>,
    val rings: List<Ring>
)

fun parseBoss(bossInput: String): Boss {
    val lines = bossInput.lines()
    val hp = lines[0].split(": ")[1].toInt()
    val damage = lines[1].split(": ")[1].toInt()
    val armor = lines[2].split(": ")[1].toInt()
    return Boss(hp, damage, armor)
}

fun parseShop(shopInput: String): Shop {
    val lines = shopInput.lines()
    
    val weapons = mutableListOf<Weapon>()
    for (i in 1..5) {
        val parts = lines[i].trim().split(Regex("\\s+"))
        val name = parts[0]
        val cost = parts[1].toInt()
        val damage = parts[2].toInt()
        val armor = parts[3].toInt()
        weapons.add(Weapon(cost, damage, armor))
    }
    
    val armors = mutableListOf<Armor>()
    for (i in 8..12) {
        val parts = lines[i].trim().split(Regex("\\s+"))
        val name = parts[0]
        val cost = parts[1].toInt()
        val damage = parts[2].toInt()
        val armor = parts[3].toInt()
        armors.add(Armor(cost, damage, armor))
    }
    
    val rings = mutableListOf<Ring>()
    for (i in 15..20) {
        val parts = lines[i].trim().split(Regex("\\s+"))
        val name = if (parts[0] == "Damage" || parts[0] == "Defense") {
            parts[0] + " " + parts[1]
        } else {
            parts[0]
        }
        val cost = parts[parts.size - 3].toInt()
        val damage = parts[parts.size - 2].toInt()
        val armor = parts[parts.size - 1].toInt()
        rings.add(Ring(cost, damage, armor))
    }
    
    return Shop(weapons, armors, rings)
}

fun part2(boss: Boss, shop: Shop): Int {
    return 0
}   

fun part1(boss: Boss, shop: Shop): Int {
    println(boss)
    println(shop)
    return 0
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