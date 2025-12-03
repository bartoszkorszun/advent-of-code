import java.io.File

fun part2(input: List<String>): Int {
    return 0
}   

fun part1(input: List<String>): Int {
    var sum = 0
    var indexFirst = 0
    var first = '0'
    var second = '0'
    for (line in input) {
        // Szukanie pierwszej cyfry
        for (i in 0..line.length-2) {
            if (line[i] > first) {
                first = line[i]
                indexFirst = i
            }
            if (first == '9') break
        }
        // Szukanie drugiej cyfry
        for (i in indexFirst+1..line.length-1) {
            if (line[i] > second) second = line[i]
            if (second == '9') break
        }

        val combined = "${first}" + "${second}"
        sum += combined.toInt()

        first = '0'
        second = '0'
        indexFirst = 0
    }
    return sum
}

fun main() {
    val input = File("input.txt").readText().trim().lines()
    
    val p1 = part1(input)
    val p2 = part2(input)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}