import java.io.File

fun part2(ranges: List<Pair<Long, Long>>): Long {
    return 0L
}   

fun isInvalid(n: Long): Boolean {
    val s = n.toString()
    if (s.length % 2 != 0) return false
    val mid = s.length / 2
    return s.substring(0, mid) == s.substring(mid)
}

fun part1(ranges: List<Pair<Long, Long>>): Long {
    var sum = 0L
    for (range in ranges) {
        for (i in range.first..range.second) {
            if (isInvalid(i)) sum += i
        }
    }
    return sum
}

fun parseRanges(input: String): List<Pair<Long, Long>> {
    return input
        .split(",")
        .map { part ->
            val (start, end) = part.split("-")
            start.toLong() to end.toLong()
        }
}

fun main() {
    val input = File("input.txt").readText().trim()
    val ranges = parseRanges(input)
    
    val p1 = part1(ranges)
    val p2 = part2(ranges)

    println("Part 1: $p1")   
    println("Part 2: $p2")
}