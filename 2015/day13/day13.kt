import java.io.File

data class Happiness(
    val person1: String, 
    val person2: String, 
    val value: Int
)

fun findOptimalHappiness(people: List<String>, happinessMap: Map<Pair<String, String>, Int>): Int {
    return people.permutations().maxOf { calculateHappiness(it, happinessMap) }
}

fun <T> List<T>.permutations(): List<List<T>> {
    if (size == 1) return listOf(this)
    val perms = mutableListOf<List<T>>()
    val sub = this[0]
    for (perm in (this - sub).permutations())
        for (i in perm.indices + 1)
            perms.add(perm.toMutableList().apply { add(i, sub) })
    return perms
}

fun calculateHappiness(arrangement: List<String>, happinessMap: Map<Pair<String, String>, Int>): Int {
    var totalHappiness = 0
    for (i in arrangement.indices) {
        val person1 = arrangement[i]
        val person2 = arrangement[(i + 1) % arrangement.size]
        totalHappiness += happinessMap[Pair(person1, person2)] ?: 0
        totalHappiness += happinessMap[Pair(person2, person1)] ?: 0
    }
    return totalHappiness
}

fun parseInput(input: String): List<Happiness> {
    val regex = Regex("""(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).""")
    return input.lines().mapNotNull { line ->
        regex.matchEntire(line)?.destructured?.let { (person1, gainOrLose, units, person2) ->
            val value = if (gainOrLose == "gain") units.toInt() else -units.toInt()
            Happiness(person1, person2, value)
        }
    }
}

fun part2(input: String): Int {
    val happinessChanges = parseInput(input)
    val happinessMap = happinessChanges.associate { Pair(it.person1, it.person2) to it.value }.toMutableMap()
    val people = happinessChanges.flatMap { listOf(it.person1, it.person2) }.distinct().toMutableList()

    val me = "Me"
    people.forEach { person ->
        happinessMap[Pair(me, person)] = 0
        happinessMap[Pair(person, me)] = 0
    }
    people.add(me)

    return findOptimalHappiness(people, happinessMap)
}   

fun part1(input: String): Int {
    val happinessChanges = parseInput(input)
    val happinessMap = happinessChanges.associate { Pair(it.person1, it.person2) to it.value }
    val people = happinessChanges.flatMap { listOf(it.person1, it.person2) }.distinct()
    return findOptimalHappiness(people, happinessMap)
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}