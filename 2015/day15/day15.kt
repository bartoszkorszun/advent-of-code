import java.io.File

data class Ingredient(
    val name: String,
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int
)

fun parseInput(input: String): List<Ingredient> {
    val regex = Regex("""(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""")
    return input.lines().map {
        val match = regex.matchEntire(it)!!
        Ingredient(
            match.groupValues[1],
            match.groupValues[2].toInt(),
            match.groupValues[3].toInt(),
            match.groupValues[4].toInt(),
            match.groupValues[5].toInt(),
            match.groupValues[6].toInt()
        )
    }
}

fun generateCombinations(n: Int, k: Int): List<List<Int>> {
    if (k == 1) return listOf(listOf(n))
    val combinations = mutableListOf<List<Int>>()
    for (i in 0..n) {
        for (comb in generateCombinations(n - i, k - 1)) {
            combinations.add(listOf(i) + comb)
        }
    }
    return combinations
}

fun calculateScore(ingredients: List<Ingredient>, amounts: List<Int>): Int {
    var capacity = 0
    var durability = 0
    var flavor = 0
    var texture = 0

    for (i in ingredients.indices) {
        capacity += ingredients[i].capacity * amounts[i]
        durability += ingredients[i].durability * amounts[i]
        flavor += ingredients[i].flavor * amounts[i]
        texture += ingredients[i].texture * amounts[i]
    }

    capacity = maxOf(0, capacity)
    durability = maxOf(0, durability)
    flavor = maxOf(0, flavor)
    texture = maxOf(0, texture)

    return capacity * durability * flavor * texture
}

fun part2(ingredients: List<Ingredient>): Int {
    return 0
}   

fun part1(ingredients: List<Ingredient>): Int {
    val combinations = generateCombinations(100, ingredients.size)
    return combinations.maxOf { calculateScore(ingredients, it) }
}

fun main() {
    val input = File("test.txt").readText().trim()
    val ingredients = parseInput(input)

    val p1 = part1(ingredients)
    val p2 = part2(ingredients)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}