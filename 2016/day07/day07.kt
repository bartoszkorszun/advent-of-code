import java.io.File

fun hasAbba(s: String): Boolean {
    for (i in 0 until s.length - 3) {
        if (s[i] != s[i + 1] && s[i] == s[i + 3] && s[i + 1] == s[i + 2]) {
            return true
        }
    }
    return false
}

fun supportsTls(ip: String): Boolean {
    val parts = ip.split("[", "]")
    
    val outsideBrackets = parts.filterIndexed { index, _ -> index % 2 == 0 }
    val insideBrackets = parts.filterIndexed { index, _ -> index % 2 == 1 }

    val hasAbbaOutside = outsideBrackets.any { hasAbba(it) }
    val hasAbbaInside = insideBrackets.any { hasAbba(it) }

    return hasAbbaOutside && !hasAbbaInside
}

fun part2(input: String): Int {
    return 0
}   

fun part1(input: String): Int {
    return input.lines().count { supportsTls(it) }
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}