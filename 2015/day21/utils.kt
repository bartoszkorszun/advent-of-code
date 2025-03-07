// PARSE FUNCTIONS
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

// PART 1 FUNCTIONS
fun tryNoArmor(
    weapon: Weapon,
    boss: Boss,
    playerHP: Int,
    currentMinCost: Int
): Int? {
    val cost = weapon.cost
    if (cost >= currentMinCost) return null

    val playerDamage = weapon.damage
    val playerArmor = weapon.armor

    return if (
        simulateFight(
            playerHP,
            playerDamage,
            playerArmor,
            boss
        )
    ) cost else null
}

fun tryNoRings(
    weapon: Weapon, 
    armor: Armor, 
    boss: Boss, 
    playerHP: Int, 
    currentMinCost: Int
): Int? {
    val cost = weapon.cost + armor.cost
    if (cost >= currentMinCost) return null
    
    val playerDamage = weapon.damage + armor.damage
    val playerArmor = weapon.armor + armor.armor
    
    return if (
        simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun tryOneRing(
    weapon: Weapon, 
    armor: Armor, 
    ring: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMinCost: Int
): Int? {
    val cost = weapon.cost + armor.cost + ring.cost
    if (cost >= currentMinCost) return null
    
    val playerDamage = weapon.damage + armor.damage + ring.damage
    val playerArmor = weapon.armor + armor.armor + ring.armor
    
    return if (
        simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun tryTwoRings(
    weapon: Weapon, 
    armor: Armor, 
    ring1: Ring, 
    ring2: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMinCost: Int
): Int? {
    val cost = weapon.cost + armor.cost + ring1.cost + ring2.cost
    if (cost >= currentMinCost) return null
    
    val playerDamage = weapon.damage + armor.damage + ring1.damage + ring2.damage
    val playerArmor = weapon.armor + armor.armor + ring1.armor + ring2.armor
    
    return if (
        simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun tryOneRingNoArmor(
    weapon: Weapon, 
    ring: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMinCost: Int
): Int? {
    val cost = weapon.cost + ring.cost
    if (cost >= currentMinCost) return null
    
    val playerDamage = weapon.damage + ring.damage
    val playerArmor = weapon.armor + ring.armor
    
    return if (
        simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun tryTwoRingsNoArmor(
    weapon: Weapon, 
    ring1: Ring, 
    ring2: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMinCost: Int
): Int? {
    val cost = weapon.cost + ring1.cost + ring2.cost
    if (cost >= currentMinCost) return null
    
    val playerDamage = weapon.damage + ring1.damage + ring2.damage
    val playerArmor = weapon.armor + ring1.armor + ring2.armor
    
    return if (
        simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun simulateFight(
    playerHP: Int, 
    playerDamage: Int, 
    playerArmor: Int, 
    boss: Boss
): Boolean {
    var currentPlayerHP = playerHP
    var currentBossHP = boss.hp
    
    while (true) {
        val playerAttack = maxOf(1, playerDamage - boss.armor)
        currentBossHP -= playerAttack
        if (currentBossHP <= 0) return true
        
        val bossAttack = maxOf(1, boss.damage - playerArmor)
        currentPlayerHP -= bossAttack
        if (currentPlayerHP <= 0) return false
    }
}

// PART 2 FUNCTIONS
fun loseNoArmor(
    weapon: Weapon, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage
    val playerArmor = weapon.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun loseNoRings(
    weapon: Weapon,
    armor: Armor, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost + armor.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage + armor.damage
    val playerArmor = weapon.armor + armor.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun loseOneRing(
    weapon: Weapon, 
    armor: Armor, 
    ring: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost + armor.cost + ring.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage + armor.damage + ring.damage
    val playerArmor = weapon.armor + armor.armor + ring.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun loseTwoRings(
    weapon: Weapon, 
    armor: Armor, 
    ring1: Ring, 
    ring2: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost + armor.cost + ring1.cost + ring2.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage + armor.damage + ring1.damage + ring2.damage
    val playerArmor = weapon.armor + armor.armor + ring1.armor + ring2.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun loseOneRingNoArmor(
    weapon: Weapon, 
    ring: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost + ring.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage + ring.damage
    val playerArmor = weapon.armor + ring.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}

fun loseTwoRingsNoArmor(
    weapon: Weapon, 
    ring1: Ring, 
    ring2: Ring, 
    boss: Boss, 
    playerHP: Int, 
    currentMaxCost: Int
): Int? {
    val cost = weapon.cost + ring1.cost + ring2.cost
    if (cost <= currentMaxCost) return null
    
    val playerDamage = weapon.damage + ring1.damage + ring2.damage
    val playerArmor = weapon.armor + ring1.armor + ring2.armor
    
    return if (
        !simulateFight(
            playerHP, 
            playerDamage, 
            playerArmor, 
            boss
        )
    ) cost else null
}