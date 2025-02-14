import java.io.File
import java.util.*

fun removeRedObjects(input: String): String {
    val sb = StringBuilder(input)
    val stack = Stack<Int>()
    
    var i = 0
    while (i < sb.length) {
        if (sb[i] == '{') {
            stack.push(i) 
        } else if (sb[i] == '}') {
            val start = stack.pop()
            val obj = sb.substring(start, i + 1)
            if (Regex(""":\s*"red"+""").containsMatchIn(obj)) {
                sb.replace(start, i + 1, " ".repeat(i + 1 - start))
                i = start
            }
        }
        i++
    }

    return sb.toString()
}

fun sumNumbers(input: String): Int {
    val regex = Regex("-?\\d+")
    return regex.findAll(input).sumOf { it.value.toInt() }
}

fun part2(input: String): Int {
    val modifiedInput = removeRedObjects(input)
    return sumNumbers(modifiedInput)
}

fun part1(input: String): Int {
    return sumNumbers(input)
}

fun main() {
    val input = File("input.txt").readText().trim()

    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")
    println("Part 2: $p2")
}
