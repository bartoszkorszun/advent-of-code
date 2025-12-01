import java.io.File

fun parseInput(input: String): List<Pair<String, Int>> {
    return input
        .lines()
        .filter { it.isNotBlank() }
        .map { line ->
            val dir = line.first().toString()
            val num = line.drop(1).toInt()
            dir to num
        }
}

fun part1(instructions: List<Pair<String, Int>>): Int {
    var dialPoint = 50
    var zeroCount = 0

    for ((dir, dist) in instructions) {
        dialPoint = when (dir) {
            "L" -> (dialPoint - dist).mod(100)
            "R" -> (dialPoint + dist).mod(100)
            else -> error("Invalid direction")
        }

        if (dialPoint == 0) zeroCount++
    }

    return zeroCount
}

fun part2(instructions: List<Pair<String, Int>>): Int {
    var dialPoint = 50
    var zeroCount = 0

    for ((dir, dist) in instructions) {

        val step = if (dir == "L") -1 else 1

        zeroCount += dist / 100

        var leftover = dist % 100
        repeat(leftover) {
            dialPoint = (dialPoint + step).mod(100)
            if (dialPoint == 0) zeroCount++
        }
    }

    return zeroCount
}

fun main() {
    val input = File("input.txt").readText().trim()
    val instructions = parseInput(input)
    
    val p1 = part1(instructions)
    val p2 = part2(instructions)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}
