import java.io.File

fun calculateResult(input: String, iterations: Int): Int {
    var result = StringBuilder(input)
    repeat(iterations) {
        val newInput = StringBuilder()
        var i = 0
        while (i < result.length) {
            var count = 1
            while (i + 1 < result.length && result[i] == result[i + 1]) {
                count++
                i++
            }
            newInput.append(count)
            newInput.append(result[i])
            i++
        }
        result = newInput
    }
    return result.length
}

fun part2(input: String): Int {
    return calculateResult(input, 50)
}   

fun part1(input: String): Int {
    return calculateResult(input, 40)
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}