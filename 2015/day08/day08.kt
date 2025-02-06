import java.io.File

fun part2(input: String): Int {
    val lines = input.lines()
    var totalCode = 0
    var totalMemory = 0
    lines.forEach {
        totalCode += it.length
        totalMemory += it
        .replace("\"", "aa")
        .replace("\\", "aa")
        .length + 2
    }
    return totalMemory - totalCode
}

fun part1(input: String): Int {
    val lines = input.lines()
    var totalCode = 0
    var totalMemory = 0
    lines.forEach {
        totalCode += it.length
        totalMemory += it.substring(1, it.length - 1)
        .replace("\\\\", "a")
        .replace("\\\"", "a")
        .replace(Regex("""\\x[a-f0-9]{2}"""), "a")
        .length
    }
    return totalCode - totalMemory
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}