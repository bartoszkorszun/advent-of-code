import java.io.File

fun isNice2(s: String): Boolean {
    val hasPair = (0 until s.length - 2).any { i ->
        val pair = s.substring(i, i + 2)
        s.indexOf(pair, i + 2) != -1
    }

    val hasRepeat = (0 until s.length - 2).any { i ->
        s[i] == s[i + 2]
    }

    return hasPair && hasRepeat
}

fun part2(input: String): Int {
    val lines = input.lines()
    return lines.count { isNice2(it) }
}

fun isNice1(s: String): Boolean {
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
    return lines.count { isNice1(it) }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}