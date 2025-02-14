import java.io.File

fun part2(input: String): Int {
    return 0    
}

fun part1(input: String): Int {
    val regex = Regex("-?\\d+")
    return regex.findAll(input).sumOf { it.value.toInt() }
}

fun main() {
    val input = File("input.txt").readText().trim()

    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")
    println("Part 2: $p2")
}
