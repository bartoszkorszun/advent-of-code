import java.io.File

fun part2(input: String): Int {
    return 0
}

fun isNice(s: String): Boolean {
    val vowels = "aeiou".toSet()
    val forbidden = setOf("ab", "cd", "pq", "xy")
    val doubles = ('a'..'z').map { it.toString() + it.toString() }.toSet()

    val hasThreeVowels = s.count { it in vowels } >= 3
    val hasDouble = doubles.any { it in s }
    val hasForbidden = forbidden.any { it in s }

    return hasThreeVowels && hasDouble && !hasForbidden
}

fun part1(input: String): Int {
    val lines = input.lines()
    return lines.count { isNice(it) }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}