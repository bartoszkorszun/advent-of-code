import java.io.File

fun part2(input: String): Long {
    var totalLength = 0L
    var i = 0
    while (i < input.length) {
        if (input[i] == '(') {
            val end = input.indexOf(')', i)
            val marker = input.substring(i + 1, end).split('x')
            val length = marker[0].toInt()
            val repeat = marker[1].toLong()
            val subString = input.substring(end + 1, end + 1 + length)
            totalLength += part2(subString) * repeat
            i = end + 1 + length
        } else {
            totalLength++
            i++
        }
    }
    return totalLength
}   

fun part1(input: String): Int {
    var totalLength = 0
    var i = 0
    while (i < input.length) {
        if (input[i] == '(') {
            val end = input.indexOf(')', i)
            val marker = input.substring(i + 1, end).split('x')
            val length = marker[0].toInt()
            val repeat = marker[1].toInt()
            totalLength += length * repeat
            i = end + 1 + length
        } else {
            totalLength++
            i++
        }
    }
    return totalLength
}

fun main() {
    val input = File("input.txt").readText().trim()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}