import java.io.File

fun main() {
    val fileName = "input.txt"
    val pattern = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""")

    var sum = 0
    var shouldDo = true

    File(fileName).forEachLine { line ->
        pattern.findAll(line).forEach { matchResult ->
            if (matchResult.value == "do()") {
                shouldDo = true
            } else if (matchResult.value == "don't()") {
                shouldDo = false
            } else if (shouldDo) {
                val (a, b) = extractIntegers(matchResult.value, pattern)
                sum += mul(a, b)
            }
        }
    }

    println(sum)
}

fun extractIntegers(match: String, pattern: Regex): Pair<Int, Int> {
    val result = pattern.find(match)
    if (result != null) {
        val (a, b) = result.destructured
        return Pair(a.toInt(), b.toInt())
    } else {
        throw IllegalArgumentException("Invalid match: $match")
    }
}

fun mul(a: Int, b: Int): Int {
    return a * b
}