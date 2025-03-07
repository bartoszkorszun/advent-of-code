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