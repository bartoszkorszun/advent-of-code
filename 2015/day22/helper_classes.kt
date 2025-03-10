data class Boss(
    val hp: Int,
    val damage: Int
)

enum class SpellType {
    MAGIC_MISSILE,
    DRAIN,
    SHIELD,
    POISON,
    RECHARGE
}

data class Spell(
    val type: SpellType,
    val manaCost: Int,
    val damage: Int = 0,
    val healing: Int = 0,
    val effectDuration: Int = 0
)

data class GameState(
    val playerHp: Int,
    val playerMana: Int,
    val playerArmor: Int,
    val bossHp: Int,
    val bossDamage: Int,
    val activeEffects: Map<SpellType, Int>,
    val manaSpent: Int,
    val isPlayerTurn: Boolean,
    val spellHistory: List<SpellType> = emptyList(),
    val hardMode: Boolean = false
)