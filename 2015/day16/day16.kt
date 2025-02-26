import java.io.File

data class Aunt(
    val number: Int, 
    val properties: Map<String, Int>
)

data class Criteria(
    val properties: Map<String, Int>
)

fun parseInput(input: String, sCriteria: String): Pair<List<Aunt>, Criteria> {
    val auntRegex = Regex("""Sue (\d+): (.+)""")
    val criteriaRegex = Regex("""(\w+): (\d+)""")

    val aunts = input.lines().map { line ->
        val match = auntRegex.matchEntire(line)!!
        val number = match.groupValues[1].toInt()
        val properties = match.groupValues[2].split(", ").map { property ->
            val (name, value) = property.split(": ")
            name to value.toInt()
        }.toMap()
        Aunt(number, properties)
    }

    val properties = sCriteria.lines().map { line ->
        val match = criteriaRegex.matchEntire(line)!!
        val name = match.groupValues[1]
        val value = match.groupValues[2].toInt()
        name to value
    }.toMap()

    val criteria = Criteria(properties)

    return Pair(aunts, criteria)
}

fun part2(aunts: List<Aunt>, criteria: Criteria): Int {
    aunts.forEach { aunt ->
        val properties = aunt.properties
        val match = properties.all { (name, value) ->
            when (name) {
                "cats", "trees" -> criteria.properties[name]?.let { it < value } ?: true
                "pomeranians", "goldfish" -> criteria.properties[name]?.let { it > value } ?: true
                else -> criteria.properties[name]?.let { it == value } ?: true
            }
        }
        if (match) return aunt.number
    }
    return -1
}   

fun part1(aunts: List<Aunt>, criteria: Criteria): Int {
    aunts.forEach { aunt ->
        val properties = aunt.properties
        val match = properties.all { (name, value) ->
            criteria.properties[name]?.let { it == value } ?: true
        }
        if (match) return aunt.number
    }
    return -1
}

fun main() {
    val (aunts, criteria) = parseInput(
        File("input.txt").readText().trim(),
        File("criteria.txt").readText().trim()
    )
    
    val p1 = part1(aunts, criteria)
    val p2 = part2(aunts, criteria)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}