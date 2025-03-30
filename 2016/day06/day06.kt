import java.io.File

fun parseInput(input: String): Array<CharArray> {
    return input.lines().map { it.toCharArray() }.toTypedArray()
}

fun part2(grid: Array<CharArray>): String {
    return ""
}   

fun part1(grid: Array<CharArray>): String {
    val message = StringBuilder()
    for (i in grid[0].indices) {
        val charCount = mutableMapOf<Char, Int>()
        for (j in grid.indices) {
            val char = grid[j][i]
            charCount[char] = charCount.getOrDefault(char, 0) + 1
        }
        message.append(charCount.maxByOrNull { it.value }?.key ?: ' ')
    }
    return message.toString()
}

fun main() {
    val input = File("input.txt").readText().trim()
    val grid = parseInput(input)

    val p1 = part1(grid)
    val p2 = part2(grid)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}